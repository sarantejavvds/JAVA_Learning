package _008_Object_class_Concept._Object_Array_1D;

public class _1D_Object_Array 
{

	public static void main(String[] args)
	{
		Object any[] = new Object[5];
		
		any[0] = "Hai";
		any[1] = 2000;
		any[2] = 34567.65456;
		any[3] = true;
		any[4] = '$';
		
		for (Object each : any) 
		{
			System.out.println(each);
		}

	}

}
