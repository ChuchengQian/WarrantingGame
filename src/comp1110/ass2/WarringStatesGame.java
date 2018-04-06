package comp1110.ass2;

import comp1110.util.DataUtil;

import java.util.ArrayList;

/**
 * This class provides the text interface for the Warring States game
 */
public class WarringStatesGame {

    /**
     * Determine whether a card placement is well-formed according to the following:
     * - it consists of exactly three characters
     * - the first character is in the range a..g (kingdom) or is z (Zhang Yi)
     * - the second character is numeric, and is a valid character number for that kingdom (9 for Zhang Yi)
     * - the third character is in the range A .. Z or 0..9 (location)
     *
     * @param cardPlacement A string describing a card placement
     * @return true if the card placement is well-formed
     */
    static boolean isCardPlacementWellFormed(String cardPlacement) {
        // FIXME Task 2: determine whether a card placement is well-formed
        char character1 = cardPlacement.charAt(0);
        char character2 = cardPlacement.charAt(1);
        char character3 = cardPlacement.charAt(2);
        if (character1 == 'z'){
            if (character2 == '9'){
                return ((character3 >= 'A' && character3 <= 'Z') || (character3 >= '0' && character3 <= '9'));
            }
        }
        if (character1 >= 'a' && character1 <= 'g'){
            if (character2 >= '0' && character2 <= '9'){
                return (character3 >= 'A' && character3 <= 'Z') || (character3 >= '0' && character3 <= '9');
            }
        }
        return false;
    }

    /**
     * Determine whether a placement string is well-formed:
     * - it consists of exactly N three-character card placements (where N = 1 .. 36);
     * - each card placement is well-formed
     * - no card appears more than once in the placement
     * - no location contains more than one card
     *
     * @param placement A string describing a placement of one or more cards
     * @return true if the placement is well-formed
     */
    static boolean isPlacementWellFormed(String placement) {
        // FIXME Task 3: determine whether a placement is well-formed
        int count = 0;
        boolean check = true;
        if (placement == null || placement.length() <= 0) { //testEmpty
            return false;
        } else if (placement.length() % 3 != 0 ) { //testIncomplete
            return false;
        } else { //testGood
            System.out.println(placement+" test");
            System.out.println(placement.length()/3+" length");

            for (int i = 0; i < placement.length(); i = i + 3) {
                if (isCardPlacementWellFormed(placement.substring(i, i+3))) {
                    count++;
                }
            }
        }
        if (count != placement.length()/3) {
            check = false;
        }
        System.out.println("!!!!!!!!!!!"+check);
        //testDuplicate
        int count2 = 0;
        int locationCheck = 0;
        for(int i = 0; i < placement.length(); i = i + 3) {
            for (int j = 0; j < placement.length(); j = j + 3) {
                if (placement.substring(i, i + 2).equals(placement.substring(j, j + 2))) {
                    count2++;
                }
            }
        }
        for (int i = 2; i< placement.length(); i = i + 3) {
            for (int j = 2; j < placement.length(); j = j + 3) {
                if (placement.charAt(i) == placement.charAt(j)) {
                    locationCheck++;
                }
            }
        }
        if(count2 > placement.length() / 3 || locationCheck > placement.length() / 3) {
            check = false;
        }

        return check;
    }

    /**
     * Determine whether a given move is legal given a provided valid placement:
     * - the location char is in the range A .. Z or 0..9
     * - there is a card at the chosen location;
     * - the location is in the same row or column of the grid as Zhang Yi's current position; and
     * - drawing a line from Zhang Yi's current location through the card at the chosen location,
     *   there are no other cards along the line from the same kingdom as the chosen card
     *   that are further away from Zhang Yi.
     * @param placement    the current placement string
     * @param locationChar a location for Zhang Yi to move to
     * @return true if Zhang Yi may move to that location
     */
    public static boolean isMoveLegal(String placement, char locationChar) {
        // FIXME Task 5: determine whether a given move is legal
        ArrayList<String> cardLocalList;
        DataUtil util = new DataUtil();
        // placement as a card string that is not placed in a positional order.
        cardLocalList = util.placementSortToList(placement);
        if (cardLocalList == null) {
            return false;
        }
        // target position is converted to a position number.
        int target;
        if (locationChar <= 'Z' && locationChar >= 'A') {
            target = locationChar - 'A';
        }
        else if (locationChar <= '9' && locationChar >= '0') {
            target = locationChar - '0';
            target = target + 26;
        }
        else
            return false;
        // no card in the target position.
        if (cardLocalList.get(target).equals(" ")) {
            return false;
        }
        // find ZhangYi
        int ZYlocal = -1;
        for (int i = 0; i < 36; i++) {
            if (cardLocalList.get(i).equals("z9")) {
                ZYlocal = i;
            }
        }

        if (ZYlocal != -1) {
            // The number of the target location card.
            String tarCode = cardLocalList.get(target);
            // The relative position of ZhangYi.
            int coltmp = ZYlocal / 6;
            // column
            if (target >= coltmp * 6 && target < (coltmp + 1) * 6) {
                // upper part of ZhangYi
                if (target < ZYlocal) {
                    // there is a same country card in the same direction
                    for (int i = target - 1; i >= coltmp * 6; i--){
                        if (cardLocalList.get(i).charAt(0) == tarCode.charAt(0)) {
                            return false;
                        }
                    }
                    return true;
                }
                // below part of ZhangYi
                if (target > ZYlocal) {
                    for (int i = target + 1; i < (coltmp + 1) * 6; i++){
                        if (cardLocalList.get(i).charAt(0) == tarCode.charAt(0)) {
                            return false;
                        }
                    }
                    return true;
                }

            }
            int rowtmp = ZYlocal % 6;
            // row
            if (target % 6 == rowtmp) {
                // left part of ZhangYi
                if (target > ZYlocal) {
                    for (int i = target + 6; i <= (rowtmp + 30); i+=6){
                        if (cardLocalList.get(i).charAt(0) == tarCode.charAt(0)) {
                            return false;
                        }
                    }
                    return true;
                }
                // right part of ZhangYi
                if (target < ZYlocal) {
                    for (int i = target - 6; i >= rowtmp; i-=6){
                        if (cardLocalList.get(i).charAt(0) == tarCode.charAt(0)) {
                            return false;
                        }
                    }
                    return true;
                }

            }
            return false;
        }

        return false;
    }

    /**
     * Determine whether a move sequence is valid.
     * To be valid, the move sequence must be comprised of 1..N location characters
     * showing the location to move for Zhang Yi, and each move must be valid
     * given the placement that would have resulted from the preceding sequence
     * of moves.
     *
     * @param setup        A placement string representing the board setup
     * @param moveSequence a string of location characters representing moves
     * @return True if the placement sequence is valid
     */
    static boolean isMoveSequenceValid(String setup, String moveSequence) {
        // FIXME Task 6: determine whether a placement sequence is valid
        /**
         * use if...then
         * At first ,take the first character of the moveSequence string,
         * call the method 'isMoveLegal' to check whether it is legal(the parameter placement will be 'setup'.
         * If ture,continue call the isMoveSequenceValid method inside a nested if...then statement
         * with updated 'setup' and the second character of 'moveSequence'.
         * Then continue the loop.
         * if get any 'false',jump out the loop.
         * (if 'if..then'  cannot work ,change the condition and try 'for' or 'whhile'
         */
        return false;
    }

    /**
     * Get the list of supporters for the chosen player, given the provided
     * setup and move sequence.
     * The list of supporters is a sequence of two-character card IDs, representing
     * the cards that the chosen player collected by moving Zhang Yi.
     *
     * @param setup        A placement string representing the board setup
     * @param moveSequence a string of location characters representing moves
     * @param numPlayers   the number of players in the game, must be in the range [2..4]
     * @param playerId     the player number for which to get the list of supporters, [0..(numPlayers-1)]
     * @return the list of supporters for the given player
     */
    public static String getSupporters(String setup, String moveSequence, int numPlayers, int playerId) {
        // FIXME Task 7: get the list of supporters for a given player after a sequence of moves
        return null;
    }

    /**
     * Given a setup and move sequence, determine which player controls the flag of each kingdom
     * after all the moves in the sequence have been played.
     *
     * @param setup        A placement string representing the board setup
     * @param moveSequence a string of location characters representing a sequence of moves
     * @param numPlayers   the number of players in the game, must be in the range [2..4]
     * @return an array containing the player ID who controls each kingdom, where
     * - element 0 contains the player ID of the player who controls the flag of Qin
     * - element 1 contains the player ID of the player who controls the flag of Qi
     * - element 2 contains the player ID of the player who controls the flag of Chu
     * - element 3 contains the player ID of the player who controls the flag of Zhao
     * - element 4 contains the player ID of the player who controls the flag of Han
     * - element 5 contains the player ID of the player who controls the flag of Wei
     * - element 6 contains the player ID of the player who controls the flag of Yan
     * If no player controls a particular house, the element for that house will have the value -1.
     */
    public static int[] getFlags(String setup, String moveSequence, int numPlayers) {
        // FIXME Task 8: determine which player controls the flag of each kingdom after a given sequence of moves
        return null;
    }

    /**
     * Generate a legal move, given the provided placement string.
     * A move is valid if:
     * - the location char is in the range A .. Z or 0..9
     * - there is a card at the chosen location;
     * - the destination location is different to Zhang Yi's current location;
     * - the destination is in the same row or column of the grid as Zhang Yi's current location; and
     * - drawing a line from Zhang Yi's current location through the card at the chosen location,
     * there are no other cards along the line from the same kingdom as the chosen card
     * that are further away from Zhang Yi.
     * If there is no legal move available, return the null character '\0'.
     * @param placement the current placement string
     * @return a location character representing Zhang Yi's destination for the move
     */
    public static char generateMove(String placement) {
        // FIXME Task 10: generate a legal move
        return '\0';
    }
}
