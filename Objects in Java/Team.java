public class Team {

    //Variables
    private String name;
    private Coach coach;
    private String city;

    //Default constructor which sets default values to variables if no arguments are passed
    public Team() {
        this.name = "?";
        this.coach = new Coach();
        this.city = "?";
    }

    //Constructor that sets variables value to the passed value
    public Team(String name, Coach coach, String city) {
        this.name = name;
        this.coach = coach;
        this.city = city;
    }

    //Returns name of the team
    public String getName() {
        return this.name;
    }

    //Returns Coach class associated with this class
    public Coach getCoach() {
        return this.coach;
    }

    //Returns name of the city
    public String getCity() {
        return this.city;
    }

    //Sets name of the team to the passed value
    public void setName(String name) {
        this.name = name;
    }

    //Sets name of the city to the passed value
    public void setCity(String city) {
        this.city = city;
    }

    //This method here takes first name, last name and years coached information about the coach and stores that data in
    // this class under coach variable
    public void setCoach(String firstName, String lastName, int yearsCoached) {
        this.coach = new Coach(firstName, lastName, yearsCoached);
    }


    @Override
    public String toString() {
        return "Team's name:\t" + name
                + " from " + city +
                "\nCoach Information:" + coach.toString();
    }
}
