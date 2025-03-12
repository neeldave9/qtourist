package example.application;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import example.data.StaticUserDAO;
import example.data.User;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the main view of the application.
 */
public class MainViewActivity extends AppCompatActivity {
    StaticUserDAO staticUserDAO = RegisterActivity.staticUserDAO;

    // Declare class variables for the views
    private TextView userEmailView;
    private TextView userNameView;
    private Button logoutButton;
    private Button changeEmailButton;
    private Button changePassButton;
    private Button deleteButton;
    private TextView passwordTextView;
    private ArrayAdapter<User> usersAdapter;
    private FloatingActionButton mapViewButton;

    /**
     * Creates the main view.
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        // Initialize the views
        userEmailView = findViewById(R.id.user_email_textview);
        logoutButton = findViewById(R.id.logout_button);
        userNameView = findViewById(R.id.username_view);
        mapViewButton = findViewById(R.id.mapViewButton);
        passwordTextView = findViewById(R.id.newPassword_textview);
        changeEmailButton = findViewById(R.id.change_email_button);
        changePassButton = findViewById(R.id.change_password_button);
        deleteButton = findViewById(R.id.delete_button);


        // Retrieve the current logged in user from the Intent extras
        Intent intent = getIntent();

        String currentUser = intent.getStringExtra("CURRENT_USER");
        User userObj = staticUserDAO.getUserName(currentUser);
        userNameView.setText("Welcome, " + currentUser + ".\n" +
                "You have " + userObj.getPoints() + " points.");
        userEmailView.setText(staticUserDAO.getUserName(currentUser).getEmail());

        // Set up the Map floating button click listener.
        mapViewButton.setOnClickListener(v -> onMapViewButtonClick());

        // Set up the logout button click listener
        logoutButton.setOnClickListener(v -> logOutButtonClick());

        // Set up the change email button click listener
        changeEmailButton.setOnClickListener(v -> onChangeEmailButtonClick());

        // Set up the change password button click listener
        changePassButton.setOnClickListener(v -> onChangePasswordButtonClick());

        // Set up the delete button click listener
        deleteButton.setOnClickListener(v -> onDeleteButtonClick());
    }

    private void onMapViewButtonClick(){
        // Transition to the map view
        launchMapView();
        finish();
    }
    private void onChangeEmailButtonClick(){
        // retrieve current username
        Intent intent = getIntent();
        String currentUser = intent.getStringExtra("CURRENT_USER");

        // get email from email field
        String newEmail = userEmailView.getText().toString();

        try{
            // change email
            staticUserDAO.getUserName(currentUser).setEmail(newEmail);

            // Transition to the login view
            launchLoginView();
            finish();
        } catch (IllegalArgumentException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void onChangePasswordButtonClick(){
        // retrieve current username
        Intent intent = getIntent();
        String currentUser = intent.getStringExtra("CURRENT_USER");

        // get password from password field
        String newPassword = passwordTextView.getText().toString();

        try{
            // change password
            staticUserDAO.getUserName(currentUser).setPassword(newPassword);

            // Transition to the login view
            launchLoginView();
            finish();
        } catch (IllegalArgumentException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void onDeleteButtonClick(){
        // retrieve current username
        Intent intent = getIntent();
        String currentUser = intent.getStringExtra("CURRENT_USER");

        // delete user
        staticUserDAO.deleteUserName(currentUser);

        // Transition to the login view
        launchLoginView();
        finish();
    }

    private void launchMapView() {
        // Create new Activity that leads to MainViewActivity.
        Intent intent = new Intent(this, MapsActivity.class);

        // Original Username from the user class instantiated in LoginActivity.
            Intent originalIntent = getIntent();
            String currentUser = originalIntent.getStringExtra("CURRENT_USER");
            intent.putExtra("CURRENT_USER", currentUser);

        // TODO(Geoffrey): Look into StaticUserDAO.
        startActivity(intent);
    }

    private void launchLoginView() {
        // Clear the back stack
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    /**
     * Event handler for the logout button click event.
     */
    private void logOutButtonClick() {
        // Transition to the login view
        launchLoginView();
        finish();
    }

    /**
     * Represents the adapter for the user list view. This adapter is responsible for
     * populating the ListView with the users.
     */
    public class UserListAdapter extends ArrayAdapter<User> {

        /**
         * Creates a new UserListAdapter.
         * @param context the context
         * @param users the list of users to display
         */
        public UserListAdapter(Context context, List<User> users) {
            super(context, 0, users);
        }

        /**
         * Gets the view for the user at the specified position.
         * @param position the position
         * @param convertView the view to convert
         * @param parent the parent view
         * @return the view
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            User user = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            // This view use the item_user layout as the template
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
            }

            // Lookup views within item layout
            TextView emailTextView = convertView.findViewById(R.id.email_textview);
            TextView nameTextView = convertView.findViewById(R.id.name_textview);
            TextView pointsTextView = convertView.findViewById(R.id.points_textview);

            // Populate the data into the template view using the data object
            emailTextView.setText(user.getEmail());
            nameTextView.setText(user.getName());
            pointsTextView.setText(user.getPoints());

            // Return the completed view to render on screen
            return convertView;
        }


    }
}
