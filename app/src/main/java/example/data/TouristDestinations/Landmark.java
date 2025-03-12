package example.data.TouristDestinations;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class Landmark extends TouristDestination {

    private int year;
    private String description;



    public Landmark(String name, double xCoordinate, double yCoordinate, double rating, int pointVal, int year, String description){
        super(name, xCoordinate, yCoordinate, rating, pointVal);
        this.year = year;
        this.description = description;
    }
    public Landmark(JSONObject obj) throws JSONException {
        super(obj);
        this.year = obj.getInt("year");
        this.description = obj.getString("description");
    }
    //region Get Set properties

    //region Year
    public int getYear() {
        return this.year;
    }
    public void setYear(int newYear) {
        this.year = newYear;
    }
    //endregion

    //region Description
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }
    //endregion

    //endregion.

    public String yearDescription(){
        return "This landmark was built in " + year + ". That's "
                + yearDiffCalc(Calendar.getInstance().get(Calendar.YEAR))
                + " years ago!";
    }

    private int yearDiffCalc(int yearToCalc){

        return yearToCalc - this.year;
    }

}
