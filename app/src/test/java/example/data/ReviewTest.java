package example.data;

import java.time.LocalDate;

import example.Lists.Review;
import example.data.TouristDestinations.Beach;
import static org.junit.Assert.*;
import org.junit.Test;
public class ReviewTest {

    public LocalDate currentDate = LocalDate.now();
    Beach beach = new Beach("testBeach", 1, 1, 1, 1, 2);
    User alice = new User("Alice", "example@example.com", "password123!" );
    Review review = new Review(3, "Great beach :)", currentDate, beach, alice);

    //Tests all the getters for the review class. Ensures that when a review is shown using a GUI, it will be in a readable format.
    @Test
    public void testRating(){
        assertEquals(3, review.getRating());
    }
    @Test
    public void testComments(){
        assertEquals("Great beach :)",review.getComments());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidComments() {
        Review invalidComment = new Review(2, "ok", currentDate, beach, alice);
    }
    @Test
    public void testDestinationName(){
        assertEquals("testBeach", review.getTouristDestinationName());
    }
    @Test
    public void testUserName(){
        assertEquals("Alice", review.getUser());
    }

    //Tests Date to ensure that its in day-month-year format and not by min/sec/hr format.
    @Test
    public void testDate(){
        assertEquals(LocalDate.now(), review.getDatePosted());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidRating() {
        Review invalidReview = new Review(-1, "Bad Beach ;(", currentDate, beach, alice);
    }
}