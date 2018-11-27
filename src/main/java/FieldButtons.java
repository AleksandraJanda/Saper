import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class FieldButtons {
    private static int buttonSize = 10;
    private static List<JButton> listOfButtons = new ArrayList<>();
    private static JButton[][] buttonArray = new JButton[10][10];
    private static int numberOfButtons = 100;
    private static int ii = 10;
    private static int gap = 0;
    private static ImageIcon bombImg = new ImageIcon("files/bomb.png");
    private static ImageIcon emptyImg = new ImageIcon("files/empty.png");
    private static ImageIcon oneImg = new ImageIcon("files/one.png");
    private static ImageIcon twoImg = new ImageIcon("files/two.png");
    private static ImageIcon threeImg = new ImageIcon("files/three.png");
    private static List<ImageIcon> listOfImages = Arrays.asList(bombImg,emptyImg,oneImg,twoImg);

    JPanel setFieldContent(){
        JPanel panel = new JPanel();
        panel.setSize(Window.width,Window.height);
        panel.setLayout(new GridLayout(ii, numberOfButtons / ii, gap, gap));
        makeListOfButtons(panel);
        panel.setBackground(Color.black);
        panel.setVisible(true);
        return panel;
    }
    private JButton makeButton(){
        JButton button = new JButton();
        button.setBorder(new LineBorder(Color.black));
        button.setBackground(Color.lightGray);
        button.setPreferredSize(new Dimension(buttonSize, buttonSize));
        setRevealButton(button);
        Random random = new Random();
        button.setIcon(listOfImages.get(random.nextInt(listOfImages.size())));
        button.setBackground(Color.lightGray);

        return button;
    }
    private void makeListOfButtons(JPanel panel){
        for(int i = 0; i<numberOfButtons; i++){
            JButton button = makeButton();
            listOfButtons.add(button);
            panel.add(button);
        }
    }
    private void makeArrayOfButtons(){
        int k = 0;
        for (JButton[] rowButtonArray : buttonArray) {
            for (int j = 0; j < buttonArray.length; j++) {
                rowButtonArray[j].add(listOfButtons.get(k));
                k++;
            }
        }
    }
    private void selectImage(){
        Random random = new Random();
        buttonArray[0][0].setIcon(listOfImages.get(random.nextInt(listOfImages.size())));
        
    }
    private void setRevealButton(JButton button){
        button.addMouseListener(makeVisible);
    }

    private MouseListener makeVisible = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Button clicked!");
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
    };
}
