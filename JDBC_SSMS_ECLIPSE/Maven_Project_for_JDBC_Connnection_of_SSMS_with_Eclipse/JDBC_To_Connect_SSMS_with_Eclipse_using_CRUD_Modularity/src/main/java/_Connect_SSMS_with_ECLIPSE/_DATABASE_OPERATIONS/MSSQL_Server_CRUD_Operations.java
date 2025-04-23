package _Connect_SSMS_with_ECLIPSE._DATABASE_OPERATIONS;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import _Connect_SSMS_with_ECLIPSE._Connection_Utilities.JDBC_Connection_Manager;
import _Connect_SSMS_with_ECLIPSE._Connection_Utilities.MSSQL_Server_Connection_Manager;
import _Connect_SSMS_with_ECLIPSE._Input_Validations.Input_Validator;
import _Connect_SSMS_with_ECLIPSE._Input_Validations.MSSQL_Server_INPUT_Validator;
import _Connect_SSMS_with_ECLIPSE._METADATA_Getter_Functions.MSSQL_Server_MetaDATA_Service;
import _Connect_SSMS_with_ECLIPSE._METADATA_Getter_Functions.MetaData_Service;
import _Connect_SSMS_with_ECLIPSE._SQL_Key_Constraints_Operations.KEY_Constraint_Service;
import _Connect_SSMS_with_ECLIPSE._SQL_Key_Constraints_Operations.MSSQL_Server_KEY_Constraint_Service;


public class MSSQL_Server_CRUD_Operations implements Database_CRUD_Operations
{
	JDBC_Connection_Manager jdbc_connection = new MSSQL_Server_Connection_Manager();
	Input_Validator input_validation = new MSSQL_Server_INPUT_Validator();
	MetaData_Service metadata_service = new MSSQL_Server_MetaDATA_Service();
	KEY_Constraint_Service key_constraint_service = new MSSQL_Server_KEY_Constraint_Service();
	
	public final String RESET = "\u001B[0m";
	public final String RED_BG = "\u001B[41m"; /* Red background for headers */
	public final String YELLOW_BG = "\u001B[43m"; /* Yellow background for data */
	public final String GREEN_BG = "\u001B[42m"; /* Green background for headers */
	public final String BLUE_BG = "\u001B[44m"; /* Blue background for headers */
	public final String MAGENTA_BG = "\u001B[45m"; /* Magenta background for headers */
	public final String BLACK_BG = "\u001B[40m"; /* Black background for headers */
	public final String WHITE_BG = "\u001B[47m"; /* White background for headers */
	
	/* Method to insert new data/record into the Table in it's respective database */
	@Override
	public void _INSERT_Record(String Table_Name, List<Map<String, String>> columns, Scanner scanner) throws SQLException 
	{
		Connection connect = jdbc_connection.get_Connection();

		try
		{
			if(connect == null || connect.isClosed())
			{
				connect = jdbc_connection.get_Connection(); /* Ensure connection is active */
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
			Map<String, int[]> column_Constraints = metadata_service.get_Column_Constraints(Table_Name);

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
					if(key_constraint_service.is_PrimaryKey_Available(Table_Name, primaryKey_Column, primaryKey_Input))
					{
						System.err.println("❌ Error: Value for primary key '" + primaryKey_Column + "' already exists. Please enter a different value.");

						retry_Count--;

						continue;
					}

					final String final_primaryKey_Column = primaryKey_Column;
					primaryKey_Value = input_validation.validate_Input_based_on_Column_datatype(primaryKey_Input, columns.stream().filter(col -> col.get("COLUMN_NAME").equals(final_primaryKey_Column)).findFirst().orElseThrow(() -> new RuntimeException("Error: Primary Key column metadata not found!")).get("TYPE_NAME"), false, "255");
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
					if(key_constraint_service.is_Column_a_ForeignKey(Table_Name, columnName))
					{
						String referencedTable = key_constraint_service.get_Referenced_Table_Name(Table_Name, columnName);

						List<String> validValues = key_constraint_service.get_Valid_ForeignKey_Values(referencedTable, columnName);
						System.out.println("Valid Foreign Key values for " + columnName + ": " + validValues);

						while(!key_constraint_service.is_Referenced_Value_Foreign_Key_Exists(columnName, user_Input, Table_Name))
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
					Object value = (user_Input.isEmpty() && isNullable) ? null : input_validation.validate_Input_based_on_Column_datatype(user_Input, columnType, isNullable, column.get("COLUMN_SIZE"));

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
	/* This method inserts data into a table by prompting the user for column values. It also applies constraint checks before inserting. */
	public void Simple_INSERT_Record_Method() throws SQLException 
	{
        Scanner sc = new Scanner(System.in);
        Connection connect = jdbc_connection.get_Connection();

        System.out.print("Enter the name of the table to insert into: ");
        String Table_Name = sc.nextLine();

        String getColumnsQuery = "SELECT COLUMN_NAME, DATA_TYPE, IS_NULLABLE, CHARACTER_MAXIMUM_LENGTH FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ?";
        PreparedStatement columnStmt = connect.prepareStatement(getColumnsQuery);
        columnStmt.setString(1, Table_Name);
        ResultSet rs = columnStmt.executeQuery();

        StringBuilder insertQuery = new StringBuilder("INSERT INTO " + Table_Name + " (");
        StringBuilder valuesQuery = new StringBuilder("VALUES (");
        int columnCount = 0;
        Map<Integer, Object> valuesMap = new HashMap<>();

        while (rs.next()) {
            String columnName = rs.getString("COLUMN_NAME");
            String dataType = rs.getString("DATA_TYPE");
            String isNullable = rs.getString("IS_NULLABLE");
            String columnSize = rs.getString("CHARACTER_MAXIMUM_LENGTH");

            System.out.print("Enter value for column '" + columnName + "' (" + dataType + "): ");
            String userInput = sc.nextLine();

            // Convert user input to appropriate type
            Object value = null;
            try 
            {
                switch (dataType.toUpperCase()) 
                {
                    case "INT":
                    case "INTEGER":
                        value = (userInput.isEmpty() ? null : Integer.parseInt(userInput));
                        break;
                    case "FLOAT":
                    case "DECIMAL":
                    case "NUMERIC":
                        value = (userInput.isEmpty() ? null : Double.parseDouble(userInput));
                        break;
                    case "DATE":
                        value = (userInput.isEmpty() ? null : Date.valueOf(userInput));
                        break;
                    case "CHAR":
                    case "VARCHAR":
                    case "NVARCHAR":
                    default:
                        value = (userInput.isEmpty() ? null : userInput);
                        break;
                }
            } 
            catch (Exception e)
            {
                System.err.println("Invalid input for column '" + columnName + "'. Skipping insert.");
                sc.close();
                return;
            }

            if (!userInput.isEmpty() || "YES".equalsIgnoreCase(isNullable)) 
            {
                if (columnCount > 0) 
                {
                    insertQuery.append(", ");
                    valuesQuery.append(", ");
                }
                insertQuery.append(columnName);
                valuesQuery.append("?");
                valuesMap.put(++columnCount, value);
            }
        }

        insertQuery.append(") ").append(valuesQuery).append(")");

        try (PreparedStatement insertStmt = connect.prepareStatement(insertQuery.toString())) 
        {
            for (Map.Entry<Integer, Object> entry : valuesMap.entrySet()) 
            {
                insertStmt.setObject(entry.getKey(), entry.getValue());
            }

            int result = insertStmt.executeUpdate();
            if (result > 0) 
            {
                System.out.println("✅ Record inserted successfully.");
            }
            sc.close();
        } 
        catch (SQLException e) 
        {
            System.err.println("❌ Insert failed: " + e.getMessage());
        }
	}

	
	
	/* Method to select and retrieve all or Specific data from the Table in the database. */
	@Override
	public void _Select_and_Retrieve_Records(String Table_Name, boolean retrieve_specific_or_Every_Record, String column_in_Table, String value_of_Column) throws SQLException 
	{
		String select_query = null;

		if((!retrieve_specific_or_Every_Record) && (!metadata_service.is_Column_Exists(column_in_Table, Table_Name)))
		{
			throw new IllegalArgumentException("Invalid Column name: " + column_in_Table);
		}
		if((!retrieve_specific_or_Every_Record) && (!metadata_service.is_Value_of_Column_Exists(column_in_Table, value_of_Column, Table_Name)))
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

		try (	Connection connect = jdbc_connection.get_Connection(); /* Make sure to handle the connection properly */
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
	/* This method fetches and displays records from a table. */
	public void Simple_SELECT_and_Display_Records_Method() throws SQLException 
	{
        Scanner sc = new Scanner(System.in);
        Connection connect = jdbc_connection.get_Connection();

        System.out.print("Enter the table name: ");
        String tableName = sc.nextLine();

        String selectQuery = "SELECT * FROM " + tableName;

        try (PreparedStatement stmt = connect.prepareStatement(selectQuery);
             ResultSet rs = stmt.executeQuery();
            ) 
        {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            System.out.println("\n📋 Records in table '" + tableName + "':\n");

            // Print column headers
            for (int i = 1; i <= columnCount; i++)
            {
                System.out.print(metaData.getColumnName(i) + "\t");
            }
            System.out.println("\n" + "-".repeat(50));

            // Print rows
            boolean hasData = false;
            while (rs.next()) 
            {
                hasData = true;
                for (int i = 1; i <= columnCount; i++) 
                {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }

            if (!hasData) 
            {
                System.out.println("⚠ No records found in table.");
            }

        } 
        catch (SQLException e) 
        {
            System.err.println("❌ Failed to retrieve data: " + e.getMessage());
        }
    }

	
	
	/* Method to update the data of a specific Record, based on specified column. */
	@Override
	public void _UPDATE_TABLE_Data(String Table_Name, boolean update_every_row_value_or_based_on_column, String column_to_perform_Update_operation, String new_Value_of_selected_Column, String existing_other_Column_Name_in_Table, String existing_Value_of_that_other_column) throws SQLException
	{
		Connection connect = jdbc_connection.get_Connection();
		
		if(connect == null || connect.isClosed())
		{
			connect = jdbc_connection.get_Connection(); /* Ensure connection is active */
		}

		int maxLength = metadata_service.get_Column_Length(column_to_perform_Update_operation, Table_Name);

		if(new_Value_of_selected_Column.length() > maxLength)
		{
			throw new IllegalArgumentException("Error: Input Exceeds allowed length of " + maxLength + " characters.");
		}

		try
		{
			/* Check if the specified column exists */
			if(!metadata_service.is_Column_Exists(column_to_perform_Update_operation, Table_Name))
			{
				throw new IllegalArgumentException("Invalid column name: " + column_to_perform_Update_operation);
			}

			if(key_constraint_service.is_Column_has_Primary_Key(column_to_perform_Update_operation, Table_Name))
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
				if(!metadata_service.is_Column_Exists(existing_other_Column_Name_in_Table, Table_Name))
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
			connect = jdbc_connection.get_Connection(); /* Make sure to handle the connection properly */

			if(connect == null || connect.isClosed())
			{
				connect = jdbc_connection.get_Connection(); /* Ensure connection is active */
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
	/* This method updates a row in a specified table by prompting the user for:
       A target column to match,
       A new column and value to update. */
	public void Simple_UPDATE_Record_Method() throws SQLException
	{
        Scanner sc = new Scanner(System.in);
        Connection connect = jdbc_connection.get_Connection();

        System.out.print("Enter the table name: ");
        String tableName = sc.nextLine();

        System.out.print("Enter the column name to match (WHERE clause): ");
        String matchColumn = sc.nextLine();

        System.out.print("Enter the value to match: ");
        String matchValue = sc.nextLine();

        System.out.print("Enter the column name to update: ");
        String updateColumn = sc.nextLine();

        System.out.print("Enter the new value: ");
        String newValue = sc.nextLine();

        String updateQuery = "UPDATE " + tableName + " SET " + updateColumn + " = ? WHERE " + matchColumn + " = ?";

        try (PreparedStatement stmt = connect.prepareStatement(updateQuery)) {
            stmt.setString(1, newValue);
            stmt.setString(2, matchValue);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0)
            {
                System.out.println("✅ Record updated successfully.");
            } 
            else 
            {
                System.out.println("⚠ No matching record found.");
            }
        } 
        catch (SQLException e) 
        {
            System.err.println("❌ Update failed: " + e.getMessage());
        }
    }

	
	
	/* Method to delete Specific records or Entire records from the database */
	@Override
	public void _Delete_Table_Data(String Table_Name, boolean delete_specific_or_every_record, String column_in_Table, String value_of_column) throws SQLException 
	{
		Connection connect = jdbc_connection.get_Connection();

		if(connect == null || connect.isClosed())
		{
			jdbc_connection.get_Connection(); /* Ensure connection is active */
		}

		/* Checks if the column is a PrimaryKey */
		if((!delete_specific_or_every_record) && (key_constraint_service.is_Column_a_PrimaryKey(column_in_Table, Table_Name)))
		{
			throw new RuntimeException("Error: cannot delete record because '" + column_in_Table + "' is a PrimaryKey.");
		}

		/* Checks if the column is a ForeignKey */
		if((!delete_specific_or_every_record) && (key_constraint_service.is_Column_a_ForeignKey(column_in_Table, Table_Name)))
		{
			throw new RuntimeException("Error: cannot delete record because '" + column_in_Table + "' is a ForeignKey.");
		}

		/* Checks if PrimaryKey value is referenced in another table. */
		if((!delete_specific_or_every_record) && (key_constraint_service.is_PrimaryKey_Referenced_in_Other_Tables(column_in_Table)))
		{
			throw new RuntimeException("Error: Cannot delete record because it is referenced as ForeignKey in another table.");
		}

		/* Checks if ForeignKey value is referenced in another table. */
		if((!delete_specific_or_every_record) && (key_constraint_service.is_Value_Referenced_as_ForeignKey(column_in_Table, value_of_column, Table_Name)))
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

		try (	Connection connection = jdbc_connection.get_Connection(); /* Establishing Connection. */
				PreparedStatement prep = connection.prepareStatement(delete_query);
			)
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
	/* This method removes a record based on a user-defined condition. */
	public void Simple_DELETE_Record_Method() throws SQLException
	{
        Scanner sc = new Scanner(System.in);
        Connection connect = jdbc_connection.get_Connection();

        System.out.print("Enter the table name: ");
        String tableName = sc.nextLine();

        System.out.print("Enter the column name to match (WHERE clause): ");
        String matchColumn = sc.nextLine();

        System.out.print("Enter the value to match for deletion: ");
        String matchValue = sc.nextLine();

        String deleteQuery = "DELETE FROM " + tableName + " WHERE " + matchColumn + " = ?";

        try (PreparedStatement stmt = connect.prepareStatement(deleteQuery)) 
		{
            stmt.setString(1, matchValue);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) 
			{
                System.out.println("✅ Record deleted successfully.");
            } 
			else
			{
                System.out.println("⚠ No matching record found to delete.");
            }
        } 
		catch (SQLException e) 
		{
            System.err.println("❌ Delete failed: " + e.getMessage());
        }
    }
	
	
	
	/* Method to create a New Table in the Database */
	@Override
	public void _CREATE_NEW_Table(String new_table_Name, List<String> columns, String primaryKey, String foreignKey, String referenced_Table, String referenced_Column) throws Throwable 
	{
		Connection connect = jdbc_connection.get_Connection();
		
		StringBuilder create_query;

		/* Start of CREATE query */
		create_query = new StringBuilder("CREATE TABLE " + new_table_Name + " (");

		/* Add column definitions */
		for(int i = 0; i < columns.size(); i++)
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
	/* This method builds a table interactively, prompting the user to define each column’s name, type, size, nullability, and constraints. */
	public void Simple_Create_Table_Method() throws SQLException 
	{
        Scanner sc = new Scanner(System.in);
        Connection connect = jdbc_connection.get_Connection();

        System.out.print("Enter Table Name to be created: ");
        String Table_Name = sc.nextLine();

        if(Table_Name.isEmpty())
        {
            System.err.println("Table Name cannot be Empty.");
            sc.close();
            return;
        }

        System.out.print("Enter the number of Columns you want in the table: ");
        int number_Of_Columns = sc.nextInt();
        sc.nextLine(); // Consume newline

        StringBuilder create_Table_Query = new StringBuilder("CREATE TABLE " + Table_Name + " (");

        for (int i = 1; i <= number_Of_Columns; i++) 
        {
            System.out.print("Enter name of column " + i + ": ");
            String column_name = sc.nextLine();

            System.out.print("Enter data type for column " + column_name + ": ");
            String data_type = sc.nextLine().toUpperCase();

            String size_input = "";
            if(data_type.equalsIgnoreCase("VARCHAR") || data_type.equalsIgnoreCase("CHAR") || data_type.equalsIgnoreCase("NVARCHAR")) 
            {
                System.out.print("Enter size/length for column " + column_name + ": ");
                size_input = sc.nextLine();
            }

            System.out.print("Allow NULL values for " + column_name + "? (yes/no): ");
            String null_allowed = sc.nextLine();

            System.out.print("Set column " + column_name + " as Primary Key? (yes/no): ");
            String primary_key = sc.nextLine();

            create_Table_Query.append(column_name).append(" ").append(data_type);

            if (!size_input.isEmpty()) 
            {
                create_Table_Query.append("(").append(size_input).append(")");
            }

            if ("no".equalsIgnoreCase(null_allowed)) 
            {
                create_Table_Query.append(" NOT NULL");
            }

            if ("yes".equalsIgnoreCase(primary_key)) 
            {
                create_Table_Query.append(" PRIMARY KEY");
            }

            if (i != number_Of_Columns) 
            {
                create_Table_Query.append(", ");
            }
        }

        create_Table_Query.append(")");

        try (PreparedStatement stmt = connect.prepareStatement(create_Table_Query.toString()))
        {
            stmt.executeUpdate();
            System.out.println("✅ Table created successfully.");
        }
        catch (SQLException e) 
        {
            System.err.println("❌ Failed to create table: " + e.getMessage());
        }
        sc.close();
    }
	
}
