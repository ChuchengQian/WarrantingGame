package comp1110.ass2.gui;

import comp1110.ass2.WarringStatesGame;
import comp1110.ass2.gui.CardImage;
import comp1110.util.DataUtil;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.shape.Polygon;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Collections;
import comp1110.ass2.gui.Viewer;

import javafx.scene.text.Text;

public class Game extends Application {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;



    /**
     * generate  a random setup for the game
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

    /* node groups */
    private final Group root = new Group();
    private final Group controls = new Group();
    StackPane stackPane= new StackPane();


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
     * show the current board
     * allow user to click on the button which represents the cards
     * when the game is finished,show the winner
     * when the move is invalid,notice the user
     * @param placement  A placement string representing the board setup
     * @param id the id of current player
     * @author Chucheng Qian with some structures from Task4 by Chunxiang Song
     */
    private void putPlacement(String placement,int id) {
        int num = Integer.parseInt(theNumberOfplayers);
        ArrayList<String> cardLocalList;
        DataUtil util = new DataUtil();
        cardLocalList = util.placementSortToList(placement);

        if (WhetherFinished(cardLocalList)) {
            Text winMessage = new Text("Player" + Winnners() + " wins!");
            winMessage.setFont(Font.font("Tahoma", FontWeight.BOLD, 50));
            winMessage.setFill(Color.RED);
            winMessage.setLayoutX(130);
            winMessage.setLayoutY(400);
            controls.getChildren().add(winMessage);}
        else{
            //clear the previous board
            grid.getChildren().clear();
            int index = 0;
            for (int i = 5; i >= 0; i--){
                for (int j = 0; j < 6; j++){
                    int idd = id;//as varrable should be final in the lambda expression
                    //one button is one card
                    Button card = new Button(cardLocalList.get(index));
                    card.setMinSize(80,80);
                    card.setMaxSize(80,80);
                    int indexx = index;//as varrable should be final in the lambda expression
                    char location =indexx < 26 ? (char)(indexx + 'A') : (char)(indexx - 26 + '0');
                    //event handler ,inited when user click the card button
                    card.setOnMousePressed(event -> {
                        if(WarringStatesGame.isMoveLegal(placement,location)){
                            moves =moves+location;
                            controls.getChildren().clear();
                            putPlacement(placement,id);
                            SetupGrid();
                            if(num==2){
                                NumberofPlayerEqualto2(cardLocalList,indexx,idd);
                            }
                            if(num==3){
                                NumberofPlayerEqualto3(cardLocalList,indexx,idd);
                            }
                            if(num==4){
                                NumberofPlayerEqualto4(cardLocalList,indexx,idd);
                            }
                        }else { InvalidMoveNotice(); }
                    });
                    CardImage.setImageToButton(card,cardLocalList.get(index));
                    grid.add(card, i, j);
                    index ++;
                }
            }
        }

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
        button1.setOnAction(event -> {
            theNumberOfplayers = numberOfplayersforH.getText();
            int num = Integer.parseInt(theNumberOfplayers);
            if(num>=2 && num <=4){
                controls.getChildren().clear();
                putPlacement(randomSetup,0);
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
        button1.setOnAction(event -> {
            theNumberOfplayers = numberOfplayersforC.getText();
            int num = Integer.parseInt(theNumberOfplayers);
            if(num>=2 && num <=4){
                controls.getChildren().clear();
                putPlacement(randomSetup,0);
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
     * add the notice message to the root when the move is invalid
     * @author Chucheng Qian
     */
    private void InvalidMoveNotice(){
        Text notice = new Text("Invalid move ,try again!");
        notice.setFont(Font.font("Tahoma", FontWeight.BOLD,50));
        notice.setFill(Color.RED);
        notice.setLayoutX(130);
        notice.setLayoutY(400);
        controls.getChildren().add(notice);
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
        rectangle.setOnMouseClicked(event -> {
            controls.getChildren().clear();
            StartingView();
        });

    }

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
        NumberOfPlayerWhenWithHuman();
        NumberOfPlayerWhenWithComputer();
    }




    /**
     * get all the flags owned by one sepecific player
     * @param id the id of current player
     * @author Chucheng Qian
     *
     * *used when intended to show the flags of each player on the scene
     *
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
        String newSetup  = WarringStatesGame.GetNewSetup(cardLocalList1);
        return newSetup;
    }

    /**
     * update the scene accoroding to the player id when the number of players are 2
     *
     * show the flags owned bu each player
     *
     * * It is a draft ,upgrade required
     *
     * @param pb  an Arraylist of string representing the card placement
     * @param id the id of current player
     * @author Chucheng Qian
     */
    private void NumberofPlayerEqualto2(ArrayList<String> pb,int index,int id){
        player0.setLayoutX(600);
        player0.setLayoutY(340);
        player1.setLayoutX(800);
        player1.setLayoutY(340);
        controls.getChildren().addAll(player0,player1);
        showFlags(GetFlagofSepecificPlayer(0),600.0,600.0);
        showFlags(GetFlagofSepecificPlayer(1),800.0,600.0);
        if(id==0){
            System.out.println(GetFlagofSepecificPlayer(0));

            putPlacement(updateBoard(pb,index),1);

        }else{
            System.out.println(GetFlagofSepecificPlayer(1));

            putPlacement(updateBoard(pb,index),0);
        }


    }


    /**
     * update the scene seperately accoroding to the player id when the number of players are 3
     *
     * show the flags owned bu each player
     *
     * * It is a draft ,upgrade required
     *
     * @param pb  an Arraylist of string representing the card placement
     * @param id the id of current player
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
        showFlags(GetFlagofSepecificPlayer(1),800.0,300.0);
        showFlags(GetFlagofSepecificPlayer(2),600.0,600.0);
        if(id==0){

            putPlacement(updateBoard(pb,index),1);

        }
        if(id==1){
            //showFlags(GetFlagofSepecificPlayer(1),700.0,600.0);

            putPlacement(updateBoard(pb,index),2);
        }
        if(id==2){
            //showFlags(GetFlagofSepecificPlayer(2),700.0,600.0);
            putPlacement(updateBoard(pb,index),0);
        }
    }

    /**
     * update the scene seperately accoroding to the player id when the number of players are 4
     *
     * show the flags owned bu each player
     *
     * It is a draft ,upgrade required
     *
     * @param pb  an Arraylist of string representing the card placement
     * @param id the id of current player
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
        showFlags(GetFlagofSepecificPlayer(1),800.0,300.0);
        showFlags(GetFlagofSepecificPlayer(2),600.0,600.0);
        showFlags(GetFlagofSepecificPlayer(3),800.0,600.0);
        if(id==0){
            //showFlags(GetFlagofSepecificPlayer(0),700.0,600.0);
            putPlacement(updateBoard(pb,index),1);

        }
        if(id==1){
            //showFlags(GetFlagofSepecificPlayer(1),700.0,600.0);

            putPlacement(updateBoard(pb,index),2);
        }
        if(id==2){
            //showFlags(GetFlagofSepecificPlayer(2),700.0,600.0);

            putPlacement(updateBoard(pb,index),3);
        }
        if(id==3){
            //showFlags(GetFlagofSepecificPlayer(3),700.0,600.0);
            putPlacement(updateBoard(pb,index),0);
        }
    }

    /**
     * show flags owned one player on the scene
     *
     * It is a draft ,upgrade required
     *
     * @param list  an Arraylist of string representing all the flags owned by one user
     * @param x the x-coodinate
     * @param y the y-coodinate
     * @author Chucheng Qian
     */
    private void showFlags(ArrayList<Character> list,double x,double y){
        for(int i = 0; i<list.size();i++){
            Polygon t = new Polygon();
            t.getPoints().addAll(new Double[]{
                    x,y,
                    x+30, y,
                    x+30, y+30,
                    x, y+30

            });
            x+=30;
            if(list.get(i).equals('a')){
                t.setFill(new ImagePattern(CardImage.aa0));
            }
            if(list.get(i).equals('b')){
                t.setFill(new ImagePattern(CardImage.bb0));
            }
            if(list.get(i).equals('c')){
                t.setFill(new ImagePattern(CardImage.cc0));
            }
            if(list.get(i).equals('d')){
                t.setFill(new ImagePattern(CardImage.dd0));
            }
            if(list.get(i).equals('e')){
                t.setFill(new ImagePattern(CardImage.ee0));
            }
            if(list.get(i).equals('f')){
                t.setFill(new ImagePattern(CardImage.ff0));
            }
            if(list.get(i).equals('g')){
                t.setFill(new ImagePattern(CardImage.gg0));
            }
            controls.getChildren().add(t);

        }
    }

    /**
     * search the upper,lower,right and left side of Zhangyi to make sure the game is finished
     * (no valid moves can be done)
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
        for(int i =coltmp*6;i<=ZYlocal;i++){
            if((!presentBoard.get(i).equals(" ")) && (!presentBoard.get(i).equals("z9"))){
                return false;
            }
        }
        for(int i =(coltmp+1)*6-1;i>=ZYlocal;i--){
            if((!presentBoard.get(i).equals(" ")) && (!presentBoard.get(i).equals("z9"))){
                return false;
            }
        }
        for(int i =(ZYlocal + (6 - coltmp - 1) * 6);i>=ZYlocal;i-=6){
            if((!presentBoard.get(i).equals(" ")) && (!presentBoard.get(i).equals("z9"))){
                return false;
            }
        }
        for(int i = rowtmp;i<=ZYlocal;i+=6){
            if((!presentBoard.get(i).equals(" ")) && (!presentBoard.get(i).equals("z9"))){
                return false;
            }
        }
        return true;
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

