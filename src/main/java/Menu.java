import javax.swing.*;
import java.awt.*;

class Menu {
    static JMenu reset,gameStatus;
    static JMenuBar createMenuBar(){
        JMenu info;
        JMenuItem leftButtonInfo;
        JMenuItem middleButtonInfo;
        JMenuItem rightButtonInfo;
        JMenuItem information;
        JMenuBar menuBar = new JMenuBar();
        reset = addMenu("NEW GAME");
        menuBar.add(reset);
        info = addMenu("INFO:");
        info.addSeparator();
        information = addMenuItem("There are 15 bombs hidden!");
        info.add(information);
        leftButtonInfo = addMenuItem("Left Button: Reveal button");
        info.add(leftButtonInfo);
        rightButtonInfo = addMenuItem("Right Button: Add question icon");
        info.add(rightButtonInfo);
        middleButtonInfo = addMenuItem("Middle Button: Delete question icon");
        info.add(middleButtonInfo);
        menuBar.add(info);
        gameStatus = addMenu("Game started!");
        menuBar.add(gameStatus);
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
