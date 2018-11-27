import javax.swing.*;
import java.awt.*;

class Menu {
    private static JMenu start, reset, info;
    private static JMenuItem information;

    static JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        start = addMenu("START");
        menuBar.add(start);
        reset = addMenu("RESET");
        menuBar.add(reset);
        info = addMenu("INFO");
        info.addSeparator();
        information = addMenuItem("Click on field to reveal");
        info.add(information);
        menuBar.add(info);
        return menuBar;
    }
    private static JMenuItem addMenuItem(String name){
        JMenuItem menuItem = new JMenuItem(name);
        menuItem.setFont(font());
        return menuItem;
    }
    private static JMenu addMenu(String name){
        JMenu menu = new JMenu(name);
        menu.setFont(font());
        return menu;
    }
    private static Font font(){
        return new Font("Lato", Font.PLAIN, 14);
    }
}
