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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    TextField numberOfplayers;
    String theNumberOfplayers;

    Image aa0=new Image("comp1110/ass2/gui/pics/a0Duke Xiao.jpg");
    ImageView a0 = new ImageView(aa0);
    Image aa1=new Image("comp1110/ass2/gui/pics/a1Shang Yang.jpg");
    ImageView a1 = new ImageView(aa1);
    Image aa2=new Image("comp1110/ass2/gui/pics/a2King Hui.jpg");
    ImageView a2 = new ImageView(aa2);
    Image aa3=new Image("comp1110/ass2/gui/pics/a3King Zhaoxiang.jpg");
    ImageView a3 = new ImageView(aa3);
    Image aa4=new Image("comp1110/ass2/gui/pics/a4Lady Mi.jpg");
    ImageView a4 = new ImageView(aa4);
    Image aa5=new Image("comp1110/ass2/gui/pics/a5Bai Qi.jpg");
    ImageView a5 = new ImageView(aa5);
    Image aa6=new Image("comp1110/ass2/gui/pics/a6Lady Zhao.jpg");
    ImageView a6 = new ImageView(aa6);
    Image aa7=new Image("comp1110/ass2/gui/pics/a7King Zheng.jpg");
    ImageView a7 = new ImageView(aa7);
    Image bb0=new Image("comp1110/ass2/gui/pics/b0King Xuan.jpg");
    ImageView b0 = new ImageView(bb0);
    Image bb1=new Image("comp1110/ass2/gui/pics/b1Zhong Wuyan.jpg");
    ImageView b1 = new ImageView(bb1);
    Image bb2=new Image("comp1110/ass2/gui/pics/b2Lord Mengchang.jpg");
    ImageView b2 = new ImageView(bb2);
    Image bb3=new Image("comp1110/ass2/gui/pics/b3King Xiang.jpg");
    ImageView b3 = new ImageView(bb3);
    Image bb4=new Image("comp1110/ass2/gui/pics/b4Queen Junwang.jpg");
    ImageView b4 = new ImageView(bb4);
    Image bb5=new Image("comp1110/ass2/gui/pics/b5King Jian.jpg");
    ImageView b5 = new ImageView(bb5);
    Image bb6=new Image("comp1110/ass2/gui/pics/b6Sun Bin.jpg");
    ImageView b6 = new ImageView(bb6);
    Image cc0=new Image("comp1110/ass2/gui/pics/c0Wu Qi.jpg");
    ImageView c0 = new ImageView(cc0);
    Image cc1=new Image("comp1110/ass2/gui/pics/c1King kaolie.jpg");
    ImageView c1 = new ImageView(cc1);
    Image cc2=new Image("comp1110/ass2/gui/pics/c2King You.jpg");
    ImageView c2 = new ImageView(cc2);
    Image cc3=new Image("comp1110/ass2/gui/pics/c3King Ai.jpg");
    ImageView c3 = new ImageView(cc3);
    Image cc4=new Image("comp1110/ass2/gui/pics/c4Fuchu.jpg");
    ImageView c4 = new ImageView(cc4);
    Image cc5=new Image("comp1110/ass2/gui/pics/c5Lord Chunshen.jpg");
    ImageView c5 = new ImageView(cc5);
    Image dd0=new Image("comp1110/ass2/gui/pics/d0King Wuling.jpg");
    ImageView d0 = new ImageView(dd0);
    Image dd1=new Image("comp1110/ass2/gui/pics/d1Lord Pingyuan.jpg");
    ImageView d1 = new ImageView(dd1);
    Image dd2=new Image("comp1110/ass2/gui/pics/d2King Xiaocheng.jpg");
    ImageView d2 = new ImageView(dd2);
    Image dd3=new Image("comp1110/ass2/gui/pics/d3Mu Li.jpg");
    ImageView d3 = new ImageView(dd3);
    Image dd4=new Image("comp1110/ass2/gui/pics/d4Lian Po.jpg");
    ImageView d4 = new ImageView(dd4);
    Image ee0=new Image("comp1110/ass2/gui/pics/e0Marquess Ai.jpg");
    ImageView e0 = new ImageView(ee0);
    Image ee1=new Image("comp1110/ass2/gui/pics/e1King Huanhui.jpg");
    ImageView e1 = new ImageView(ee1);
    Image ee2=new Image("comp1110/ass2/gui/pics/e2King An.jpg");
    ImageView e2 = new ImageView(ee2);
    Image ee3=new Image("comp1110/ass2/gui/pics/e3Han Fei.jpg");
    ImageView e3 = new ImageView(ee3);
    Image ff0=new Image("comp1110/ass2/gui/pics/f0Marquess Wen.jpg");
    ImageView f0 = new ImageView(ff0);
    Image ff1=new Image("comp1110/ass2/gui/pics/f1Lord Xinling.jpg");
    ImageView f1 = new ImageView(ff1);
    Image ff2=new Image("comp1110/ass2/gui/pics/f2King Anxi.jpg");
    ImageView f2 = new ImageView(ff2);
    Image gg0=new Image("comp1110/ass2/gui/pics/g0King Xi.jpg");
    ImageView g0 = new ImageView(gg0);
    Image gg1=new Image("comp1110/ass2/gui/pics/g1Prince Dan.jpg");
    ImageView g1 = new ImageView(gg1);
    Image zz9=new Image("comp1110/ass2/gui/pics/z9Zhang Yi.jpg");
    ImageView z9 = new ImageView(zz9);





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
                    card.setMinSize(120,120);
                    card.setMaxSize(120,120);
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
                    setImageToButton(card,cardLocalList.get(index));
                    grid.add(card, i, j);
                    index ++;
                }
            }
        }

    }

    private void setImageToButton(Button card,String cardInfo) {

        if(cardInfo.equals("a0")){
            a0.setFitHeight(110);
            a0.setFitWidth(110);
            card.setStyle("-fx-background-color: #f5b9c2");
            card.setGraphic(a0);
        }
        if(cardInfo.equals("a1")){
            a1.setFitHeight(110);
            a1.setFitWidth(110);
            card.setStyle("-fx-background-color: #f5b9c2");
            card.setGraphic(a1);
        }
        if(cardInfo.equals("a2")){
            a2.setFitHeight(110);
            a2.setFitWidth(110);
            card.setStyle("-fx-background-color: #f5b9c2");
            card.setGraphic(a2);
        }
        if(cardInfo.equals("a3")){
            a3.setFitHeight(110);
            a3.setFitWidth(110);
            card.setStyle("-fx-background-color: #f5b9c2");
            card.setGraphic(a3);
        }
        if(cardInfo.equals("a4")){
            a4.setFitHeight(110);
            a4.setFitWidth(110);
            card.setStyle("-fx-background-color: #f5b9c2");
            card.setGraphic(a4);
        }
        if(cardInfo.equals("a5")){
            a5.setFitHeight(110);
            a5.setFitWidth(110);
            card.setStyle("-fx-background-color: #f5b9c2");
            card.setGraphic(a5);
        }
        if(cardInfo.equals("a6")){
            a6.setFitHeight(110);
            a6.setFitWidth(110);
            card.setStyle("-fx-background-color: #f5b9c2");
            card.setGraphic(a6);
        }
        if(cardInfo.equals("a7")){
            a7.setFitHeight(110);
            a7.setFitWidth(110);
            card.setStyle("-fx-background-color: #f5b9c2");
            card.setGraphic(a7);
        }
        if(cardInfo.equals("b0")){
            b0.setFitHeight(110);
            b0.setFitWidth(110);
            card.setStyle("-fx-background-color: #eee7b1");
            card.setGraphic(b0);
        }
        if(cardInfo.equals("b1")){
            b1.setFitHeight(110);
            b1.setFitWidth(110);
            card.setStyle("-fx-background-color: #eee7b1");
            card.setGraphic(b1);
        }
        if(cardInfo.equals("b2")){
            b2.setFitHeight(110);
            b2.setFitWidth(110);
            card.setStyle("-fx-background-color: #eee7b1");
            card.setGraphic(b2);
        }
        if(cardInfo.equals("b3")){
            b3.setFitHeight(110);
            b3.setFitWidth(110);
            card.setStyle("-fx-background-color: #eee7b1");
            card.setGraphic(b3);
        }
        if(cardInfo.equals("b4")){
            b4.setFitHeight(110);
            b4.setFitWidth(110);
            card.setStyle("-fx-background-color: #eee7b1");
            card.setGraphic(b4);
        }
        if(cardInfo.equals("b5")){
            b5.setFitHeight(110);
            b5.setFitWidth(110);
            card.setStyle("-fx-background-color: #eee7b1");
            card.setGraphic(b5);
        }
        if(cardInfo.equals("b6")){
            b6.setFitHeight(110);
            b6.setFitWidth(110);
            card.setStyle("-fx-background-color: #eee7b1");
            card.setGraphic(b6);
        }
        if(cardInfo.equals("c0")){
            c0.setFitHeight(110);
            c0.setFitWidth(110);
            card.setStyle("-fx-background-color: #a6ea99");
            card.setGraphic(c0);
        }
        if(cardInfo.equals("c1")){
            c1.setFitHeight(110);
            c1.setFitWidth(110);
            card.setStyle("-fx-background-color: #a6ea99");
            card.setGraphic(c1);
        }
        if(cardInfo.equals("c2")){
            c2.setFitHeight(110);
            c2.setFitWidth(110);
            card.setStyle("-fx-background-color: #a6ea99");
            card.setGraphic(c2);
        }
        if(cardInfo.equals("c3")){
            c3.setFitHeight(110);
            c3.setFitWidth(110);
            card.setStyle("-fx-background-color: #a6ea99");
            card.setGraphic(c3);
        }
        if(cardInfo.equals("c4")){
            c4.setFitHeight(110);
            c4.setFitWidth(110);
            card.setStyle("-fx-background-color: #a6ea99");
            card.setGraphic(c4);
        }
        if(cardInfo.equals("c5")){
            c5.setFitHeight(110);
            c5.setFitWidth(110);
            card.setStyle("-fx-background-color: #a6ea99");
            card.setGraphic(c5);
        }
        if(cardInfo.equals("d0")){
            d0.setFitHeight(110);
            d0.setFitWidth(110);
            card.setStyle("-fx-background-color: #bbeced");
            card.setGraphic(d0);
        }
        if(cardInfo.equals("d1")){
            d1.setFitHeight(110);
            d1.setFitWidth(110);
            card.setStyle("-fx-background-color: #bbeced");
            card.setGraphic(d1);
        }
        if(cardInfo.equals("d2")){
            d2.setFitHeight(110);
            d2.setFitWidth(110);
            card.setStyle("-fx-background-color: #bbeced");
            card.setGraphic(d2);
        }
        if(cardInfo.equals("d3")){
            d3.setFitHeight(110);
            d3.setFitWidth(110);
            card.setStyle("-fx-background-color: #bbeced");
            card.setGraphic(d3);
        }
        if(cardInfo.equals("d4")){
            d4.setFitHeight(110);
            d4.setFitWidth(110);
            card.setStyle("-fx-background-color: #bbeced");
            card.setGraphic(d4);
        }
        if(cardInfo.equals("e0")){
            e0.setFitHeight(110);
            e0.setFitWidth(110);
            card.setStyle("-fx-background-color: #8c75d4");
            card.setGraphic(e0);
        }
        if(cardInfo.equals("e1")){
            e1.setFitHeight(110);
            e1.setFitWidth(110);
            card.setStyle("-fx-background-color: #8c75d4");
            card.setGraphic(e1);
        }
        if(cardInfo.equals("e2")){
            e2.setFitHeight(110);
            e2.setFitWidth(110);
            card.setStyle("-fx-background-color: #8c75d4");
            card.setGraphic(e2);
        }
        if(cardInfo.equals("e3")){
            e3.setFitHeight(110);
            e3.setFitWidth(110);
            card.setStyle("-fx-background-color: #8c75d4");
            card.setGraphic(e3);
        }
        if(cardInfo.equals("f0")){
            f0.setFitHeight(110);
            f0.setFitWidth(110);
            card.setStyle("-fx-background-color: #f3a481");
            card.setGraphic(f0);
        }
        if(cardInfo.equals("f1")){
            f1.setFitHeight(110);
            f1.setFitWidth(110);
            card.setStyle("-fx-background-color: #f3a481");
            card.setGraphic(f1);
        }
        if(cardInfo.equals("f2")){
            f2.setFitHeight(110);
            f2.setFitWidth(110);
            card.setStyle("-fx-background-color: #f3a481");
            card.setGraphic(f2);
        }
        if(cardInfo.equals("g0")){
            g0.setFitHeight(110);
            g0.setFitWidth(110);
            card.setStyle("-fx-background-color: #d3d3d3");
            card.setGraphic(g0);
        }
        if(cardInfo.equals("g1")){
            g1.setFitHeight(110);
            g1.setFitWidth(110);
            card.setStyle("-fx-background-color: #d3d3d3");
            card.setGraphic(g1);
        }
        if(cardInfo.equals("z9")){
            z9.setFitHeight(110);
            z9.setFitWidth(110);
            card.setStyle("-fx-background-color: #000000");
            card.setGraphic(z9);
        }





    }




    /**
     * allow user to type in the number of players
     * allow user to click on the button "Start" to start the game
     * Ensure that the game can be played by 2-4 human players.
     * when the number of players are invalid,notice the user
     * @author Chucheng Qian
     */
    private void Number_of_player() {
        Label label0 = new Label("Number of players:");
        numberOfplayers = new TextField();
        numberOfplayers.setPrefWidth(50);
        Button button1 = new Button("Start");
        button1.setOnAction(event -> {
            theNumberOfplayers = numberOfplayers.getText();
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
        hb1.getChildren().addAll(label0, numberOfplayers, button1);
        hb1.setSpacing(10);
        hb1.setLayoutX(300);
        hb1.setLayoutY(500);
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
        if(id==0){
            //showFlags(GetFlagofSepecificPlayer(0),700.0,600.0);

            putPlacement(updateBoard(pb,index),1);

        }else{
            //showFlags(GetFlagofSepecificPlayer(0),700.0,600.0);
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
        Number_of_player();
        //show the scene
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

