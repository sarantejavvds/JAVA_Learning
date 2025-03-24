package _018_StringBuilder_Class._1_When_Repeatedly_Modifying_Strings;

public class _example_of_StringBuilder
{

	public static void main(String[] args)
	{
		StringBuilder sb = new StringBuilder("Java");
		sb.append(" Programming");
		System.out.println(sb); 

		sb.append(" Today");
		
		System.out.println(sb);

	}

}
