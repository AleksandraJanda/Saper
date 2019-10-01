import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

class Window extends JFrame implements MouseListener{
    static int width = 550;
    static int height = 600;
    Window(){
        super("Saper");
        Dimension size = new Dimension(width,height);
        setSize(size);
        setIconImage(FieldButtons.bombImg.getImage());
        setJMenuBar(Menu.createMenuBar());
        Menu.reset.addMouseListener(this);
        setContentPane(Content.setAppContent());
        setResizable(false);
        setVisible(true);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        FieldButtons.allButtonNumberLists = new ArrayList<>();
        FieldButtons.mapOfImages = new HashMap<>();
        FieldButtons.listOfButtons = new ArrayList<>();
        setContentPane(Content.setAppContent());
        Menu.gameStatus.setText("Game started!");
        Menu.gameStatus.setSelected(false);
        setVisible(true);
    }
    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
