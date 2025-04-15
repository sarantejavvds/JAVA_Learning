package __Object_Oriented_Programming_Features._4_Inheritance._4_Hybrid_Inheritance._example_of_Vehicle;

public class _ELECTRIC_CAR  extends _CAR
							implements _Electricity, _Maintainablility
{

	@Override
	public void _Service()
	{
		System.out.println("Charging the electric car.");
		
	}

	@Override
	public void _charge_Battery() 
	{
		System.out.println("Servicing the electric car.");
		
	}

}
