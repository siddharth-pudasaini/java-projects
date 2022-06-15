/*
    Assignment #: 5
    Name: Siddhartha Pudasaini
    StudentID: 1222261339
    Lecture: T,Th 4:30
    Description: This class contains functions that take input from
     the user and gives them an option to add, list, compute combat points of players

*/

import java.io.*;         //to use InputStreamReader and BufferedReader
import java.util.*;       //to use ArrayList


public class Assignment5 {
    public static void main(String[] args) {
        char input;
        String line;
        String inputInfo;

        // ArrayList used to store the hero objects
        ArrayList<PlayerEntity> playerList = new ArrayList<>();

        try {
            System.out.println("Welcome to the battle stats simulator!");
            printMenu();

            // create a BufferedREader object to read input from a keyboard
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader stdin = new BufferedReader(isr);

            do {
                System.out.println("\nWhat action would you like to perform?");
                line = stdin.readLine().trim();
                input = line.charAt(0);
                input = Character.toUpperCase(input);
                if (line.length() == 1) {
                    switch (input) {
                        case 'A': // add hero to the guild
                            System.out.println("Please enter your hero stats:");
                            inputInfo = stdin.readLine().trim();

                            PlayerEntity newPlayer = new PlayerParser().parseNewPlayer(inputInfo);
                            playerList.add(newPlayer);

                            break;

                        case 'C': // calculate combat points

                            for (PlayerEntity player : playerList) {
                                player.computeCombatPower();
                            }

                            System.out.print("Combat points computed\n");
                            break;

                        case 'D': // how many heroes have combat points equal to or larger than a user-defined value
                            System.out.print("Please enter a minimum combat points you want to calculate:\n");
                            inputInfo = stdin.readLine().trim();
                            int min = Integer.parseInt(inputInfo);
                            int count = 0;

                            for (PlayerEntity player : playerList) {
                                count = player.getCombatPoints() >= min ? ++count : count;
                            }

                            System.out.println("The number of heroes with " + min
                                    + " combat points or more is: " + count);
                            break;

                        case 'L': // list heroes
                            if (playerList.isEmpty()) System.out.print("No heroes in guild yet.\n");
                            else {
                                for (PlayerEntity player : playerList) {
                                    System.out.println(player.toString());
                                    System.out.println();
                                }
                            }
                            break;

                        case 'Q':
                            break;

                        case '?':
                            printMenu();
                            break;

                        default:
                            System.out.print("Unknown action\n");
                            break;
                    }
                } else
                    System.out.println("Unknown action");

            } while (input != 'Q');
            System.out.println("Thanks for playing!");
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
    }


    public static void printMenu() {
        System.out.print("Choice\t\tAction\n" +
                "------\t\t------\n" +
                "A\t\tAdd Hero\n" +
                "C\t\tCompute Combat Points\n" +
                "D\t\tCount Heroes with Minimum Combat Points\n" +
                "L\t\tList Heroes\n" +
                "Q\t\tQuit\n" +
                "?\t\tDisplay Help\n\n");
    }
}
