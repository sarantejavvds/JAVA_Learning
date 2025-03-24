package _017_Interface_in_Java._Animal;

public class _Lion  implements Animal
{

	@Override
	public void animal_Eat() 
	{
		System.out.println("Lion eats meat");
		
	}

	@Override
	public void animal_Sleep() 
	{
		System.out.println("Lion Sleeps inside Jungle.");
		
	}

	@Override
	public void animal_Sound() 
	{
		System.out.println("Roar!!!!!");
		
	}

}
