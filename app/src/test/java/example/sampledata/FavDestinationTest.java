package example.sampledata;

import example.data.FavDestination;
import org.junit.Test;
import static org.junit.Assert.*;
public class FavDestinationTest {
    FavDestination F = new FavDestination(3,
            "Rainbow Bay",
            FavDestination.LocationType.Beach,
            "Wednesday, 19th April",
            "Would like to visit during weekdays as it will be less populated"
    );

    //Run tests for each value
    @Test
    public void testPriority() {
        assertEquals(3, F.getPriority());
    }
    @Test
    public void testTouristDestination(){
        assertEquals("Rainbow Bay", F.getTouristDestination());
    }
    @Test
    public void testLocationType() {
        assertEquals(FavDestination.LocationType.Beach, F.getLocationType());
    }
    @Test
    public void testVisitDate(){
        assertEquals("Wednesday, 19th April", F.getDateGoingToVisit());
    }
    @Test
    public void testNotes(){
        assertEquals("Would like to visit during weekdays as it will be less populated",F.getNotes());
    }
}
