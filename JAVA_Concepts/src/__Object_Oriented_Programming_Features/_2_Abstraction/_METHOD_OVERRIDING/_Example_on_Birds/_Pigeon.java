package __Object_Oriented_Programming_Features._2_Abstraction._METHOD_OVERRIDING._Example_on_Birds;

public class _Pigeon extends _BIRDs 
{
	public  void _Flying()
	{
		System.out.println("Pigeon can fly");
	}

	@Override
	public void _Sounds() 
	{
		System.out.println("coo coo");
	}

}
