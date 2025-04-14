package __Object_Oriented_Programming_Features._2_Abstraction._METHOD_OVERRIDING._Example_on_Birds;

public class _Crow extends _BIRDs
{

	public  void _Flying()
	{
		System.out.println("Crow can fly");
	}
	
	@Override
	public void _Sounds() 
	{
		System.out.println("caw caw");
	}
	
}
