package _Connect_SSMS_with_ECLIPSE.MAIN_Container;

import java.sql.SQLException;
import java.util.Scanner;

import _Connect_SSMS_with_ECLIPSE._Connection_Utilities.JDBC_Connection_Manager;
import _Connect_SSMS_with_ECLIPSE._DATABASE_OPERATIONS.Database_CRUD_Operations;
import _Connect_SSMS_with_ECLIPSE._Input_Validations.Input_Validator;
import _Connect_SSMS_with_ECLIPSE._METADATA_Getter_Functions.MetaData_Service;
import _Connect_SSMS_with_ECLIPSE._SQL_Key_Constraints_Operations.KEY_Constraint_Service;

public class Calling_UPDATE_Operation_Method implements CRUD_Operation 
{

	@Override
	public void Execute_Operation(String TABLE_Name, Scanner scanner, JDBC_Connection_Manager jdbc_connection, Input_Validator input_validation, MetaData_Service metadata_service, KEY_Constraint_Service key_constraint_service, Database_CRUD_Operations database_CRUD_operations) throws SQLException
	{
		String column_To_Update, new_Value, fetch_Column, fetch_Value, proceed_or_not;

		System.out.println("Do you want to update all values in the specified column or update based on a specific column?");
		System.out.println("➡ Type 'all' to update all values of the column.");
		System.out.println("➡ Type 'specific' to update specific value of the specified column.");
		System.out.println("➡ Type 'cancel' to cancel the Update operation.");

		System.out.print("\n Please Enter your choice : ");
		String user_Input_to_update = scanner.next().trim().toLowerCase();
		scanner.nextLine();

		if(user_Input_to_update.equalsIgnoreCase("all")) /* "equalIgnoreCase" ensures that "all"/"ALL"/"All"/"alL"..... all are same. */
		{
			try
			{
				metadata_service.Display_All_Columns_of_the_Table(TABLE_Name);

				do
				{
					System.out.print("Enter the column name you want to update: ");
					column_To_Update = scanner.nextLine().trim();
					if(column_To_Update.isEmpty())
					{
						System.err.println("Column cannot be Empty. Please enter a valid column Name.");
					}
				}
				while (column_To_Update.isEmpty());

				if(!metadata_service.is_Column_Exists(column_To_Update, TABLE_Name))
				{
					throw new IllegalArgumentException("Invalid column name: " + column_To_Update);
				}

				do
				{
					System.out.print("Enter the new value for the column to Update the existed value: ");
					new_Value = scanner.nextLine().trim();
					if(new_Value.isEmpty())
					{
						System.err.println("Value cannot be Empty. Please enter a valid Value.");
					}
				}
				while (new_Value.isEmpty());

				database_CRUD_operations._UPDATE_TABLE_Data(TABLE_Name, true, column_To_Update, new_Value, null, null); /* Updates all Rows of that column, since where clause is not there to filter. */
			}
			catch (RuntimeException e)
			{
				e.printStackTrace();
			}
		}
		else if(user_Input_to_update.equalsIgnoreCase("specific")) /* "equalIgnoreCase" ensures that "specific"/ "SPECIFIC"/ "spEcIFic"/ "SpeCIfiC"... .. all are same. */
		{
			try
			{
				metadata_service.Display_All_Columns_of_the_Table(TABLE_Name);

				do
				{
					System.out.print("Enter the column name you want to update: ");
					column_To_Update = scanner.nextLine().trim();
					if(column_To_Update.isEmpty())
					{
						System.err.println("Column cannot be Empty. Please enter a valid column Name.");
					}
				}
				while (column_To_Update.isEmpty());

				if(!metadata_service.is_Column_Exists(column_To_Update, TABLE_Name))
				{
					throw new IllegalArgumentException("Invalid column name: " + column_To_Update);
				}

				do
				{
					System.out.print("Enter the new value for the column to overwrite the previous value: ");
					new_Value = scanner.nextLine().trim(); /* Give The new Value to overwrite the previous value. */
					if(new_Value.isEmpty())
					{
						System.err.println("Value cannot be Empty. Please enter a valid Value.");
					}
				}
				while (new_Value.isEmpty());

				/**
				 * Here Fetch records filtered by WHERE clause
				 **/
				metadata_service.Display_All_Columns_of_the_Table(TABLE_Name);

				do
				{
					System.out.print("Enter the column name to fetch the particular record based on that for which record's column we want to overwrite the value : ");
					fetch_Column = scanner.nextLine().trim();
					if(fetch_Column.isEmpty())
					{
						System.err.println("Where Clause column cannot be empty. Please Enter a valid column.");
					}
				}
				while (fetch_Column.isEmpty());

				if(!metadata_service.is_Column_Exists(fetch_Column, TABLE_Name))
				{
					throw new IllegalArgumentException("Invalid WHERE column: " + fetch_Column);
				}

				metadata_service.Display_Values_of_Each_Column_of_the_Table(fetch_Column, TABLE_Name);

				System.out.println("Please provide the column of the record ");
				do
				{
					System.out.print("Enter the value for the column that need to be fetched for particular record(s) : ");
					fetch_Value = scanner.nextLine().trim();
					if(fetch_Value.isEmpty())
					{
						System.err.println("Where Clause value cannot be empty. Please Enter a valid value.");
					}
				}
				while (fetch_Value.isEmpty());

				System.out.println("These are the Record(s) that got filtered, These will get updated after Update Operation.");
				database_CRUD_operations._Select_and_Retrieve_Records(TABLE_Name, false, fetch_Column, fetch_Value);

				System.out.println("\nThese are the Record(s) currently exists before Updating value.");

				do
				{
					System.out.print("Are you sure you want to modify the data in this particular field (YES/NO): ");
					proceed_or_not = scanner.nextLine().trim();
					if(!proceed_or_not.equalsIgnoreCase("YES") && !proceed_or_not.equalsIgnoreCase("NO"))
					{
						System.err.println("Invalid input! Please enter either 'YES' or 'NO'.");
					}
				}
				while (!proceed_or_not.equalsIgnoreCase("YES") && !proceed_or_not.equalsIgnoreCase("NO"));

				if(proceed_or_not.equalsIgnoreCase("YES"))
				{
					System.out.println("Proceeding with Update Operation.");
					database_CRUD_operations._UPDATE_TABLE_Data(TABLE_Name, false, column_To_Update, new_Value, fetch_Column, fetch_Value);
				}
				else
				{
					System.out.println("Update operation was stopped.");
				}
			}
			catch (RuntimeException e)
			{
				e.printStackTrace();
			}
		}
		else if(user_Input_to_update.equalsIgnoreCase("cancel")) /* "equalIgnoreCase" ensures that "CANCEL"/"cancel" /"CanCel"/ "canCEL"..... all are same. */
		{
			System.out.println("Update operation was cancelled.");
		}
		else
		{
			System.err.println("Invalid Input ! Please Enter either 'all', 'specific', or 'cancel'.");
		}
	}

}
