package comp1110.ass2;


import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

public interface ActionEvent {

    public void mouseDragged(MouseEvent event);

    public void mouseClick(MouseEvent event);

    public void keyPressed(KeyEvent event);
}
