package __Object_Oriented_Programming_Features._2_Abstraction._METHOD_OVERRIDING._Example_on_Birds;

public class main_test
{

	public static void main(String[] args) 
	{
		_BIRDs _bird, crow, pigeon;
		
		crow = new _Crow();
		pigeon = new _Pigeon();

		crow._Flying();
		pigeon._Flying();
		
		crow._Sounds();
		pigeon._Sounds();
		
		
		_bird = new _BIRDs() {
			                   @Override
			                   public void _Sounds() 
			                   {
				                // TODO Auto-generated method stub
				
			                   }
		                      };
		
		_bird._Flying();
		
	}

}
