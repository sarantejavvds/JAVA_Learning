package _012_Exception_Handling_in_JAVA._2_Unchecked_Exceptions;

public class ArrayIndexOutOfBoundsException_Example 
{
	public static void main(String[] args)
    {
        // Inserting elements into Array
        int a[] = { 1, 2, 3, 4, 5 };
 
        // Try block for exceptions
        try 
        {
 
            // Forcefully trying to access and print
            // element/s beyond indexes of the array
 
            System.out.println(a[5]);
        }
 
        // Catch block for catching exceptions
        catch (ArrayIndexOutOfBoundsException e) 
        {
 
            // Printing display message when index not
            // present in a array is accessed
            System.out.println("Out of index  please check your code");
        }
    }
}
