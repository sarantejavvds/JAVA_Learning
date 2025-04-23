package _Connect_SSMS_with_ECLIPSE._DATABASE_OPERATIONS;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
 * Consists of All methods that perform actual data modification or table creation, like:
Create table
Insert record
Update record
Delete record
Select/Display records 
 */

public interface Database_CRUD_Operations
{
	
	public void _INSERT_Record(String Table_Name, List<Map<String, String>> columns, Scanner scanner) throws SQLException;
	
	public void _Select_and_Retrieve_Records(String Table_Name, boolean retrieve_specific_or_Every_Record, String column_in_Table, String value_of_Column) throws SQLException;
	
	public void _UPDATE_TABLE_Data(String Table_Name, boolean update_every_row_value_or_based_on_column, String column_to_perform_Update_operation, String new_Value_of_selected_Column, String existing_other_Column_Name_in_Table, String existing_Value_of_that_other_column) throws SQLException;
	
	public void _Delete_Table_Data(String Table_Name, boolean delete_specific_or_every_record, String column_in_Table, String value_of_column) throws SQLException;
	
	public void _CREATE_NEW_Table(String new_table_Name, List<String> columns, String primaryKey, String foreignKey, String referenced_Table, String referenced_Column) throws Throwable;
	
}
