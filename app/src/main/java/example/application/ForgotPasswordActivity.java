package example.application;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import example.data.StaticUserDAO;
import example.data.User;

/**
 * Represents the Registration view of the application.
 */
public class ForgotPasswordActivity extends AppCompatActivity {
    // Declare class variables for the views
    private EditText emailEditText;
    private EditText newPasswordEditText;
    private Button resetButton;
    private Button backButton;

    // Declare class variables for the DAO and current user
    StaticUserDAO staticUserDAO = RegisterActivity.staticUserDAO;
    private User currentUser;

    /**
     * Creates the login view.
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // Initialize the views
        emailEditText = findViewById(R.id.email_edittext);
        newPasswordEditText = findViewById(R.id.password_edittext);
        resetButton = findViewById(R.id.reset_button);
        backButton = findViewById(R.id.back_button);

        // Set up a click listener for the register button
//        resetButton.setOnClickListener(v -> onResetButtonClick());

        // Set up a click listener for the sign-in button
        backButton.setOnClickListener(v -> onBackButtonClick());
    }

    /**
     * Check User already exists
     */
    private boolean checkUserExists(String email) {
        for (User user : staticUserDAO.listUsers()) {
            if (user.getEmail().equals(email)) {
                emailEditText.setError("Email already exists");
                emailEditText.requestFocus();
                return true;
            }
        }
        return false;
    }

    /**
     * Event handler for the register button click event.
     */
//    private void onResetButtonClick() {
//
//        // Get the values from the EditTexts
//        String email = emailEditText.getText().toString();
//        String newPassword = newPasswordEditText.getText().toString();
//        boolean success = false;
//
//        try {
//            success = reset(email, newPassword);
//        }
//        catch (Exception e) {
//            Toast.makeText(ForgotPasswordActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//            registerButton.setEnabled(true);
//        }
//
//        if (success) {
//            // Show a toast message
//            Toast.makeText(this, "Registration successful", Toast.LENGTH_LONG).show();
//
//            // Transition to the map view
//            launchMapsView();
//            finish();
//        }
//
//        // Show the soft keyboard
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.showSoftInput(nameEditText, InputMethodManager.SHOW_IMPLICIT);
//    }

    /**
     * Event handler for the login button click event.
     */
    private void onBackButtonClick() {
        // Transition to the login view
        launchLoginView();
        finish();
    }

    /**
     * Logs the newly registered user in.
     * @param email the email
     * @param newPassword the password
     * @return true if the user was logged in, false otherwise
     */
    private boolean reset(String email, String newPassword){
        // Check if the user exists
        if (!checkUserExists(email)) {
            Toast.makeText(this, "User does not exist", Toast.LENGTH_LONG).show();
            return false;
        }

        // Get the user
        currentUser = staticUserDAO.getUser(email);

        // Update the user's password
        try{
            currentUser.setPassword(newPassword);
        }
        catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    /**
     * Launches the login view.
     */
    private void launchLoginView() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}