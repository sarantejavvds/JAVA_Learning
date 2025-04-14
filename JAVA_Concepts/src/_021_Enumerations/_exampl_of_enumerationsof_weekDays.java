package _021_Enumerations;

public class _exampl_of_enumerationsof_weekDays 
{
	enum Week_Days
	{
		SUNDAY,  MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
    }
	
	public static void main(String[] args) 
	{
		for (Week_Days day : Week_Days.values())
		{
			System.out.println(day);
		}

	}

}
