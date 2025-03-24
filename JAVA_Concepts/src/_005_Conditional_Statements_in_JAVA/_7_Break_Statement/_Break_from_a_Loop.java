package _005_Conditional_Statements_in_JAVA._7_Break_Statement;

public class _Break_from_a_Loop 
{

 public static void main(String args[]) 
 {
	 int i = 0;
     for (i = 0; i < 10; i++) 
     {
       
         // terminate loop when i is 5
         if (i == 5)
         {
             break;
         }

         System.out.println("i: " + i);
     }
     System.out.println("Loop got breaked at i = "+ i +".");
 }
 
}