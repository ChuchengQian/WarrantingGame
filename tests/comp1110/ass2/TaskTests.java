package comp1110.ass2;

import comp1110.ass2.WarringStatesGame;
import org.junit.Test;

import java.util.Random;
import static org.junit.Assert.*;


public class TaskTests {
    private final String str1 = "abcdefghijklmnopqrstuvwxyz";
    private final String str2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String str3 = "0123456789";
    @Test
    public void testLength () {
        Random random = new Random();
        int length = random.nextInt(100);
        String testStr = "";
        String strT = str1 + str2 + str3;
        for (int i = 0; i < length; i++) {
            int location = random.nextInt(62);
            testStr += strT.charAt(location);
        }
        if (length == 0) {
            assertFalse("Empty", WarringStatesGame.isPlacementWellFormed(testStr));
        } else if (length % 3 != 0) {
            assertFalse("Wrong Length", WarringStatesGame.isPlacementWellFormed(testStr));
        }
    }

    @Test
    public void testCardPlacement () {
        Random random = new Random();
        String testStr = "";
        String strT = str1 + str2 + str3;
        for (int i = 0; i < 3; i++) {
            int location = random.nextInt(62);
            testStr += strT.charAt(location);
        }
        if (strT.charAt(0) != 'z') {
            if (strT.charAt(0) < 'a' || strT.charAt(0) > 'g')
                assertFalse("Wrong Format", WarringStatesGame.isCardPlacementWellFormed(testStr));
        } else if (strT.charAt(2) > 'Z' || strT.charAt(2) < 'A') {
            assertFalse("Wrong Format", WarringStatesGame.isCardPlacementWellFormed(testStr));
        } else if (strT.charAt(0) >= 'a' && strT.charAt(0) <= 'g') {
            if (strT.charAt(1) < '0' || strT.charAt(1) > '9') {
                assertFalse("Wrong Foramt", WarringStatesGame.isCardPlacementWellFormed(testStr));
            }
        } else if (strT.charAt(0) == 'z') {
            if (strT.charAt(1) != '9') {
                assertFalse("Wrong Format", WarringStatesGame.isCardPlacementWellFormed(testStr));
            }
        }

    }


}
