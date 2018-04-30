package comp1110.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class DataUtilTest {
    DataUtil util = new DataUtil();

    @Test
    public void testNull(){
        assertEquals(null, util.placementSortToList(""));
    }
}