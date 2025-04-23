package _Connect_SSMS_with_ECLIPSE.MAIN_Container;

import java.sql.SQLException;
import java.util.Scanner;

import _Connect_SSMS_with_ECLIPSE._Connection_Utilities.JDBC_Connection_Manager;
import _Connect_SSMS_with_ECLIPSE._DATABASE_OPERATIONS.Database_CRUD_Operations;
import _Connect_SSMS_with_ECLIPSE._DATABASE_OPERATIONS.MSSQL_Server_CRUD_Operations;
import _Connect_SSMS_with_ECLIPSE._Input_Validations.Input_Validator;
import _Connect_SSMS_with_ECLIPSE._METADATA_Getter_Functions.MetaData_Service;
import _Connect_SSMS_with_ECLIPSE._SQL_Key_Constraints_Operations.KEY_Constraint_Service;

public class Calling_SELECT_Operation_Method implements CRUD_Operation 
{

	@Override
	public void Execute_Operation(String TABLE_Name, Scanner scanner, JDBC_Connection_Manager jdbc_connection, Input_Validator input_validation, MetaData_Service metadata_service, KEY_Constraint_Service key_constraint_service, Database_CRUD_Operations database_CRUD_operations) throws SQLException 
	{	
		System.out.println("Do you want to retrieve all records or filter based on a specific column?");
		System.out.println("➡ Type 'all' to retrieve all records.");
		System.out.println("➡ Type 'specific' to retrieve specific records.");
		System.out.println("➡ Type 'cancel' to Cancel the Retrieval operation.");

		System.out.print("\n Please Enter your choice : ");
		String user_Input_to_retrieve = scanner.nextLine().trim().toLowerCase();

		if(user_Input_to_retrieve.equalsIgnoreCase("all")) /* "equalIgnoreCase" ensures that "all"/"ALL"/"All"/"alL"..... all are same. */
		{
			try
			{
				database_CRUD_operations._Select_and_Retrieve_Records(TABLE_Name, true, null, null); /* Retrieves all records */
			}
			catch (RuntimeException e)
			{
				e.printStackTrace();
			}
		}
		else if(user_Input_to_retrieve.equalsIgnoreCase("specific")) /* "equalIgnoreCase" ensures that "specific"/ "SPECIFIC"/ "spEcIFic"/ "SpeCIfiC"... .. all are same. */
		{
			try
			{
				metadata_service.Display_All_Columns_of_the_Table(TABLE_Name);

				System.out.print("Enter the column name from the above list: ");
				String column_name = scanner.nextLine().trim(); /* Provide column name present in the table. */
				/* Accepts Case-insensitive input as well */

				/* Validate the column name */
				if(!metadata_service.is_Column_Exists(column_name, TABLE_Name))
				{

					throw new IllegalArgumentException("Invalid column name: " + column_name);
				}

				metadata_service.Display_Values_of_Each_Column_of_the_Table(column_name, TABLE_Name);

				System.out.print("Enter the value for column '" + column_name + "': ");
				String column_value = scanner.nextLine().trim(); /* give value of that column */
				/* Accepts Case-insensitive input as well */

				/* Validate the Value of column */
				if(!metadata_service.is_Value_of_Column_Exists(column_name, column_value, TABLE_Name))
				{
					throw new IllegalArgumentException("Invalid column value: " + column_value);
				}

				database_CRUD_operations._Select_and_Retrieve_Records(TABLE_Name, false, column_name, column_value); /* Retrieves Specific Records */
			}
			catch (RuntimeException e)
			{
				System.err.println(e.getMessage());
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		else if(user_Input_to_retrieve.equalsIgnoreCase("cancel")) /* "equalIgnoreCase" ensures that "CANCEL"/"cancel" /"CanCel"/ "canCEL"..... all are same. */
		{
			System.out.println("Retrieve operation was cancelled.");
		}
		else
		{
			System.err.println("Invalid input. Please Enter either 'all', 'specific', or 'cancel'.");
		}
	}

}
