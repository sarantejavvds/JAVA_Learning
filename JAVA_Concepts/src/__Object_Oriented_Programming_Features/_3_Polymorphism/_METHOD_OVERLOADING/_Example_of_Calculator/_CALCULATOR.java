package __Object_Oriented_Programming_Features._3_Polymorphism._METHOD_OVERLOADING._Example_of_Calculator;

public class _CALCULATOR 
{
	public int addition(int a, int b)
	{
        return a + b;
    }

	public double addition(double a, double b) 
    {
        return a + b;
    }
	
	// Method to add any number of integers using VarArgs(...)
    public int addition(int... numbers) 
    {
        int sum = 0;
        for(int n : numbers) 
        {
            sum += n;
        }
        
        return sum;
    }
    
    // Method to add any number of doubles using VarArgs(...)
    public double addition(double... numbers) 
    {
        double sum = 0;
        for(double n : numbers) 
        {
            sum += n;
        }
        
        return sum;
    }
	
}
