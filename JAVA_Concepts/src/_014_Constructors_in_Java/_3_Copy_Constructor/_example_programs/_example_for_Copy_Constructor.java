package _014_Constructors_in_Java._3_Copy_Constructor._example_programs;

public class _example_for_Copy_Constructor 
{

	String _hai = "hai hello Goodmorning";
	
	public _example_for_Copy_Constructor() 
	{
		this._hai = _hai;
	}

	public _example_for_Copy_Constructor(_example_for_Copy_Constructor ob_1)
	{
		 this._hai = ob_1._hai;
	}

	public static void main(String[] args) 
	{
		_example_for_Copy_Constructor ob_1 = new _example_for_Copy_Constructor();
		
		_example_for_Copy_Constructor ob_2 = new _example_for_Copy_Constructor(ob_1);
		
		System.out.println("ob_1._hai: " + ob_1._hai); 
        System.out.println("ob_2._hai: " + ob_2._hai);
	}

}
