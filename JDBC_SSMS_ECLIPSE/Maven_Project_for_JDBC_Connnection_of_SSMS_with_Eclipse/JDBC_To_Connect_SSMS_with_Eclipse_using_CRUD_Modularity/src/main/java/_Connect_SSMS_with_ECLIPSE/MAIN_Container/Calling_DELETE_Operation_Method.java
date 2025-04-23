package _Connect_SSMS_with_ECLIPSE.MAIN_Container;

import java.sql.SQLException;
import java.util.Scanner;

import _Connect_SSMS_with_ECLIPSE._Connection_Utilities.JDBC_Connection_Manager;
import _Connect_SSMS_with_ECLIPSE._DATABASE_OPERATIONS.Database_CRUD_Operations;
import _Connect_SSMS_with_ECLIPSE._Input_Validations.Input_Validator;
import _Connect_SSMS_with_ECLIPSE._METADATA_Getter_Functions.MetaData_Service;
import _Connect_SSMS_with_ECLIPSE._SQL_Key_Constraints_Operations.KEY_Constraint_Service;

public class Calling_DELETE_Operation_Method implements CRUD_Operation 
{

	@Override
	public void Execute_Operation(String TABLE_Name, Scanner scanner, JDBC_Connection_Manager jdbc_connection, Input_Validator input_validation, MetaData_Service metadata_service, KEY_Constraint_Service key_constraint_service, Database_CRUD_Operations database_CRUD_operations) throws SQLException
	{
		System.out.println("Please tell us whether you want to delete All records or any Specific record from the Table? \n");
		System.out.println("➡ type 'all' to proceed with Deleting all the records as input.");
		System.out.println("➡ type 'specific' to proceed with Deleting a specified record as input.");
		System.out.println("➡ type 'cancel' for not to proceed with Deletion operation as input.");

		System.out.print("\n Enter your choice: ");
		String user_Input_to_delete = scanner.nextLine().trim().toLowerCase();

		if(user_Input_to_delete.equalsIgnoreCase("all")) /* "equalIgnoreCase" ensures that "all"/"ALL"/"All"/"alL"..... all are same. */
		{
			try
			{
				System.out.print("You really want to delete all records in the Table? (YES/NO) : ");

				String confirm_to_delete = scanner.nextLine().trim().toLowerCase();

				if(confirm_to_delete.equalsIgnoreCase("YES")) /* "equalIgnoreCase" ensures that "YES"/"yes"/"Yes"/ "yEs"..... all are same. */
				{
					database_CRUD_operations._Delete_Table_Data(TABLE_Name, true, null, null); /* Delete all records */

					System.out.println("All Records have been Successfully Deleted");
				}
				else if(confirm_to_delete.equalsIgnoreCase("NO")) /* "equalIgnoreCase" ensures that "NO"/"no"/ "No"/"nO".... . all are same. */
				{
					System.out.println("Operation cancelled. No records were deleted.");
				}
				else
				{
					System.err.println("Invalid input! Deletion aborted.");
				}
			}
			catch (SQLException e)
			{
				System.err.println("Error while deleting all records: " + e.getMessage());

				e.printStackTrace();

				throw e;
			}
		}
		else if(user_Input_to_delete.equalsIgnoreCase("specific")) /* "equalIgnoreCase" ensures that "specific"/ "SPECIFIC"/ "spEcIFic"/ "SpeCIfiC"... .. all are same. */
		{
			try
			{
				/* Fetch and display all column names */
				metadata_service.Display_All_Columns_of_the_Table(TABLE_Name); /* First Displays all available columns in table */

				System.out.print("Enter te column name from the Above List : ");
				String column_name = scanner.nextLine(); /* Specific column name in given table provided by the user */

				/* Validate the column name */
				if(!metadata_service.is_Column_Exists(column_name, TABLE_Name))
				{
					throw new IllegalArgumentException("Invalid column name: " + column_name);
				}

				/* Fetch and display all unique values in the specified column */
				metadata_service.Display_Values_of_Each_Column_of_the_Table(column_name, TABLE_Name);

				System.out.print("Enter the value of that column '" + column_name + "': ");
				String column_value = scanner.nextLine().trim(); /* Specific value of column in given table provided by the user */

				/* Validate the value */
				if(!metadata_service.is_Value_of_Column_Exists(column_name, column_value, TABLE_Name))
				{
					throw new RuntimeException("No record found with " + column_name + " = " + column_value);
				}

				database_CRUD_operations._Delete_Table_Data(TABLE_Name, false, column_name, column_value);
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
		else if(user_Input_to_delete.equalsIgnoreCase("cancel")) /* "equalIgnoreCase" ensures that "CANCEL"/"cancel" /"CanCel"/ "canCEL"..... all are same. */
		{
			System.out.println("Operation cancelled. No Delete Operation Has been Happened");
		}
		else
		{
			System.err.println("Invalid Input. Please Enter either 'all' or 'specific' or 'cancel'. ");
		}
	}

}
