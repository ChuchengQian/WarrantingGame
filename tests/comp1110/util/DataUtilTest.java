package comp1110.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class DataUtilTest {
    private DataUtil util = new DataUtil();

    @Test
    public void testNull(){
        assertEquals(null, util.placementSortToList(""));
    }

    @Test
    public void testOne(){
        String str = " , , , , , , , , , , , , , , , , , , , , , , , ,A0, , , , , , , , , , , ";
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(str.split(",")));
        assertEquals(arrayList, util.placementSortToList("A0Y"));
    }

    @Test
    public void testTwo(){
        String str = " , , , ,f0, , , , , , , , , , , , , , , , , , , ,A0, , , , , , , , , , , ";
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(str.split(",")));
        assertEquals(arrayList, util.placementSortToList("f0EA0Y"));
    }
}