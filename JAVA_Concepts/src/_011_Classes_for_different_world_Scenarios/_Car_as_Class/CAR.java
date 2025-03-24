package _011_Classes_for_different_world_Scenarios._Car_as_Class;

public class CAR 
{

	String Name_of_CAR;
	
	String Colour_of_CAR;
	
	double price_of_CAR;
	
	static int number_of_Wheels = 4;
	
	
	public CAR() 
	{
		// TODO Auto-generated constructor stub
	}

	static void Start_the_CAR()
	{
		System.out.println("CAR was Started");
	}
	
	void Invoke_Brakes_of_CAR()
	{
		System.out.println("Brakes Pressed ...");
	}
	
	static void Stop_the_CAR()
	{
		System.out.println("CAR was Stopped");
	}
	
/*	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub	
	} */

}
