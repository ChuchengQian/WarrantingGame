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

    @Test
    public void testDisorder(){
        String str = " , ,A0, ,b5, ,A5, ,A7, ,A2, ,b2, ,A3, , ,c0, , ,c4, , , , , ,E3, , ,Z9,d1,g1, ,E0, ,d2";
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(str.split(",")));
        assertEquals(arrayList, util.placementSortToList("A0CA5Gb2MA3Od29Z93d14E30g15A2Kb5EA7Ic4Uc0RE07"));
    }
}