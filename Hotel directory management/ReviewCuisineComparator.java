// Assignment: 8
// Name: Siddhartha Pudasaini
// StudentID: 1222261339
// Lecture: T,TH 4:30
// Description: The review Rating comparator class implement comparator, and it compares two restaurant objects according to the given hierarchy.

import java.util.ArrayList;
import java.util.Comparator;

public class ReviewCuisineComparator implements Comparator<Restaurant>{
    @Override
    public int compare(Restaurant r1, Restaurant r2) {

        ArrayList<Integer> compareOrderList =new ArrayList<Integer>();
        String cuisine1=r1.getCuisine().getName();
        String cuisine2=r2.getCuisine().getName();

        int priceRange1=r1.getPriceRange();
        int priceRange2=r2.getPriceRange();

        String name1=r1.getRestaurantName();
        String name2=r2.getRestaurantName();

        String location1= r1.getLocation();
        String location2= r2.getLocation();

        String review1= r1.getReview();
        String review2=r2.getReview();

        int cuisineCompare=compareStrings(cuisine1,cuisine2);
        int priceCompare=intCompare(priceRange1,priceRange2);
        int nameCompare=compareStrings(name1,name2);
        int locationCompare=compareStrings(location1,location2);
        int reviewCompare=compareStrings(review1,review2);

        compareOrderList.add(cuisineCompare);
        compareOrderList.add(priceCompare);
        compareOrderList.add(nameCompare);
        compareOrderList.add(locationCompare);
        compareOrderList.add(reviewCompare);

        for(int compareResult:compareOrderList){
            if (compareResult!=0) return compareResult;
        }
        return 0;

    }

    private int intCompare(int r1,int r2){
        if(r1>r2) return 1;
        else if(r2>r1) return -1;
        else return 0;
    }

    private int compareStrings(String r1,String r2){
        if(r1.compareTo(r2)>0) return 1;
        else if(r1.compareTo(r2)<0) return -1;
        else return 0;
    }


}
