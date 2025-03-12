package example.application;

import example.data.TouristDestinations.Landmark;
import org.junit.Test;
import static org.junit.Assert.*;

public class LandmarkUnitTest {

    Landmark l = new Landmark("testChurch",
            5.3,
            2.3,
            1.4,
            1,
            2,
            "This church has a lot of history");


    @Test
    public void landmarkConstructorTest() {
        assertEquals("testChurch", l.getName());
        // TODO(Geoffrey): test super constructor.
    }

    @Test
    public void testGetYear(){ assertEquals(2, l.getYear());}
    @Test
    public void testSetYear(){
        l.setYear(2023);
        assertEquals(2023, l.getYear());
    }
    @Test
    public void yearDescriptionTest() {
        assertEquals(
                "This landmark was built in 2. That's 2021 years ago!",
                l.yearDescription()
                );
    }
    @Test
    public void polymorphismTest() {
        assertEquals("testChurch",l.getName());
    }
    @Test
    public void wrongLocationTest() {
        assertNotEquals(null, l.getXLocation());
        assertNotEquals(null, l.getYLocation());
        assertEquals(5.3, 5.3, l.getXLocation());
        assertEquals(2.3, 2.3, l.getYLocation());
    }
}
