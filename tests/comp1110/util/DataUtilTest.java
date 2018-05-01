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
        String str = " , , , , , , , , , , , , , , , , , , , , , , , ,a0, , , , , , , , , , , ";
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(str.split(",")));
        assertTrue(util.placementSortToList("a0Y").equals(arrayList));
    }

    @Test
    public void testTwo(){
        String str = " , , , ,f0, , , , , , , , , , , , , , , , , , , ,a0, , , , , , , , , , , ";
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(str.split(",")));
        assertEquals(arrayList, util.placementSortToList("f0Ea0Y"));
    }

    @Test
    public void testsOrder(){
        String str = "d3,f2,a0,d4,b5,b3,a5,a4,a7,b6,a2,c2,b2,c1,a3,a1,b4,c0,d0,e2,c4,c5,f0,e1,f1,g0,e3,c3,b1,z9,d1,g1,a6,e0,b0,d2";
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(str.split(",")));
        assertEquals(arrayList, util.placementSortToList("d3Af2Ba0Cd4Db5Eb3Fa5Ga4Ha7Ib6Ja2Kc2Lb2Mc1Na3Oa1Pb4Qc0Rd0Se2Tc4Uc5Vf0We1Xf1Yg0Ze30c31b12z93d14g15a66e07b08d29"));
    }

    @Test
    public void testDisorder(){
        String str = "d3,f2,a0,d4,b5,b3,a5,a4,a7,b6,a2,c2,b2,c1,a3,a1,b4,c0,d0,e2,c4,c5,f0,e1,f1,g0,e3,c3,b1,z9,d1,g1,a6,e0,b0,d2";
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(str.split(",")));
        assertEquals(arrayList, util.placementSortToList("a0Ca5Gb2Ma3Od29z93d14e30d4Dg15a2Kb5Ea7Ic4Uc0Re07a1Pd3Ab3Fa4Hf2Bc31a66b12c2Lb6Jb4Qe2Td0Sc1Ne1Xc5Vf0Wg0Zb08f1Y"));
    }
}