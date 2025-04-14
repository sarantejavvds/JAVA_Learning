package __Object_Oriented_Programming_Features._4_Inheritance._2_Multiple_Inheritance._example_of_Animal;

public class main_Test 
{
	public static void main(String[] args) 
	{
		_Dog dog = new _Dog();
		
		dog._Bark();
		dog._Sleep();
		
		_Puppy puppy = new _Puppy();
		
		puppy._weep();
		puppy._Bark();
		puppy._Sleep();

	}

}
