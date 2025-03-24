/**
 * 
 */
package _011_Classes_for_different_world_Scenarios._Mouse_as_Class;

/**
 * 
 */
public class Test_all_about_Mouse 
{

	/**
	 * 
	 */
	public Test_all_about_Mouse() 
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Mouse md = new Mouse();

		md.mouse_Company = "DELL";
		
		md.mouse_ID = 123;
		
		md.colour = "brown";
		
		md.price_of_Mouse = 100.50f;
		
		System.out.println(md.mouse_Company + " company has created a new Mouse with ID no. " + md.mouse_ID + " and colour of " + md.colour +" for the price of " + md.price_of_Mouse);
		
	}

}
