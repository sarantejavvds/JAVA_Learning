package practice_programs._3_String_based_Programs;

import java.util.Scanner;

public class _Character_Frequency_of_a_given_Input_String 
{
    public static void main(String[] args) 
    {
    	Scanner scanner = new Scanner(System.in);
    	System.out.print("Enter any String : ");
        String input = scanner.nextLine();
        
        // Convert the string to lowercase to treat 'H' and 'h' the same
        input = input.toLowerCase();
        
        // Create an array to store the frequency of each letter (26 letters in the alphabet)
        int[] freq = new int[26];
        
        // Loop through each character in the string
        for (int i = 0; i < input.length(); i++) 
        {
            char character_input = input.charAt(i);
            
            // If the character is a letter (ignoring spaces and non-letter characters)
            if(character_input >= 'a' && character_input <= 'z') 
            {
                freq[character_input - 'a']++;  // Increment the frequency of the character
            }
        }
        
        // Output the frequencies
        for (int i = 0; i < 26; i++) 
        {
            if (freq[i] > 0)
            {
                System.out.println((char)(i + 'a') + " = " + freq[i]);
            }
        }
        
        scanner.close();
    }
}

