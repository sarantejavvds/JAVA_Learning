package _017_Interface_in_Java._Animal;

public class _Dog  implements Animal
{

	@Override
	public void animal_Eat() 
	{
		System.out.println("Dog eat pedigree");
		
	}

	@Override
	public void animal_Sleep() 
	{
		System.out.println("Dog Sleep in a House.");
		
	}

	@Override
	public void animal_Sound() 
	{
		System.out.println("Bark..");
		
	}

}
