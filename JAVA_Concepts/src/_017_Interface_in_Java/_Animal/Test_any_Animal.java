package _017_Interface_in_Java._Animal;

public class Test_any_Animal 
{

	public static void main(String[] args) 
	{
		_Lion lion = new _Lion();
		
		lion.animal_Eat();
		lion.animal_Sleep();
		lion.animal_Sound();
		
		
		_Cat cat = new _Cat();
		
		cat.animal_Eat();
		cat.animal_Sleep();
		cat.animal_Sound();
		
		
		_Dog dog = new _Dog();
		
		dog.animal_Eat();
		dog.animal_Sleep();
		dog.animal_Sound();

	}

}
