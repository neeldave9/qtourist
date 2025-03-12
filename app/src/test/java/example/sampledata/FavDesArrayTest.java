package example.sampledata;

import example.data.FavDesArray;
import example.data.FavDestination;
import org.junit.Test;
import static org.junit.Assert.*;
public class FavDesArrayTest {
    FavDestination F1 = new FavDestination(3,
            "Rainbow Bay",
            FavDestination.LocationType.Beach,
            "Wednesday, 19th April",
            "Would like to visit during weekdays as it will be less populated"
    );
    FavDestination F2 = new FavDestination(5,
            "Albert Park",
            FavDestination.LocationType.Park,
            "Wednesday, 26th April",
            ""
    );

    FavDesArray testFavDesArray = new FavDesArray();

    //Test whether size of array increases after using the add method
    @Test
    public void testAddFavDes(){
        testFavDesArray.addFavDes(F1);
        testFavDesArray.addFavDes(F2);
        assertEquals(2, testFavDesArray.getFavDesArray().size());
    }

    //Test whether size of array decreases after using the remove method
    @Test
    public void testRemoveFavDes(){
        testFavDesArray.addFavDes(F1);
        testFavDesArray.addFavDes(F2);
        testFavDesArray.removeFavDes(F1);
        assertEquals(1, testFavDesArray.getFavDesArray().size());
    }
}
