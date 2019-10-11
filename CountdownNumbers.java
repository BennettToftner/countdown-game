import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CountdownNumbers {

	static List<Integer> smallNumbers = new LinkedList<Integer>(Arrays.asList(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10));
	static List<Integer> largeNumbers = new LinkedList<Integer>(Arrays.asList(25, 50, 75, 100));
	
	public static void main(String[] args) {
		Scanner k = new Scanner(System.in);
		int targetNum = (int)(Math.random() * 900) + 100;
		
		List<Integer> nums = new ArrayList<Integer>();
		
		for (int i = 0; i < 6; i++)
		{
			String largeOrSmall = "";
			while (true)
			{
				//Number of larges and number of smalls so you don't have to type in like 5 smalls
				System.out.println("Large or small number?");
				largeOrSmall = k.nextLine();
				largeOrSmall = largeOrSmall.toLowerCase();
				if (largeOrSmall.equals("large") || largeOrSmall.equals("small"))
				{
					break;
				}
				else
				{
					System.out.println("Invalid. Please try again.");
				}
			}
			nums.add(addNumber(largeOrSmall));
			System.out.println("The number you got is " + nums.get(i) + ".");
		}
		
		System.out.println();
		System.out.println("Your numbers are:");
		for (int num: nums)
		{
			System.out.print( num + " ");
		}
		System.out.println();
		System.out.println("The target number is " + targetNum + ".");
		
		while (nums.size() > 1)
		{
			String operation = "";
			while (true)
			{
				System.out.println("What operation would you like to do? (ex. 24 / 4) Or type \"Done\" if you are done.");
				operation = k.nextLine();
				operation = operation.toLowerCase();
				operation = operation.replace(" ", "");
				if (validateOperation(nums, operation) || operation.equals("done"))
				{
					break;
				}
				else
				{
					System.out.println("Invalid. Please try again.");
				}
			}
			
			if (operation.equals("done"))
			{
				
				int closest = handleDone(nums, targetNum);
				nums.clear();
				nums.add(closest);
			}
			else
			{
				int[] opNums = getNums(operation);
				int result = evaluateOperation(operation);
				nums.remove(nums.indexOf(opNums[0]));
				nums.remove(nums.indexOf(opNums[1]));
				nums.add(result);
			}
			
			System.out.println();
			System.out.println("Your numbers are:");
			for (int num: nums)
			{
				System.out.print( num + " ");
			}
			System.out.println();
		}
		
		int resultNum = nums.get(0);
		int difference = Math.abs(resultNum - targetNum);
		System.out.println("The number you made is " + resultNum + ", which is " + difference + " from the target number, " + targetNum + ".");
		
		k.close();
	}
	
	public static int handleDone(List<Integer> nums, int target)
	{
		int closestNum = -1;
		for (int i: nums)
		{
			if (Math.abs(i - target) < Math.abs(closestNum - target))
			{
				closestNum = i;
			}
		}
		return closestNum;
	}
	
	public static boolean validateOperation(List<Integer> nums, String op)
	{
		char opType = getOperationType(op);
		
		if (opType == ' ' || (opType != '*' && opType != '/' && opType != '+' && opType != '-'))
		{
			return false;
		}
		
		int[] opNums = getNums(op);
		
		if (opNums.length != 2)
		{
			return false;
		}
		
		for (int i = 0; i < opNums.length; i++)
		{
			if (!nums.contains(opNums[i]))
			{
				return false;
			}
		}
		
		//if not divisible
		if (opType == '/' && (opNums[0] % opNums[1] != 0))
		{
			return false;
		}
		
		//stop negative numbers
		if (opType == '-' && (opNums[0] <= opNums[1]))
		{
			return false;
		}
		
		return true;
	}
	
	public static int evaluateOperation(String op)
	{
		char opType = getOperationType(op);
		int[] nums = getNums(op);
		switch(opType)
		{
			case '*':
				return nums[0] * nums[1];
			case '/':
				return nums[0] / nums[1];
			case '+':
				return nums[0] + nums[1];
			case '-':
				return nums[0] - nums[1];
			default:
				return -1;
		}
	}
	
	public static int[] getNums(String op)
	{
		char opType = getOperationType(op);
		String[] numStrings;
		
		numStrings = op.split("\\" + opType);
		
		int[] opNums = new int[2];
		for (int i = 0; i < numStrings.length; i++)
		{
			try
			{
				opNums[i] = Integer.parseInt(numStrings[i]);
			}
			catch(Exception e)
			{
				return new int[] {-1};
			}
		}
		return opNums;
		
	}
	
	public static char getOperationType(String op)
	{
		char opType = ' ';
		for (int i = 0; i < op.length(); i++)
		{
			if (!Character.isDigit(op.charAt(i)))
			{
				opType = op.charAt(i);
			}
		}
		return opType;
	}
	
	public static int addNumber(String size)
	{
		if (size.equals("large"))
		{
			if (largeNumbers.size() == 0)
			{
				int rand = (int)(Math.random() * smallNumbers.size());
				return smallNumbers.remove(rand);
			}
			else
			{
				int rand = (int)(Math.random() * largeNumbers.size());
				return largeNumbers.remove(rand);
			}
		}
		else
		{
			int rand = (int)(Math.random() * smallNumbers.size());
			return smallNumbers.remove(rand);
		}
	}

}
