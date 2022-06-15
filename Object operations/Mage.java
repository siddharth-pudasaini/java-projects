/*
    Assignment #: 5
    Name: Siddhartha Pudasaini
    StudentID: 1222261339
    Lecture: T,Th 4:30
    Description: This class extends Player entity class. It has methods to compute combat power and
     a tostring method to return data in form of string.

*/


public class Mage extends PlayerEntity {
    //Defining variables
    private double mana;

    //Constructor
    public Mage(double health, String name, int stamina, int attack, double mana) {
        super(health, name, stamina, attack);
        this.mana = mana;
    }

    //Method to compute combat points
    @Override
    public void computeCombatPower() {
        this.combatPoints = (int) ((attack + health) * (1 + mana));
    }

    //To string method to return data in form of string
    @Override
    public String toString() {
        return "\nMage Hero:" +
                super.toString() +
                "Mana:\t\t\t" + (int) (this.mana * 100) + "%" + "\n";
    }
}
