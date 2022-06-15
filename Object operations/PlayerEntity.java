/*
    Assignment #: 5
    Name: Siddhartha Pudasaini
    StudentID: 1222261339
    Lecture: T,Th 4:30
    Description: This is an abstract class that has data about player's health , name , stamina , attack
    and combat points. This class has an abstract method to compute combat points. Also this class consists
    of a method to get combat points.

*/


public abstract class PlayerEntity {

    //Defining variables
    protected double health;
    protected String entityName;
    protected int stamina;
    protected int attack;
    protected int combatPoints;

    //Constructor class to initialize variables
    public PlayerEntity(double health, String entityName, int stamina, int attack) {
        this.health = health;
        this.entityName = entityName;
        this.attack = attack;
        this.stamina = stamina;
        this.combatPoints = 0;
    }

    //Returns combat points
    public int getCombatPoints() {
        return this.combatPoints;
    }

    @Override
    //Returns data stored in the class in form of string
    public String toString() {
        return "\nHero name:\t\t" + this.entityName + "\n" +
                "Current Health:\t\t" + this.health + "\n" +
                "Stamina:\t\t" + this.stamina + "\n" +
                "Attack Damage:\t\t" + this.attack + "\n" +
                "Current Combat Points:\t" + this.combatPoints + "\n";

    }

    //Abstract method to compute combat power. This method gets overidden in every class that extends this class
    public abstract void computeCombatPower();
}
