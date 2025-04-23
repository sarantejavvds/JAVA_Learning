package _Connect_SSMS_with_ECLIPSE._SQL_Key_Constraints_Operations;

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
import _Connect_SSMS_with_ECLIPSE._METADATA_Getter_Functions.MSSQL_Server_MetaDATA_Service;
import _Connect_SSMS_with_ECLIPSE._METADATA_Getter_Functions.MetaData_Service;

public class MSSQL_Server_KEY_Constraint_Service implements KEY_Constraint_Service 
{
	MetaData_Service metadata_service = new MSSQL_Server_MetaDATA_Service();
	JDBC_Connection_Manager jdbc_connection = new MSSQL_Server_Connection_Manager();
	
	/* This method Check primary key availability */
	/* This method checks if a given value already exists in a primary key column. This ensures no duplicate primary key is inserted */
	@Override
	public boolean is_PrimaryKey_Available(String TableName, String primaryKeyColumn, Object value) throws SQLException 
	{
		Connection connect = jdbc_connection.get_Connection();
		
		/* Validate Table and Column Names to Prevent SQL Injection */
		if(!is_Valid_Identifier(TableName) || !is_Valid_Identifier(primaryKeyColumn))
		{
			throw new IllegalArgumentException("Invalid table or column name.");
		}

		/* Ensure Connection is Active */
		if(connect == null || connect.isClosed())
		{
			connect =	jdbc_connection.get_Connection();
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
	/* This method specifically typed for String values. It performs the primary key existence check, helping enforce integrity during inserts. */
	@Override
	public boolean is_PrimaryKey_Available(String TableName, String primaryKey_Column, String primaryKey_value) throws SQLException 
	{
		Connection connect = jdbc_connection.get_Connection();
		
		/* Validate Table and Column Names to Prevent SQL Injection */
		if(!is_Valid_Identifier(TableName) || !is_Valid_Identifier(primaryKey_Column))
		{
			throw new IllegalArgumentException("Invalid table or column name.");
		}

		/* Ensure Connection is Active */
		if(connect == null || connect.isClosed())
		{
			connect = jdbc_connection.get_Connection();
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

	/* This method checks if a Primary Key column is referenced by any Foreign Keys in other tables. This is crucial before deleting rows or modifying PrimaryKeys. */
	@Override
	public boolean is_PrimaryKey_Referenced_in_Other_Tables(String primary_key_Column) throws SQLException 
	{
		Connection connect = jdbc_connection.get_Connection();
		
		String check_query = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE COLUMN_NAME = ? AND CONSTRAINT_NAME LIKE 'FK_%' ";

		try (PreparedStatement prep = connect.prepareStatement(check_query);)
		{
			prep.setString(1, primary_key_Column);

			ResultSet result_set = prep.executeQuery();

			return result_set.next(); /* Returns true if this PrimaryKey is referenced as ForeignKey in other Tables */
		}
	}

	/* Check if a column is a PrimaryKey in a Table */
	/* This method checks if a specific column is a Primary Key in a table by querying 'INFORMATION_SCHEMA.KEY_COLUMN_USAGE' with a LIKE 'PK_%' filter. */
	@Override
	public boolean is_Column_a_PrimaryKey(String column_Name, String Table_Name) throws SQLException
	{
		Connection connect = jdbc_connection.get_Connection();
		
		String check_query = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE TABLE_NAME = ? AND COLUMN_NAME = ? AND CONSTRAINT_NAME LIKE 'PK_%' ";

		try (PreparedStatement prep = connect.prepareStatement(check_query);)
		{
			prep.setString(1, Table_Name);

			prep.setString(2, column_Name);

			ResultSet result_set = prep.executeQuery();

			return result_set.next(); /* Returns true if this column is a PrimaryKey */
		}
	}

	/* Method to check if column has a Primary Key or not */
	/* This method checks if a given column has a primary key constraint or unique index. It uses both 'DatabaseMetaData.getPrimaryKeys(') and 'getIndexInfo()' to determine whether a column is uniquely constrained */
	@Override
	public boolean is_Column_has_Primary_Key(String column_To_Check, String Table_Name) throws SQLException 
	{
		Connection connect = jdbc_connection.get_Connection();
		
		if(connect == null || connect.isClosed())
		{
			 connect = jdbc_connection.get_Connection(); /* Ensure connection is active */
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
		while(index_ResultSet.next())
		{
			/* String index_Name = index_ResultSet.getString("INDEX_NAME"); */
			if( !index_ResultSet.getBoolean("NON_UNIQUE") )
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
	/* This method checks where a column is used as a primary key in other tables by querying 'INFORMATION_SCHEMA.KEY_COLUMN_USAGE'. */
	@Override
	public Map<String, String> find_PrimaryKey_In_Other_Tables(String column_Name) throws SQLException 
	{
		Connection connect = jdbc_connection.get_Connection();
		
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
	/* This method checks whether a specific column is defined as a Foreign Key using ''INFORMATION_SCHEMA.KEY_COLUMN_USAGE' and a LIKE 'FK_%' filter.' */
	@Override
	public boolean is_Column_a_ForeignKey(String column_Name, String Table_Name) throws SQLException 
	{
		Connection connect = jdbc_connection.get_Connection();
		
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
	/* This method determines whether a given column and value is used as a foreign key reference in another table. It queries system views like 'sys.foreign_keys' and 'sys.foreign_key_columns'. */
	@Override
	public boolean is_Value_Referenced_as_ForeignKey(String columnName, String columnValue, String tableName) throws SQLException 
	{
		Connection connect = jdbc_connection.get_Connection();
		
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

	/* Method to check if a Primary Key Value Exists in the Referenced Table or not */
	/* This method checks if a given Primary Key value is being referenced in any foreign key column of another table. It joins system views to trace the relationship and performs a lookup. */
	@Override
	public boolean is_Referenced_Value_Primary_Key_Exists(String primaryKey_Column, Object primaryKey_Value, String Table_Name) throws SQLException
	{
		Connection connect = jdbc_connection.get_Connection();
		
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

	/* Method to check if a Foreign Key Value Exists in the Referenced Table or not */
	/* This method checks if a Foreign Key value exists in its referenced Primary Key table. It dynamically resolves the referenced table and column, and then verifies the FK value is valid. */
	@Override
	public boolean is_Referenced_Value_Foreign_Key_Exists(String column_Name, Object value, String Table_Name) throws SQLException
	{
		Connection connect = jdbc_connection.get_Connection();
		
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

	/* Method to check if a Foreign Key Value Exists in the Referenced Table or not using MetaData. */
	/* This is a method with the FK value as a String. It provides type-specific convenience while enforcing the same FK→PK rule. */
	@Override
	public boolean is_Referenced_Value_Foreign_Key_Exists(String fk_column_Name, String fk_value, String Table_Name) throws SQLException
	{
		Connection connect = jdbc_connection.get_Connection();
		
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
	/* This method checks if the referenced column in a foreign key relationship actually exists in its supposed target table. It ensures the FK is not pointing to an invalid or dropped column. */
	@Override
	public boolean is_Referenced_Column_exists_in_Referenced_Table(String referenced_Column, String referenced_Table) throws SQLException 
	{
		Connection connect = jdbc_connection.get_Connection();
		
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
	
	/* Method to Check if a new Primary Key matches an existing Foreign Key in other tables */
	/* This method checks whether a primary key column’s data type matches the corresponding foreign key it’s being referenced by in other tables. This prevents data type mismatches and ensures clean key relationships. */
	@Override
	public boolean is_PrimaryKey_Matching_Existing_ForeignKey(String primaryKey_Column, String dataType) throws SQLException 
	{
		Connection connect = jdbc_connection.get_Connection();
		
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
				if(metadata_service.is_Column_Matching_DataType(existingColumn, existingTable, dataType))
				{
					System.out.println("✔ Foreign Key in table '" + existingTable + "' matches the new Primary Key.");

					return true;
				}
			}
		}

		return false;
	}

	/* Method to Check if a new Foreign Key matches an existing Primary Key in other tables */
	/* This method ensures a foreign key column's type matches its referenced primary key column's type. */
	@Override
	public boolean is_ForeignKey_Matching_Existing_PrimaryKey(String referenced_Column, String referenced_Table, String foreignKey_Column, String Table_Name) throws SQLException
	{
		Connection connect = jdbc_connection.get_Connection();
		
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
    /* Same as above method */
    public boolean _is_ForeignKey_Matching_Existing_PrimaryKey(String referenced_Column, String referenced_Table, String foreignKey_Column, String Table_Name) throws SQLException 
    {
        String referenced_Type_Query = "SELECT DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ? AND COLUMN_NAME = ?";
        String foreignKey_Type_Query = "SELECT DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ? AND COLUMN_NAME = ?";

        Connection connect = jdbc_connection.get_Connection();

        String referenced_Data_Type = null;
        String fk_Data_Type = null;

        try (
            PreparedStatement ref_stmt = connect.prepareStatement(referenced_Type_Query);
            PreparedStatement fk_stmt = connect.prepareStatement(foreignKey_Type_Query)
            )
        {
            ref_stmt.setString(1, referenced_Table);
            ref_stmt.setString(2, referenced_Column);
            ResultSet ref_rs = ref_stmt.executeQuery();
            if (ref_rs.next()) 
            {
                referenced_Data_Type = ref_rs.getString("DATA_TYPE");
            }

            fk_stmt.setString(1, Table_Name);
            fk_stmt.setString(2, foreignKey_Column);
            ResultSet fk_rs = fk_stmt.executeQuery();
            if (fk_rs.next()) 
            {
                fk_Data_Type = fk_rs.getString("DATA_TYPE");
            }
        }

        if (referenced_Data_Type != null && fk_Data_Type != null) 
        {
            return referenced_Data_Type.equalsIgnoreCase(fk_Data_Type);
        }

        return false;
    }

    /* Method to fetch Referenced PrimaryKey Table Name */
    /* This method retrieves the referenced table name for a given foreign key column. It uses system views to resolve relational mappings. */
	@Override
	public String get_Referenced_Table_Name(String columnName, String tableName) throws SQLException
	{
		Connection connect = jdbc_connection.get_Connection();
		
		String query = "SELECT pkTable.name AS ReferencedTable " + "FROM sys.foreign_keys fk " + "JOIN sys.foreign_key_columns fkCols ON fk.object_id = fkCols.constraint_object_id " + "JOIN sys.tables pkTable ON fkCols.referenced_object_id = pkTable.object_id " + "JOIN sys.columns pkCol ON fkCols.referenced_object_id = pkCol.object_id " + "AND fkCols.referenced_column_id = pkCol.column_id " + "WHERE fk.parent_object_id = OBJECT_ID(?) AND pkCol.name = ?";

		try (PreparedStatement prep = connect.prepareStatement(query))
		{
			prep.setString(1, tableName);
			prep.setString(2, columnName);
			ResultSet rs = prep.executeQuery();

			if(rs.next())
			{
				return rs.getString("ReferencedTable");
			}
		}

		return null;
	}

	/* Method to get Valid ForeignKey Values. */
	/* This method returns all valid values from the referenced primary key column that a foreign key points to. It’s essential for providing suggestions or validation before inserts. */
	@Override
	public List<String> get_Valid_ForeignKey_Values(String columnName, String tableName) throws SQLException 
	{
		Connection connect = jdbc_connection.get_Connection();
		
		List<String> valid_Values = new ArrayList<>();
		String referenced_Table = get_Referenced_Table_Name(columnName, tableName);

		if(referenced_Table == null)
		{
			return valid_Values;
		}

		String query = "SELECT DISTINCT " + columnName + " FROM " + referenced_Table;
		try (PreparedStatement prep = connect.prepareStatement(query); 
			ResultSet rs = prep.executeQuery();
			)
		{
			while (rs.next())
			{
				valid_Values.add(rs.getString(columnName));
			}
		}

		return valid_Values;
	}

	/* Method to Validate Table and Column Names */
	public boolean is_Valid_Identifier(String name)
	{
		return name.matches("[a-zA-Z0-9_]+"); /* Allows only letters, numbers, and underscores */
	}

}
