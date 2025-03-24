package _016_Lambda_Expressions_in_Java;

//User-defined interface
interface Greeting
{
 void greet(String name);
}

public class _example_Lambda_expression
{
 public static void main(String[] args) 
 {
     // Lambda expression to implement the Greeting interface
     Greeting greeting = (name) -> System.out.println("Hello, " + name + "!");
     
     // Call the greet method using lambda expression
     greeting.greet("Saran"); 
     greeting.greet("Teja");   
 }
}
