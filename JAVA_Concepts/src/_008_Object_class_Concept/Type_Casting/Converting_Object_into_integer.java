package _008_Object_class_Concept.Type_Casting;

public class Converting_Object_into_integer 
{

	public Converting_Object_into_integer() 
	{
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		Object x = 10;
		
		int value = (int)x;
		
		System.out.println(value);
		
		int same_value = Integer.valueOf(x.toString());
		System.out.println(same_value);
	}

}
