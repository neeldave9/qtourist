package example.data.TouristDestinations;
//import example.Lists.ReviewList;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.Math;

public abstract class TouristDestination {

    // Name of destination
    private String name;

    // Coordinates
    private double xLocation;
    private double yLocation;
    // Rating given by users
    private double rating;

    // Points given for visiting.
    private int pointVal;

    //Review list for each destination
//    private ReviewList reviewList;

    TouristDestination(String name, double x, double y, double rating, int pointVal) {
        this.name = name;
        this.xLocation = x;
        this.yLocation = y;
        this.rating = rating;
        this.pointVal = pointVal;
//        this.reviewList = new ReviewList();
    }

    TouristDestination(JSONObject obj) throws JSONException {
        this.name = obj.getString("name");
        this.xLocation = obj.getDouble("xCoordinate");
        this.yLocation = obj.getDouble("yCoordinate");
        this.rating = obj.getDouble("rating");
        this.pointVal = obj.getInt("pointVal");
    }
    //region Get, set properties for classes that implement this.


    //region Name of destination
    public String getName() {
        return this.name;
    }
    public void setName(String newName) {
        this.name = newName;
    }
    //endregion

    //region X coordinate.
    public double getXLocation(){
        return this.xLocation;
    }
    public void setXLocation(double newXLocation){
        this.xLocation = newXLocation;
    }
    //endregion

    //region Y coordinate.
    public double getYLocation(){
        return this.yLocation;
    }
    public void setYLocation(double newYLocation){
        this.yLocation = newYLocation;
    }
    //endregion

    //region Rating.
    public double getRating(){
        return this.rating;
    }
    public void setRating(double newRating){
        this.rating = newRating;
    }
    //endregion

    //region Point value for destination.
    public int getPointVal(){
        return this.pointVal;
    }
    public void setPointVal(int newPointVal){
        this.pointVal = newPointVal;
    }
    //endregion

    //endregion


    // Get distance from User location to tourist destination.
    public double distanceFromUser(double userX, double userY){
        double deltaY = this.yLocation - userY;
        double deltaX = this.xLocation - userX;

        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }


}
