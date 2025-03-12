package example.application;

import example.data.TouristDestinations.Beach;
import org.junit.Test;
import static org.junit.Assert.*;

public class BeachUnitTest {
    Beach b = new Beach("testBeach", 1, 1,
            1, 1, 2);
    @Test
    public void beachConstructorTest() {
        assertEquals("testBeach", b.getName());
        //TODO(Geoffrey): test super constructor.
    }

    @Test
    public void beachGetSwimmingAreasTest() {
        assertEquals(2, b.getSwimmingAreas());
    }

    @Test
    public void beachSetSwimmingAreasTest() {
        b.setSwimmingAreas(3);

        assertEquals(3, b.getSwimmingAreas());
    }
    @Test
    public void polymorphismTest() {
        assertNotEquals("testChurch",b.getName());
        assertEquals("testBeach", b.getName());
    }
    @Test
    public void wrongLocationTest(){
        assertNotEquals(null, b.getXLocation());
        assertNotEquals(null, b.getYLocation());
        assertEquals(1, 1.0, b.getXLocation());
        assertEquals(1, 1.0, b.getYLocation());
    }
}
