package _016_Lambda_Expressions_in_Java._Built_in_Functional_Interfaces_of_Lambda_Expressions._4_Supplier_for_Accepts_No_Input_but_Returns_a_value_Randomly;

import java.util.function.Supplier;

public class _example_to_use_Supplier 
{

	public static void main(String[] args)
	{
		Supplier<Double> randomValue_1 = () -> Math.random();
        System.out.println(randomValue_1.get());
        
	}

}
