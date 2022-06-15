public class Coach {

    //Variables
    private String firstName;
    private String lastName;
    private int yearsCoached;

    //Default constructor that sets default values if no arguments are passed
    public Coach() {
        this.firstName = "?";
        this.lastName = "?";
        this.yearsCoached = 0;
    }

    //Constructor that sets default values as the passed arguments
    public Coach(String firstName, String lastName, int yearsCoached) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearsCoached = yearsCoached;
    }

    //Takes coach class as an argument and sets variables value to that of the passed class
    public Coach(Coach coach) {
        this.firstName = coach.getFirstName();
        this.lastName = coach.getLastName();
        this.yearsCoached = coach.getYearsCoached();
    }

    //Returns first name
    public String getFirstName() {
        return this.firstName;
    }

    //Returns years coached
    public int getYearsCoached() {
        return this.yearsCoached;
    }

    //Returns last name
    public String getLastName() {
        return this.lastName;
    }

    //Sets first name as the passed value
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //Sets last name as the passed value
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //Sets years coached as the passed value
    public void setYearsCoached(int yearsCoached) {
        this.yearsCoached = yearsCoached;
    }

    //Returns class data in the form of string
    @Override
    public String toString() {
        return "\nLast Name:" + "\t" + this.lastName +
                "\nFirst Name:" + "\t" + this.firstName +
                "\nYears of Experience:" + "\t" + this.yearsCoached + "\n";
    }
}
