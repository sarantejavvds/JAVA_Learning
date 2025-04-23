package _Connect_SSMS_with_ECLIPSE._SQL_Key_Constraints_Operations;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/*
 * Consits of methods that:
 * (1.)Checks or validates Primary/Foreign Key
 * (2.)Confirms if a key exists or is referenced
 * (3.)Compares key compatibility across tables
 */

public interface KEY_Constraint_Service 
{

	public boolean is_PrimaryKey_Available(String TableName, String primaryKeyColumn, Object value) throws SQLException;
	
	public boolean is_PrimaryKey_Available(String TableName, String primaryKey_Column, String primaryKey_value) throws SQLException;
	
	public boolean is_PrimaryKey_Referenced_in_Other_Tables(String primary_key_Column) throws SQLException;
	
	public boolean is_Column_a_PrimaryKey(String column_Name, String Table_Name) throws SQLException;
	
	public boolean is_Column_has_Primary_Key(String column_To_Check, String Table_Name) throws SQLException;
	
	public Map<String, String> find_PrimaryKey_In_Other_Tables(String column_Name) throws SQLException;
	
	public boolean is_Column_a_ForeignKey(String column_Name, String Table_Name) throws SQLException;
	
	public boolean is_Value_Referenced_as_ForeignKey(String columnName, String columnValue, String tableName) throws SQLException;
	
	public boolean is_Referenced_Value_Primary_Key_Exists(String primaryKey_Column, Object primaryKey_Value, String Table_Name) throws SQLException;
	
	public boolean is_Referenced_Value_Foreign_Key_Exists(String column_Name, Object value, String Table_Name) throws SQLException;
	
	public boolean is_Referenced_Value_Foreign_Key_Exists(String fk_column_Name, String fk_value, String Table_Name) throws SQLException;
	
	public boolean is_Referenced_Column_exists_in_Referenced_Table(String referenced_Column, String referenced_Table) throws SQLException;
	
	public boolean is_PrimaryKey_Matching_Existing_ForeignKey(String primaryKey_Column, String dataType) throws SQLException;
	
	public boolean is_ForeignKey_Matching_Existing_PrimaryKey(String referenced_Column, String referenced_Table, String foreignKey_Column, String Table_Name) throws SQLException;
	
	public String get_Referenced_Table_Name(String columnName, String tableName) throws SQLException;
	
	public List<String> get_Valid_ForeignKey_Values(String columnName, String tableName) throws SQLException;
	
}
