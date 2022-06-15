// Assignment: 8
// Name: Siddhartha Pudasaini
// StudentID: 1222261339
// Lecture: T,TH 4:30
// Description: The Restaurant class implements serializable. This class consists data and functionalities to set and get restaurant review data.

import java.io.Serializable;

public class Restaurant implements Serializable{

    //Defining variables
    private static final long serialVersionUID=205L;
    private String restaurantName;
    private int stars;
    private String review;
    private int priceRange;
    private String location;
    private Cuisine cuisine;

    //Constructor class
    public Restaurant(String restaurantName,int stars,String review, int priceRange,String location,Cuisine cuisine){
        this.restaurantName=restaurantName;
        this.stars=stars;
        this.review=review;
        this.priceRange=priceRange;
        this.location=location;
        this.cuisine=cuisine;
    }

    public String getRestaurantName(){
        return restaurantName;
    }

    public int getStars() {return stars;}

    public int getPriceRange() {
        return priceRange;
    }

    public String getLocation() { return location;}

    public String getReview() { return review;}

    public Cuisine getCuisine() { return cuisine;}

    @Override
    public String toString() {

        String star="";
        String price="";
        for(int i=0;i<stars;i++){
            star+="*";
        }
        for (int i=0;i<priceRange;i++){
            price+="$";
        }

        return restaurantName + " restaurant\n" +
                star+ "\t\t" + price + "\n" +
                cuisine.toString() +
                "Location: " + location + "\n" +
                "Review:\t" + review + "\n\n";
    }
}
