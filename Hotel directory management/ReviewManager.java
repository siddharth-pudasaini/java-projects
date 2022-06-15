// Assignment: 8
// Name: Siddhartha Pudasaini
// StudentID: 1222261339
// Lecture: T,TH 4:30
// Description: The ReviewManager class implement serializable. This class contains the list of reviews. This class has methods to add, remove sort and get reviews.

import java.io.Serializable;
import java.util.ArrayList;


public class ReviewManager implements Serializable {
    private static final long serialVersionUID = 205L;
    ArrayList<Restaurant> reviewList;

    //Constructor class
    public ReviewManager() {
        reviewList = new ArrayList<>();
    }

    //Check if restaurant exists
    public int restaurantExists(String name,String location){
        int i=0;
        for(Restaurant restaurant:reviewList){
            String restaurantName= restaurant.getRestaurantName().toLowerCase();
            String restaurantLocation=restaurant.getLocation().toLowerCase();
            if(restaurantName.equals(name.toLowerCase())&&restaurantLocation.equals(location.toLowerCase())){
                return i;
            }
            i+=1;
        }
        return -1;
    }

    //Check if cuisine exists
    public ArrayList<Integer> cuisineExists(String cuisineName){
        ArrayList<Integer> indices=new ArrayList<Integer>();
        int i=0;
        for(Restaurant restaurant:reviewList){
            String cuisine= restaurant.getCuisine().getName().toLowerCase();
            if(cuisine.equals(cuisineName.toLowerCase())){
                indices.add(i);
            }
            i+=1;
        }
        return indices;
    }

    //Returns restaurant data
    public Restaurant getRestaurant(int index){
        return reviewList.get(index);
    }


    //Addibg review to the review list
    public boolean addReview(String restaurantName, int stars, String review, String priceRange, String cuisineName, String location, String signatureDish) {
        if (restaurantExists(restaurantName, location) == -1) {
            int price = priceRange.length();
            Cuisine newCuisine = new Cuisine(signatureDish, cuisineName);
            Restaurant newRestaurant = new Restaurant(restaurantName, stars, review, price, location, newCuisine);
            reviewList.add(newRestaurant);
            return true;
        }
        return false;
    }

    //Removing review from the review list
    public boolean removeReview(String name,String location){

        for(Restaurant res:reviewList){
            String resName=res.getRestaurantName().toLowerCase();
            String resLocation=res.getLocation().toLowerCase();
            if(name.toLowerCase().equals(resName)&&location.toLowerCase().equals(resLocation)){
                reviewList.remove(res);
                return true;
            }
        }
        return false;
    }


    //Sort reviews by rating
    public void sortByRating(){
        ReviewRatingComparator ratingComparator=new ReviewRatingComparator();
        Sorts.sort(reviewList,ratingComparator);
        reviewList=Sorts.newList;
    }

    //Sort reviews by cuisine
    public void sortByCuisine(){
        ReviewCuisineComparator cuisineComparator=new ReviewCuisineComparator();
        Sorts.sort(reviewList,cuisineComparator);
        reviewList=Sorts.newList;
    }

    //Listing all reviews
    public String listReviews(){
        String restaurantList="";

        if(reviewList.isEmpty()) return "No Reviews available\n";

        for(Restaurant res:reviewList){
             restaurantList+=res.toString();
        }
        return restaurantList;
    }

    //Clearing all data from the review list
    public void closeReviewManager(){
        reviewList.clear();
    }

}
