package _Connect_SSMS_with_ECLIPSE._METADATA_Getter_Functions;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import _Connect_SSMS_with_ECLIPSE._Connection_Utilities.JDBC_Connection_Manager;
import _Connect_SSMS_with_ECLIPSE._Connection_Utilities.MSSQL_Server_Connection_Manager;


public class MSSQL_Server_MetaDATA_Service  implements MetaData_Service
{
	JDBC_Connection_Manager jdbc_connection = new MSSQL_Server_Connection_Manager();

	/* Method to check value of column exists in table or not. */
	/* This method checks if a particular value exists in a column of a specific table. It first validates column existence using 'is_Column_Exists' method before executing a lookup. */
	@Override
	public boolean is_Value_of_Column_Exists(String column_in_Table, String value_of_column, String Table_Name) throws SQLException 
	{
		/* Validate if the column exists before building the query */
		if(!is_Column_Exists(column_in_Table, Table_Name))
		{
			throw new SQLException("Column " + column_in_Table + " does not exist in the table.");
		}
		String check_value_query = "SELECT COUNT(*) FROM " + Table_Name + " WHERE " + column_in_Table + " = ?";

		boolean exists = false;

		try (	Connection connect = jdbc_connection.get_Connection(); /* Establishing Connection. */
				PreparedStatement prep = connect.prepareStatement(check_value_query);
			)
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

	/* Method to check if a column exists in the table or not */
	/* This method checks if a specific column exists in a specific table by querying 'INFORMATION_SCHEMA.COLUMNS'. */
	@Override
	public boolean is_Column_Exists(String column_Name, String Table_Name) throws SQLException 
	{
		/* Query to check if the column exists in the Selected Table */
		String check_column_query = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + Table_Name + "' AND COLUMN_NAME = ?";

		boolean exists = false;

		try (	Connection connect = jdbc_connection.get_Connection(); /* Establishing Connection. */
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

	/* Method to check if the Table is present in Database or not. */
	/* This method uses JDBC's DatabaseMetaData to check if a table exists in the current database. */
	@Override
	public boolean is_Table_exists(String Table_Name) throws SQLException
	{
		DatabaseMetaData meta = jdbc_connection.get_Connection().getMetaData();

		try (ResultSet result_count_of_tables = meta.getTables(null, null, Table_Name, new String[]{"TABLE"}))
		{
			boolean exists = result_count_of_tables.next();

			return exists;
		}
	}
	
	/* Method to Check if a column in a table has the same data type */
	/* This method checks if a given column’s data type matches an expected value using the INFORMATION_SCHEMA.COLUMNS view. */
	@Override
	public boolean is_Column_Matching_DataType(String columnName, String tableName, String dataType) throws SQLException 
	{
		String datatype_query = "SELECT DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ? AND COLUMN_NAME = ?";

		Connection connect = jdbc_connection.get_Connection();
		
		try (PreparedStatement prep = connect.prepareStatement(datatype_query))
		{
			prep.setString(1, tableName);
			prep.setString(2, columnName);

			ResultSet rs = prep.executeQuery();

			if(rs.next())
			{
				return (rs.getString("DATA_TYPE").equalsIgnoreCase(dataType));
			}
		}

		return false;
	}

	/* Method to get Length of the Column */
	/* This method checks the maximum length of a specific column by querying the INFORMATION_SCHEMA.COLUMNS view. */
	@Override
	public int get_Column_Length(String column_Name, String Table_Name) throws SQLException 
	{
		Connection connect = jdbc_connection.get_Connection(); /* Establishing Connection. */
		if(connect == null || connect.isClosed())
		{
			connect = jdbc_connection.get_Connection(); /* Ensure connection is active */
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

	/* This method pulls raw CHECK constraint definitions using the system views (sys.check_constraints, sys.columns, sys.sysconstraints). */
	@Override
	public Map<String, String> _get_ColumnConstraints(String Table_Name) throws SQLException 
	{
		Map<String, String> constraintsMap = new HashMap<>();
		
		String query = "SELECT col.name AS column_name, chk.definition AS check_clause " + "FROM sys.check_constraints chk " + "JOIN sys.columns col ON chk.parent_object_id = col.object_id " + "JOIN sys.sysconstraints sc ON chk.object_id = sc.constid AND col.column_id = sc.colid " + "WHERE chk.parent_object_id = OBJECT_ID(?) ";

		Connection connect = jdbc_connection.get_Connection();
		
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
	/* This method pulls min/max numeric bounds from CHECK constraints on columns via INFORMATION_SCHEMA views. */
	@Override
	public  Map<String, int[]> get_Column_Constraints(String Table_Name) throws SQLException 
	{
		Map<String, int[]> constraints_map = new HashMap<>();

		String check_constraints_query = "SELECT ccu.COLUMN_NAME, cc.CHECK_CLAUSE " + "FROM INFORMATION_SCHEMA.CHECK_CONSTRAINTS AS cc " + "JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE AS ccu " + "ON cc.CONSTRAINT_NAME = ccu.CONSTRAINT_NAME " + "WHERE ccu.TABLE_NAME = ?";

		Connection connect = jdbc_connection.get_Connection();
		
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

	/* This method Retrieves column metadata */
	/* This method uses JDBC's DatabaseMetaData to retrieve detailed information about each column in a given table (like name, type, nullability, size) */
	@Override
	public List<Map<String, String>> get_Table_Metadata_to_obtain_Column_info(String Table_Name) throws SQLException 
	{
		Connection connect = jdbc_connection.get_Connection();
		
		if(connect == null || connect.isClosed())
		{
			connect = jdbc_connection.get_Connection(); /* Ensure connection is active */
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

	/* Method to display the values present in a specific column. */
	/* This method fetches and prints distinct values from a specified column in a table. It’s a read-only operation that involves querying data structure and values, often used for metadata inspection or reference. */
	@Override
	public void Display_Values_of_Each_Column_of_the_Table(String column_name, String Table_Name) 
	{
		String display_value_query = "SELECT DISTINCT " + column_name + " FROM " + Table_Name;

		try (	Connection connect = jdbc_connection.get_Connection(); /* Establishing Connection. */
				PreparedStatement prep = connect.prepareStatement(display_value_query); 
				ResultSet result_Value = prep.executeQuery();
			)
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

	/* Method to display all columns in the table. */
	/* This method queries 'INFORMATION_SCHEMA.COLUMNS' to list all column names of a given table. It simply reads and displays schema structure */
	@Override
	public void Display_All_Columns_of_the_Table(String Table_Name)
	{
		String display_column_query = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + Table_Name + "' ";

		try (	Connection connect = jdbc_connection.get_Connection(); /* Establishing Connection. */
				PreparedStatement prep = connect.prepareStatement(display_column_query); 
				ResultSet result_Column = prep.executeQuery()
			)
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

	/* Method to Display all Tables in the Current Database. */
	/* This method fetches all available user-defined tables in the database using DatabaseMetaData. */
	@Override
	public void Display_All_Tables_in_Database() throws SQLException
	{
		Connection connect = jdbc_connection.get_Connection();
		
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

	/* Method to Get Column Type from Metadata */
	public String get_Column_Type(List<Map<String, String>> columns, String columnName)
	{
		return (columns.stream().filter(col -> col.get("COLUMN_NAME").equals(columnName)).findFirst().orElseThrow(() -> new RuntimeException("Column type not found!")).get("TYPE_NAME"));
	}
	
}
