package _Connect_SSMS_with_ECLIPSE.MAIN_Container;

import java.sql.SQLException;
import java.util.Scanner;

import _Connect_SSMS_with_ECLIPSE._Connection_Utilities.JDBC_Connection_Manager;
import _Connect_SSMS_with_ECLIPSE._DATABASE_OPERATIONS.Database_CRUD_Operations;
import _Connect_SSMS_with_ECLIPSE._Input_Validations.Input_Validator;
import _Connect_SSMS_with_ECLIPSE._METADATA_Getter_Functions.MetaData_Service;
import _Connect_SSMS_with_ECLIPSE._SQL_Key_Constraints_Operations.KEY_Constraint_Service;

public interface CRUD_Operation 
{
	
	public void Execute_Operation(String TABLE_Name, Scanner scanner, JDBC_Connection_Manager jdbc_connection, Input_Validator input_validation, MetaData_Service metadata_service, KEY_Constraint_Service key_constraint_service, Database_CRUD_Operations database_CRUD_operations) throws SQLException;

}
