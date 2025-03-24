package _011_Classes_for_different_world_Scenarios._Car_as_Class;

import java.util.Scanner;

public class Test_a_CAR 
{

	public Test_a_CAR() 
	{
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		CAR car_object = new CAR();
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter the name of the CAR: ");
		car_object.Name_of_CAR = scanner.next();
		scanner.nextLine();
		
		System.out.print("Enter the colour of the CAR: ");
		car_object.Colour_of_CAR = scanner.next();
		scanner.nextLine();
		
		System.out.print("Enter the price of the CAR: ");
		car_object.price_of_CAR = scanner.nextDouble();
		scanner.nextLine();
		
		System.out.println();
		System.out.println("Name of CAR was " + car_object.Name_of_CAR);
		System.out.println("Colour of CAR is " + car_object.Colour_of_CAR);
		System.out.println("Price of CAR is " + car_object.price_of_CAR);
		System.out.println("This " + car_object.Name_of_CAR + " CAR has " + CAR.number_of_Wheels + " Wheels." );
		
		System.out.println("\nTesting the CAR : \n");
		
		CAR.Start_the_CAR();
		
		car_object.Invoke_Brakes_of_CAR();
		
		CAR.Stop_the_CAR();
		
		scanner.close();
	}

}
