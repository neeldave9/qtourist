package example.data.TouristDestinations;


import org.json.JSONException;
import org.json.JSONObject;

public class Beach extends TouristDestination {

    private int swimmingAreas;

    public Beach(String name, double xCoordinate, double yCoordinate, double rating, int pointVal, int swimmingAreas){
        super(name, xCoordinate, yCoordinate, rating, pointVal);
        this.swimmingAreas = swimmingAreas;
    }

    public Beach(JSONObject obj) throws JSONException {
        super(obj);
        this.swimmingAreas = obj.getInt("swimmingAreas");

    }
    //region Swimming Areas
    public int getSwimmingAreas() {
        return this.swimmingAreas;
    }
    public void setSwimmingAreas(int newSwimmingAreas) {
        this.swimmingAreas = newSwimmingAreas;
    }
    //endregion



}
