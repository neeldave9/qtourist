package example.Lists;

import example.data.TouristDestinations.TouristDestination;
import example.data.User;

import java.time.LocalDate;


public class Review {

    //initialising all the required variables
    private int rating;
    private String comments;
    private LocalDate datePosted;
    private TouristDestination touristDestination;
    private User user;

    public Review(int rating, String comments, LocalDate datePosted, TouristDestination touristDestination, User user){
        setRating(rating);
        setComments(comments);
        this.datePosted = datePosted;
        this.touristDestination = touristDestination;
        this.user = user;
    }

    //Returns the rating the user gave the tourist destination
    public int getRating() {
        return rating;
    }

    //Setter for ratings with validation
    public void setRating(int Rating){
        if (Rating < 0 || Rating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5.");
        } else{
            this.rating = Rating;
        }
    }

    //Setter for comments with validation of characters
    public void setComments(String comments){
        if(comments.length()<10) {
            throw new IllegalArgumentException("Comments must have more than 10 characters.");
        } else {
            this.comments = comments;
        }
    }

    //Method returns string representation of the user comments of a particular destination.
    public String getComments(){
        return comments;
    }

    //Methods returns the date the review was posted.
    public LocalDate getDatePosted(){
        return datePosted;
    }

    //Method to return the tourist destination name
    public String getTouristDestinationName() {
        return touristDestination.getName();
    }

    //Method to return the user's Name for the review
    public String getUser() {
        return user.getName();
    }
}