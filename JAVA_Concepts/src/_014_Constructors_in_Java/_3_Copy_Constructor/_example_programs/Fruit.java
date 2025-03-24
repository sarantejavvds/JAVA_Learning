package _014_Constructors_in_Java._3_Copy_Constructor._example_programs;

public class Fruit 
{
	private double fprice;
	private String fname;

	//constructor to initialize price and name of the fruit  
	Fruit(double fPrice, String fName) 
	{
		fprice = fPrice;
		fname = fName;
	}

	//creating a copy constructor  
	Fruit(Fruit fruit) 
	{
		System.out.println("\nAfter invoking the Copy Constructor:\n");
		fprice = fruit.fprice;
		fname = fruit.fname;
	}

	//creating a method that returns the price of the fruit  
	double showPrice() 
	{
		return fprice;
	}

	//creating a method that returns the name of the fruit  
	String showName() 
	{
		return fname;
	}

	public static void main(String args[]) 
	{
		
		Fruit f1 = new Fruit(399, "Ruby Roman Grapes");
		System.out.println("Name of the first fruit: " + f1.showName());
		System.out.println("Price of the first fruit: " + f1.showPrice());
		
		//passing the parameters to the copy constructor  
		Fruit f2 = new Fruit(f1);
		System.out.println("Name of the second fruit: " + f2.showName());
		System.out.println("Price of the second fruit: " + f2.showPrice());
	}
}
