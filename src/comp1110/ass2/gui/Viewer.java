package comp1110.ass2.gui;

import comp1110.util.DataUtil;
import gittest.A;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;

/**
 * A very simple viewer for card layouts in the Warring States game.
 * <p>
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various card placements.
 */
public class Viewer extends Application {

    private static final int VIEWER_WIDTH = 933;
    private static final int VIEWER_HEIGHT = 700;

    private static final String URI_BASE = "assets/";

    private static ArrayList<CardInfo> cardInit = new ArrayList<>();

    private final Group root = new Group();
    private final Group controls = new Group();
    GridPane grid = new GridPane();
    TextField textField;

    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement A valid placement string
     * @author Chunxiang Song
     */
    void makePlacement(String placement) {
        // FIXME Task 4: implement the simple placement viewer
        ArrayList<String> cardLocalList;
        DataUtil util = new DataUtil();
        cardLocalList = util.placementSortToList(placement);

        grid.getChildren().clear();
        int flag = 0;
        for (int i = 5; i >= 0; i--){
            for (int j = 0; j < 6; j++){
                Button text = new Button(cardLocalList.get(flag));
                grid.add(text, i, j);
                flag ++;
            }
        }
    }

    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label label1 = new Label("Placement:");
        textField = new TextField();
        textField.setPrefWidth(300);
        Button button = new Button("Refresh");
        button.setOnAction(e -> {
            controls.getChildren().clear();
            makePlacement(textField.getText());
            controls.getChildren().add(grid);
            HBox hb = new HBox();
            hb.getChildren().addAll(label1, textField, button);
            hb.setSpacing(10);
            hb.setLayoutX(130);
            hb.setLayoutY(VIEWER_HEIGHT - 50);
            controls.getChildren().add(hb);
            textField.clear();

        });
        grid.setHgap(6);
        grid.setHgap(6);
        grid.setPadding(new Insets(0,10,0,10));
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Warring States Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);



        root.getChildren().add(controls);

        makeControls();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
