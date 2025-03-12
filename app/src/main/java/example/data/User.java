package example.data;

import android.widget.Toast;
import example.data.TouristDestinations.Beach;
import example.data.TouristDestinations.Landmark;
import example.data.TouristDestinations.TouristDestination;

import java.util.ArrayList;

/**
 * Represents a user of the application.
 */
public class User {
    private String name;
    private String email;
    private String password;
    private int points;
    // A bonus number of points given when signing up for the first time.
    private int signUpBonus = 100;

    private ArrayList savedLocations;

    /**
     * Creates a new User.
     *
     * @param name     The name of the user.
     * @param password The password of the user.
     * @param email    The email of the user.
     */
    public User(String name, String email, String password) {
        setEmail(email);
        setName(name);
        setPassword(password);
        this.points = signUpBonus;
        //TODO(Geoffrey) check this doesn't break when different objects added.
        this.savedLocations  = new ArrayList();
    }

    /**
     * Gets the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the email of the user.
     *
     * @return The email of the user.
     */
    public String getEmail() { return email; }

    /**
     * Sets the name of the user.
     *
     * @param newName The new name of the user.
     */
    public void setName(String newName) {
        if(newName.isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        else if (newName.length() < 3 || newName.length() > 20) {
            throw new IllegalArgumentException("Name must be between 3 and 20 characters.");
        }
        else if(newName.contains(" ") || newName.contains("\t") || newName.contains("\n")){
            throw new IllegalArgumentException("Name cannot contain spaces");
        }
        else{
            this.name = newName;
        }
    }

    /**
     * Sets the password of the user.
     *
     * @param newPassword The new password of the user.
     */
    public void setPassword(String newPassword) {
        if(newPassword.isEmpty()){
            throw new IllegalArgumentException("Password cannot be empty");
        }
        else if (newPassword.length() < 6 || newPassword.length() > 20) {
            throw new IllegalArgumentException("Password must be between 6 and 20 characters.");
        }
        else if(newPassword.contains(" ") || newPassword.contains("\t") || newPassword.contains("\n")){
            throw new IllegalArgumentException("Password cannot contain spaces");
        }
        else{
            this.password = newPassword;
        }
    }

    /**
     * Sets the email of the user.
     *
     * @param newEmail The new email of the user.
     */

    public void setEmail(String newEmail) {
        if(newEmail.isEmpty()){
//            Toast.makeText("Email cannot be empty", Toast.LENGTH_SHORT).show();
            throw new IllegalArgumentException("Email cannot be empty");
        }
        else if (!newEmail.contains("@") || !newEmail.contains(".") || newEmail.indexOf("@") > newEmail.indexOf(".") || newEmail.indexOf("@") == 0) {
            throw new IllegalArgumentException("Email must be valid email address format (e.g. example@example.com)");
        }
        else if(newEmail.contains(" ") || newEmail.contains("\t") || newEmail.contains("\n")){
            throw new IllegalArgumentException("Email cannot contain spaces");
        }
        else{
            this.email = newEmail;
        }
    }

    /**
     * Gets the points of the user.
     *
     * @return int with the user's points
     */
    public String getPoints() {
        return Integer.toString(points);
    }

    /**
     * Add points to the user's account.
     *
     * @param addedPoints The number of points to be added to the user
     */
    public void addPoints(int addedPoints) {
        this.points += addedPoints;
    }

    public void saveLocation(TouristDestination location){
        savedLocations.add(location);
    }

    //TODO(Geoffrey) return nicely formatted location query.
    public ArrayList getSavedLocations() {
        return savedLocations;
    }
}
