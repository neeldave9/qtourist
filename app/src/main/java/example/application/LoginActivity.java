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
 * Represents the login view of the application.
 */
public class LoginActivity extends AppCompatActivity {
    // Declare class variables for the views
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button forgotPasswordButton;
    private Button loginButton;
    private Button registerButton;

    // Use the StaticUserDAO to access the users
    StaticUserDAO staticUserDAO = RegisterActivity.staticUserDAO;
    private User currentUser;

    private void showKeyboard(EditText field) {
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
        setContentView(R.layout.activity_login);

        // Initialize the views
        emailEditText = findViewById(R.id.email_edittext);
        passwordEditText = findViewById(R.id.password_edittext);
        loginButton = findViewById(R.id.reset_button);
        registerButton = findViewById(R.id.register_button);
        forgotPasswordButton = findViewById(R.id.back_button);

        // Set up a click listener for the forgot password button
        forgotPasswordButton.setOnClickListener(v -> onForgotPasswordButtonClick());
//        forgotPasswordButton.setVisibility(View.GONE);
        // Set up a click listener for the login button
        loginButton.setOnClickListener(v -> onLoginButtonClick());
        // Set up a click listener for the register button
        registerButton.setOnClickListener(v -> onRegisterButtonClick());
    }

    /*
     * Event handler for the forgot password button click event.
     */
    private void onForgotPasswordButtonClick() {
        // Transition to the forgot password view
//        launchForgotPasswordView();
//        finish();
    }

    /*
     * Event handler for the register button click event.
     */
    private void onRegisterButtonClick() {
        // Transition to the register view
        launchRegisterView();
        finish();
    }

    /*
     * Event handler for the login button click event.
     */
    private void onLoginButtonClick(){
        // Get the values from the views
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        boolean success = false;
        try {
            success = logIn(email, password);
        }
        catch (Exception e) {
            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        // Log the user in
        if(success){
            // Welcome message
            Toast.makeText(LoginActivity.this, R.string.welcome + currentUser.getName(), Toast.LENGTH_SHORT).show();

            // Transition to the main view
            launchMapsView();
            finish();
        } else {
            // Show an error message
            Toast.makeText(LoginActivity.this, R.string.error_message_user_not_found, Toast.LENGTH_SHORT).show();
        }

        // Show the soft keyboard
        showKeyboard(emailEditText);
    }

    // Check if user matches in database
    private boolean matchedUser(User user, String email, String password) {
        return user.getEmail().equals(email) && user.getPassword().equals(password);
    }

    /**
     * Logs the user in.
     * @param email the email
     * @param password the password
     * @return true if the user was logged in, false otherwise
     */
    private boolean logIn(String email, String password) {
        for (User user : staticUserDAO.listUsers()) {
            if (matchedUser(user, email, password)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    /**
     * Launches the maps view.
     */
    private void launchMapsView() {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("CURRENT_USER", currentUser.getName());
        startActivity(intent);
    }

    /**
     * Launches the Registration view.
     */
    private void launchRegisterView() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
    /**
     * Launches the Forgot Password view.
     */
    private void launchForgotPasswordView() {
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        startActivity(intent);
    }
}