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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
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

    String stt = generateRandomSetup();












    private static final String URI_BASE = "assets/";

    private static ArrayList<CardInfo> cardInit = new ArrayList<>();

    private final Group root = new Group();
    private final Group controls = new Group();
    GridPane grid = new GridPane();

    String moves="";

    public String Winnners(){

        int num = Integer.parseInt(tt);
        int allFlags[]=WarringStatesGame.getFlags(stt,moves,num);
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
        int maxFlag =Collections.max(store);
        for(int i=0;i<store.size();i++){
            if(store.get(i)==maxFlag){
                winners=winners+" "+i;
            }
        }
        return winners;
    }


    void makePlacement(String placement,int id) {
        int num = Integer.parseInt(tt);
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



        grid.getChildren().clear();
        int index = 0;
        for (int i = 5; i >= 0; i--){
            for (int j = 0; j < 6; j++){
                int idd = id;
                Button text = new Button(cardLocalList.get(index));
                int indexx = index;
                char location =indexx < 26 ? (char)(indexx + 'A') : (char)(indexx - 26 + '0');
                text.setOnMousePressed(event -> {
                    if(WarringStatesGame.isMoveLegal(placement,location)){
                        moves =moves+location;
                        controls.getChildren().clear();
                        makePlacement(placement,id);
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


                    }else {
                        InvalidMoveNotice();
                    }


                });
                grid.add(text, i, j);
                index ++;
            }
        }
        }

    }

    TextField numbr;
    String tt;

    /**
     * Ensure that the game can be played by 2-4 human players.
     */
    void Number_of_player() {
        Label label0 = new Label("Number of players:");
        numbr = new TextField();
        numbr.setPrefWidth(50);
        Button button1 = new Button("Start");
        button1.setOnAction(event -> {
            tt = numbr.getText();
            int num = Integer.parseInt(tt);
            if(num>=2 && num <=4){
                controls.getChildren().clear();
                makePlacement(stt,0);
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
        hb1.getChildren().addAll(label0, numbr, button1);
        hb1.setSpacing(10);
        hb1.setLayoutX(300);
        hb1.setLayoutY(500);
        controls.getChildren().add(hb1);
    }

    void InvalidMoveNotice(){
        Text notice = new Text("Invalid move ,try again!");
        notice.setFont(Font.font("Tahoma", FontWeight.BOLD,50));
        notice.setFill(Color.RED);
        notice.setLayoutX(130);
        notice.setLayoutY(400);
        controls.getChildren().add(notice);
    }





    private void SetupGrid() {
        grid.setHgap(2);
        grid.setVgap(2);
        grid.setScaleX(2);
        grid.setScaleY(2);
        grid.setPadding(new Insets(80, 0, 0, 80));
        controls.getChildren().add(grid);
    }






    ArrayList<Character> GetFlagofSepecificPlayer(int id){
        ArrayList<Character> flags = new ArrayList<>();
        int num = Integer.parseInt(tt);
        int allFlags[]=WarringStatesGame.getFlags(stt,moves,num);
        for(int i = 0;i < allFlags.length;i++){
            if(allFlags[i]==id){
                flags.add((char)(i+'a'));
            }

        }
        return flags;
    }





    String updateBoard(ArrayList<String> cardLocalList1,int target){
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


    void NumberofPlayerEqualto2(ArrayList<String> pb,int index,int id){
        if(id==0){
            //showFlags(GetFlagofSepecificPlayer(0),700.0,600.0);

            makePlacement(updateBoard(pb,index),1);

        }else{
            //showFlags(GetFlagofSepecificPlayer(0),700.0,600.0);
            makePlacement(updateBoard(pb,index),0);
        }


    }



    void NumberofPlayerEqualto3(ArrayList<String> pb,int index,int id){
        if(id==0){
            //showFlags(GetFlagofSepecificPlayer(0),700.0,600.0);

            makePlacement(updateBoard(pb,index),1);

        }
        if(id==1){
            //showFlags(GetFlagofSepecificPlayer(1),700.0,600.0);

            makePlacement(updateBoard(pb,index),2);
        }
        if(id==2){
            //showFlags(GetFlagofSepecificPlayer(2),700.0,600.0);
            makePlacement(updateBoard(pb,index),0);
        }
    }


    void NumberofPlayerEqualto4(ArrayList<String> pb,int index,int id){
        if(id==0){
            //showFlags(GetFlagofSepecificPlayer(0),700.0,600.0);
            makePlacement(updateBoard(pb,index),1);

        }
        if(id==1){
            //showFlags(GetFlagofSepecificPlayer(1),700.0,600.0);

            makePlacement(updateBoard(pb,index),2);
        }
        if(id==2){
            //showFlags(GetFlagofSepecificPlayer(2),700.0,600.0);

            makePlacement(updateBoard(pb,index),3);
        }
        if(id==3){
            //showFlags(GetFlagofSepecificPlayer(3),700.0,600.0);
            makePlacement(updateBoard(pb,index),0);
        }
    }
    StackPane stackPane= new StackPane();

    void showFlags(ArrayList<Character> list,double x,double y){
        System.out.println(list);
        for(int i = 0; i<list.size();i++){
            Polygon t = new Polygon();
            t.getPoints().addAll(new Double[]{
                    x,y,
                    x+2, y,
                    x, y+2,
                    x+2, y+2

            });
            String flagname = list.get(i).toString();
            Text flagName = new Text(flagname);
            t.setLayoutX(x+i*20);
            t.setLayoutY(y+i*20);
            t.setFill(Color.RED);
            stackPane.getChildren().addAll(t,flagName);
        }
    }

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
        primaryStage.setTitle("Warring States Viewer");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);




        root.getChildren().add(controls);

        Number_of_player();




        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

