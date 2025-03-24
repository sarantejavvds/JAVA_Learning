package _003_Operators._04_Logical_Operators;

public class _example_on_Logical_Operators 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		int a = 200, b = 500, c = 200;
		
		System.out.println( (a == c) && (c != b) );
		
		System.out.println( (a == b) && (c > b) );
		
		System.out.println( (a == c) || (c != b) );
		
		System.out.println( (a != c) || (c != b) );
		
		System.out.println( !(a == b) );
		
		System.out.println( !(a == c) );
		
	}

}
