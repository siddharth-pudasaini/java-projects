
/*
 * Assignment #: 2
 * Name: Siddhartha Pudasaini
 * Student ID: 1222261339
 * Lecture time: T,Th 4:30-5:45
 * Time to complete the assignment: 10 mins
 * Description: This file consist of a class Assignment 2 which has a main function.
 *               When called main function takes input from the user till the user presses 0.
 *               When user presses 0 the function will stop taking input from the user and display
 *               data such as largest integer, largest even integer, total number of positive integers in the
 *               sequence and the total sum of integers passed in the sequence to the console.
 *
 * */

import java.util.Scanner; //importing Scanner to take input from console

public class Assignment2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean runWhileLoop = true;
        int numPositiveInt = 0;
        int largestInt = 0;
        int largestEvenInt = 0;
        int sum = 0;

        //The loop is used here to take the input until the user presses 0
        while (runWhileLoop) {
            int inputInt = scanner.nextInt();

            if (inputInt == 0) break;//Breaking the loop if 0 is pressed

            numPositiveInt = inputInt > 0 ? ++numPositiveInt : numPositiveInt;
            largestInt = inputInt > largestInt ? inputInt : largestInt;
            largestEvenInt = inputInt > largestEvenInt && inputInt % 2 == 0 ? inputInt : largestEvenInt;
            sum += inputInt;
        }

        System.out.printf("The largest integer in the sequence is %d\n", largestInt);
        System.out.printf("The largest even integer in the sequence is %d\n", largestEvenInt);
        System.out.printf("The count of positive integers in the sequence is %d\n", numPositiveInt);
        System.out.printf("The sum of all integers is %d\n", sum);

    }
}


