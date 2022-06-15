/*
    Assignment #: 5
    Name: Siddhartha Pudasaini
    StudentID: 1222261339
    Lecture: T,Th 4:30
    Description: This class extends Player entity class. It has private data about a fighter's armor,
    max attack and whether a fighter is ranged or not. The abstract method to compute combat points is
    overridden in this class.

*/


public class Fighter extends PlayerEntity {

    //Defining variables
    private double armor;
    private int maxAttack;
    private boolean isRaged;

    //Constructor
    public Fighter(double health, String name, int stamina, int attack, boolean isRanged) {
        super(health, name, stamina, attack);
        this.isRaged = isRanged;
        armor = isRanged ? 0.25 : 0.5;
        maxAttack = isRanged ? 125 : 100;
    }

    @Override
    //Method to compute combat power
    public void computeCombatPower() {
        combatPoints = maxAttack <= attack
                ?
                (int) ((maxAttack + health) * (1 - armor))
                :
                (int) ((attack + health) * (1 - armor));
    }

    @Override
    //Method to return data in form of string
    public String toString() {
        String playerType = isRaged ? "Ranged Type" : "Melee Type";
        return "\nFighter Hero:\t\t" + playerType +
                super.toString() +
                "Armor:\t\t\t" + (int) (this.armor * 100) + "%\n";
    }
}
