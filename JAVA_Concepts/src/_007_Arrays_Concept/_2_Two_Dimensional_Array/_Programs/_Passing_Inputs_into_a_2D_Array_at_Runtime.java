package _007_Arrays_Concept._2_Two_Dimensional_Array._Programs;

import java.util.Scanner;

public class _Passing_Inputs_into_a_2D_Array_at_Runtime 
{

	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter the number of columns: ");
        int columns = scanner.nextInt();

        int[][] Number_2D = new int[rows][columns];

        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < rows; i++)
        {
        	
            for (int j = 0; j < columns; j++) 
            {
            	System.out.print("Element at row '" + (i + 1) + "' " + "column '" + (j + 1) + "' : ");
                Number_2D[i][j] = scanner.nextInt();
            }
        }

        System.out.println("The Number 2D array :");
        for (int i = 0; i < rows; i++) 
        {
            for (int j = 0; j < columns; j++)
            {
                System.out.print(Number_2D[i][j] + " ");
            }
            System.out.println();
        }
        scanner.close();

	}

}
