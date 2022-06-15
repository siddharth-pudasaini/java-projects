// Assignment: 8
// Name: Siddhartha Pudasaini
// StudentID: 1222261339
// Lecture: T,TH 4:30
// Description: The assignment class instantiates the review manager class. Review manager class provides us with data
//              and functionalities to work with reviews.

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Assignment8 {
    public static void main(String[] args) {
        // Menu options
        char inputOpt = ' ';
        String inputLine;
        // Restaurant and Cuisine information
        String restaurantName, cuisineName;
        String review = null, location, signatureDish, priceRange;

        int rating;
        // Output information
        String outFilename, inFilename;
        String outMsg, inMsg;
        // Restaurant manager
        ReviewManager reviewManager = new ReviewManager();
        // Operation result
        boolean opResult;     
        
        try {
            printMenu();
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader stdin = new BufferedReader(isr);

            do {
                System.out.print("\nWhat action would you like to perform?\n");
                inputLine = stdin.readLine().trim();
                if (inputLine.isEmpty()) {
                    continue;
                }
                inputOpt = inputLine.charAt(0);
                inputOpt = Character.toUpperCase(inputOpt);

                switch (inputOpt) {

                    case 'A': // Add a new Restaurant Review
                        System.out.print("Please enter the restaurant information:\n");
                        System.out.print("Enter the restaurant name:\n");
                        restaurantName = stdin.readLine().trim();
                        System.out.print("Enter the review:\n");
                        review = stdin.readLine().trim();
                        System.out.print("Enter the price range:\n");
                        priceRange = stdin.readLine().trim();
                        System.out.print("Enter the rating:\n");
                        rating = Integer.parseInt(stdin.readLine().trim());
                        System.out.print("Enter the cuisine name:\n");
                        cuisineName = stdin.readLine().trim();
                        System.out.print("Enter the location:\n");
                        location = stdin.readLine().trim();
                        System.out.print("Enter the signature dish\n");
                        signatureDish = stdin.readLine().trim();
                        boolean restaurantAdded=reviewManager.addReview(restaurantName,rating,review,priceRange,cuisineName,location,signatureDish);
                        if (restaurantAdded) {
                            System.out.println("Restaurant added");
                        }
                        else {
                            System.out.printf("%s, %s was NOT added",restaurantName,location);
                        }
                        break;

                    case 'D': // Search a Restaurant
                        System.out.print("Please enter the restaurant name to search:\n");
                        restaurantName = stdin.readLine().trim();
                        System.out.print("Please enter the restaurant's location':\n");
                        location = stdin.readLine().trim();
                        int restaurantExistance=reviewManager.restaurantExists(restaurantName,location);
                        if(restaurantExistance==-1){
                            System.out.println("Restaurant not found. Please try again");
                        }
                        else{
                            Restaurant foundRestaurant=reviewManager.getRestaurant(restaurantExistance);
                            System.out.print("Restaurant found. Here's the review:\n"+foundRestaurant.getReview()+"\n");
                        }
                        break;


                    case 'E': // Search a cuisine
                        System.out.print("Please enter the cuisine name to search:\n");
                        cuisineName = stdin.readLine().trim();
                        ArrayList<Integer> indices=reviewManager.cuisineExists(cuisineName);
                        if(indices.isEmpty()){
                            System.out.printf("Cuisine: %s was NOT found\n",cuisineName);
                        }
                        else{
                            System.out.printf("\n%d Restaurants matching %s cuisine were found:\n",indices.size(),cuisineName);
                            for(int index:indices){
                                System.out.println(reviewManager.getRestaurant(index).toString());
                            }
                        }
                        break;
   
                    case 'L': // List restaurant's reviews
                        System.out.print("\n" + reviewManager.listReviews() + "\n");
                        break;

                    case 'N':
                        reviewManager.sortByRating();
                        System.out.println("sorted by rating");
                        break;

                    case 'P':
                        reviewManager.sortByCuisine();
                        System.out.println("sorted by cuisine");
                        break;

                    case 'Q': // Quit
                        break;

                    case 'R': // Remove a review
                        System.out.print("Please enter the restaurant name of the review to remove:\n");
                        restaurantName = stdin.readLine().trim();
                        System.out.print("Please enter the location to remove:\n");
                        location = stdin.readLine().trim();
                        boolean reviewRemoved=reviewManager.removeReview(restaurantName,location);
                        if(reviewRemoved){
                            System.out.printf("%s, %s was removed\n",restaurantName,location);
                        }
                        else{
                            System.out.printf("%s, %s was NOT removed\n",restaurantName,location);
                        }
                        break;
                        
                    case 'T': // Close reviewList
                        reviewManager.closeReviewManager();
                        System.out.print("Restaurant management system was reset\n");
                        break;

                    case 'U': // Write restaurant names and reviews to a text file
                        System.out.print("Please enter a file name that we will write to:\n");
                        outFilename = stdin.readLine().trim();
                        System.out.print("Please enter the name of the restaurant:\n");
                        restaurantName = stdin.readLine().trim();
                        System.out.println("Please enter a review to save locally:\n");
                        review = stdin.readLine().trim();
                        outMsg = restaurantName + "\n" + review + "\n";

                        try{
                            FileWriter fileWriter=new FileWriter(outFilename);
                            fileWriter.write(outMsg);
                            fileWriter.close();
                            System.out.printf("%s is written",outFilename);
                            break;
                        }
                        catch(IOException e){
                            System.out.println("Write string inside the file error\n");
                            break;
                        }


                    case 'V': // Read strings from a text file
                        System.out.print("Please enter a file name which we will read from:\n");
                        inFilename = stdin.readLine().trim();
                        try{
                            FileReader fileReader=new FileReader(inFilename);
                            BufferedReader reader=new BufferedReader(fileReader);
                            String line=reader.readLine();
                            System.out.printf("%s was read\nThe contents of the file are:\n",inFilename);
                            while (line!=null){
                                System.out.println(line);
                                line=reader.readLine();
                            }
                            reader.close();
                            fileReader.close();
                            break;
                        }

                        catch(FileNotFoundException e){
                            System.out.printf("%s was not found",inFilename);
                            break;
                        }
                        catch(IOException e){
                            System.out.println("Read string from file error");
                            break;
                        }


                    case 'W': // Serialize ReviewManager to a data file
                        System.out.print("Please enter a file name to write:\n");
                        outFilename = stdin.readLine().trim();
                        try{
                            FileOutputStream outputStream=new FileOutputStream(outFilename);
                            ObjectOutputStream objectOutputStream=new ObjectOutputStream(outputStream);
                            objectOutputStream.writeObject(reviewManager);
                            objectOutputStream.close();
                            outputStream.close();
                            break;
                        }
                        catch(NotSerializableException e){
                            System.out.println("Not serializable exception");
                            break;
                        }
                        catch(IOException e){
                            System.out.println("Data file written exception");
                            break;
                        }

                    case 'X': // Deserialize ReviewManager from a data file
                        System.out.print("Please enter a file name which we will read from:\n");
                        inFilename = stdin.readLine().trim();

                        try{
                            FileInputStream stream=new FileInputStream(inFilename);
                            ObjectInputStream inputStream=new ObjectInputStream(stream);
                            reviewManager=(ReviewManager) inputStream.readObject();
                            System.out.printf("%s was read\n",inFilename);
                            break;
                        }
                        catch(NotSerializableException e){
                            System.out.println("Not serializable exception");
                            break;
                        }
                        catch(IOException e){
                            System.out.println("Data file written exception");
                            break;
                        }
                        catch (ClassNotFoundException e) {
                            System.out.println("Class not found exception");
                            break;
                        }

                    case '?': // Display help
                        printMenu();
                        break;

                    default:
                        System.out.print("Unknown action\n");
                        break;
                }

            } while (inputOpt != 'Q' || inputLine.length() != 1);
        }
        catch (IOException exception) {
            System.out.print("IO Exception\n");
        }
    }

    public static void printMenu() {
        System.out.println("Welcome to Kelp! ");
        System.out.println("Find or post reviews for your favorite (and not so favorite) restaurants.");

        System.out.print("Choice\t\tAction\n" + "------\t\t------\n" + "A\t\tAdd a review\n"
                + "D\t\tSearch for a restaurant\n" + "E\t\tSearch for a cuisine\n"
                + "L\t\tList all reviews\n" + "N\t\tSort by stars\n" + "P\t\tSort by cuisine\n"
                + "Q\t\tQuit\n" + "R\t\tRemove a review\n"
                + "U\t\tAdd personal review to a local file\n" + "V\t\tRetrieve personal review from a local file\n"
                + "W\t\tSave reviews to a file\n"
                + "X\t\tUpload reviews from a file\n"
                + "T\t\t(admin) reset database\n"
                + "?\t\tDisplay Help\n");
    }

}
