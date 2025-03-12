package example.authentication;

import example.application.RegisterActivity;
import example.data.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class LoginTest {
    @Test
    public void testValidEmail() {
        assertDoesNotThrow(() -> {
            User validUser = new User("example", "test@example.com", "example");
        });
    }
    @Test
    public void testInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            User invalidEmail = new User("example", "test#example.com", "example");
        });
    }

    @Test
    public void testInvalidEmail1() {
        assertThrows(IllegalArgumentException.class, () -> {
            User invalidEmail = new User("example", "test.example@com", "example");
        });
    }

    @Test
    public void testInvalidEmail_Spaces() {
        assertThrows(IllegalArgumentException.class, () -> {
            User invalidEmail = new User("example", "test @ example.com", "example");
        });
    }

    @Test
    public void testValidPassword() {
        assertDoesNotThrow(() -> {
            User validUser = new User("example", "test@example.com", "example");
        });
    }

    @Test
    public void testInvalidPassword() {
        assertThrows(IllegalArgumentException.class, () -> {
            User invalidPassword = new User("example", "test@test.com", "dddd");
        });
    }

    @Test
    public void testInvalidPassword_Spaces() {
        assertThrows(IllegalArgumentException.class, () -> {
            User invalidPassword = new User("example", "test@test.com", "dddd 23");
        });
    }

    @Test
    public void testInvalidPassword_More() {
        assertThrows(IllegalArgumentException.class, () -> {
            User invalidPassword = new User("example", "test@test.com", "dddddddddddddddddddddddddddddddd");
        });
    }

    @Test
    public void testValidUsername() {
        assertDoesNotThrow(() -> {
            User validUser = new User("example", "test@example.com", "example");
        });
    }

    @Test
    public void testInvalidUsername() {
        assertThrows(IllegalArgumentException.class, () -> {
            User invalidPassword = new User("ex", "test@test.com", "dddd");
        });
    }

    @Test
    public void testInvalidUsername_Spaces() {
        assertThrows(IllegalArgumentException.class, () -> {
            User invalidPassword = new User("exa mple", "test@test.com", "dddd 23");
        });
    }

    @Test
    public void testInvalidUsername_More() {
        assertThrows(IllegalArgumentException.class, () -> {
            User invalidPassword = new User("example23123123123123123123123", "test@test.com", "dddddddddddddddddddddddddddddddd");
        });
    }
}

