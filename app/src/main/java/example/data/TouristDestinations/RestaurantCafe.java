package example.data.TouristDestinations;

import org.json.JSONException;
import org.json.JSONObject;

public class RestaurantCafe extends TouristDestination {

    private int capacity;
    private Specialty specialty;
    // Specialties are taken from Google Maps
    public enum Specialty {
        FRENCH,
        ASIAN_FUSION,
        CHINESE,
        GREEK,
        INDIAN,
        ITALIAN,
        JAPANESE,
        LEBANESE,
        MALAYSIAN,
        MODERN_AUSTRALIAN,
        MIDDLE_EASTERN,
        SEAFOOD,
        SUSHI,
        BAKERY,
        THAI,
        VIETNAMESE,
        CONTEMPORARY,
        CAFE
    };

    public RestaurantCafe(String name, double xCoordinate, double yCoordinate, double rating, int pointVal, int capacity, Specialty specialty){
        super(name, xCoordinate, yCoordinate, rating, pointVal);
        this.capacity = capacity;
        this.specialty = specialty;
    }
    public RestaurantCafe(JSONObject obj) throws JSONException {
        super(obj);
        this.capacity = obj.getInt("capacity");
        this.specialty = Specialty.valueOf(obj.getString("specialty"));
    }

    //region Get Set properties

    //region Capacity
    public int getCapacity() {
        return this.capacity;
    }
    public void setCapacity(int newCapacity) {
        this.capacity = newCapacity;
    }
    //endregion

    //region Specialty
    public Specialty getSpecialty() {
        return this.specialty;
    }
    public void setName(Specialty newSpecialty) {
        this.specialty = newSpecialty;
    }
    //endregion

    //endregion.



}
