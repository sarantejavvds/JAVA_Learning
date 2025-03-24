package practice_programs._3_String_based_Programs;

public class _Performing_Functions_on_Different_Strings 
{

	public _Performing_Functions_on_Different_Strings() 
	{
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		String string_1, string_2, string_3,string_4;
		boolean check_equal;
		
		string_1 = "Google";
		string_2 = "Google";
		
	    check_equal =  string_1.equals(string_2);
		System.out.println("Check Both strings using equals() : " + check_equal);
		
		string_3 = "google";
		string_4 = "Google";
		
		check_equal =  string_3.equals(string_4);
		System.out.println("Check Both strings using equals() : " + check_equal);
		
		check_equal =  string_3.equalsIgnoreCase(string_4);
		System.out.println("Check Both strings using equalsIgnoreCase() : " + check_equal);
		
		String expected_url, actual_url;
		
		expected_url = "gmail";		
	    actual_url = "https://workspace.google.com/intl/en-US/gmail/";
		
		boolean is_actual = actual_url.contains(expected_url);
		System.out.println("was Expected founded in Actual? using contains() : " + is_actual);
		
		expected_url = "GmAil";		
	    actual_url = "https://workspace.google.com/intl/en-US/gmail/";
	    
	    is_actual = actual_url.contains(expected_url);
		System.out.println("was Expected founded in Actual? using contains() : " + is_actual);
		

		String str1,str2;
		
		str1 = "A";
		str2 = "A";		
		System.out.println(str2.compareTo(str1)); //output 0
		
		str1 = "A";
		str2 = "C";		
		System.out.println(str2.compareTo(str1)); //output 1
		
		str1 = "B";
		str2 = "A";		
		System.out.println(str2.compareTo(str1)); //output -1
	}

}
