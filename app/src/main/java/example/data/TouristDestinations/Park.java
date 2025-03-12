package example.data.TouristDestinations;

import org.json.JSONException;
import org.json.JSONObject;

public class Park extends TouristDestination{

    // Whether the park is suitable for camping
    private boolean campStatus;
    // The number of playgrounds found at the park
    private int playgrounds;
    // The number of picnic areas found at the park
    private int picnicAreas;

    public Park(String name, double xCoordinate, double yCoordinate, double rating, int pointVal, boolean campStatus, int playgrounds, int picnicAreas) {
        super(name, xCoordinate, yCoordinate, rating, pointVal);
        this.campStatus = campStatus;
        this.playgrounds = playgrounds;
        this.picnicAreas = picnicAreas;
    }


    // Camp status get set methods
    public boolean getCampStatus(){
        return this.campStatus;
    }
    public void setCampStatus(boolean newCampStatus){
        this.campStatus = newCampStatus;
    }

    // Playground areas get set methods
    public int getPlaygrounds(){
        return this.playgrounds;
    }
    public void setPlaygrounds(int updatedPlaygrounds){
        this.playgrounds = updatedPlaygrounds;
    }

    // Picnic Areas get set methods
    public int getPicnicAreas(){
        return this.picnicAreas;
    }
    public void setPicnicAreas(int updatedPicnicAreas){
        this.picnicAreas = updatedPicnicAreas;
    }

}
