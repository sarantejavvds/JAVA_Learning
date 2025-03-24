package _016_Lambda_Expressions_in_Java._Types_of_Lambda_Expressions._3_Lambda_with_Multiple_Parameters;

interface Arithmatic_Operations
{
	int operation(int value_1, int value_2);
}

public class _example_for_Lambda_with_Multiple_Parameters 
{

	public static void main(String[] args) 
	{
		Arithmatic_Operations addition = (value_1, value_2) -> ( value_1 + value_2 );
		
		Arithmatic_Operations substraction = (value_1, value_2) -> ( value_1 - value_2 );
		
		Arithmatic_Operations multiplication = (value_1, value_2) -> ( value_1 * value_2 );
		
		Arithmatic_Operations division = (value_1, value_2) -> ( value_1 / value_2 );
		
		Arithmatic_Operations modulus = (value_1, value_2) -> ( value_1 % value_2 );
		

		
		System.out.println("Addition : " + addition.operation(6, 9));
		
		System.out.println("Substraction : " + substraction.operation(6, 9));
		
		System.out.println("Multiplication : " + multiplication.operation(6, 9));
		
		System.out.println("Division : " + division.operation(6, 9));
		
		System.out.println("Modulus : " + modulus.operation(6, 9));
		
		
		
	}

}
