import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CountdownLetters {
	
	static List<String> vowels = new LinkedList<String>(Arrays.asList("a", "a", "a", "e", "e", "e", "e", "i", "i", "o", "o", "u"));
	static List<String> consonants = new ArrayList<String>();
	
	public static void main(String[] args)
	{
		Scanner k = new Scanner(System.in);
		fillConsonants();
		try
		{
			List<String> letters = new ArrayList<String>();
			int numVowels = -1;
			int numConsonants = -1;
			while (true)
			{
				System.out.println("How many vowels? (3 to 5)");
				numVowels = k.nextInt();
				k.nextLine();
				if (numVowels <= 5 && numVowels >= 3)
				{
					break;
				}
				else
				{
					System.out.println("Please use a number from 3 to 5.");
				}
			}
			numConsonants = 9 - numVowels;
			for (int vowel = 0; vowel < numVowels; vowel++)
			{
				letters.add(addLetter("vowel"));
			}
			for (int cons = 0; cons < numConsonants; cons++)
			{
				letters.add(addLetter("consonant"));
			}
			
			Collections.shuffle(letters);
			System.out.println();
			System.out.print("Your letters are: ");
			for (String s: letters)
			{
				System.out.print(s);
			}
			
			
			System.out.println("\nWhat word did you make?");
			String word = k.nextLine();
			if(!contains(word) || !validate(letters, word))
			{
				System.out.println("That's wrong. You lose!");
			}
			else
			{
				System.out.println("You win.");
			}
			
			System.out.println();
			System.out.println("");
		}
		catch(Exception e)
		{
			System.out.println("ERROR: File not found.");
		}
		
		k.close();
	}
	
	public static boolean validate(List<String> letters, String word)
	{
		for (int i = 0; i < word.length(); i++)
		{
			String curLetter = word.substring(i, i+1);
			if (letters.contains(curLetter))
			{
				letters.remove(letters.indexOf(curLetter));
			}
			else
			{
				return false;
			}
		}
		return true;
	}
	
	public static void printArray(List<String> arr)
	{
		
	}
	
	public static String addLetter(String type)
	{
		if (type.equals("vowel"))
		{
			int rand = (int)(Math.random() * vowels.size());
			return vowels.remove(rand);
		}
		else
		{
			int rand = (int)(Math.random() * consonants.size());
			return consonants.remove(rand);
		}
	}
	
	public static void fillConsonants()
	{
		for (int b = 0; b < 20; b++)
		{
			consonants.add("b");
		}
		for (int c = 0; c < 37; c++)
		{
			consonants.add("c");
		}
		for (int d = 0; d < 57; d++)
		{
			consonants.add("d");
		}
		for (int f = 0; f < 30; f++)
		{
			consonants.add("f");
		}
		for (int g = 0; g < 27; g++)
		{
			consonants.add("g");
		}
		for (int h = 0; h < 82; h++)
		{
			consonants.add("h");
		}
		for (int j = 0; j < 2; j++)
		{
			consonants.add("j");
		}
		for (int k = 0; k < 10; k++)
		{
			consonants.add("k");
		}
		for (int l = 0; l < 54; l++)
		{
			consonants.add("l");
		}
		for (int m = 0; m < 30; m++)
		{
			consonants.add("m");
		}
		for (int n = 0; n < 91; n++)
		{
			consonants.add("n");
		}
		for (int p = 0; p < 26; p++)
		{
			consonants.add("p");
		}
		for (int q = 0; q < 1; q++)
		{
			consonants.add("q");
		}
		for (int r = 0; r < 81; r++)
		{
			consonants.add("r");
		}
		for (int s = 0; s < 85; s++)
		{
			consonants.add("s");
		}
		for (int t = 0; t < 122; t++)
		{
			consonants.add("t");
		}
		for (int v = 0; v < 13; v++)
		{
			consonants.add("v");
		}
		for (int w = 0; w < 31; w++)
		{
			consonants.add("w");
		}
		for (int x = 0; x < 2; x++)
		{
			consonants.add("x");
		}
		for (int y = 0; y < 26; y++)
		{
			consonants.add("y");
		}
		for (int z = 0; z < 1; z++)
		{
			consonants.add("z");
		}
	}
	
	public static boolean contains(String s) throws IOException
	{
		File f = new File("words.txt");
		Scanner k = new Scanner(f);
		while (k.hasNextLine())
		{
			String thisString = k.nextLine();
			if (thisString.equalsIgnoreCase(s))
			{
				k.close();
				return true;
			}
		}
		k.close();
		return false;
	}
}
