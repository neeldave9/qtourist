package example.application;

import example.data.Common;
import example.data.User;
import org.junit.Test;
//import org.mockito.Mockito;
import android.os.Looper;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    // Test testFunc Function.
    public void testFunc_isCorrect() {
        assertEquals(1, testFunc("test"));
    }

    @Test
    // Test if Common.java is there
    public void commonImported(){
        assertEquals(0, Common.returnNum());
    }


    // Unimportant code for trying out tests.
    private int testFunc(String x) {
        if(x.length() > 3) {
            return 1;
        }
        else if (x.length() == 3) {
            return 2;
        }
        return 0;
    }
}