package __Object_Oriented_Programming_Features._3_Polymorphism._METHOD_OVERLOADING._Example_of_Calculator;

public class main_test {

	public static void main(String[] args)
	{
		_CALCULATOR calulator = new _CALCULATOR();

		double addition_result =  calulator.addition(1.0, 2.1 , 3.0, 4.0);
		System.out.println("Addition result : " + addition_result);
	}

}
