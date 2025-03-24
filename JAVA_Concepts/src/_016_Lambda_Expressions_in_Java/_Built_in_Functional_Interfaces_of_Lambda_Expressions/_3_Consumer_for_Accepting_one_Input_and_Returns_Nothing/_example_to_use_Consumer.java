package _016_Lambda_Expressions_in_Java._Built_in_Functional_Interfaces_of_Lambda_Expressions._3_Consumer_for_Accepting_one_Input_and_Returns_Nothing;

import java.util.function.Consumer;

public class _example_to_use_Consumer 
{

	public static void main(String[] args)
	{
		Consumer<String> printMessage = message -> System.out.println("Message: " + message);
        printMessage.accept("Hello, Java!");

	}

}
