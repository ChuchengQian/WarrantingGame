package comp1110.ass2.gui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;

public class CardImage {


    static Image wel=new Image("comp1110/ass2/gui/pics/Welcome.JPG");
    static Image start=new Image("comp1110/ass2/gui/pics/Starting.JPG");

    static Image aa0=new Image("comp1110/ass2/gui/pics/a0Duke Xiao.jpg");
    static ImageView a0 = new ImageView(aa0);
    static Image aa1=new Image("comp1110/ass2/gui/pics/a1Shang Yang.jpg");
    static ImageView a1 = new ImageView(aa1);
    static Image aa2=new Image("comp1110/ass2/gui/pics/a2King Hui.jpg");
    static ImageView a2 = new ImageView(aa2);
    static Image aa3=new Image("comp1110/ass2/gui/pics/a3King Zhaoxiang.jpg");
    static ImageView a3 = new ImageView(aa3);
    static Image aa4=new Image("comp1110/ass2/gui/pics/a4Lady Mi.jpg");
    static ImageView a4 = new ImageView(aa4);
    static Image aa5=new Image("comp1110/ass2/gui/pics/a5Bai Qi.jpg");
    static ImageView a5 = new ImageView(aa5);
    static Image aa6=new Image("comp1110/ass2/gui/pics/a6Lady Zhao.jpg");
    static ImageView a6 = new ImageView(aa6);
    static Image aa7=new Image("comp1110/ass2/gui/pics/a7King Zheng.jpg");
    static ImageView a7 = new ImageView(aa7);
    static Image bb0=new Image("comp1110/ass2/gui/pics/b0King Xuan.jpg");
    static ImageView b0 = new ImageView(bb0);
    static Image bb1=new Image("comp1110/ass2/gui/pics/b1Zhong Wuyan.jpg");
    static ImageView b1 = new ImageView(bb1);
    static Image bb2=new Image("comp1110/ass2/gui/pics/b2Lord Mengchang.jpg");
    static ImageView b2 = new ImageView(bb2);
    static Image bb3=new Image("comp1110/ass2/gui/pics/b3King Xiang.jpg");
    static ImageView b3 = new ImageView(bb3);
    static Image bb4=new Image("comp1110/ass2/gui/pics/b4Queen Junwang.jpg");
    static ImageView b4 = new ImageView(bb4);
    static Image bb5=new Image("comp1110/ass2/gui/pics/b5King Jian.jpg");
    static ImageView b5 = new ImageView(bb5);
    static Image bb6=new Image("comp1110/ass2/gui/pics/b6Sun Bin.jpg");
    static ImageView b6 = new ImageView(bb6);
    static Image cc0=new Image("comp1110/ass2/gui/pics/c0Wu Qi.jpg");
    static ImageView c0 = new ImageView(cc0);
    static Image cc1=new Image("comp1110/ass2/gui/pics/c1King kaolie.jpg");
    static ImageView c1 = new ImageView(cc1);
    static Image cc2=new Image("comp1110/ass2/gui/pics/c2King You.jpg");
    static ImageView c2 = new ImageView(cc2);
    static Image cc3=new Image("comp1110/ass2/gui/pics/c3King Ai.jpg");
    static ImageView c3 = new ImageView(cc3);
    static Image cc4=new Image("comp1110/ass2/gui/pics/c4Fuchu.jpg");
    static ImageView c4 = new ImageView(cc4);
    static Image cc5=new Image("comp1110/ass2/gui/pics/c5Lord Chunshen.jpg");
    static ImageView c5 = new ImageView(cc5);
    static Image dd0=new Image("comp1110/ass2/gui/pics/d0King Wuling.jpg");
    static ImageView d0 = new ImageView(dd0);
    static Image dd1=new Image("comp1110/ass2/gui/pics/d1Lord Pingyuan.jpg");
    static ImageView d1 = new ImageView(dd1);
    static Image dd2=new Image("comp1110/ass2/gui/pics/d2King Xiaocheng.jpg");
    static ImageView d2 = new ImageView(dd2);
    static Image dd3=new Image("comp1110/ass2/gui/pics/d3Mu Li.jpg");
    static ImageView d3 = new ImageView(dd3);
    static Image dd4=new Image("comp1110/ass2/gui/pics/d4Lian Po.jpg");
    static ImageView d4 = new ImageView(dd4);
    static Image ee0=new Image("comp1110/ass2/gui/pics/e0Marquess Ai.jpg");
    static ImageView e0 = new ImageView(ee0);
    static Image ee1=new Image("comp1110/ass2/gui/pics/e1King Huanhui.jpg");
    static ImageView e1 = new ImageView(ee1);
    static Image ee2=new Image("comp1110/ass2/gui/pics/e2King An.jpg");
    static ImageView e2 = new ImageView(ee2);
    static Image ee3=new Image("comp1110/ass2/gui/pics/e3Han Fei.jpg");
    static ImageView e3 = new ImageView(ee3);
    static Image ff0=new Image("comp1110/ass2/gui/pics/f0Marquess Wen.jpg");
    static ImageView f0 = new ImageView(ff0);
    static Image ff1=new Image("comp1110/ass2/gui/pics/f1Lord Xinling.jpg");
    static ImageView f1 = new ImageView(ff1);
    static Image ff2=new Image("comp1110/ass2/gui/pics/f2King Anxi.jpg");
    static ImageView f2 = new ImageView(ff2);
    static Image gg0=new Image("comp1110/ass2/gui/pics/g0King Xi.jpg");
    static ImageView g0 = new ImageView(gg0);
    static Image gg1=new Image("comp1110/ass2/gui/pics/g1Prince Dan.jpg");
    static ImageView g1 = new ImageView(gg1);
    static Image zz9=new Image("comp1110/ass2/gui/pics/z9Zhang Yi.jpg");
    static ImageView z9 = new ImageView(zz9);

    static final void setImageToButton(Button card, String cardInfo) {

        if(cardInfo.equals("a0")){
            a0.setFitHeight(70);
            a0.setFitWidth(70);
            card.setStyle("-fx-background-color: #f5b9c2");
            card.setGraphic(a0);
        }
        if(cardInfo.equals("a1")){
            a1.setFitHeight(70);
            a1.setFitWidth(70);
            card.setStyle("-fx-background-color: #f5b9c2");
            card.setGraphic(a1);
        }
        if(cardInfo.equals("a2")){
            a2.setFitHeight(70);
            a2.setFitWidth(70);
            card.setStyle("-fx-background-color: #f5b9c2");
            card.setGraphic(a2);
        }
        if(cardInfo.equals("a3")){
            a3.setFitHeight(70);
            a3.setFitWidth(70);
            card.setStyle("-fx-background-color: #f5b9c2");
            card.setGraphic(a3);
        }
        if(cardInfo.equals("a4")){
            a4.setFitHeight(70);
            a4.setFitWidth(70);
            card.setStyle("-fx-background-color: #f5b9c2");
            card.setGraphic(a4);
        }
        if(cardInfo.equals("a5")){
            a5.setFitHeight(70);
            a5.setFitWidth(70);
            card.setStyle("-fx-background-color: #f5b9c2");
            card.setGraphic(a5);
        }
        if(cardInfo.equals("a6")){
            a6.setFitHeight(70);
            a6.setFitWidth(70);
            card.setStyle("-fx-background-color: #f5b9c2");
            card.setGraphic(a6);
        }
        if(cardInfo.equals("a7")){
            a7.setFitHeight(70);
            a7.setFitWidth(70);
            card.setStyle("-fx-background-color: #f5b9c2");
            card.setGraphic(a7);
        }
        if(cardInfo.equals("b0")){
            b0.setFitHeight(70);
            b0.setFitWidth(70);
            card.setStyle("-fx-background-color: #eee7b1");
            card.setGraphic(b0);
        }
        if(cardInfo.equals("b1")){
            b1.setFitHeight(70);
            b1.setFitWidth(70);
            card.setStyle("-fx-background-color: #eee7b1");
            card.setGraphic(b1);
        }
        if(cardInfo.equals("b2")){
            b2.setFitHeight(70);
            b2.setFitWidth(70);
            card.setStyle("-fx-background-color: #eee7b1");
            card.setGraphic(b2);
        }
        if(cardInfo.equals("b3")){
            b3.setFitHeight(70);
            b3.setFitWidth(70);
            card.setStyle("-fx-background-color: #eee7b1");
            card.setGraphic(b3);
        }
        if(cardInfo.equals("b4")){
            b4.setFitHeight(70);
            b4.setFitWidth(70);
            card.setStyle("-fx-background-color: #eee7b1");
            card.setGraphic(b4);
        }
        if(cardInfo.equals("b5")){
            b5.setFitHeight(70);
            b5.setFitWidth(70);
            card.setStyle("-fx-background-color: #eee7b1");
            card.setGraphic(b5);
        }
        if(cardInfo.equals("b6")){
            b6.setFitHeight(70);
            b6.setFitWidth(70);
            card.setStyle("-fx-background-color: #eee7b1");
            card.setGraphic(b6);
        }
        if(cardInfo.equals("c0")){
            c0.setFitHeight(70);
            c0.setFitWidth(70);
            card.setStyle("-fx-background-color: #a6ea99");
            card.setGraphic(c0);
        }
        if(cardInfo.equals("c1")){
            c1.setFitHeight(70);
            c1.setFitWidth(70);
            card.setStyle("-fx-background-color: #a6ea99");
            card.setGraphic(c1);
        }
        if(cardInfo.equals("c2")){
            c2.setFitHeight(70);
            c2.setFitWidth(70);
            card.setStyle("-fx-background-color: #a6ea99");
            card.setGraphic(c2);
        }
        if(cardInfo.equals("c3")){
            c3.setFitHeight(70);
            c3.setFitWidth(70);
            card.setStyle("-fx-background-color: #a6ea99");
            card.setGraphic(c3);
        }
        if(cardInfo.equals("c4")){
            c4.setFitHeight(70);
            c4.setFitWidth(70);
            card.setStyle("-fx-background-color: #a6ea99");
            card.setGraphic(c4);
        }
        if(cardInfo.equals("c5")){
            c5.setFitHeight(70);
            c5.setFitWidth(70);
            card.setStyle("-fx-background-color: #a6ea99");
            card.setGraphic(c5);
        }
        if(cardInfo.equals("d0")){
            d0.setFitHeight(70);
            d0.setFitWidth(70);
            card.setStyle("-fx-background-color: #bbeced");
            card.setGraphic(d0);
        }
        if(cardInfo.equals("d1")){
            d1.setFitHeight(70);
            d1.setFitWidth(70);
            card.setStyle("-fx-background-color: #bbeced");
            card.setGraphic(d1);
        }
        if(cardInfo.equals("d2")){
            d2.setFitHeight(70);
            d2.setFitWidth(70);
            card.setStyle("-fx-background-color: #bbeced");
            card.setGraphic(d2);
        }
        if(cardInfo.equals("d3")){
            d3.setFitHeight(70);
            d3.setFitWidth(70);
            card.setStyle("-fx-background-color: #bbeced");
            card.setGraphic(d3);
        }
        if(cardInfo.equals("d4")){
            d4.setFitHeight(70);
            d4.setFitWidth(70);
            card.setStyle("-fx-background-color: #bbeced");
            card.setGraphic(d4);
        }
        if(cardInfo.equals("e0")){
            e0.setFitHeight(70);
            e0.setFitWidth(70);
            card.setStyle("-fx-background-color: #8c75d4");
            card.setGraphic(e0);
        }
        if(cardInfo.equals("e1")){
            e1.setFitHeight(70);
            e1.setFitWidth(70);
            card.setStyle("-fx-background-color: #8c75d4");
            card.setGraphic(e1);
        }
        if(cardInfo.equals("e2")){
            e2.setFitHeight(70);
            e2.setFitWidth(70);
            card.setStyle("-fx-background-color: #8c75d4");
            card.setGraphic(e2);
        }
        if(cardInfo.equals("e3")){
            e3.setFitHeight(70);
            e3.setFitWidth(70);
            card.setStyle("-fx-background-color: #8c75d4");
            card.setGraphic(e3);
        }
        if(cardInfo.equals("f0")){
            f0.setFitHeight(70);
            f0.setFitWidth(70);
            card.setStyle("-fx-background-color: #f3a481");
            card.setGraphic(f0);
        }
        if(cardInfo.equals("f1")){
            f1.setFitHeight(70);
            f1.setFitWidth(70);
            card.setStyle("-fx-background-color: #f3a481");
            card.setGraphic(f1);
        }
        if(cardInfo.equals("f2")){
            f2.setFitHeight(70);
            f2.setFitWidth(70);
            card.setStyle("-fx-background-color: #f3a481");
            card.setGraphic(f2);
        }
        if(cardInfo.equals("g0")){
            g0.setFitHeight(70);
            g0.setFitWidth(70);
            card.setStyle("-fx-background-color: #d3d3d3");
            card.setGraphic(g0);
        }
        if(cardInfo.equals("g1")){
            g1.setFitHeight(70);
            g1.setFitWidth(70);
            card.setStyle("-fx-background-color: #d3d3d3");
            card.setGraphic(g1);
        }
        if(cardInfo.equals("z9")){
            z9.setFitHeight(70);
            z9.setFitWidth(70);
            card.setStyle("-fx-background-color: #000000");
            card.setGraphic(z9);
        }


    }

    static final void setImageToSuppoters(Polygon card, String cardInfo) {

        if(cardInfo.equals("a0")){
            card.setFill(new ImagePattern(aa0));
        }
        if(cardInfo.equals("a1")){
            card.setFill(new ImagePattern(aa1));
        }
        if(cardInfo.equals("a2")){
            card.setFill(new ImagePattern(aa2));
        }
        if(cardInfo.equals("a3")){
            card.setFill(new ImagePattern(aa3));
        }
        if(cardInfo.equals("a4")){
            card.setFill(new ImagePattern(aa4));
        }
        if(cardInfo.equals("a5")){
            card.setFill(new ImagePattern(aa5));
        }
        if(cardInfo.equals("a6")){
            card.setFill(new ImagePattern(aa6));
        }
        if(cardInfo.equals("a7")){
            card.setFill(new ImagePattern(aa7));
        }
        if(cardInfo.equals("b0")){
            card.setFill(new ImagePattern(bb0));
        }
        if(cardInfo.equals("b1")){
            card.setFill(new ImagePattern(bb1));
        }
        if(cardInfo.equals("b2")){
            card.setFill(new ImagePattern(bb2));
        }
        if(cardInfo.equals("b3")){
            card.setFill(new ImagePattern(bb3));
        }
        if(cardInfo.equals("b4")){
            card.setFill(new ImagePattern(bb4));
        }
        if(cardInfo.equals("b5")){
            card.setFill(new ImagePattern(bb5));
        }
        if(cardInfo.equals("b6")){
            card.setFill(new ImagePattern(bb6));
        }
        if(cardInfo.equals("c0")){
            card.setFill(new ImagePattern(cc0));
        }
        if(cardInfo.equals("c1")){
            card.setFill(new ImagePattern(cc1));
        }
        if(cardInfo.equals("c2")){
            card.setFill(new ImagePattern(cc2));
        }
        if(cardInfo.equals("c3")){
            card.setFill(new ImagePattern(cc3));
        }
        if(cardInfo.equals("c4")){
            card.setFill(new ImagePattern(cc4));
        }
        if(cardInfo.equals("c5")){
            card.setFill(new ImagePattern(cc5));
        }
        if(cardInfo.equals("d0")){
            card.setFill(new ImagePattern(dd0));
        }
        if(cardInfo.equals("d1")){
            card.setFill(new ImagePattern(dd1));
        }
        if(cardInfo.equals("d2")){
            card.setFill(new ImagePattern(dd2));
        }
        if(cardInfo.equals("d3")){
            card.setFill(new ImagePattern(dd3));
        }
        if(cardInfo.equals("d4")){
            card.setFill(new ImagePattern(dd4));
        }
        if(cardInfo.equals("e0")){
            card.setFill(new ImagePattern(ee0));
        }
        if(cardInfo.equals("e1")){
            card.setFill(new ImagePattern(ee1));
        }
        if(cardInfo.equals("e2")){
            card.setFill(new ImagePattern(ee2));
        }
        if(cardInfo.equals("e3")){
            card.setFill(new ImagePattern(ee3));
        }
        if(cardInfo.equals("f0")){
            card.setFill(new ImagePattern(ff0));
        }
        if(cardInfo.equals("f1")){
            card.setFill(new ImagePattern(ff1));
        }
        if(cardInfo.equals("f2")){
            card.setFill(new ImagePattern(ff2));
        }
        if(cardInfo.equals("g0")){
            card.setFill(new ImagePattern(gg0));
        }
        if(cardInfo.equals("g1")){
            card.setFill(new ImagePattern(gg1));
        }


    }





}
