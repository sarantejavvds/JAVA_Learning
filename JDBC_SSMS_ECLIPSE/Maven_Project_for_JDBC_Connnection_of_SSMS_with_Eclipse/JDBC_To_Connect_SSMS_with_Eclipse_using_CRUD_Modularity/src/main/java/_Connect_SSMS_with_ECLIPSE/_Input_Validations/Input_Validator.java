package _Connect_SSMS_with_ECLIPSE._Input_Validations;


/*
 * Logic that validates user input against:
 * (1.)data types (e.g., INT, VARCHAR, DATE),
 * (2.)nullability,
 * (3.)column sizes,
 * ...should be grouped together. 
 * These checks are reusable across insert/update operations
 */

public interface Input_Validator 
{
	
	public Object validate_Input_based_on_Column_datatype(String user_input, String column_type, boolean isNullable, String column_size);
	
}
