/*
    Assignment #: 5
    Name: Siddhartha Pudasaini
    StudentID: 1222261339
    Lecture: T,Th 4:30
    Description: This is a parser class that has a parseNewPlayer method which takes in a string input and
    returns an object of either maze or a fighter class.

*/

public class PlayerParser {

    //Method that parse the string data and return a fighter or a mage object depending upon input
    public static PlayerEntity parseNewPlayer(String lineToParse) {
        String[] splitString = lineToParse.split("/");
        String playerType = splitString[0];
        double health = Double.parseDouble(splitString[1]);
        String name = splitString[2];
        int stamina = Integer.parseInt(splitString[3]);
        int attack = Integer.parseInt(splitString[4]);

        if (playerType.toLowerCase().equals("fighter")) {
            String fighterType = splitString[5].toLowerCase();
            boolean isRaged = fighterType.equals("range");
            return new Fighter(health, name, stamina, attack, isRaged);
        } else {
            double mana = Double.parseDouble(splitString[5]);
            return new Mage(health, name, stamina, attack, mana);
        }

    }
}
