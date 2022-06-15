// Assignment: 8
// Name: Siddhartha Pudasaini
// StudentID: 1222261339
// Lecture: T,TH 4:30
// Description: The Sorts class contains of sort method that will sort the given list of data.



import java.util.ArrayList;
import java.util.Comparator;


public class Sorts {

    public static ArrayList<Restaurant> newList;

    //Sort method--uses merge sort
    public static void sort(ArrayList<Restaurant> restaurants,Comparator<Restaurant> restaurantComparator){
        newList=mergeSort(restaurants,restaurantComparator);

    }

    private static ArrayList<Restaurant> mergeSort(ArrayList<Restaurant> restaurants,Comparator<Restaurant> restaurantComparator){

        //Base case
        if(restaurants.size()==1) return restaurants;

        int mid=restaurants.size()/2;

        //Recursion
        ArrayList<Restaurant> arr1=mergeSort(new ArrayList<Restaurant>(restaurants.subList(0,mid)),restaurantComparator);
        ArrayList<Restaurant> arr2=mergeSort(new ArrayList<Restaurant>(restaurants.subList(mid,restaurants.size())),restaurantComparator);

        //Merging two sorted lists
        return merge(arr1,arr2,restaurantComparator);
    }


    //Method to merge two sorted list
    private static ArrayList<Restaurant> merge(ArrayList<Restaurant> arr1,ArrayList<Restaurant> arr2,Comparator<Restaurant> restaurantComparator){
         ArrayList sortedArr=new ArrayList<Restaurant>();

         while (!arr1.isEmpty()&&!arr2.isEmpty()){
             Restaurant r1=arr1.get(0);
             Restaurant r2=arr2.get(0);

             if(restaurantComparator.compare(r1,r2)<0){
                 sortedArr.add(r1);
                 arr1.remove(r1);
             }
             else if(restaurantComparator.compare(r1,r2)>=0){
                 sortedArr.add(r2);
                 arr2.remove(r2);
             }
         }

         while(!arr1.isEmpty()){
             sortedArr.add(arr1.get(0));
             arr1.remove(arr1.get(0));
         }

        while(!arr2.isEmpty()){
            sortedArr.add(arr2.get(0));
            arr2.remove(arr2.get(0));
        }

         return  sortedArr;
    }

}
