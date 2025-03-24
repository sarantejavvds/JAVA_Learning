package _008_Object_class_Concept.Type_Casting;

public class Converting_Object_into_String 
{

	public Converting_Object_into_String() 
	{
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		Object a = "Saran";
		
		Object b = "Teja";
		
		/*Method-1*/
		String Name_1 = a.toString();
		System.out.println(Name_1);
		
		/*Method-2*/
		String Name_2 = (String)b;
		System.out.println(Name_2);
	}

}
