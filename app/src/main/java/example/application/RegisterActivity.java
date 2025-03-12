package example.application;

import android.content.Context;
import android.content.Intent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import example.data.StaticUserDAO;
import example.data.User;

/**
 * Represents the Registration view of the application.
 */
public class RegisterActivity extends AppCompatActivity {
    // Declare class variables for the views
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button registerButton;
    private Button signInButton;

    // Declare class variables for the DAO and current user
    protected static final StaticUserDAO staticUserDAO = new StaticUserDAO();
    private User currentUser;

    // Store all error messages
    public void showKeyboard(EditText field) {
        // Show the soft keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(field, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * Creates the login view.
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize the views
        nameEditText = findViewById(R.id.name_edittext);
        emailEditText = findViewById(R.id.email_edittext);
        passwordEditText = findViewById(R.id.password_edittext);
        registerButton = findViewById(R.id.register_button);
        signInButton = findViewById(R.id.signin_button);

        // Set up a click listener for the register button
        registerButton.setOnClickListener(v -> onRegisterButtonClick());

        // Set up a click listener for the sign-in button
        signInButton.setOnClickListener(v -> onSignInButtonClick());
    }

    /**
     * Check User already exists
     */
    private boolean checkUserExists(String email, String username) {
        for (User user : staticUserDAO.listUsers()) {
            if (checkEmail(email, user)) return true;
            if (checkUsername(username, user)) return true;
        }
        return false;
    }

    private boolean checkUsername(String username, User user) {
        if (user.getName().equals(username)) {
            nameEditText.setError("Username already exists");
            nameEditText.requestFocus();
            return true;
        }
        return false;
    }

    private boolean checkEmail(String email, User user) {
        if (user.getEmail().equals(email)) {
            emailEditText.setError("Email already exists");
            emailEditText.requestFocus();
            return true;
        }
        return false;
    }

    /**
     * Event handler for the register button click event.
     */
    private void onRegisterButtonClick() {

        // Get the values from the EditTexts
        String username = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        boolean success = false;

        try {
            success = register(email, password, username);
        }
        catch (Exception e) {
            Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            registerButton.setEnabled(true);
        }

        if (success) {
            // Show a toast message
            Toast.makeText(this, R.string.message_registration_successful, Toast.LENGTH_LONG).show();

            // Transition to the map view
            launchMapsView();
            finish();
        }

        // Show the soft keyboard
        showKeyboard(nameEditText);
    }

    /**
     * Event handler for the login button click event.
     */
    private void onSignInButtonClick() {
        // Transition to the login view
        launchLoginView();
        finish();
    }

    /**
     * Logs the newly registered user in.
     * @param email the email
     * @param password the password
     * @param name the name
     * @return true if the user was logged in, false otherwise
     */
    private boolean register(String email, String password, String name){
        // Check if email and username already exists
        if (checkUserExists(email, name)) {
            return false;
        }

        // Add the user
        currentUser = new User(name, email, password);
        staticUserDAO.addUser(currentUser);

        // Always log the user in.
        return true;
    }

    /**
     * Launches the login view.
     */
    private void launchLoginView() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Launches the maps view.
     */
    private void launchMapsView() {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("CURRENT_USER", currentUser.getName());
        intent.putExtra("EMAIL", currentUser.getEmail());
        startActivity(intent);
    }
}