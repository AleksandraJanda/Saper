import javax.swing.*;
import java.awt.*;

class Window extends JFrame {
    static int width = 550;
    static int height = 600;
    Window(){
        super("Saper");
        Dimension size = new Dimension(width,height);
        setSize(size);
        setJMenuBar(Menu.createMenuBar());
        setContentPane(Content.setAppContent());

        setResizable(false);
        setVisible(true);
    }
}
