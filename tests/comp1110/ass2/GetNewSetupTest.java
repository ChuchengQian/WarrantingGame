package comp1110.ass2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static comp1110.ass2.TestUtility.*;

import static org.junit.Assert.*;

/**
 * Test objective:
 * <p>
 * Determine whether a set up in its array list form  can  be onverted to the string list
 */
public class GetNewSetupTest {
    @Test
    public void TestEmptyandNull(){
        ArrayList<String> test =  new ArrayList();
        String str1= "";
        assertTrue("When the Arraylist of current setup is empty,the new setup should be empty list.",str1.equals(WarringStatesGame.GetNewSetup(test)));

        assertTrue("When the Arraylist of current setup is Null,the new setup should be empty list.",str1.equals(WarringStatesGame.GetNewSetup(null)));
    }

    @Test
    public void TestOne(){

        String str2="g0, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , ,";
        ArrayList<String> test =  new ArrayList(Arrays.asList(str2.split(",")));
        assertTrue("When the Arraylist of current setup is "+test+" ,the new setup should be \"g0A\".","g0A".equals(WarringStatesGame.GetNewSetup(test)));
    }

    @Test
    public void TestAll(){

        String str3="b5,e0,c3,a7,a1,c1,g1,g0,a0,f0,b2,b1,a3,a2,b0,c5,e2,d0,d2,d4,d3,a4,a5,b6,z9, , ,f1,a6,e3,c0,f2,c4,c2,d1,e1";
        ArrayList<String> test =  new ArrayList(Arrays.asList(str3.split(",")));
        assertTrue("When the Arraylist of current setup is "+test+" ,the new setup should be \"b5Ae0Bc3Ca7Da1Ec1Fg1Gg0Ha0If0Jb2Kb1La3Ma2Nb0Oc5Pe2Qd0Rd2Sd4Td3Ua4Va5Wb6Xz9Yf11a62e33c04f25c46c27d18e19\".","b5Ae0Bc3Ca7Da1Ec1Fg1Gg0Ha0If0Jb2Kb1La3Ma2Nb0Oc5Pe2Qd0Rd2Sd4Td3Ua4Va5Wb6Xz9Yf11a62e33c04f25c46c27d18e19".equals(WarringStatesGame.GetNewSetup(test)));
    }




}
