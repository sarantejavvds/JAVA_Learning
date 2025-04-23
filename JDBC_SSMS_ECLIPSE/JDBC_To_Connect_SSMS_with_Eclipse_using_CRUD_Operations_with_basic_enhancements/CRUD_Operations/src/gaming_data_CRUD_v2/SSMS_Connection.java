package gaming_data_CRUD_v2;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.ArrayList;
/* import java.util.Arrays; */
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * Runtime input for PrimaryKey Column will throw Exceptions, if the Data was already existed in the Table.
 * 
 * Password to connect with SSMS was Saved in an Environment Variable in System variables and also in Run Configurations in Eclipse, to avoid security issues.
 * 
 * This version is now using where clause and give us options for Select(Retrieve) operation, so Every Record will be Retrieved or any specific record will be Retrieved.
 * 
 * In this version, Update operation will give us options to update all rows or specific rows based on a given condition.
 * 
 * In this version, the Delete operation will give us options to either remove Entire Records or specific Record in the table.
 * 
 * In this version, Insert operation will check the conditions in CHECK constraint column and proceed with Insert operation.
 **/

public class SSMS_Connection
{
	private static Connection connect;

	private static final String URL = "jdbc:sqlserver://DESKTOP-2ROPMHT;databaseName=JDBC_Connects_Eclipse_and_SSMS;integratedSecurity=true;encrypt=false";

	private static final String USER = "DESKTOP-2ROPMHT";

	/* private static String PASSWORD = ""; */

	final String RESET = "\u001B[0m";
	final String RED_BG = "\u001B[41m"; /* Red background for headers */
	final String YELLOW_BG = "\u001B[43m"; /* Yellow background for data */
	final String GREEN_BG = "\u001B[42m"; /* Green background for headers */
	final String BLUE_BG = "\u001B[44m"; /* Blue background for headers */
	final String MAGENTA_BG = "\u001B[45m"; /* Magenta background for headers */
	final String BLACK_BG = "\u001B[40m"; /* Black background for headers */
	final String WHITE_BG = "\u001B[47m"; /* White background for headers */

	public SSMS_Connection()
	{
		/* TODO Auto-generated constructor stub */
		System.out.println("SSMS_Connection class's Constructor is Executed \n");
	}

	/* Set the password for the database connection (!! Not using this Method now, because of low security !!) public void setPassword(String password) { PASSWORD = password; } */

	/* Method to establish and return a connection to the SQL Server database */
	public static Connection get_Connection() throws SQLException
	{
		if((connect == null) || (connect.isClosed())) /* Check if the connection is closed or no connection. */
		{
			try
			{

				String PASSWORD = System.getenv("JDBC_ECLIPSE_SSMS_PASSWORD");

				if((PASSWORD == null) || (PASSWORD.isEmpty())) /* Checking if Password is Empty. */
				{
					throw new RuntimeException("Environment Variable 'JDBC_ECLIPSE_SSMS_PASSWORD' is Not Set or is Empty.");
				}

				connect = DriverManager.getConnection(URL, USER, PASSWORD); /* Passing URL, USER, Password to make Connction with SSMS. */

				try (PreparedStatement stmt = connect.prepareStatement("SELECT 1"))
				{
					stmt.executeQuery();
				}

				System.out.println("\u001B[46m Database Connection has been Established Successfully 😁 \u001B[0m\n");
			}
			catch (SQLException e)
			{
				e.printStackTrace();

				System.err.println("Failed to Connect to Database 😥");

				throw e;
			}
		}

		return connect; /* Returns connection */
	}

	/* Method to close the connection */
	public void closeConnection() throws SQLException /* When this method gets called, it closes the connection. */
	{
		if(connect != null && !connect.isClosed()) /* Checks if the connection is still established. */
		{
			connect.close(); /* Closes the existing Connection. */

			System.out.println("Database Connection Closed Successfully.");
		}
	}

	/* Method to Validate Table and Column Names */
	private boolean is_Valid_Identifier(String name)
	{
		return name.matches("[a-zA-Z0-9_]+"); /* Allows only letters, numbers, and underscores */
	}

	/* Retrieve column metadata */
	public List<Map<String, String>> get_Table_Metadata_to_obtain_Column_info(String Table_Name) throws SQLException
	{
		if(connect == null || connect.isClosed())
		{
			get_Connection(); /* Ensure connection is active */
		}

		List<Map<String, String>> columns = new ArrayList<>();

		DatabaseMetaData metaData = connect.getMetaData();

		ResultSet rs = metaData.getColumns(null, null, Table_Name, null);

		while (rs.next())
		{
			Map<String, String> column_Info = new HashMap<>();

			column_Info.put("COLUMN_NAME", rs.getString("COLUMN_NAME"));

			column_Info.put("TYPE_NAME", rs.getString("TYPE_NAME"));

			column_Info.put("IS_NULLABLE", rs.getString("IS_NULLABLE"));

			column_Info.put("COLUMN_SIZE", rs.getString("COLUMN_SIZE"));

			column_Info.put("IS_AUTOINCREMENT", rs.getString("IS_AUTOINCREMENT"));

			columns.add(column_Info);
		}

		return columns; /* Returns list of columns */
	}

	/* Validate input based on column type */
	public Object validate_Input_based_on_Column_datatype(String user_input, String column_type, boolean isNullable, String column_size)
	{
		if(user_input.isEmpty() && !isNullable)
		{
			System.err.println("Error: This field cannot be null.");

			return null;
		}

		if((user_input == null) || (user_input.isEmpty()))
		{
			return (isNullable ? null : "");
		}

		try
		{
			switch(column_type.toUpperCase())
			{
				case "BIT":
					try
					{
						return (user_input.equalsIgnoreCase("true") || user_input.equals("1"));
					}
					catch (IllegalArgumentException e)
					{
						throw new IllegalArgumentException("Error: Invalid Bit value (Expected: 1/0): " + user_input);
					}

				case "BOOLEAN":
					try
					{
						if("true".equalsIgnoreCase(user_input) || "false".equalsIgnoreCase(user_input))
						{
							return Boolean.parseBoolean(user_input);
						}
					}
					catch (IllegalArgumentException e)
					{
						throw new IllegalArgumentException("Error: Invalid Boolean value (Expected: true/false): " + user_input);
					}

				case "BIGINT":
					try
					{
						return Long.parseLong(user_input);
					}
					catch (NumberFormatException e)
					{
						throw new IllegalArgumentException("Error: Invalid BIGINT value: " + user_input);
					}

				case "SMALLINT":
				case "INT":
				case "INTEGER":
					try
					{
						return Integer.parseInt(user_input);
					}
					catch (NumberFormatException e)
					{
						throw new IllegalArgumentException("Error: Invalid integer value: " + user_input);
					}

				case "TEXT":
				case "VARCHAR":
				case "NVARCHAR":
					if(user_input.length() > Integer.parseInt(column_size))
					{
						throw new IllegalArgumentException("Error: Input exceeds allowed length of " + column_size + " characters.");
					}
					return user_input;

				case "CHAR":
					int char_Size = Integer.parseInt(column_size);
					if(user_input.length() > Integer.parseInt(column_size))
					{
						throw new IllegalArgumentException("Error: Input must be exactly " + column_size + " characters.");
					}
					else if(user_input.length() < char_Size)
					{
						user_input = String.format("%-" + char_Size + "s", user_input); /* Pad with spaces if too short */
					}
					return user_input;

				case "DECIMAL":
				case "NUMERIC":
				case "FLOAT":
				case "DOUBLE":
				case "MONEY":
					try
					{
						return Double.parseDouble(user_input);
					}
					catch (NumberFormatException e)
					{
						throw new IllegalArgumentException("Error: Invalid float/decimal value: " + user_input);
					}

				case "DATE":
					try
					{
						return java.sql.Date.valueOf(user_input); /* Format: YYYY-MM-DD */
					}
					catch (IllegalArgumentException e)
					{
						throw new IllegalArgumentException("Error: Invalid Date Format (Expected YYYY-MM-DD): " + user_input);
					}

				case "TIMESTAMP":
				case "DATETIME":
					try
					{
						return java.sql.Timestamp.valueOf(user_input); /* Format: YYYY-MM-DD HH:MM:SS */
					}
					catch (IllegalArgumentException e)
					{
						throw new IllegalArgumentException("Error: Invalid Date Format (Expected YYYY-MM-DD HH:MM:SS): " + user_input);
					}

				default:
					throw new UnsupportedOperationException("Error: Unsupported column datatype: " + column_type + "for input: " + user_input);

			}
		}
		catch (Exception e)
		{
			throw new IllegalArgumentException("Error: Invalid input for type " + column_type + ".");
		}
	}

	/* Check primary key availability */
	public boolean is_PrimaryKey_Available(String TableName, String primaryKeyColumn, Object value) throws SQLException
	{
		/* Validate Table and Column Names to Prevent SQL Injection */
		if(!is_Valid_Identifier(TableName) || !is_Valid_Identifier(primaryKeyColumn))
		{
			throw new IllegalArgumentException("Invalid table or column name.");
		}

		/* Ensure Connection is Active */
		if(connect == null || connect.isClosed())
		{
			get_Connection();
		}

		/* Verify the column actually exists */
		DatabaseMetaData metaData = connect.getMetaData();
		ResultSet columns = metaData.getColumns(null, null, TableName, primaryKeyColumn);
		if(!columns.next())
		{
			throw new SQLException("Column '" + primaryKeyColumn + "' does not exist in table '" + TableName + "'.");
		}

		String check_PK_query = "SELECT COUNT(*) FROM " + TableName + " WHERE " + primaryKeyColumn + " = ? ";

		try (PreparedStatement prep = connect.prepareStatement(check_PK_query); ResultSet rs = prep.executeQuery();)
		{
			prep.setObject(1, value);

			if(rs.next())
			{
				return (rs.getInt(1) > 0);
			}
		}

		return false;
	}

	/* Check primary key availability with primaryKey value */
	public boolean is_PrimaryKey_Available(String TableName, String primaryKey_Column, String primaryKey_value) throws SQLException
	{
		/* Validate Table and Column Names to Prevent SQL Injection */
		if(!is_Valid_Identifier(TableName) || !is_Valid_Identifier(primaryKey_Column))
		{
			throw new IllegalArgumentException("Invalid table or column name.");
		}

		/* Ensure Connection is Active */
		if(connect == null || connect.isClosed())
		{
			get_Connection();
		}

		/* Verify the column actually exists */
		DatabaseMetaData metaData = connect.getMetaData();
		ResultSet columns = metaData.getColumns(null, null, TableName, primaryKey_Column);
		if(!columns.next())
		{
			throw new SQLException("Column '" + primaryKey_Column + "' does not exist in table '" + TableName + "'.");
		}

		String check_PK_query = "SELECT COUNT(*) FROM " + TableName + " WHERE " + primaryKey_Column + " = ? ";

		try (PreparedStatement prep = connect.prepareStatement(check_PK_query);)
		{
			prep.setObject(1, primaryKey_value);
			try (ResultSet rs = prep.executeQuery();)
			{
				if(rs.next())
				{
					return (rs.getInt(1) > 0);
				}
			}
			System.out.println("Executing: " + check_PK_query + " with value: " + primaryKey_value);
		}
		catch (SQLException e)
		{
			System.err.println("Error: checking PrimaryKey availability: " + e.getMessage());

			throw e;
		}

		return false;
	}

	/* Checks if a PrimaryKey is referenced as a ForeignKey in other Tables */
	public boolean is_PrimaryKey_Referenced_in_Other_Tables(String primary_key_Column) throws SQLException
	{
		String check_query = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE COLUMN_NAME = ? AND CONSTRAINT_NAME LIKE 'FK_%' ";

		try (PreparedStatement prep = connect.prepareStatement(check_query);)
		{
			prep.setString(1, primary_key_Column);

			ResultSet result_set = prep.executeQuery();

			return result_set.next(); /* Returns true if this PrimaryKey is referenced as ForeignKey in other Tables */
		}
	}

	/* Check if a column is a PrimaryKey in a Table */
	public boolean is_Column_a_PrimaryKey(String column_Name, String Table_Name) throws SQLException
	{
		String check_query = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE TABLE_NAME = ? AND COLUMN_NAME = ? AND CONSTRAINT_NAME LIKE 'PK_%' ";

		try (PreparedStatement prep = connect.prepareStatement(check_query);)
		{
			prep.setString(1, Table_Name);

			prep.setString(2, column_Name);

			ResultSet result_set = prep.executeQuery();

			return result_set.next(); /* Returns true if this column is a PrimaryKey */
		}
	}

	/* Method to check if a Primary Key Value Exists in the Referenced Table or not */
	public boolean is_Referenced_Value_Primary_Key_Exists(String primaryKey_Column, Object primaryKey_Value, String Table_Name) throws SQLException
	{
		/* Query to find tables where this Primary Key is referenced as a Foreign Key */
		String fetch_PK_Details_Query = "SELECT KU.TABLE_NAME, KU.COLUMN_NAME " + "FROM INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS RC " + "JOIN INFORMATION_SCHEMA.KEY_COLUMN_USAGE KU " + "ON RC.CONSTRAINT_NAME = KU.CONSTRAINT_NAME " + "WHERE RC.UNIQUE_CONSTRAINT_TABLE_NAME = ? " + "AND KU.COLUMN_NAME = ?";

		try (PreparedStatement prep = connect.prepareStatement(fetch_PK_Details_Query))
		{
			prep.setString(1, Table_Name);
			prep.setString(2, primaryKey_Column);

			ResultSet rs = prep.executeQuery();

			while (rs.next())
			{
				String referencingTable = rs.getString("TABLE_NAME");
				String referencingColumn = rs.getString("COLUMN_NAME");

				/* Check if the PK value exists in the referencing table */
				String check_Existence_Query = "SELECT COUNT(*) FROM " + referencingTable + " WHERE " + referencingColumn + " = ?";
				try (PreparedStatement checkStmt = connect.prepareStatement(check_Existence_Query))
				{
					checkStmt.setObject(1, primaryKey_Value);
					ResultSet countRs = checkStmt.executeQuery();
					if(countRs.next() && countRs.getInt(1) > 0)
					{
						return true; /* Primary Key is referenced in at least one table */
					}
				}
			}
		}
		return false; /* Primary Key value is not referenced anywhere */
	}

	/* Method to check if column has a Primary Key or not */
	public boolean is_Column_has_Primary_Key(String column_To_Check, String Table_Name) throws SQLException
	{
		if(connect == null || connect.isClosed())
		{
			get_Connection(); /* Ensure connection is active */
		}

		DatabaseMetaData metaData = connect.getMetaData();

		/* Check if the column is part of a primary key */
		ResultSet pk_ResultSet = metaData.getPrimaryKeys(null, null, Table_Name);

		while (pk_ResultSet.next())
		{
			String pkColumnName = pk_ResultSet.getString("COLUMN_NAME");
			if(column_To_Check.equalsIgnoreCase(pkColumnName))
			{
				return true;
			}
		}

		/* Check if the column has a unique constraint (not just primary key) */
		ResultSet index_ResultSet = metaData.getIndexInfo(null, null, Table_Name, false, false);
		while (index_ResultSet.next())
		{
			/* String index_Name = index_ResultSet.getString("INDEX_NAME"); */
			if(!index_ResultSet.getBoolean("NON_UNIQUE"))
			{
				String indexed_Column = index_ResultSet.getString("COLUMN_NAME");
				if(column_To_Check.equalsIgnoreCase(indexed_Column))
				{
					return true;
				}
			}
		}
		return false;
	}

	/* Method to check if PrimaryKey column avaiable in Other tables */
	public Map<String, String> find_PrimaryKey_In_Other_Tables(String column_Name) throws SQLException 
	{
	    Map<String, String> result = new HashMap<>();
	    String query = "SELECT TABLE_NAME, COLUMN_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE " +
	                   "WHERE COLUMN_NAME = ? AND CONSTRAINT_NAME IN " +
	                   "(SELECT CONSTRAINT_NAME FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS WHERE CONSTRAINT_TYPE = 'PRIMARY KEY')";

	    try (PreparedStatement prep = connect.prepareStatement(query)) 
	    {
	        prep.setString(1, column_Name);
	        ResultSet rs = prep.executeQuery();
	        if (rs.next()) 
	        {
	            result.put("table", rs.getString("TABLE_NAME"));
	            result.put("column", rs.getString("COLUMN_NAME"));
	        }
	    }
	    
	    return result;
	}
	
	/* Method to Check if a column is a ForeignKey in a Table */
	public boolean is_Column_a_ForeignKey(String column_Name, String Table_Name) throws SQLException
	{
		String check_query = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE TABLE_NAME = ? AND COLUMN_NAME = ? AND CONSTRAINT_NAME LIKE 'FK_%' ";

		try (PreparedStatement prep = connect.prepareStatement(check_query);)
		{
			prep.setString(1, Table_Name);

			prep.setString(2, column_Name);

			ResultSet result_set = prep.executeQuery();

			return result_set.next(); /* Returns true if this column is a ForeignKey */
		}
	}

	/* Method to check if a value is referenced as ForeignKey in another Table or not. */
	public boolean is_Value_Referenced_as_ForeignKey(String columnName, String columnValue, String tableName) throws SQLException
	{
		/* String query = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE " + "WHERE REFERENCED_TABLE_NAME = ? AND REFERENCED_COLUMN_NAME = ?"; */

		String query = "SELECT fk.name AS ForeignKeyName, " + " OBJECT_NAME(fk.parent_object_id) AS TableName, " + " COL_NAME(fc.parent_object_id, fc.parent_column_id) AS ColumnName, " + " OBJECT_NAME(fk.referenced_object_id) AS ReferencedTable " + "FROM sys.foreign_keys AS fk " + "JOIN sys.foreign_key_columns AS fc ON fk.object_id = fc.constraint_object_id " + "WHERE OBJECT_NAME(fk.parent_object_id) = ? AND COL_NAME(fc.parent_object_id, fc.parent_column_id) = ? ";

		try (PreparedStatement prep = connect.prepareStatement(query))
		{
			prep.setString(1, tableName);
			prep.setString(2, columnName);

			ResultSet rs = prep.executeQuery();

			if(rs.next() && rs.getInt(1) > 0)
			{
				System.out.println("⚠ Record in '" + tableName + "' is referenced as a Foreign Key in another table.");

				return true;
			}
		}

		return false;
	}

	/* Method to check if a Foreign Key Value Exists in the Referenced Table or not */
	public boolean is_Referenced_Value_Foreign_Key_Exists(String column_Name, Object value, String Table_Name) throws SQLException
	{
		/* Get the referenced table and column dynamically */
		String fetch_FK_Details_Query = "SELECT UNIQUE_CONSTRAINT_TABLE_NAME, UNIQUE_CONSTRAINT_COLUMN_NAME " + "FROM INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS RC " + "JOIN INFORMATION_SCHEMA.KEY_COLUMN_USAGE KU " + "ON RC.CONSTRAINT_NAME = KU.CONSTRAINT_NAME " + "WHERE KU.TABLE_NAME = ? AND KU.COLUMN_NAME = ?";

		try (PreparedStatement prep = connect.prepareStatement(fetch_FK_Details_Query))
		{
			prep.setString(1, Table_Name);
			prep.setString(2, column_Name);

			ResultSet rs = prep.executeQuery();
			if(!rs.next())
			{
				return false; /* No FK reference found */
			}

			String referenced_Table = rs.getString("UNIQUE_CONSTRAINT_TABLE_NAME");
			String referenced_Column = rs.getString("UNIQUE_CONSTRAINT_COLUMN_NAME");

			/* Check if value exists in the referenced table */
			String check_value_query = "SELECT COUNT(*) FROM " + referenced_Table + " WHERE " + referenced_Column + " = ?";
			try (PreparedStatement checkStmt = connect.prepareStatement(check_value_query))
			{
				checkStmt.setObject(1, value);
				ResultSet countRs = checkStmt.executeQuery();

				return (countRs.next() && countRs.getInt(1) > 0); /* True if value exists */
			}
		}
	}

	/* Method to check if a Foreign Key Value Exists in the Referenced Table or not using MetaData */
	public boolean is_Referenced_Value_Foreign_Key_Exists(String fk_column_Name, String fk_value, String Table_Name) throws SQLException
	{
		String referenced_Table = null, referenced_Column = null;

		DatabaseMetaData metaData = connect.getMetaData();

		try (ResultSet rs = metaData.getImportedKeys(null, null, Table_Name);)
		{
			while (rs.next())
			{
				if(fk_column_Name.equalsIgnoreCase(rs.getString("FKCOLUMN_NAME")))
				{
					referenced_Table = rs.getString("PKTABLE_NAME");
					referenced_Column = rs.getString("PKCOLUMN_NAME");

					break;
				}
			}
		}

		referenced_Table = get_Referenced_Table_Name(fk_column_Name, Table_Name);

		if((referenced_Table == null) || (referenced_Column == null))
		{
			System.err.println("⚠ Warning: No ForeignKey constraint found for column " + fk_column_Name);

			return false;
		}

		/* Get the referenced table and column dynamically */
		String fetch_FK_count_Query = "SELECT COUNT(*) FROM " + referenced_Table + " WHERE " + referenced_Column + " = ? ";

		try (PreparedStatement prep = connect.prepareStatement(fetch_FK_count_Query))
		{
			prep.setString(1, fk_value);
			try (ResultSet rs = prep.executeQuery();)
			{
				if(rs.next())
				{
					return rs.getInt(1) > 0; /* returns true, FK reference found */
				}
			}
		}
		catch (SQLException e)
		{
			System.err.println("Error: checking foreign key reference: " + e.getMessage());

			throw e;
		}

		return false;
	}

	/* Method to check if referenced column is existing in referenced table or not */
	public boolean is_Referenced_Column_exists_in_Referenced_Table(String referenced_Column, String referenced_Table) throws SQLException
	{
		String check_referenced_column_query = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ? AND COLUMN_NAME = ?";

		try (PreparedStatement prep = connect.prepareStatement(check_referenced_column_query);)
		{
			prep.setString(1, referenced_Table);

			prep.setString(2, referenced_Column);

			try (ResultSet result_of_query = prep.executeQuery();)
			{
				return result_of_query.next(); /* Returns true if column Exists */
			}
		}
	}

	/* Method to Check if a column in a table has the same data type */
	public boolean is_Column_Matching_DataType(String columnName, String tableName, String dataType) throws SQLException
	{
		String datatype_query = "SELECT DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ? AND COLUMN_NAME = ?";

		try (PreparedStatement stmt = connect.prepareStatement(datatype_query))
		{
			stmt.setString(1, tableName);
			stmt.setString(2, columnName);

			ResultSet rs = stmt.executeQuery();

			if(rs.next())
			{
				return (rs.getString("DATA_TYPE").equalsIgnoreCase(dataType));
			}
		}

		return false;
	}

	/* Method to Check if a new Primary Key matches an existing Foreign Key in other tables */
	public boolean is_PrimaryKey_Matching_Existing_ForeignKey(String primaryKey_Column, String dataType) throws SQLException
	{
		String check_pk_fk_query = "SELECT TABLE_NAME, COLUMN_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE " + "WHERE COLUMN_NAME = ? AND CONSTRAINT_NAME LIKE 'FK_%'";

		try (PreparedStatement prep = connect.prepareStatement(check_pk_fk_query))
		{
			prep.setString(1, primaryKey_Column);
			ResultSet rs = prep.executeQuery();

			while (rs.next())
			{
				String existingTable = rs.getString("TABLE_NAME");
				String existingColumn = rs.getString("COLUMN_NAME");

				/* Check if the column exists in the referenced table with the same data type */
				if(is_Column_Matching_DataType(existingColumn, existingTable, dataType))
				{
					System.out.println("✔ Foreign Key in table '" + existingTable + "' matches the new Primary Key.");

					return true;
				}
			}
		}

		return false;
	}

	/* Method to Check if a new Foreign Key matches an existing Primary Key in other tables */
	public boolean is_ForeignKey_Matching_Existing_PrimaryKey(String referenced_Column, String referenced_Table, String foreignKey_Column, String Table_Name) throws SQLException
	{
		String check_fk_pk_query = "SELECT TABLE_NAME, COLUMN_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE " + "WHERE TABLE_NAME = ? AND COLUMN_NAME = ? AND CONSTRAINT_NAME LIKE 'PK_%'";

		try (PreparedStatement prep = connect.prepareStatement(check_fk_pk_query))
		{
			prep.setString(1, referenced_Table);

			prep.setString(2, referenced_Column);

			ResultSet rs = prep.executeQuery();

			if(rs.next())
			{
				System.out.println("✔ Foreign Key '" + foreignKey_Column + "' in Table '" + Table_Name + "' successfully references Primary Key '" + referenced_Column + "' in table '" + referenced_Table + "'.");

				return true;
			}
		}
		return false;
	}

	/* Method to fetch Referenced PrimaryKey Table Name */
	public String get_Referenced_Table_Name(String columnName, String tableName) throws SQLException
	{
		String query = "SELECT pkTable.name AS ReferencedTable " + "FROM sys.foreign_keys fk " + "JOIN sys.foreign_key_columns fkCols ON fk.object_id = fkCols.constraint_object_id " + "JOIN sys.tables pkTable ON fkCols.referenced_object_id = pkTable.object_id " + "JOIN sys.columns pkCol ON fkCols.referenced_object_id = pkCol.object_id " + "AND fkCols.referenced_column_id = pkCol.column_id " + "WHERE fk.parent_object_id = OBJECT_ID(?) AND pkCol.name = ?";

		try (PreparedStatement stmt = connect.prepareStatement(query))
		{
			stmt.setString(1, tableName);
			stmt.setString(2, columnName);
			ResultSet rs = stmt.executeQuery();

			if(rs.next())
			{
				return rs.getString("ReferencedTable");
			}
		}

		return null;
	}

	/* Method to get Valid ForeignKey Values */
	public List<String> get_Valid_ForeignKey_Values(String columnName, String tableName) throws SQLException
	{
		List<String> validValues = new ArrayList<>();
		String referencedTable = get_Referenced_Table_Name(columnName, tableName);

		if(referencedTable == null)
		{
			return validValues;
		}

		String query = "SELECT DISTINCT " + columnName + " FROM " + referencedTable;
		try (PreparedStatement stmt = connect.prepareStatement(query); ResultSet rs = stmt.executeQuery())
		{
			while (rs.next())
			{
				validValues.add(rs.getString(columnName));
			}
		}

		return validValues;
	}

	/* Method to get Length of the Column */
	public int get_Column_Length(String column_Name, String Table_Name) throws SQLException
	{
		if(connect == null || connect.isClosed())
		{
			get_Connection(); /* Ensure connection is active */
		}

		String check_length_query = "SELECT CHARACTER_MAXIMUM_LENGTH FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ? AND COLUMN_NAME = ?";

		try (PreparedStatement prep = connect.prepareStatement(check_length_query))
		{
			prep.setString(1, Table_Name);
			prep.setString(2, column_Name);

			try (ResultSet rs = prep.executeQuery())
			{
				if(rs.next())
				{
					int length = rs.getInt("CHARACTER_MAXIMUM_LENGTH");

					return ((rs.wasNull()) ? Integer.MAX_VALUE : length);
				}
			}
		}

		return Integer.MAX_VALUE; /* Default to no limit */
	}

	/* Method to check if a column exists in the table or not */
	public boolean is_Column_Exists(String column_Name, String Table_Name) throws SQLException
	{
		/* Query to check if the column exists in the Selected Table */
		String check_column_query = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + Table_Name + "' AND COLUMN_NAME = ?";

		boolean exists = false;

		try (	Connection connect = SSMS_Connection.get_Connection(); /* Establishing Connection. */
				PreparedStatement prep = connect.prepareStatement(check_column_query); /* contains the SQL query. */
		)
		{
			/* Bind the column name */
			prep.setString(1, column_Name); /* Replaces the questionMark(?) in the above query with column_Name value */

			ResultSet result_count = prep.executeQuery();

			if(result_count.next())
			{
				exists = result_count.getInt(1) > 0; /* Check if the count is greater than 0 */
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();

			throw e; /* Re-throw exception to indicate failure */
		}

		return exists; /* returns true if column_Name actually exists in the table, otherwise returns false */
	}

	/* Method to check value of column exists in table or not. */
	public boolean is_Value_of_Column_Exists(String column_in_Table, String value_of_column, String Table_Name) throws SQLException
	{
		/* Validate if the column exists before building the query */
		if(!is_Column_Exists(column_in_Table, Table_Name))
		{
			throw new SQLException("Column " + column_in_Table + " does not exist in the table.");
		}
		String check_value_query = "SELECT COUNT(*) FROM " + Table_Name + " WHERE " + column_in_Table + " = ?";

		boolean exists = false;

		try (	Connection connect = SSMS_Connection.get_Connection(); /* Establishing Connection. */
				PreparedStatement prep = connect.prepareStatement(check_value_query);)
		{
			prep.setString(1, value_of_column); /* Bind the value provided by User here. */

			ResultSet result_count = prep.executeQuery();

			if(result_count.next())
			{
				exists = result_count.getInt(1) > 0; /* checks if the count is greater than 0. */
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();

			throw e;
		}

		return exists;
	}

	/* Method to check if the Table is Existing in Database or not. */
	public boolean is_Table_exists(String Table_Name) throws SQLException
	{
		DatabaseMetaData meta = get_Connection().getMetaData();

		try (ResultSet result_count_of_tables = meta.getTables(null, null, Table_Name, new String[]{"TABLE"}))
		{
			boolean exists = result_count_of_tables.next();

			return exists;
		}
	}

	/* Method to display the values present in a specific column. */
	public void Display_Values_of_Each_Column_of_the_Table(String column_name, String Table_Name)
	{
		String display_value_query = "SELECT DISTINCT " + column_name + " FROM " + Table_Name;

		try (	Connection connect = get_Connection(); /* Establishing Connection. */
				PreparedStatement prep = connect.prepareStatement(display_value_query); ResultSet result_Value = prep.executeQuery();)
		{

			System.out.println("Values in column '" + column_name + "' are :");
			while (result_Value.next())
			{
				System.out.println("➡ " + result_Value.getString(1));
			}

			System.out.println();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/* Method to Get Column Type from Metadata */
	private String get_Column_Type(List<Map<String, String>> columns, String columnName)
	{
		return (columns.stream().filter(col -> col.get("COLUMN_NAME").equals(columnName)).findFirst().orElseThrow(() -> new RuntimeException("Column type not found!")).get("TYPE_NAME"));
	}

	
	public Map<String, String> _get_ColumnConstraints(String Table_Name) throws SQLException
	{
		Map<String, String> constraintsMap = new HashMap<>();
		String query = "SELECT col.name AS column_name, chk.definition AS check_clause " + "FROM sys.check_constraints chk " + "JOIN sys.columns col ON chk.parent_object_id = col.object_id " + "JOIN sys.sysconstraints sc ON chk.object_id = sc.constid AND col.column_id = sc.colid " + "WHERE chk.parent_object_id = OBJECT_ID(?) ";

		try (PreparedStatement prep = connect.prepareStatement(query))
		{
			prep.setString(1, Table_Name);
			ResultSet rs = prep.executeQuery();

			while (rs.next())
			{
				String columnName = rs.getString("column_name");
				String checkClause = rs.getString("check_clause");
				constraintsMap.put(columnName, checkClause);
			}

			System.out.println();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return constraintsMap;
	}

	/* Method to Fetch Check Constraint for Any Columns in the Table */
	public Map<String, int[]> get_Column_Constraints(String Table_Name) throws SQLException
	{
		Map<String, int[]> constraints_map = new HashMap<>();

		String check_constraints_query = "SELECT ccu.COLUMN_NAME, cc.CHECK_CLAUSE " + "FROM INFORMATION_SCHEMA.CHECK_CONSTRAINTS AS cc " + "JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE AS ccu " + "ON cc.CONSTRAINT_NAME = ccu.CONSTRAINT_NAME " + "WHERE ccu.TABLE_NAME = ?";

		try (PreparedStatement prep = connect.prepareStatement(check_constraints_query))
		{
			prep.setString(1, Table_Name);
			ResultSet rs = prep.executeQuery();

			while (rs.next())
			{
				String column_Name = rs.getString("COLUMN_NAME");
				String check_Clause = rs.getString("CHECK_CLAUSE");

				if(check_Clause == null || check_Clause.isEmpty())
				{
					System.err.println("⚠ Warning: No CHECK constraint found for column: " + column_Name);
					continue;
				}

				/* Remove column names and operators, keeping only numbers */
				check_Clause = check_Clause.replaceAll("[^0-9\\s]", ""); /* Keep only numbers and spaces */
				check_Clause = check_Clause.trim();

				String[] numbers = check_Clause.split("\\s+"); /* Split by spaces */
				if(numbers.length == 2)
				{
					try
					{
						int minValue = Integer.parseInt(numbers[0]);
						int maxValue = Integer.parseInt(numbers[1]);

						constraints_map.put(column_Name, new int[]{minValue, maxValue});
						System.out.println("✅ Check Constraint Found: " + column_Name + " (Min: " + minValue + ", Max: " + maxValue + ")");

						System.out.println();
					}
					catch (NumberFormatException e)
					{
						System.err.println("⚠ Warning: Could not parse extracted values for column: " + column_Name);
					}
				}
				else
				{
					System.err.println("⚠ Warning: Could not extract valid min/max values for column: " + column_Name);
				}
			}
		}
		catch (SQLException e)
		{
			System.err.println("❌ Error: When trying to fetch column constraints: " + e.getMessage());
		}

		if(constraints_map.isEmpty())
		{

			throw new RuntimeException("Error: No constraints retrieved for table " + Table_Name);
		}

		return constraints_map;
	}

	/* Method to display all columns in the table */
	public void Display_All_Columns_of_the_Table(String Table_Name)
	{
		String display_column_query = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + Table_Name + "' ";

		try (	Connection connect = get_Connection(); /* Establishing Connection. */
				PreparedStatement prep = connect.prepareStatement(display_column_query); ResultSet result_Column = prep.executeQuery())
		{

			System.out.println("Columns present in the table are: ");
			while (result_Column.next())
			{
				System.out.println("➡ " + result_Column.getString("COLUMN_NAME"));
			}

			System.out.println();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/* Method to Display all Tables in the Current Database */
	public void Display_All_Tables_in_Database() throws SQLException
	{
		DatabaseMetaData meta = connect.getMetaData();

		ResultSet tables_data = meta.getTables(null, null, "%", new String[]{"TABLE"});

		System.out.println("Avaliable Tables in the current Database: ");
		while (tables_data.next())
		{
			String Table_Name = tables_data.getString("TABLE_NAME");

			if(!Table_Name.startsWith("trace_xe_")) /* Excludes all trace_xe tables. */
			{
				System.out.println("➡ " + Table_Name);
			}
		}

		System.out.println();
	}

	/* Method to create a New Table in the Database */
	public void CREATE_NEW_Table(String new_table_Name, List<String> columns, String primaryKey, String foreignKey, String referenced_Table, String referenced_Column)
	{
		StringBuilder create_query;

		/* Start of CREATE query */
		create_query = new StringBuilder("CREATE TABLE " + new_table_Name + " (");

		/* Add column definitions */
		for (int i = 0; i < columns.size(); i++)
		{
			create_query.append(columns.get(i)); /* Appending Each column name one by one */

			if(i < (columns.size() - 1))
			{
				create_query.append(", "); /* Separating Each column with comma separator. */
			}

		}

		/* Add primary key */
		if(primaryKey != null)
		{
			create_query.append(", PRIMARY KEY (" + primaryKey + "), ");
		}

		/* Add foreign key */
		if((foreignKey != null) && (referenced_Table != null) && (referenced_Column != null))
		{
			create_query.append(", FOREIGN KEY (" + foreignKey + ") REFERENCES " + referenced_Table + "(" + referenced_Column + "), ");
		}

		create_query.setLength(create_query.length() - 2); /* Remove the Trailing comma and space at end. */

		create_query.append(");"); /* Closing point of query */
		/* End of CREATE query */

		/* Execute the CREATE query */
		try (Statement stmt = connect.createStatement())
		{
			stmt.execute(create_query.toString());

			System.out.println("Table " + new_table_Name + " created successfully!");
		}
		catch (SQLException e)
		{
			System.err.println("Error when creating table: " + e.getMessage());
		}
	}

	/* Method to insert new data/record into the Table in it's respective database */
	public void INSERT_Record(String Table_Name, List<Map<String, String>> columns, Scanner scanner) throws Throwable
	{
		try
		{
			if(connect == null || connect.isClosed())
			{
				get_Connection(); /* Ensure connection is active */
			}

			StringBuilder insert_query, values;
			int retry_Count, min_Value, max_Value;
			Long user_value;

			/* Start of Insert Query */
			insert_query = new StringBuilder("INSERT INTO ").append(Table_Name).append(" (");

			values = new StringBuilder(" VALUES (");

			List<Object> inputValues = new ArrayList<>();

			String primaryKey_Column = null, primaryKey_Input, user_Input;
			Object primaryKey_Value = null;

			/* Retrieve Primary Key using JDBC Metadata */
			DatabaseMetaData metaData = connect.getMetaData();

			ResultSet rs = metaData.getPrimaryKeys(null, null, Table_Name);

			while (rs.next())
			{
				primaryKey_Column = rs.getString("COLUMN_NAME");
				System.out.println("Primary Key Column Found: " + primaryKey_Column);
			}

			/* To debug only */
			ResultSet r_set = metaData.getColumns(null, null, Table_Name, null);

			while (r_set.next())
			{
				String pk_column = r_set.getString("COLUMN_NAME");

			}

			/* Identify PrimaryKey column Dynamically */
			if(primaryKey_Column == null)
			{
				System.err.println("Warning: No Primary Key detected by metadata.");

				for (Map<String, String> column : columns)
				{
					System.out.println("Column: " + column.get("COLUMN_NAME") + ", Type: " + column.get("TYPE_NAME") + ", Is Primary Key: " + column.get("IS_PRIMARY_KEY") + ", Is Nullable: " + column.get("IS_NULLABLE") + ", Is AutoIncrement: " + column.get("IS_AUTOINCREMENT"));

					if("YES".equalsIgnoreCase(column.get("IS_PRIMARY_KEY")))
					{
						primaryKey_Column = column.get("COLUMN_NAME");

						break;
					}
				}

				if(primaryKey_Column == null)
				{
					throw new RuntimeException("❌ Critical Error: No Primary Key column found. Please check table structure.");
				}
			}

			/* Prompt for Primary Key FIRST */
			final String _primaryKey_Column = primaryKey_Column;
			boolean is_PrimaryKey_AutoIncremented = "YES".equalsIgnoreCase(columns.stream().filter(col -> col.get("COLUMN_NAME").equals(_primaryKey_Column)).findFirst().map(col -> col.get("IS_AUTOINCREMENT")).orElse("NO"));

			/* Fetch column constraints */
			Map<String, int[]> column_Constraints = get_Column_Constraints(Table_Name);

			if(column_Constraints.isEmpty())
			{
				System.err.println("ERROR: No constraints retrieved for table " + Table_Name);
			}

			/* Check if PrimaryKey is Auto-Incremented */
			if(!is_PrimaryKey_AutoIncremented)
			{
				retry_Count = 3;

				while (retry_Count > 0)
				{
					System.out.print("Enter value for " + primaryKey_Column + " (Primary Key): ");
					primaryKey_Input = scanner.nextLine().trim();

					if(primaryKey_Input.isEmpty())
					{
						System.err.println("Error: Primary Key cannot be empty. Please enter a value.");

						retry_Count--;

						continue;
					}

					/* Check if primary key already exists */
					if(is_PrimaryKey_Available(Table_Name, primaryKey_Column, primaryKey_Input))
					{
						System.err.println("❌ Error: Value for primary key '" + primaryKey_Column + "' already exists. Please enter a different value.");

						retry_Count--;

						continue;
					}

					final String final_primaryKey_Column = primaryKey_Column;
					primaryKey_Value = validate_Input_based_on_Column_datatype(primaryKey_Input, columns.stream().filter(col -> col.get("COLUMN_NAME").equals(final_primaryKey_Column)).findFirst().orElseThrow(() -> new RuntimeException("Error: Primary Key column metadata not found!")).get("TYPE_NAME"), false, "255");
					/* columns.stream().filter(col -> col.get("COLUMN_NAME").equals(final_primaryKey_Column)).findFirst().map(col -> col.get("COLUMN_SIZE")).map(size -> size == null || size.isEmpty() ? "255" : size).orElse("255")); */

					/* Add Primary Key to Query */
					insert_query.append(primaryKey_Column).append(", ");
					values.append("?, ");
					inputValues.add(primaryKey_Value);

					break; /* Proceed further, if PrimaryKey is valid */
				}
				if(retry_Count == 0)
				{
					throw new RuntimeException("\nToo many failed attempts. Insertion aborted. Restarting CRUD Menu");
				}
			}

			/* Process other columns */
			for (Map<String, String> column : columns)
			{
				String columnName = column.get("COLUMN_NAME");

				String columnType = column.get("TYPE_NAME");

				String column_Size_String = column.get("COLUMN_SIZE");

				int column_Size = ((column_Size_String == null) || (column_Size_String.isEmpty())) ? 255 : Integer.parseInt(column_Size_String);

				boolean isNullable = "YES".equalsIgnoreCase(column.get("IS_NULLABLE") == null ? "NO" : column.get("IS_NULLABLE"));

				boolean isAutoIncrement = "YES".equalsIgnoreCase(column.get("IS_AUTOINCREMENT") == null ? "NO" : column.get("IS_AUTOINCREMENT"));

				/* Skip auto-increment columns and PrimaryKey Column */
				if(isAutoIncrement || columnName.equalsIgnoreCase(primaryKey_Column))
				{
					continue;
				}

				/* Prompt for user input */
				retry_Count = 3;

				while (retry_Count > 0)
				{
					System.out.print("Enter value for " + columnName + " (" + columnType + "): ");
					user_Input = scanner.nextLine().trim();

					if(user_Input.length() > column_Size)
					{
						System.err.println("Error: Input exceeds allowed length of " + column_Size + " characters. Please re-enter.");

						retry_Count--;

						continue;
					}

					if(user_Input.isEmpty())
					{
						if(!isNullable)
						{
							System.err.println("Error: " + columnName + " cannot be NULL. Please enter a value.");

							retry_Count--;

							continue;
						}
						else
						{
							inputValues.add(null); /* Allow NULL if the column is nullable */

							insert_query.append(columnName).append(", ");

							values.append("NULL, "); /* Handle NULL values in query */

							break;
						}
					}

					/* Check foreign key constraints */
					if(is_Column_a_ForeignKey(Table_Name, columnName))
					{
						String referencedTable = get_Referenced_Table_Name(Table_Name, columnName);

						List<String> validValues = get_Valid_ForeignKey_Values(referencedTable, columnName);
						System.out.println("Valid Foreign Key values for " + columnName + ": " + validValues);

						while (!is_Referenced_Value_Foreign_Key_Exists(columnName, user_Input, Table_Name))
						{
							System.err.println("Error: Foreign Key value '" + user_Input + "' does not exist in the referenced table.");
							System.out.println("Valid options: " + validValues);

							System.out.print("Enter a valid ForeignKey value for " + columnName + ": ");
							user_Input = scanner.nextLine().trim();
						}
					}

					/* Validate against column constraints */
					if(column_Constraints.containsKey(columnName))
					{

						min_Value = column_Constraints.get(columnName)[0];

						max_Value = column_Constraints.get(columnName)[1];

						retry_Count = 3; /* Reset retry count for this column */
						while (retry_Count > 0)
						{
							try
							{
								user_value = Long.parseLong(user_Input);
								if((user_value < min_Value) || (user_value > max_Value))
								{
									System.err.println("Error: " + columnName + " column must have value between " + min_Value + " and " + max_Value + ". Insertion was stopped.");

									ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
									scheduler.schedule(() ->
									{

									}, 2, TimeUnit.SECONDS);
									scheduler.shutdown();

									return;
								}

								break; /* If valid, Exit Loop */
							}
							catch (NumberFormatException e)
							{
								System.err.println("Error: " + columnName + " must be a valid number.");

								retry_Count--;

								if(retry_Count > 0)
								{
									System.out.print("Enter a valid value for " + columnName + " (" + columnType + "): ");
									user_Input = scanner.nextLine().trim();
								}
								else
								{
									throw new RuntimeException("Too many invalid attempts for column '" + columnName + "'. Insertion aborted.");
								}
							}
						}
						if(retry_Count == 0)
						{
							throw new RuntimeException("Too many invalid attempts for column '" + columnName + "'. Insertion aborted.");
						}
					}

					/* Validate input based on Column type */
					Object value = (user_Input.isEmpty() && isNullable) ? null : validate_Input_based_on_Column_datatype(user_Input, columnType, isNullable, column.get("COLUMN_SIZE"));

					insert_query.append(columnName).append(", ");

					values.append("?, ");

					inputValues.add(value);

					break;
				}

				if(retry_Count == 0)
				{
					throw new RuntimeException("Too many failed attempts. Insetion aborted.");
				}

			}

			insert_query.setLength(insert_query.length() - 2); /* Remove trailing comma */

			values.setLength(values.length() - 2);

			insert_query.append(")").append(values).append(")");
			/* End of Insert Query */

			/* Execute insert query */
			try (PreparedStatement prep = connect.prepareStatement(insert_query.toString()))
			{
				for (int i = 0; i < inputValues.size(); i++)
				{
					prep.setObject(i + 1, inputValues.get(i));
				}

				prep.addBatch();
				prep.executeBatch();

				System.out.println("\u001B[43mRecord inserted successfully 😊! \u001B[0m");
			}
			catch (SQLException e)
			{
				System.err.println("Error: When Inserting Record: " + e.getMessage());

				return;
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();

			e.getMessage();
		}
	}

	/* Method to select and retrieve all or Specific data from the Table in the database */
	public void Select_and_Retrieve_Records(String Table_Name, boolean retrieve_specific_or_Every_Record, String column_in_Table, String value_of_Column) throws SQLException
	{
		String select_query = null;

		if((!retrieve_specific_or_Every_Record) && (!is_Column_Exists(column_in_Table, Table_Name)))
		{
			throw new IllegalArgumentException("Invalid Column name: " + column_in_Table);
		}
		if((!retrieve_specific_or_Every_Record) && (!is_Value_of_Column_Exists(column_in_Table, value_of_Column, Table_Name)))
		{
			throw new IllegalArgumentException("No Records found with " + column_in_Table + " = " + value_of_Column);
		}

		/* when retrieve_specific_or_Every_Record = true */
		if(retrieve_specific_or_Every_Record && column_in_Table == null && value_of_Column == null)
		{
			select_query = "SELECT * from " + Table_Name; /* Retrieves Every Record */
			/* No "WHERE clause" means this Method will retrieve entire records in the table. */
		}
		/* when retrieve_specific_or_Every_Record = false */
		else if(!retrieve_specific_or_Every_Record && column_in_Table != null && value_of_Column != null)
		{
			select_query = "SELECT * from " + Table_Name + " WHERE " + column_in_Table + " = ?"; /* Retrieves Specific Records */
		}
		else
		{
			System.out.println("Invalid parameters provided for filtering records.");
			return; /* Exit if invalid parameters are provided */
		}

		try (	Connection connect = get_Connection(); /* Make sure to handle the connection properly */
				PreparedStatement prep = connect.prepareStatement(select_query);)
		{
			/* Set the parameter for filtering */
			if(!retrieve_specific_or_Every_Record && column_in_Table != null && value_of_Column != null)
			{
				prep.setString(1, value_of_Column);
			}

			/* Execute the query */
			try (ResultSet table_data = prep.executeQuery();)
			{
				ResultSetMetaData metaData = table_data.getMetaData();
				int columnCount = metaData.getColumnCount();
				int[] each_Column_Width = new int[columnCount];

				/* Calculate maximum column width */
				for (int i = 1; i <= columnCount; i++)
				{
					each_Column_Width[i - 1] = metaData.getColumnName(i).length();
				}

				/* Iterate through data once to determine max column width */
				/* List<String[]> tableRows = new ArrayList<>(); while (table_data.next()) { String[] row_size = new String[columnCount]; for (int i = 1; i <= columnCount; i++) { row_size[i - 1] = table_data.getString(i); each_Column_Width[i - 1] =
				 * Math.max(each_Column_Width[i - 1], row_size[i - 1].length()); } tableRows.add(row_size); } */

				/* Print column names */
				System.out.println("Records in the respective " + Table_Name + " table: ");
				for (int i = 1; i <= columnCount; i++)
				{
					System.out.printf(RED_BG + "%-25s" + RESET, metaData.getColumnName(i));
					// System.out.printf(RED_BG + " %-" + each_Column_Width[i - 1] + "s " + RESET, metaData.getColumnName(i));
				}
				System.out.println();

				/* Print each row */
				boolean record_Exists = false;

				while (table_data.next()) /* To fetch all records in the Table */
				{
					record_Exists = true;
					for (int i = 1; i <= columnCount; i++)
					{
						System.out.printf(YELLOW_BG + "%-25s" + RESET, table_data.getString(i));
						// System.out.printf(YELLOW_BG + " %-" + each_Column_Width[i - 1] + "s " + RESET, table_data.getString(i));
					}
					System.out.println();
				}

				System.out.println("\n" + Table_Name + " Table Data Retrieved Successfully 😊!");

				/* If no records were found */
				if(!record_Exists)
				{
					System.err.println("No records found. ☹");
				}

			}
		}
		catch (Exception e)
		{
			System.err.println("Error while retrieving records: " + e.getMessage());

			e.printStackTrace();
		}
	}

	/* Method to update the data of a specific Record, based on specified column. */
	public void UPDATE_TABLE_Data(String Table_Name, boolean update_every_row_value_or_based_on_column, String column_to_perform_Update_operation, String new_Value_of_selected_Column, String existing_other_Column_Name_in_Table, String existing_Value_of_that_other_column) throws SQLException
	{
		if(connect == null || connect.isClosed())
		{
			connect = get_Connection(); /* Ensure connection is active */
		}

		int maxLength = get_Column_Length(column_to_perform_Update_operation, Table_Name);

		if(new_Value_of_selected_Column.length() > maxLength)
		{
			throw new IllegalArgumentException("Error: Input Exceeds allowed length of " + maxLength + " characters.");
		}

		try
		{
			/* Check if the specified column exists */
			if(!is_Column_Exists(column_to_perform_Update_operation, Table_Name))
			{
				throw new IllegalArgumentException("Invalid column name: " + column_to_perform_Update_operation);
			}

			if(is_Column_has_Primary_Key(column_to_perform_Update_operation, Table_Name))
			{
				throw new UnsupportedOperationException("Updating the PrimaryKey '" + column_to_perform_Update_operation + "' is not allowed.");
			}

			String update_query;

			if(update_every_row_value_or_based_on_column)
			{
				/* Update all rows of a column */
				update_query = "UPDATE " + Table_Name + " SET " + column_to_perform_Update_operation + " = ?"; /* + new_Value_of_selected_Column + "'"; */
			}
			else
			{
				/* Update specific rows using WHERE clause */
				if(!is_Column_Exists(existing_other_Column_Name_in_Table, Table_Name))
				{
					throw new IllegalArgumentException("Invalid column: " + existing_other_Column_Name_in_Table);
				}

				if((!update_every_row_value_or_based_on_column) && (existing_other_Column_Name_in_Table == null))
				{
					throw new IllegalArgumentException("Where clause's Column name cannot be null for specific updates.");
				}

				if(existing_other_Column_Name_in_Table == null)
				{
					throw new IllegalArgumentException("Where clause's Column name cannot be null for specific updates.");
				}

				update_query = "UPDATE " + Table_Name + " SET " + column_to_perform_Update_operation + " = ? " + " WHERE " + existing_other_Column_Name_in_Table + " = ? ";

			}
			/* Execute the query */
			Connection connect = SSMS_Connection.get_Connection(); /* Make sure to handle the connection properly */

			if(connect == null || connect.isClosed())
			{
				get_Connection(); /* Ensure connection is active */
			}

			try (PreparedStatement prep = connect.prepareStatement(update_query);)
			{
				prep.setString(1, new_Value_of_selected_Column);
				if(!update_every_row_value_or_based_on_column)
				{

					prep.setString(2, existing_Value_of_that_other_column);
				}

				int rowsAffected = prep.executeUpdate();

				System.out.println("Update successful. Rows affected: " + rowsAffected);
			}
		}
		catch (SQLException e)
		{
			System.err.println("SQL Error: " + e.getMessage());

			e.printStackTrace();
		}
		catch (IllegalArgumentException e)
		{
			System.err.println("Error: " + e.getMessage());
		}
		catch (UnsupportedOperationException e)
		{
			System.err.println(e.getMessage());

			System.exit(1);
		}
	}

	/* Method to delete Specific records or Entire records from the database */
	public void Delete_Table_Data(String Table_Name, boolean delete_specific_or_every_record, String column_in_Table, String value_of_column) throws SQLException
	{
		if(connect == null || connect.isClosed())
		{
			get_Connection(); /* Ensure connection is active */
		}

		/* Checks if the column is a PrimaryKey */
		if((!delete_specific_or_every_record) && (is_Column_a_PrimaryKey(column_in_Table, Table_Name)))
		{
			throw new RuntimeException("Error: cannot delete record because '" + column_in_Table + "' is a PrimaryKey.");
		}

		/* Checks if the column is a ForeignKey */
		if((!delete_specific_or_every_record) && (is_Column_a_ForeignKey(column_in_Table, Table_Name)))
		{
			throw new RuntimeException("Error: cannot delete record because '" + column_in_Table + "' is a ForeignKey.");
		}

		/* Checks if PrimaryKey value is referenced in another table. */
		if((!delete_specific_or_every_record) && (is_PrimaryKey_Referenced_in_Other_Tables(column_in_Table)))
		{
			throw new RuntimeException("Error: Cannot delete record because it is referenced as ForeignKey in another table.");
		}

		/* Checks if ForeignKey value is referenced in another table. */
		if((!delete_specific_or_every_record) && (is_Value_Referenced_as_ForeignKey(column_in_Table, value_of_column, Table_Name)))
		{
			throw new RuntimeException("Error: Cannot delete record because it is referenced as a Foreign Key in another table.");
		}

		String delete_query;

		/* Check if "all" records should be deleted */
		if(delete_specific_or_every_record && column_in_Table == null && value_of_column == null)
		{
			delete_query = "DELETE FROM " + Table_Name; /* Deletes all records. */
			/* No "WHERE clause" means this Method will remove entire rows in the table record by record. */
		}
		else
		{
			/* Write query for specific record deletion */
			delete_query = "DELETE FROM " + Table_Name + " WHERE " + column_in_Table + " = ?";
		}

		try (	Connection connect = SSMS_Connection.get_Connection(); /* Establishing Connection. */
				PreparedStatement prep = connect.prepareStatement(delete_query);)
		{
			if(!delete_specific_or_every_record)
			{
				/* Set the parameter value for specific deletion */
				prep.setString(1, value_of_column);
			}

			int rows_Affected = prep.executeUpdate(); /* storing the count of rows that got Affected. */

			if(rows_Affected > 0)
			{
				System.out.println(rows_Affected + " record(s) deleted Successfully 😅");
			}
			else
			{
				System.err.println("No records matched the criteria for deletion.");
			}

		}
		catch(SQLException e) /* catches if any SQLException occurs */
		{
			System.err.println("Error executing delete operation: " + e.getMessage());

			e.printStackTrace();

			throw e;
		}
	}

} /* End of "SSMS_Connection" */
