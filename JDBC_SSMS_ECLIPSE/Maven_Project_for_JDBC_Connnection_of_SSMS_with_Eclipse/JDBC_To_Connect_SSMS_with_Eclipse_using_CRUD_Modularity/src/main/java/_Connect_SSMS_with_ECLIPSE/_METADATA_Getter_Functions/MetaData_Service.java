package _Connect_SSMS_with_ECLIPSE._METADATA_Getter_Functions;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;


/*
 * The methods Deals with: 
 * (1.)fetching column names,
 * (2.)checking if a column or table exists,
 * (3.)retrieving metadata like column types, sizes, constraints, keys, etc., 
 */ 

public interface MetaData_Service 
{
	
	public boolean is_Value_of_Column_Exists(String column_in_Table, String value_of_column, String Table_Name) throws SQLException;
	
	public boolean is_Column_Exists(String column_Name, String Table_Name) throws SQLException;
	
	public boolean is_Table_exists(String Table_Name) throws SQLException;
	
	public boolean is_Column_Matching_DataType(String columnName, String tableName, String dataType) throws SQLException;
	
	public int get_Column_Length(String column_Name, String Table_Name) throws SQLException;
	
	public Map<String, String> _get_ColumnConstraints(String Table_Name) throws SQLException;
	
	public  Map<String, int[]> get_Column_Constraints(String Table_Name) throws SQLException;
	
	public List<Map<String, String>> get_Table_Metadata_to_obtain_Column_info(String Table_Name) throws SQLException;
	
	public void Display_Values_of_Each_Column_of_the_Table(String column_name, String Table_Name);
	
	public void Display_All_Columns_of_the_Table(String Table_Name);
	
	public void Display_All_Tables_in_Database() throws SQLException;
	
}
