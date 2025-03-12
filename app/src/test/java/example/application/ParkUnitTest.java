package example.application;

import com.google.android.gms.common.internal.FallbackServiceBroker;
import example.data.TouristDestinations.Park;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
public class ParkUnitTest {

    Park p = new Park("Springbrook National Park",
            28,
            153,
            4.8,
            1,
            false,
            0,
            12);

    //Test: Get the status of whether the park is suitable for camping
    @Test
    public void testGetCampStatus(){
        assertFalse(p.getCampStatus());
    }

    //Test: Set the status of whether the park is suitable for camping
    @Test
    public void testSetCampStatus(){
        p.setCampStatus(true);
        assertTrue(p.getCampStatus());
    }


    //Test: Get the number of playgrounds at the park
    @Test
    public void testGetPlayground(){
        assertEquals(0,p.getPlaygrounds());
    }

    //Test: Set the number of playgrounds at the park
    @Test
    public void testSetPlayground(){
        p.setPlaygrounds(4);
        assertEquals(4, p.getPlaygrounds());
    }

    //Test: Get the number of picnic areas at the park
    @Test
    public void testGetPicnicAreas(){
        assertEquals(12,p.getPicnicAreas());
    }

    //Test: Set the number of picnic areas at the park
    @Test
    public void testSetPicnicAreas(){
        p.setPicnicAreas(1);
        assertEquals(1, p.getPicnicAreas());
    }




}
