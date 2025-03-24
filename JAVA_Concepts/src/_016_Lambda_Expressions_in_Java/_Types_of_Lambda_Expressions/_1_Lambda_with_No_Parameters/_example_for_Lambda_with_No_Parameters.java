package _016_Lambda_Expressions_in_Java._Types_of_Lambda_Expressions._1_Lambda_with_No_Parameters;

interface Message_1
{
	void Display_Message();
}

public class _example_for_Lambda_with_No_Parameters
{

	public static void main(String[] args) 
	{
		Message_1 message = () -> { System.out.println("Lambda Expression with No Parameters ......."); };
		
		message.Display_Message();

	}

}
