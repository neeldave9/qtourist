package example.Lists;

//Import relevant packages

import java.util.ArrayList;

public class ReviewList implements DataLists<Review>  {
    private ArrayList<Review> reviewList;
    public ReviewList(){
        reviewList = new ArrayList<>();
    }
    //method to add a review to the list.


    public void addToList(Review review){
        reviewList.add(review);
    }
    //method to remove a review from the list.
    public void removeFromList(Review review) {reviewList.remove(review);}

    public ArrayList<Review> getList() {
        return reviewList;
    }

    //Method which shows the list of reviews for each tourist destination through iteration and conditional statements.
    public ArrayList<Review> getByTouristDestination(String touristDestinationName){
        ArrayList<Review> destinationReviews = new ArrayList<>();
        for(Review review : reviewList){
            if(review.getTouristDestinationName().equals(touristDestinationName)){
                destinationReviews.add(review);
            }
        }
        return destinationReviews;
    }

}