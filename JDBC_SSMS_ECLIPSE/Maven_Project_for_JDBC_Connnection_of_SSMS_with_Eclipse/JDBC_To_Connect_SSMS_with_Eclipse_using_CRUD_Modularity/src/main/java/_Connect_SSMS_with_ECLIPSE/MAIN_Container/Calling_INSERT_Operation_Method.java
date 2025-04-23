package _Connect_SSMS_with_ECLIPSE.MAIN_Container;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import _Connect_SSMS_with_ECLIPSE._Connection_Utilities.JDBC_Connection_Manager;
import _Connect_SSMS_with_ECLIPSE._DATABASE_OPERATIONS.Database_CRUD_Operations;
import _Connect_SSMS_with_ECLIPSE._Input_Validations.Input_Validator;
import _Connect_SSMS_with_ECLIPSE._METADATA_Getter_Functions.MetaData_Service;
import _Connect_SSMS_with_ECLIPSE._SQL_Key_Constraints_Operations.KEY_Constraint_Service;

public class Calling_INSERT_Operation_Method implements CRUD_Operation 
{

	@Override
	public void Execute_Operation(String TABLE_Name, Scanner scanner, JDBC_Connection_Manager jdbc_connection, Input_Validator input_validation, MetaData_Service metadata_service, KEY_Constraint_Service key_constraint_service, Database_CRUD_Operations database_CRUD_operations) throws SQLException 
	{
		try
		{
			/* Get the column metadata for the table */
			List<Map<String, String>> columns_in_Table = metadata_service.get_Table_Metadata_to_obtain_Column_info(TABLE_Name);

			/* Pass the necessary parameters to the INSERT_Record method */
			database_CRUD_operations._INSERT_Record(TABLE_Name, columns_in_Table, scanner); /* Call the insert method */
		}
		catch (RuntimeException RE)
		{
			System.err.println(RE.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
