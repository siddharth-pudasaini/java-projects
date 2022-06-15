/*
* Assignment #: 9
* Name: Siddhartha Pudasaini
* Student ID: 1222261339
* Lecture Time: T,Th 4:30
* Description: Assignment 9 class gives user a choice to select number 1-5 each representing different functions to perform on a given array or a string.
*               The functionalities provided by the class are: finding the largest number in an array, finding the product of all prime numbers in an array,
*               finding the greatest sum of digits in a number and removing adjacent characters from a string. These functionalities are achieved by using recursive methods.
* */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Assignment9
{

    public static void main(String[] args)
    {
        int choice=0;
        int[] list;
        String inputString;
        InputStreamReader userInputStream=new InputStreamReader(System.in);
        BufferedReader reader=new BufferedReader(userInputStream);
        String line="";
		do
        {
            printMenu();
            try
            {
                line= reader.readLine().trim();
                choice=Integer.parseInt(line);
                if(choice<1 || choice>5)
                {
                    throw new Exception("Please choose a number between 1 and 5.");
                }

                else if(choice==5)
                {
                    break;
                }
                else
                {
                    switch (choice)
                    {
                        case 1:
                            list=parseInts(reader);
                            int largest=largestNumber(list,0);
                            System.out.printf("The largest number in the array is: %d\n",largest);
                            break;

                        case 2:
                            list=parseInts(reader);
                            int product=primeProduct(list,0);
                            System.out.printf("The product of all prime numbers in the array is: %d\n",product);
                            break;

                        case 3:
                            list=parseInts(reader);
                            int sum=largestSum(list,0);
                            System.out.printf("The largest sum of digits in the array is: %d\n",sum);
                            break;

                        case 4:
                            System.out.println("Please enter String:");
                            inputString=reader.readLine().trim();
                            String result=removeDuplicate(inputString,0);
                            System.out.printf("String after adjacent duplicate characters were removed: %s\n",result);
                            break;
                    }
                }
            }
            catch (IOException e)
            {
                System.out.println(e);
            }
            catch(NumberFormatException e)
            {
                System.out.println("Please choose a number between 1 and 5.");
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        while (choice!=5);
    }


    // Utility method for printing the menu 
    public static void printMenu()
    {
        System.out.print("\nWhat would you like to do?\n\n");
        System.out.print("1: Find the largest number in an array of integers\n");
        System.out.print("2: Calculate the product of all prime numbers in an array of integers\n");
        System.out.print("3: Find the element with the largest sum of digits in an array of integers\n");
        System.out.print("4: Remove adjacent duplicate characters in a String\n");
        System.out.print("5: Quit\n\n");
    }

    // utility method for parsing integers from standard input
    public static int[] parseInts(BufferedReader reader)
    {
        String line = "";
        ArrayList<Integer> container = new ArrayList<>();
        try
        {
            System.out.print("Please enter integers:\n");
            line = reader.readLine();
            int num = Integer.parseInt(line);
            while (num > 0)
            {
                container.add(num);
                line = reader.readLine();
                num = Integer.parseInt(line);
            }
        }
        catch (IOException ex)
        {
            System.out.println("IO Exception");
        }
         int[] result = new int[container.size()];
         for(int i = 0; i < container.size(); i++)
         {
             result[i] = container.get(i);
         }
        return result;
    }

    //Method to find the largest number in an array
    public static int largestNumber(int[] arr,int index){
        if(index==arr.length-1)
        {
            return arr[index];//Base case when the last element is reached
        }
        else
        {
            return Math.max(arr[index],largestNumber(arr,index+1));
        }
    }

    //Method for finding the prime product
    public static int primeProduct(int[] arr,int index)
    {
        int num=isPrime(arr[index],1)?arr[index]:1;
        if(index==arr.length-1) return num;//Base case when the last element is reached
        else return num*=primeProduct(arr, index+1);
    }

    //Method for finding the largest sum from the given array of integers
    public static int largestSum(int[] arr,int index)
    {
        String number=String.valueOf(arr[index]);
        int sum=findSum(number,0);
        if (index==arr.length-1) return sum;//Base case when last element is reached
        else return Math.max(sum,largestSum(arr,index+1));
    }

    //Method to remove duplicates
    public static String removeDuplicate(String input,int index)
    {
        if(index==input.length()-1) return input.substring(index);//Base case when the last element is reached
        else
        {
            int duplicates=numDuplicates(input,index);
            String currentCharacter=String.valueOf(input.charAt(index));
            if(duplicates==0) return currentCharacter+removeDuplicate(input,index+1);
            else if(index+1+duplicates>input.length()-1)return currentCharacter;
            else return currentCharacter+removeDuplicate(input,index+1+duplicates);
        }
    }

    //Helper Method for checking if the number is prime or not
    public static boolean isPrime(int num,int i){
        if(num==i) return true; //Base case if the number dividing is equal to the provided number
        else {
            if(num%i==0 && i!=1) return false;
            else return isPrime(num,i+1);
        }
    }

    //Helper Method for finding the sum of each character from a number
    public static int findSum(String num,int index)
    {
        int number=Integer.parseInt(String.valueOf(num.charAt(index)));
        if(index==num.length()-1)
        {
            return number;//Base case when the last number is reached
        }
        else
        {
            return number+findSum(num,index+1);
        }
    }

    //Helper method to find the number of duplicate letters in a string
    public static int numDuplicates(String input,int index)
    {
        String character=String.valueOf(input.charAt(index));
        if(index==input.length()-1) return 0;//Base case when the last element is reached
        else
        {
            if(character.equals(String.valueOf(input.charAt(index+1)))) return numDuplicates(input,index+1)+1;
            else return 0;
        }
    }

}
