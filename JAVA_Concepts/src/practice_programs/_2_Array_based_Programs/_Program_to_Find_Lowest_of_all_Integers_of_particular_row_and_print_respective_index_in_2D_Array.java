package practice_programs._2_Array_based_Programs;

public class _Program_to_Find_Lowest_of_all_Integers_of_particular_row_and_print_respective_index_in_2D_Array {

	public static void main(String[] args) 
	{
		int[][] _2D_Array = { {1,5,6,8}, {3,8,7,4}, {3, 8,4, 5}, {4,3,2,1}, {2, 1, 9, 5} };
		
		//int Sum_of_Second_column_elements = 0;
		
		for(int i=0 ; i < _2D_Array.length ; i++)
		{
			int min_Value = _2D_Array[i][0]; // Assume first element is max
            int min_Index = 0;              // Index of max element
			
			for (int j = 1; j < _2D_Array[i].length; j++)
			{
				 if (_2D_Array[i][j] < min_Value)
				 {
	                    min_Value = _2D_Array[i][j];
	                    min_Index = j;
				 }
			}
			
			System.out.println("Max value in Row '" + (i + 1) + "' : '" + min_Value +"' with respective index : '" + min_Index + "'");
		}

		
	}

}
