package _007_Arrays_Concept._1_One_Dimensional_Array._Programs;

import java.util.Scanner;

public class _Print_Element_based_on_its_Position
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
        
        System.out.println("Length of Array: " + numbers.length);

        System.out.print("Enter the Position number: ");
        int position = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("Element at position '" + position + "' is " + numbers[position]);
        
        scanner.close();
	}

}
