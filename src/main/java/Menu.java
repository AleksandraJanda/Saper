import javax.swing.*;
import java.awt.*;

class Menu {
    static JMenuBar createMenuBar(){
        JMenu start, reset, info;
        JMenuItem information;
        JMenuBar menuBar = new JMenuBar();
        start = addMenu("START");
        menuBar.add(start);
        reset = addMenu("RESET");
        menuBar.add(reset);
        info = addMenu("INFO");
        info.addSeparator();
        information = addMenuItem();
        info.add(information);
        menuBar.add(info);
        return menuBar;
    }
    private static JMenuItem addMenuItem(){
        JMenuItem menuItem = new JMenuItem("There are 15 bombs hidden!");
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
