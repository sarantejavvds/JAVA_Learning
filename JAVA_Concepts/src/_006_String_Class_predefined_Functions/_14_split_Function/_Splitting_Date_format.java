package _006_String_Class_predefined_Functions._14_split_Function;

public class _Splitting_Date_format
{

	public static void main(String[] args) 
	{
       String date = "07/05/2000";
		
		String[] temp = date.split("/");
		 
		String day = temp[0];
		String month = temp[1];
		String year = temp[2];
		
		System.out.println(day);
		System.out.println(month);
		System.out.println(year);

	}

}
