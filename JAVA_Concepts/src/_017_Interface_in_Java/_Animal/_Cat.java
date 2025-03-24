package _017_Interface_in_Java._Animal;

public class _Cat  implements Animal
{

	@Override
	public void animal_Eat() 
	{
		System.out.println("Cat eat rat");
		
	}

	@Override
	public void animal_Sleep() 
	{
		System.out.println("Cat Sleeps on a wall");
		
	}

	@Override
	public void animal_Sound() 
	{
		System.out.println("Meow..");
		
	}

}
