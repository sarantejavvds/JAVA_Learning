package _007_Arrays_Concept._1_One_Dimensional_Array._Programs;

import java.util.Scanner;

public class _Passing_Inputs_into_a_1D_Array_at_Runtime
{

	public static void main(String[] args)
	{
		 Scanner scanner = new Scanner(System.in);
		    
         System.out.print("Enter the size of the array: ");
         int size = scanner.nextInt();
         
         int[] numbers = new int[size];
 
         System.out.println("Enter the elements of the array:");
         for (int i = 0; i < size; i++) 
         {
             System.out.print("Element " + (i + 1) + ": ");
             numbers[i] = scanner.nextInt();
             scanner.nextLine();
         }
 
         System.out.println("Array elements:");
         for(int number : numbers)
         {
             System.out.print(number + " ");
         }
         
         scanner.close();
	}

}
