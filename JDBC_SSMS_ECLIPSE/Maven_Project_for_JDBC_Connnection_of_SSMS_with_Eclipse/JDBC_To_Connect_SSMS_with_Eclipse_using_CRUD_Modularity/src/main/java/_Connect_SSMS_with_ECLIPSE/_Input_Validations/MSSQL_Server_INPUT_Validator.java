package _Connect_SSMS_with_ECLIPSE._Input_Validations;

public class MSSQL_Server_INPUT_Validator implements Input_Validator
{

	/* Validate input based on column type */
	@Override
	public Object validate_Input_based_on_Column_datatype(String user_input, String column_type, boolean isNullable, String column_size) 
	{
		if(user_input.isEmpty() && !isNullable)
		{
			System.err.println("Error: This field cannot be null.");

			return null;
		}

		if((user_input == null) || (user_input.isEmpty()))
		{
			return (isNullable ? null : "");
		}

		try
		{
			switch(column_type.toUpperCase())
			{
				case "BIT":
					try
					{
						return (user_input.equalsIgnoreCase("true") || user_input.equals("1"));
					}
					catch (IllegalArgumentException e)
					{
						throw new IllegalArgumentException("Error: Invalid Bit value (Expected: 1/0): " + user_input);
					}

				case "BOOLEAN":
					try
					{
						if("true".equalsIgnoreCase(user_input) || "false".equalsIgnoreCase(user_input))
						{
							return Boolean.parseBoolean(user_input);
						}
					}
					catch (IllegalArgumentException e)
					{
						throw new IllegalArgumentException("Error: Invalid Boolean value (Expected: true/false): " + user_input);
					}

				case "BIGINT":
					try
					{
						return Long.parseLong(user_input);
					}
					catch (NumberFormatException e)
					{
						throw new IllegalArgumentException("Error: Invalid BIGINT value: " + user_input);
					}

				case "SMALLINT":
				case "INT":
				case "INTEGER":
					try
					{
						return Integer.parseInt(user_input);
					}
					catch (NumberFormatException e)
					{
						throw new IllegalArgumentException("Error: Invalid integer value: " + user_input);
					}

				case "TEXT":
				case "VARCHAR":
				case "NVARCHAR":
					if(user_input.length() > Integer.parseInt(column_size))
					{
						throw new IllegalArgumentException("Error: Input exceeds allowed length of " + column_size + " characters.");
					}
					return user_input;

				case "CHAR":
					int char_Size = Integer.parseInt(column_size);
					if(user_input.length() > Integer.parseInt(column_size))
					{
						throw new IllegalArgumentException("Error: Input must be exactly " + column_size + " characters.");
					}
					else if(user_input.length() < char_Size)
					{
						user_input = String.format("%-" + char_Size + "s", user_input); /* Pad with spaces if too short */
					}
					return user_input;

				case "DECIMAL":
				case "NUMERIC":
				case "FLOAT":
				case "DOUBLE":
				case "MONEY":
					try
					{
						return Double.parseDouble(user_input);
					}
					catch (NumberFormatException e)
					{
						throw new IllegalArgumentException("Error: Invalid float/decimal value: " + user_input);
					}

				case "DATE":
					try
					{
						return java.sql.Date.valueOf(user_input); /* Format: YYYY-MM-DD */
					}
					catch (IllegalArgumentException e)
					{
						throw new IllegalArgumentException("Error: Invalid Date Format (Expected YYYY-MM-DD): " + user_input);
					}

				case "TIMESTAMP":
				case "DATETIME":
					try
					{
						return java.sql.Timestamp.valueOf(user_input); /* Format: YYYY-MM-DD HH:MM:SS */
					}
					catch (IllegalArgumentException e)
					{
						throw new IllegalArgumentException("Error: Invalid Date Format (Expected YYYY-MM-DD HH:MM:SS): " + user_input);
					}

				default:
					throw new UnsupportedOperationException("Error: Unsupported column datatype: " + column_type + "for input: " + user_input);
			}
		}
		catch (Exception e)
		{
			throw new IllegalArgumentException("Error: Invalid input for type " + column_type + ".");
		}
	}

}
