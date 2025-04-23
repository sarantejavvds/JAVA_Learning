package _Connect_SSMS_with_ECLIPSE.MAIN_Container;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import _Connect_SSMS_with_ECLIPSE._Connection_Utilities.JDBC_Connection_Manager;
import _Connect_SSMS_with_ECLIPSE._DATABASE_OPERATIONS.Database_CRUD_Operations;
import _Connect_SSMS_with_ECLIPSE._Input_Validations.Input_Validator;
import _Connect_SSMS_with_ECLIPSE._METADATA_Getter_Functions.MetaData_Service;
import _Connect_SSMS_with_ECLIPSE._SQL_Key_Constraints_Operations.KEY_Constraint_Service;

import static _Connect_SSMS_with_ECLIPSE.MAIN_Container._Main_Controller_of_CRUD_Operations_on_Database_Table.*;

public class Calling_EXIT_Operation_Method implements CRUD_Operation
{
	public static long end_Time, elapsed_Time, hours, minutes, seconds, milli_seconds;

	@Override
	public void Execute_Operation(String TABLE_Name, Scanner scanner, JDBC_Connection_Manager jdbc_connection, Input_Validator input_validation, MetaData_Service metadata_service, KEY_Constraint_Service key_constraint_service, Database_CRUD_Operations database_CRUD_operations) throws SQLException 
	{
		System.out.println("Exiting from CRUD Menu. \n");

		try 
		{
			TimeUnit.SECONDS.sleep(3);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}

		jdbc_connection.close_Connection();

		end_Time = System.currentTimeMillis(); /* Capture End time of Execution before Exiting. */

		elapsed_Time = end_Time - start_Time; /* Calculate Total Execution Time. */

		hours = TimeUnit.MILLISECONDS.toHours(elapsed_Time);

		minutes = TimeUnit.MILLISECONDS.toMinutes(elapsed_Time) % 60;

		seconds = TimeUnit.MILLISECONDS.toSeconds(elapsed_Time) % 60;

		milli_seconds = elapsed_Time % 1000;

		/* Print Execution Time on Console */
		System.out.printf("\nTotal Execution Time: %02d hrs : %02d min : %02d sec .%03d milliseconds on '"+ Date_Time_Stamp +"' \n", hours, minutes, seconds, milli_seconds);

		/* Delay 5 seconds */
		CompletableFuture.delayedExecutor(5, TimeUnit.SECONDS).execute(() ->
		{
			scanner.close(); /* Close the scanner to avoid resource leak */
		});

		System.exit(0);

	}

}
