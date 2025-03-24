package _016_Lambda_Expressions_in_Java._Using_Lambdas_in_Collections._1_using_foreach;

import java.util.Arrays;
import java.util.List;

public class _example_of_Lambda_expression_using_ForEach_Loop 
{
    public static void main(String[] args) 
    {
    	List<String> names = Arrays.asList("Saran", "Teja", "Sai", "Durga", "Venkata");
        
        System.out.println("Names in a List: ");
        names.forEach(name -> System.out.println(name));
    }
}
