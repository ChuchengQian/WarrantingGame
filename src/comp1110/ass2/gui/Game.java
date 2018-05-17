package comp1110.ass2.gui;

import comp1110.ass2.WarringStatesGame;
import comp1110.util.DataUtil;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.shape.Polygon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.text.Text;

public class Game extends Application  {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;

    /* node groups */
    private final Group root = new Group();
    private final Group controls = new Group();


    GridPane grid = new GridPane();
    String moves="";
    String randomSetup = generateRandomSetup();

    TextField numberOfplayersforH;
    TextField numberOfplayersforC;
    String theNumberOfplayers;

    /* label used to specify player */
    Label player0= new Label("Player0");
    Label player1= new Label("Player1");
    Label player2= new Label("Player2");
    Label player3= new Label("Player3");
    Label you= new Label("You");


    /**
     * This class extends Button with the capacity for it to be clicked.
     * @author Chucheng Qian
     */
    class Card extends Button {
        String placement;
        int id;
        int index ;

        public Card(int index,String cardInfo,String placement,int id){
            super(cardInfo);
            this.id=id;
            this.index=index;
            this.placement=placement;

            char location =index < 26 ? (char)(index + 'A') : (char)(index - 26 + '0');// the loaction of this card on the board
            int num = Integer.parseInt(theNumberOfplayers);
            ArrayList<String> cardLocalList;
            DataUtil util = new DataUtil();
            cardLocalList = util.placementSortToList(placement);


            this.setMinSize(80,80);//set the size of the card
            this.setMaxSize(80,80);
            CardImage.setImageToCard(this,cardLocalList.get(index));

            //event handler ,inited when the mouse click the card
            this.setOnMousePressed(event -> {
                if(WarringStatesGame.isMoveLegal(placement,location)){
                    moves =moves+location;
                    controls.getChildren().clear();
                    putPlacement(placement,id);
                    SetupGrid();
                    //all human players
                    if(num==2 && id!=50){
                        NumberofPlayerEqualto2(cardLocalList,index,id);
                    }
                    if(num==3 && id!=50){
                        NumberofPlayerEqualto3(cardLocalList,index,id);
                    }
                    if(num==4 && id!=50){
                        NumberofPlayerEqualto4(cardLocalList,index,id);
                    }
                    //when id =50 ,it means there is only one human player
                    if(num==2 && id==50){
                        NumberofPlayerEqualto2WhenComputet(cardLocalList,index);
                    }
                    if(num==3 && id==50){
                        NumberofPlayerEqualto3WhenComputet(cardLocalList,index);
                    }
                    if(num==4 && id==50){
                        System.out.println("g");
                        NumberofPlayerEqualto4WhenComputet(cardLocalList,index);
                    }
                }else { Notice("Invalid move ,try again!"); }
            });
        }

    }




    /**
     * Generate  a random setup for the game
     * @return a string of placement
     * @author taken from the generateRandomSetup() from TestUtility.java，with little bit of change by Chucheng Qian
     */
    public static String generateRandomSetup() {
        List<String> cards = new ArrayList<>();
        for (char k = 'a'; k < 'h'; k++) {
            for (char c = '0'; c < 8 - (k - 'a') + '0'; c++) {
                cards.add("" + k + c);
            }
        }
        cards.add("z9");
        Collections.shuffle(cards);
        StringBuilder sb = new StringBuilder();
        int location = 0;
        for (String card : cards) {
            sb.append(card).append(location < 26 ? (char)(location + 'A') : (char)(location - 26 + '0'));
            location++;
        }
        return sb.toString();
    }


    /**
     * update the current board accoroding to the target location
     * @param cardLocalList1  an Arraylist of string representing the card placement
     * @param target the index of target location
     * @return a string of new card placement
     *
     * @author structures from Task5 by Chunxiang Song,some changes made by Chucheng Qian
     */
    private String updateBoard(ArrayList<String> cardLocalList1,int target){
        int ZYlocal = -1;
        for (int i = 0; i < 36; i++) {
            if (cardLocalList1.get(i).equals("z9")) {
                ZYlocal = i;
            }
        }
        if (ZYlocal != -1) {
            // The number of the target location card.
            String tarCode = cardLocalList1.get(target);
            // The relative position of ZhangYi.
            int coltmp = ZYlocal / 6;
            // column
            if (target >= coltmp * 6 && target < (coltmp + 1) * 6) {
                // upper part of ZhangYi
                if (target < ZYlocal) {
                    // there is a same country card in the same direction
                    for (int i = ZYlocal; i >= coltmp * 6; i--) {
                        if (cardLocalList1.get(i).charAt(0) == tarCode.charAt(0)) {
                            cardLocalList1.set(i, " ");
                        }
                    }
                    cardLocalList1.set(target, "z9");
                    cardLocalList1.set(ZYlocal, " ");
                }
                // below part of ZhangYi
                if (target > ZYlocal) {
                    for (int i = ZYlocal; i < (coltmp + 1) * 6; i++) {
                        if (cardLocalList1.get(i).charAt(0) == tarCode.charAt(0)) {
                            cardLocalList1.set(i, " ");
                        }
                    }
                    cardLocalList1.set(target, "z9");
                    cardLocalList1.set(ZYlocal, " ");
                }
            }
            int rowtmp = ZYlocal % 6;
            // row
            if (target % 6 == rowtmp) {
                // left part of ZhangYi
                if (target > ZYlocal) {
                    for (int i = ZYlocal; i <= (ZYlocal + (6 - coltmp - 1) * 6); i += 6) {
                        if (cardLocalList1.get(i).charAt(0) == tarCode.charAt(0)) {
                            cardLocalList1.set(i, " ");
                        }
                    }
                    cardLocalList1.set(target, "z9");
                    cardLocalList1.set(ZYlocal, " ");
                }
                // right part of ZhangYi
                if (target < ZYlocal) {
                    for (int i = ZYlocal; i >= rowtmp; i -= 6) {
                        if (cardLocalList1.get(i).charAt(0) == tarCode.charAt(0)) {
                            cardLocalList1.set(i, " ");
                        }
                    }
                    cardLocalList1.set(target, "z9");
                    cardLocalList1.set(ZYlocal, " ");
                }
            }
        }
        //turn the board setup from its "ArrayList<String>" form to the "String"
        String newSetup  = WarringStatesGame.GetNewSetup(cardLocalList1);
        return newSetup;
    }




    /**
     * Search the upper,lower,right and left side of Zhangyi to make sure the game is finished
     * When no valid moves can be done
     * @param presentBoard  an Arraylist of string representing the card placement
     * @return a boolean represents whether the game is finished
     * @author Chucheng Qian
     */
    Boolean WhetherFinished(ArrayList<String> presentBoard){
        int ZYlocal = -1;
        for (int i = 0; i < 36; i++) {
            if (presentBoard.get(i).equals("z9")) {
                ZYlocal = i;
            }
        }
        int coltmp = ZYlocal / 6;
        int rowtmp = ZYlocal % 6;
        // upper part of ZhangYi
        for(int i =coltmp*6;i<=ZYlocal;i++){
            if((!presentBoard.get(i).equals(" ")) && (!presentBoard.get(i).equals("z9"))){
                return false;
            }
        }
        // lower part of ZhangYi
        for(int i =(coltmp+1)*6-1;i>=ZYlocal;i--){
            if((!presentBoard.get(i).equals(" ")) && (!presentBoard.get(i).equals("z9"))){
                return false;
            }
        }
        // left part of ZhangYi
        for(int i =(ZYlocal + (6 - coltmp - 1) * 6);i>=ZYlocal;i-=6){
            if((!presentBoard.get(i).equals(" ")) && (!presentBoard.get(i).equals("z9"))){
                return false;
            }
        }
        // right part of ZhangYi
        for(int i = rowtmp;i<=ZYlocal;i+=6){
            if((!presentBoard.get(i).equals(" ")) && (!presentBoard.get(i).equals("z9"))){
                return false;
            }
        }
        return true;
    }



    /**
     * find the winners when the game is finished
     * @return a string which each element represents the id of winner,the length of string may be one or more.
     * @author Chucheng Qian
     */
    public String Winnners(){
        //num is the number of players involved
        int num = Integer.parseInt(theNumberOfplayers);
        int allFlags[]=WarringStatesGame.getFlags(randomSetup,moves,num);
        ArrayList<Integer> store = new ArrayList<>();
        String winners="";

        for (int ii = 0; ii < num; ii++){
            store.add(0);
            int x=0;

            for (int iii=0;iii<allFlags.length;iii++){
                if (ii==allFlags[iii]) {
                    x++;
                    store.set(ii,x);
                } }
        }
        //the number of flags owned by a winner
        int maxFlag =Collections.max(store);
        for(int i=0;i<store.size();i++){
            if(store.get(i)==maxFlag){
                winners=winners+" "+i;
            }
        }
        return winners;
    }

    /**
     * add the notice message to the root
     * eg:when the move is invalid/when some players win
     * @author Chucheng Qian
     */
    private void Notice(String theNotice){
        Text notice = new Text(theNotice);
        notice.setFont(Font.font("Tahoma", FontWeight.BOLD,50));
        notice.setFill(Color.RED);
        notice.setLayoutX(130);
        notice.setLayoutY(400);
        controls.getChildren().add(notice);
    }




    /**
     * show the current board
     * allow user to click on the button which represents the cards
     * when the game is finished,show the winner
     * when the move is invalid,notice the user
     * @param placement  A placement string representing the board setup
     * @param id the id of current player
     * @author Chucheng Qian with some structures from Task4 by Chunxiang Song
     */
    private void putPlacement(String placement,int id) {
        ArrayList<String> cardLocalList;
        DataUtil util = new DataUtil();
        cardLocalList = util.placementSortToList(placement);

        grid.getChildren().clear();//clear the current grid for new grid
        int index = 0;
        for (int i = 5; i >= 0; i--){
            for (int j = 0; j < 6; j++){
                Card card = new Card(index,cardLocalList.get(index),placement,id);
                grid.add(card, i, j);
                index ++;
            }
        }

        if (WhetherFinished(cardLocalList)) {
            Notice("PLAYER" + Winnners() + " WIN!");}

    }








    /**
     * Update the board with the user's move accoroding to the player id
     * when the number of players are 2.
     *
     * Show the flags owned by each player.
     * Show the supporters owned by each player.
     *
     * @param pb  an Arraylist of string representing the card placement
     * @param index the index of the target location
     * @param id the id of current player
     *
     * @author Chucheng Qian
     */
    private void NumberofPlayerEqualto2(ArrayList<String> pb,int index,int id){
        player0.setLayoutX(600);
        player0.setLayoutY(540);
        player1.setLayoutX(800);
        player1.setLayoutY(540);
        controls.getChildren().addAll(player0,player1);
        showFlags(GetFlagofSepecificPlayer(0),600.0,500.0);
        showSupporters(WarringStatesGame.getSupporters(randomSetup,moves,2,0),600.0,415);
        showFlags(GetFlagofSepecificPlayer(1),800.0,500.0);
        showSupporters(WarringStatesGame.getSupporters(randomSetup,moves,2,1),800.0,415);
        if(id==0){

            putPlacement(updateBoard(pb,index),1);//now it's player 1's turn

        }else{

            putPlacement(updateBoard(pb,index),0);//now it's player 0's turn
        }


    }

    /**
     * Update the board with the user's move and one randomly generated move
     * when the number of players are 2.
     *
     * Show the flags owned by each player.
     * Show the supporters owned by each player.
     *
     * @param pb  an Arraylist of string representing the card placement
     * @param index the index of the target location
     * @author Chucheng Qian
     */
    private void NumberofPlayerEqualto2WhenComputet(ArrayList<String> pb,int index){
        DataUtil util = new DataUtil();
        //check whether there are valid moves after the user's click
        if (WhetherFinished(util.placementSortToList(updateBoard(pb, index)))) {
            putPlacement(updateBoard(pb, index),50);}
        else{//generate a random move for robotA
            char randomLocation1 = WarringStatesGame.generateMove(updateBoard(pb,index));
            int randomIndex1 =randomLocation1 >=65 ? (randomLocation1 - 'A') : (randomLocation1 - 48+26);
            moves =moves+randomLocation1;
            putPlacement(updateBoard(util.placementSortToList(updateBoard(pb,index)),randomIndex1),50);
        }
        player0.setLayoutX(600);
        player0.setLayoutY(540);
        you.setLayoutX(607);
        you.setLayoutY(553);//tell the user that player1 represents the human player
        player1.setLayoutX(800);
        player1.setLayoutY(540);
        controls.getChildren().addAll(player0,player1,you);
        showFlags(GetFlagofSepecificPlayer(0),600.0,500.0);
        showSupporters(WarringStatesGame.getSupporters(randomSetup,moves,2,0),600.0,415);
        showFlags(GetFlagofSepecificPlayer(1),800.0,500.0);
        showSupporters(WarringStatesGame.getSupporters(randomSetup,moves,2,1),800.0,415);
    }


    /**
     * Update the board with the user's move accoroding to the player id
     * when the number of players are 3.
     *
     * Show the flags owned by each player.
     * Show the supporters owned by each player.
     *
     * @param pb  an Arraylist of string representing the card placement
     * @param index the index of the target location
     * @param id the id of current player
     *
     * @author Chucheng Qian
     */
    private void NumberofPlayerEqualto3(ArrayList<String> pb,int index,int id){
        player0.setLayoutX(600);
        player0.setLayoutY(340);
        player1.setLayoutX(800);
        player1.setLayoutY(340);
        player2.setLayoutX(600);
        player2.setLayoutY(640);
        controls.getChildren().addAll(player0,player1,player2);
        showFlags(GetFlagofSepecificPlayer(0),600.0,300.0);
        showSupporters(WarringStatesGame.getSupporters(randomSetup,moves,3,0),600.0,215);
        showFlags(GetFlagofSepecificPlayer(1),800.0,300.0);
        showSupporters(WarringStatesGame.getSupporters(randomSetup,moves,3,1),800.0,215);
        showFlags(GetFlagofSepecificPlayer(2),600.0,600.0);
        showSupporters(WarringStatesGame.getSupporters(randomSetup,moves,3,2),600.0,515);
        if(id==0){
            putPlacement(updateBoard(pb,index),1);//now it's player 1's turn
        }
        if(id==1){
            putPlacement(updateBoard(pb,index),2);//now it's player 2's turn
        }
        if(id==2){
            putPlacement(updateBoard(pb,index),0);//now it's player 0's turn
        }
    }
    /**
     * Update the board with the user's move and two randomly generated move
     * when the number of players are 3.
     *
     * Show the flags owned by each player.
     * Show the supporters owned by each player.
     *
     * @param pb  an Arraylist of string representing the card placement
     * @param index the index of the target location
     * @author Chucheng Qian
     */
    private void NumberofPlayerEqualto3WhenComputet(ArrayList<String> pb,int index) {
        DataUtil util = new DataUtil();
        //check whether there are valid moves after the user's click
        if (WhetherFinished(util.placementSortToList(updateBoard(pb, index)))) {
            putPlacement(updateBoard(pb, index),50);
        } else {//generate a random move for robotA
            char randomLocation1 = WarringStatesGame.generateMove(updateBoard(pb, index));
            int randomIndex1 = randomLocation1 >= 65 ?  (randomLocation1 - 'A') : (randomLocation1 - 48 + 26);
            String newBoard1 = updateBoard(util.placementSortToList(updateBoard(pb, index)), randomIndex1);
            //check whether there are valid moves after the random move for robotA
            if (WhetherFinished(util.placementSortToList(newBoard1))) {
                moves = moves + randomLocation1;
                putPlacement(newBoard1,50);
            } else {//generate a random move for robotB
                char randomLocation2 = WarringStatesGame.generateMove(newBoard1);
                int randomIndex2 = randomLocation2 >= 65 ?  (randomLocation2 - 'A') :  (randomLocation2 - 48 + 26);
                String newBoard2 = updateBoard(util.placementSortToList(newBoard1), randomIndex2);
                moves = moves + randomLocation1 + randomLocation2;

                putPlacement(updateBoard(util.placementSortToList(newBoard2), randomIndex2),50);

            }
        }

        player0.setLayoutX(600);
        player0.setLayoutY(340);
        you.setLayoutX(607);//tell the user that player1 represents the human player
        you.setLayoutY(353);
        player1.setLayoutX(800);
        player1.setLayoutY(340);
        player2.setLayoutX(600);
        player2.setLayoutY(640);
        controls.getChildren().addAll(player0,player1,player2,you);
        showFlags(GetFlagofSepecificPlayer(0),600.0,300.0);
        showSupporters(WarringStatesGame.getSupporters(randomSetup,moves,3,0),600.0,215);
        showFlags(GetFlagofSepecificPlayer(1),800.0,300.0);
        showSupporters(WarringStatesGame.getSupporters(randomSetup,moves,3,1),800.0,215);
        showFlags(GetFlagofSepecificPlayer(2),600.0,600.0);
        showSupporters(WarringStatesGame.getSupporters(randomSetup,moves,3,2),600.0,515);

    }

    /**
     *Update the board with the user's move accoroding to the player id
     * when the number of players are 4.
     *
     * Show the flags owned by each player.
     * Show the supporters owned by each player.
     *
     * @param pb  an Arraylist of string representing the card placement
     * @param index the index of the target location
     * @param id the id of current player
     *
     * @author Chucheng Qian
     */
    void NumberofPlayerEqualto4(ArrayList<String> pb,int index,int id){
        player0.setLayoutX(600);
        player0.setLayoutY(340);
        player1.setLayoutX(800);
        player1.setLayoutY(340);
        player2.setLayoutX(600);
        player2.setLayoutY(640);
        player3.setLayoutX(800);
        player3.setLayoutY(640);
        controls.getChildren().addAll(player0,player1,player2,player3);
        showFlags(GetFlagofSepecificPlayer(0),600.0,300.0);
        showSupporters(WarringStatesGame.getSupporters(randomSetup,moves,4,0),600.0,215);
        showFlags(GetFlagofSepecificPlayer(1),800.0,300.0);
        showSupporters(WarringStatesGame.getSupporters(randomSetup,moves,4,1),800.0,215);
        showFlags(GetFlagofSepecificPlayer(2),600.0,600.0);
        showSupporters(WarringStatesGame.getSupporters(randomSetup,moves,4,2),600.0,515);
        showFlags(GetFlagofSepecificPlayer(3),800.0,600.0);
        showSupporters(WarringStatesGame.getSupporters(randomSetup,moves,4,3),800.0,515);
        if(id==0){
            putPlacement(updateBoard(pb,index),1);//now it's player 1's turn
        }
        if(id==1){
            putPlacement(updateBoard(pb,index),2);//now it's player 2's turn
        }
        if(id==2){
            putPlacement(updateBoard(pb,index),3);//now it's player 3's turn
        }
        if(id==3){
            putPlacement(updateBoard(pb,index),0);//now it's player 0's turn
        }
    }

    /**
     * Update the board with the user's move and tree randomly generated move
     * when the number of players are 4.
     *
     * Show the flags owned by each player.
     * Show the supporters owned by each player.
     *
     * @param pb  an Arraylist of string representing the card placement
     * @param index the index of the target location
     * @author Chucheng Qian
     */
    void NumberofPlayerEqualto4WhenComputet(ArrayList<String> pb,int index){
        DataUtil util = new DataUtil();
        //check whether there are valid moves after the user's click
        if (WhetherFinished(util.placementSortToList(updateBoard(pb, index)))) {
            putPlacement(updateBoard(pb, index),50);
        }else {//generate a random move for robotA
            char randomLocation1 = WarringStatesGame.generateMove(updateBoard(pb, index));
            int randomIndex1 = randomLocation1 >= 65 ? (int) (randomLocation1 - 'A') : (int) (randomLocation1 - 48 + 26);
            String newBoard1 = updateBoard(util.placementSortToList(updateBoard(pb, index)), randomIndex1);
            //check whether there are valid moves after the random move for robotA
            if (WhetherFinished(util.placementSortToList(newBoard1))) {
                moves = moves + randomLocation1;
                putPlacement(newBoard1,50);
            }else{//generate a random move for robotB
                char randomLocation2 = WarringStatesGame.generateMove(newBoard1);
                int randomIndex2 = randomLocation2 >= 65 ? (int) (randomLocation2 - 'A') : (int) (randomLocation2 - 48 + 26);
                String newBoard2 = updateBoard(util.placementSortToList(newBoard1), randomIndex2);
                //check whether there are valid moves after the random move for robotB
                if(WhetherFinished(util.placementSortToList(newBoard2))){
                    moves = moves + randomLocation1 + randomLocation2;
                    putPlacement(newBoard2,50);
                }else {//generate a random move for robotC
                    char randomLocation3 = WarringStatesGame.generateMove(newBoard2);
                    int randomIndex3 = randomLocation3 >= 65 ? (int) (randomLocation3 - 'A') : (int) (randomLocation3 - 48 + 26);
                    moves = moves + randomLocation1 + randomLocation2 + randomLocation3;

                    putPlacement(updateBoard(util.placementSortToList(newBoard2), randomIndex3),50);

                }
            }
        }
        player0.setLayoutX(600);
        player0.setLayoutY(340);
        you.setLayoutX(607);//tell the user that player1 represents the human player
        you.setLayoutY(353);
        player1.setLayoutX(800);
        player1.setLayoutY(340);
        player2.setLayoutX(600);
        player2.setLayoutY(640);
        player3.setLayoutX(800);
        player3.setLayoutY(640);
        controls.getChildren().addAll(player0, player1, player2, player3,you);
        showFlags(GetFlagofSepecificPlayer(0), 600.0, 300.0);
        showSupporters(WarringStatesGame.getSupporters(randomSetup, moves, 4, 0), 600.0, 215);
        showFlags(GetFlagofSepecificPlayer(1), 800.0, 300.0);
        showSupporters(WarringStatesGame.getSupporters(randomSetup, moves, 4, 1), 800.0, 215);
        showFlags(GetFlagofSepecificPlayer(2), 600.0, 600.0);
        showSupporters(WarringStatesGame.getSupporters(randomSetup, moves, 4, 2), 600.0, 515);
        showFlags(GetFlagofSepecificPlayer(3), 800.0, 600.0);
        showSupporters(WarringStatesGame.getSupporters(randomSetup, moves, 4, 3), 800.0, 515);

    }




    /**
     * Show welcome view.
     *
     * @author Chucheng Qian
     */
    private void WelcomeView() {
        Polygon rectangle=new Polygon();
        rectangle.getPoints().addAll(
                0.0,0.0,
                0.0,700.0,
                933.0,700.0,
                933.0,0.0
        );
        rectangle.setFill(new ImagePattern(CardImage.wel));
        controls.getChildren().add(rectangle);
        //event handler,init when click any place on the board
        rectangle.setOnMouseClicked(event -> {
            controls.getChildren().clear();
            StartingView();
        });

    }


    /**
     * Show start view with .
     *
     * @author Chucheng Qian
     */
    private void StartingView() {
        Polygon rectangle=new Polygon();
        rectangle.getPoints().addAll(
                0.0,0.0,
                0.0,700.0,
                933.0,700.0,
                933.0,0.0
        );
        rectangle.setFill(new ImagePattern(CardImage.start));
        controls.getChildren().add(rectangle);
        //show the textfield which the user can enter the number of players and buttons which the user can choose to play with human or computer
        NumberOfPlayerWhenWithHuman();
        NumberOfPlayerWhenWithComputer();
    }


    /**
     * set up the card grid
     * @author Chucheng Qian
     */
    private void SetupGrid() {
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(40, 0, 0, 40));
        controls.getChildren().add(grid);
    }


    /**used when play with human
     * allow user to type in the number of players
     * allow user to click on the button "Start" to start the game
     * Ensure that the game can be played by 2-4 human players.
     * when the number of players are invalid,notice the user
     * @author Chucheng Qian
     */
    private void NumberOfPlayerWhenWithHuman() {
        Label others = new Label("Play with Others:");
        others.setFont(Font.font("Tahoma", FontWeight.BOLD,20));
        Label label0 = new Label("Number of Players:");
        label0.setFont(Font.font("Tahoma", FontWeight.BOLD,20));
        numberOfplayersforH = new TextField();
        numberOfplayersforH.setPrefWidth(50);
        Button button1 = new Button("Start");
        //event handler,init when click the button
        button1.setOnAction(event -> {
            theNumberOfplayers = numberOfplayersforH.getText();
            int num = Integer.parseInt(theNumberOfplayers);
            if(num>=2 && num <=4){
                controls.getChildren().clear();
                SetupGrid();
                putPlacement(randomSetup,0);

            }else{
                Text notice = new Text("Invalid players ,try again!");
                notice.setFont(Font.font("Tahoma", FontWeight.BOLD,50));
                notice.setFill(Color.RED);
                notice.setLayoutX(130);
                notice.setLayoutY(400);
                controls.getChildren().add(notice);

            }

        });
        HBox hb1 = new HBox();
        hb1.getChildren().addAll(others,label0, numberOfplayersforH, button1);
        hb1.setSpacing(10);
        hb1.setLayoutX(200);
        hb1.setLayoutY(350);
        controls.getChildren().add(hb1);
    }
    /**
     * used when play with Computer
     * allow user to type in the number of players
     * allow user to click on the button "Start" to start the game
     * Ensure that the game can be played by 2-4 human players.
     * when the number of players are invalid,notice the user
     * @author Chucheng Qian
     */
    private void NumberOfPlayerWhenWithComputer() {
        Label computer = new Label("Play with Computer:");
        computer.setFont(Font.font("Tahoma", FontWeight.BOLD,20));
        Label label0 = new Label("Number of Players:");
        label0.setFont(Font.font("Tahoma", FontWeight.BOLD,20));
        numberOfplayersforC = new TextField();
        numberOfplayersforC.setPrefWidth(50);
        Button button1 = new Button("Start");
        //event handler,init when click the button
        button1.setOnAction(event -> {
            theNumberOfplayers = numberOfplayersforC.getText();
            int num = Integer.parseInt(theNumberOfplayers);
            if(num>=2 && num <=4){
                controls.getChildren().clear();
                //set the id to be 50 to represent the only human player
                //make this situation which playing with computers be separated when updating the board and setting flags and supporters.
                putPlacement(randomSetup,50);
                SetupGrid();
            }else{
                Text notice = new Text("Invalid players ,try again!");
                notice.setFont(Font.font("Tahoma", FontWeight.BOLD,50));
                notice.setFill(Color.RED);
                notice.setLayoutX(130);
                notice.setLayoutY(400);
                controls.getChildren().add(notice);

            }

        });
        HBox hb1 = new HBox();
        hb1.getChildren().addAll(computer,label0, numberOfplayersforC, button1);
        hb1.setSpacing(10);
        hb1.setLayoutX(200);
        hb1.setLayoutY(450);
        controls.getChildren().add(hb1);
    }






    /**
     * get all the flags owned by one sepecific player
     * @param id the id of current player
     * @return ArrayList<Character> an Arraylist of character representing all the flags owned by one user
     * @author Chucheng Qian
     *
     **
     */
    private ArrayList<Character> GetFlagofSepecificPlayer(int id){
        ArrayList<Character> flags = new ArrayList<>();
        int num = Integer.parseInt(theNumberOfplayers);
        int allFlags[]=WarringStatesGame.getFlags(randomSetup,moves,num);
        for(int i = 0;i < allFlags.length;i++){
            if(allFlags[i]==id){
                flags.add((char)(i+'a'));
            }

        }
        return flags;
    }



    /**
     * show flags owned one player on the board
     *
     *
     * @param list  an Arraylist of character representing all the flags owned by one user
     * @param x the x-coodinate
     * @param y the y-coodinate
     * @author Chucheng Qian
     */
    private void showFlags(ArrayList<Character> list,double x,double y){
        for(int i = 0; i<list.size();i++){
            Polygon s = new Polygon();
            s.getPoints().addAll(new Double[]{
                    x,y,
                    x+30, y,
                    x+30, y+30,
                    x, y+30

            });
            x+=30;
            //set image to the square figure which shows supporters
            if(list.get(i).equals('a')){
                s.setFill(new ImagePattern(CardImage.aa0));
            }
            if(list.get(i).equals('b')){
                s.setFill(new ImagePattern(CardImage.bb0));
            }
            if(list.get(i).equals('c')){
                s.setFill(new ImagePattern(CardImage.cc0));
            }
            if(list.get(i).equals('d')){
                s.setFill(new ImagePattern(CardImage.dd0));
            }
            if(list.get(i).equals('e')){
                s.setFill(new ImagePattern(CardImage.ee0));
            }
            if(list.get(i).equals('f')){
                s.setFill(new ImagePattern(CardImage.ff0));
            }
            if(list.get(i).equals('g')){
                s.setFill(new ImagePattern(CardImage.gg0));
            }
            s.setStrokeWidth(0.5);
            s.setStroke(Color.BLACK);
            controls.getChildren().add(s);

        }
    }

    /**
     * show supporters owned one player on the board
     *
     *
     * @param supporters  an string consists of all the supporters owned by one user
     * @param x the x-coodinate
     * @param y the y-coodinate
     * @author Chucheng Qian
     */
    private void showSupporters(String supporters,double x,double y){
        for(int i = 0; i<supporters.length();i+=2){
            Polygon s = new Polygon();
            s.getPoints().addAll(new Double[]{
                    x,y,
                    x+80, y,
                    x+80, y+80,
                    x, y+80

            });
            y-=12;
            CardImage.setImageToSuppoters(s,supporters.substring(i,i+2));
            s.setStrokeWidth(0.5);
            s.setStroke(Color.BLACK);
            controls.getChildren().add(s);
        }
    }











    // FIXME Task 9: Implement a basic playable Warring States game in JavaFX

    // FIXME Task 11: Allow players of your Warring States game to play against your simple agent

    // FIXME Task 12: Integrate a more advanced opponent into your game

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Warring States Game");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);

        //add noeds to the root
        root.getChildren().add(controls);
        WelcomeView();
        //show the scene
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

