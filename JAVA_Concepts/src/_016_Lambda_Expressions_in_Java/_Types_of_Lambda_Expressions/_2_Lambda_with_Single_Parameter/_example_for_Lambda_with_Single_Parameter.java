package _016_Lambda_Expressions_in_Java._Types_of_Lambda_Expressions._2_Lambda_with_Single_Parameter;


interface Square_of_a_Number
{
	int calculate_Square_of_Number(int number);
}

public class _example_for_Lambda_with_Single_Parameter
{

	public static void main(String[] args) 
	{
		Square_of_a_Number number_square = (number -> (number * number) );
		
		int square_number_Result = number_square.calculate_Square_of_Number(5);
		
		System.out.println(square_number_Result);

	}

}
