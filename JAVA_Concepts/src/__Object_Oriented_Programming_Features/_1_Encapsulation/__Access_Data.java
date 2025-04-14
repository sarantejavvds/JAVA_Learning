package __Object_Oriented_Programming_Features._1_Encapsulation;

public class __Access_Data 
{

	public static void main(String[] args)
	{
		_PERSON_class person = new _PERSON_class();
		
		person.set_ID("500");
		person.set_Name("Saran");
		person.set_Email("qwertyyyyi@gmail.com");
		person.set_Phone_Number("567456450");
		person.set_is_Alive(true);
		
		System.out.println("Person info: ");
		System.out.println("Id : " + person.get_ID());
		System.out.println("Name : " + person.get_Name());
		System.out.println("Email : " + person.get_Email());
		System.out.println("Phone : " + person.get_Phone_Number());
		System.out.println("Alive : " + person.get_is_Alive());
	}

}
