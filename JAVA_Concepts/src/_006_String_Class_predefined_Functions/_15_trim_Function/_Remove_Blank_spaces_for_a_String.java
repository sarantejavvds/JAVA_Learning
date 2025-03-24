package _006_String_Class_predefined_Functions._15_trim_Function;

public class _Remove_Blank_spaces_for_a_String 
{

	public static void main(String[] args) 
	{
		String s1= "                     Hello "; 
		String s2= "           Hai       ";
		String s3 = "  Good              ";
		
		
		System.out.println(s1.trim() + " " + s2.trim() + " " + s3.trim());
	}

}
