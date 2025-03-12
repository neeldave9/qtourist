package example.data;

public class FavDestination {
    //Initialising required variables for the class
    private int priority;
    private String touristDestination;
    private FavDestination.LocationType locationType;
    public enum LocationType { //Taken from the location types we have entered
        Beach,
        Landmark,
        RestaurantCafe,
        Park
    }
    private String dateGoingToVisit;
    private String notes;

    public FavDestination(int priority, String touristDestination, FavDestination.LocationType locationType, String dateGoingToVisit, String notes){
        this.priority = priority;
        this.touristDestination = touristDestination;
        this.locationType = locationType;
        this.dateGoingToVisit = dateGoingToVisit;
        this.notes = notes;
    }

    //Returns priority the user is giving the destination
    public int getPriority() { if (priority < 0 || priority > 10){ //Priority limited to between 0-10
        throw new IllegalArgumentException("Priority level must be within 0-10");
    } else{
        return priority;
        }
    }

    //Method that returns tourist destination entered by user
    public String getTouristDestination() { if (touristDestination != null){
        return touristDestination; //Tourist destination string cannot be null
    } else{
        throw new NullPointerException("This field cannot be empty");
        }
    }

    //Method to return the type of location (selected from the options)
    public FavDestination.LocationType getLocationType() {
        return locationType;
    }

    //Method to retrieve the date to visit the decided location
    public String getDateGoingToVisit() {
        return dateGoingToVisit;
    }

    //Method to retrieve notes on the item
    public String getNotes() {
        return notes;
    }
}

