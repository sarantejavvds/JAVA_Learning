package _004_Looping_Statements_in_JAVA._5_Iterator;

import java.util.ArrayList;
import java.util.Iterator;

public class _Iterate_WeekDays_in_ArrayList 
{

	public static void main(String[] args) 
	{
		ArrayList<String> weekDays = new ArrayList<String>(5) ;
		
		weekDays.add("Sunday");
		weekDays.add("Monday");
		weekDays.add("Tuesday");
		weekDays.add("Wednesday");
		weekDays.add("Thursday");
		weekDays.add("Friday");
		weekDays.add("Saturday");

		Iterator<String> iterator_ref = weekDays.iterator();
		
		while(iterator_ref.hasNext())
		{
			String each_day = (String)iterator_ref.next();
			
			System.out.println(each_day);
		}
	}

}
