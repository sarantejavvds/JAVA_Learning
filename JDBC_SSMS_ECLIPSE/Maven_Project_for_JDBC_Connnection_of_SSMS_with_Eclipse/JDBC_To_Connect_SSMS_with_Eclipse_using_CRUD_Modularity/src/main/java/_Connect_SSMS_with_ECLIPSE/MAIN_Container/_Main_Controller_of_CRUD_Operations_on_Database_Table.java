package _Connect_SSMS_with_ECLIPSE.MAIN_Container;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import _Connect_SSMS_with_ECLIPSE._Connection_Utilities.JDBC_Connection_Manager;
import _Connect_SSMS_with_ECLIPSE._Connection_Utilities.MSSQL_Server_Connection_Manager;
import _Connect_SSMS_with_ECLIPSE._DATABASE_OPERATIONS.Database_CRUD_Operations;
import _Connect_SSMS_with_ECLIPSE._DATABASE_OPERATIONS.MSSQL_Server_CRUD_Operations;
import _Connect_SSMS_with_ECLIPSE._Input_Validations.Input_Validator;
import _Connect_SSMS_with_ECLIPSE._Input_Validations.MSSQL_Server_INPUT_Validator;
import _Connect_SSMS_with_ECLIPSE._METADATA_Getter_Functions.MSSQL_Server_MetaDATA_Service;
import _Connect_SSMS_with_ECLIPSE._METADATA_Getter_Functions.MetaData_Service;
import _Connect_SSMS_with_ECLIPSE._SQL_Key_Constraints_Operations.KEY_Constraint_Service;
import _Connect_SSMS_with_ECLIPSE._SQL_Key_Constraints_Operations.MSSQL_Server_KEY_Constraint_Service;


public class _Main_Controller_of_CRUD_Operations_on_Database_Table 
{

	public static long start_Time;
	public static String Date_Time_Stamp;
	
	public static void main(String[] args) throws SQLException 
	{

		start_Time = System.currentTimeMillis(); /* Capture start time */

		Date_Time_Stamp = new SimpleDateFormat("dd/MM/yyyy__HH:mm:ss").format(new Date());

		JDBC_Connection_Manager jdbc_connection = new MSSQL_Server_Connection_Manager();
		Input_Validator input_validation = new MSSQL_Server_INPUT_Validator();
		MetaData_Service metadata_service = new MSSQL_Server_MetaDATA_Service();
		KEY_Constraint_Service key_constraint_service = new MSSQL_Server_KEY_Constraint_Service();
		Database_CRUD_Operations database_CRUD_operations = new MSSQL_Server_CRUD_Operations();

		Connection connect = jdbc_connection.get_Connection(); /* Trying to establish the connection Immediately. */

		try
		{
			metadata_service.Display_All_Tables_in_Database(); /* Displays all available Tables in the Database after successful Connection. */
		}
		catch (Exception e)
		{
			System.err.println("Failed to establish a connection to the Database.");

			System.err.println("Please check the Database password in Environment Variable.");

			System.exit(1);
		}

		Scanner scanner = new Scanner(System.in); /* Declaring "scanner" object to read Runtime input Data from JAVA Console */

		while (true) /* To continuously ask until Correct Table is given */
		{
			if(connect == null || connect.isClosed())
			{
				connect = jdbc_connection.get_Connection(); /* Ensure connection is active */
			}
			/* Ask the user to input a Table Name */
			System.out.print("Enter the name of the table you want to work with: ");
			String TABLE_Name = scanner.next().trim();
			scanner.nextLine();

			/* Validate if the Table is exist in database or not */
			if(!metadata_service.is_Table_exists(TABLE_Name))
			{
				System.err.println("Error : Table '" + TABLE_Name + "' doesn't Exists in the Current Database.");

				System.out.print("Again .... ");

				continue;
			}

			System.out.println("Successfully selected Table: " + TABLE_Name);

			/* Start of the CRUD Menu */
			while(true)
			{
				System.out.print("\n---CRUD MENU---\n");

				System.out.print(" s ==> Select to Retrieve the Entire Table Data or Specific Record(s).\n");
				System.out.print(" i ==> Insert the Record.\n");
				System.out.print(" u ==> Update the Choosen Value in the Column.\n");
				System.out.print(" d ==> Delete the Choosen Record(s) or Entire Records.\n");
				System.out.print(" e ==> Exit \n");

				System.out.print("CHOOSE any option from CRUD menu : ");
				String single_character_input = scanner.next(); /* Read user choice of CRUD Menu. */
				scanner.nextLine();

				/* Check if the input is a single character */
				if(single_character_input.length() == 1)
				{
					char choice = single_character_input.charAt(0); /* Extract the character */

					/* Check for valid menu option */
					if((choice >= 'a') && (choice <= 'z'))
					{
						CRUD_Operation operation = CRUD_Operation_Selector.get_Operation(choice);
						
						if (operation != null)
						{
						    operation.Execute_Operation(TABLE_Name, scanner, jdbc_connection, input_validation, metadata_service, key_constraint_service, database_CRUD_operations);
						} 
						else 
						{
						    System.err.println("Invalid Option! Choose the Correct Necessary Operation.");
						}
					}
			        else
			        {
				        System.err.println("Invalid Input! Please choose a character from a to z.");
			        }

		        }
		        else
		        {
			        System.err.println("Invalid Input! Please enter exactly one character.");
		        }

	       } /* End of while loop for CRUD MENU. */
				
		} /* Super Loop */
	}

}
