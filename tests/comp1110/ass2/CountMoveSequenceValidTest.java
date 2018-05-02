package comp1110.ass2;

import org.junit.Test;


import static org.junit.Assert.*;
import static comp1110.ass2.TestUtility.*;

/**
 * Test objective:
 * <p>
 * Determine whether a move sequence is valid.
 * To be valid, the nubmber in the move sequence must be equal to the number of the all the moves in the movesequence.
 */

public class CountMoveSequenceValidTest {
    @Test
    public void ValidMovesTest() {
        int i1 = (int)(Math.random()*(19-0+1));
        int i2=(int)(Math.random()*(4-0+1));
        String setup1= PLACEMENTS[i1];
        String moveSequence1= MOVE_SEQUENCES[i1][i2];
        int count =  moveSequence1.length();
        assertEquals("The number of valid moves in the move sequence should be "+count+" but is "+WarringStatesGame.CountMoveSequenceValid(setup1,moveSequence1,0),count,WarringStatesGame.CountMoveSequenceValid(setup1,moveSequence1,0));

    }

    @Test
    public void InvalidMovesTest() {
        int i1 = (int)(Math.random()*(19-0+1));
        int i2=(int)(Math.random()*(4-0+1));
        String setup1= PLACEMENTS[i1];
        String moveSequence1= BAD_MOVE_SEQUENCES[i1][i2];
        int count =  moveSequence1.length();
        assertNotEquals("The number of valid moves in the move sequence should not be "+WarringStatesGame.CountMoveSequenceValid(setup1,moveSequence1,0)+" which is  equal to the number of all moves in the move sequence.",count,WarringStatesGame.CountMoveSequenceValid(setup1,moveSequence1,0));

    }

    @Test
    public void EmptyPlaceTest() {
        int i1 = (int)(Math.random()*(19-0+1));
        int i2=(int)(Math.random()*(4-0+1));
        String setup1= PLACEMENTS[i1];
        String moveSequence1= MOVE_SEQUENCES[i1][i2];
        //let x be the index of the one location in the move sequence which will be set as the empty space for following operations
        int x=(int)(Math.random()*(5-0+1));
        String moveSequence2=moveSequence1.substring(0,7)+moveSequence1.substring(x,x+1)+moveSequence1.substring(7);

        int count =  moveSequence1.length();
        assertNotEquals("The number of valid moves in the move sequence should not be "+WarringStatesGame.CountMoveSequenceValid(setup1,moveSequence2,0)+" which is  equal to the number of all moves in the move sequence.",count,WarringStatesGame.CountMoveSequenceValid(setup1,moveSequence2,0));

    }

    @Test
    public void Duplicatemove() {
        int i1 = (int)(Math.random()*(19-0+1));
        int i2=(int)(Math.random()*(4-0+1));
        String setup1= PLACEMENTS[i1];
        String moveSequence1= MOVE_SEQUENCES[i1][i2];
        //let x be the index of the one location in the move sequence which will be set as the empty space for following operations
        int x=(int)(Math.random()*(5-0+1));
        String moveSequence2=moveSequence1.substring(0,x)+moveSequence1.substring(x,x+1)+moveSequence1.substring(x);

        int count =  moveSequence1.length();
        assertNotEquals("The number of valid moves in the move sequence should not be "+WarringStatesGame.CountMoveSequenceValid(setup1,moveSequence2,0)+" which is  equal to the number of all moves in the move sequence.",count,WarringStatesGame.CountMoveSequenceValid(setup1,moveSequence2,0));

    }






}
