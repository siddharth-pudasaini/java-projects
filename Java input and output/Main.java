import java.util.Scanner;


public class Main {

    public static void main(String[] args) {


        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter normalizing value");
        int num = scnr.nextInt();

        int[] collection = new int[5];

        for (int i = 0; i < 5; i++) {
            collection[i] = i + 1 + num;
        }

        for (int number : collection) {
            System.out.println(number);
        }

    }


}
