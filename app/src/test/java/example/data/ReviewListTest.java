package example.data;

import example.Lists.Review;
import example.Lists.ReviewList;

import org.junit.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import example.data.TouristDestinations.Beach;
import static org.junit.Assert.*;

public class ReviewListTest {
    public LocalDate currentDate = LocalDate.now();
    Beach beach = new Beach("testBeach", 1, 1, 1, 1, 2);
    User alice = new User("Alice", "alice@example.com", "IamAwesome123!" );
    Review review = new Review(3, "Great beach :)", currentDate, beach, alice);
    Review review2 = new Review(2, "okay beach :)", currentDate, beach, alice);

    ReviewList testBeachList = new ReviewList();


    //Tests if the adding a review method increases the size of the Review List
    @Test
    public void testAddReview() {
        testBeachList.addToList(review);
        testBeachList.addToList(review2);
        assertEquals(2, testBeachList.getList().size());
    }
    //Tests if the removing a review decreases the size of the Review List
    @Test
    public void testRemoveReview() {
        testBeachList.addToList(review);
        testBeachList.addToList(review2);
        testBeachList.removeFromList(review2);
        assertEquals(1, testBeachList.getList().size());
    }

    //Tests to check reviews for tourist destinations can be filtered by Name.
    //Opportunity to refactor the classes for better design and include ID as unique identifier as there may be beaches with the same/similar name.
    @Test
    public void testGetReviewByTouristDestination() {
        testBeachList.addToList(review);
        testBeachList.addToList(review2);
        ArrayList<Review> beachReviews = testBeachList.getByTouristDestination("testBeach");
        assertEquals(2, beachReviews.size());
    }
}