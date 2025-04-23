package practice_programs._2_Array_based_Programs;

public class _Program_to_Find_Sum_of_all_Integers_of_particular_column_in_2D_Array {

	public static void main(String[] args) 
	{
		int[][] _2D_Array = { {1,5,6,8}, {3,8,7,4}, {3, 8, 4, 5}, {4,3,2,1}, {2, 1, 9, 5} };
		
		int Sum_of_Second_column_elements = 0;
		
		for(int i=0 ; i < _2D_Array.length ; i++)
		{
			for (int j = 0; j < _2D_Array[i].length; j++)
			{
				if(j == 1)
				{
				    Sum_of_Second_column_elements += _2D_Array[i][1];
				}
			}
		}

		System.out.println("Sum of all available 2nd column elements in 2D array : " + Sum_of_Second_column_elements);
	}

}
