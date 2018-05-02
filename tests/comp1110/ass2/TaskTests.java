package comp1110.ass2;

import comp1110.ass2.WarringStatesGame;
import org.junit.Test;

import java.util.Random;
import static org.junit.Assert.*;


public class TaskTests {
    @Test
    public void testLength () {
        String str = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        int length = random.nextInt(100);
        System.out.println(length+"!!!");
        String testStr = "";
        for (int i = 0; i < length; i++) {
            int location = random.nextInt(36);
            testStr += str.charAt(location);
        }
        System.out.println(testStr.length()+"???");
        if (length == 0) {
            assertFalse("Empty", WarringStatesGame.isPlacementWellFormed(testStr));
        } else if (length % 3 != 0) {
            assertFalse("Wrong Length", WarringStatesGame.isPlacementWellFormed(testStr));
        }
    }
}
