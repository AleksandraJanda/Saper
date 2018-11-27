import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class FieldButtons {
    private static int buttonSize = 10;
    private static List<JButton> listOfButtons = new ArrayList<>();
    private static List<JButton> bombButtons = new ArrayList<>();
    private static List<Integer> buttonNumbers = new ArrayList<>();
    private static int numberOfButtons = 100;
    private static int ii = 10;
    private static int gap = 0;
    private static int numberOfBombs = 15;
    private static ImageIcon bombImg = new ImageIcon("files/bomb.png");
    private static ImageIcon emptyImg = new ImageIcon("files/empty.png");
    private static ImageIcon oneImg = new ImageIcon("files/one.png");
    private static ImageIcon twoImg = new ImageIcon("files/two.png");
    private static ImageIcon threeImg = new ImageIcon("files/three.png");
    private static ImageIcon fourImg = new ImageIcon("files/four.png");
    private static ImageIcon fiveImg = new ImageIcon("files/five.png");

    JPanel setFieldContent(){
        JPanel panel = new JPanel();
        panel.setSize(Window.width,Window.height);
        panel.setLayout(new GridLayout(ii, numberOfButtons / ii, gap, gap));
        makeListOfButtons(panel);
        setBombs();
        selectImage();
        panel.setBackground(Color.black);
        panel.setVisible(true);
        return panel;
    }
    private JButton makeButton(){
        JButton button = new JButton();
        button.setBorder(new LineBorder(Color.black));
        button.setBackground(Color.gray);
        button.setPreferredSize(new Dimension(buttonSize, buttonSize));
        button.setIcon(emptyImg);
        setRevealButton(button);
        return button;
    }
    private void makeListOfButtons(JPanel panel){
        for(int i = 0; i<numberOfButtons; i++){
            JButton button = makeButton();
            listOfButtons.add(button);
            panel.add(button);
        }
    }
    private void setBombs(){
        Random random = new Random();
        for(int i = 0; i<numberOfBombs; i++){
            JButton button = listOfButtons.get(random.nextInt(numberOfButtons));
            button.setIcon(bombImg);
            bombButtons.add(button);
        }
        listOfButtons.get(11).setIcon(bombImg);

    }

    private void selectImage(){
        List<List<Integer>> allButtonNumberLists = new ArrayList<>();
        for(int i = 0; i<listOfButtons.size(); i++){
            buttonNumbers = new ArrayList<>();
            buttonNumbers.add(i);
            if(i -1>=90||(i -1>=0&&!String.valueOf(i -1).contains("9"))){
                buttonNumbers.add(i -1);
            }
            if(i +1<=99&&!String.valueOf(i +1).contains("0")){
                buttonNumbers.add(i +1);
            }
            if(i +10<=99){
                buttonNumbers.add(i +10);
                if(i +10-1>=90||(i +10-1>=0&&!String.valueOf(i +10-1).contains("9"))){
                    buttonNumbers.add(i +10-1);
                }
                if(i +10+1>=0&&!String.valueOf(i +10+1).contains("0")){
                    buttonNumbers.add(i +10+1);
                }
            }
            if(i -10>=0){
                buttonNumbers.add(i -10);
                if(i -10-1>=0&&!String.valueOf(i -10-1).contains("9")){
                    buttonNumbers.add(i -10-1);
                }
                if(i -10+1>=0&&!String.valueOf(i -10+1).contains("0")){
                    buttonNumbers.add(i -10+1);
                }
            }
            allButtonNumberLists.add(buttonNumbers);
        }

        for(int i = 0; i<allButtonNumberLists.size(); i++){
            int count = 0;
            for(int j = 1; j<allButtonNumberLists.get(i).size(); j++){
                if(listOfButtons.get(allButtonNumberLists.get(i).get(j)).getIcon()==bombImg){
                    count++;
                }
            }
            System.out.println(count);
            if(listOfButtons.get(i).getIcon()!=bombImg) {
                if (count == 0) {
                    listOfButtons.get(i).setIcon(emptyImg);
                } else if (count == 1) {
                    listOfButtons.get(i).setIcon(oneImg);
                } else if (count == 2) {
                    listOfButtons.get(i).setIcon(twoImg);
                } else if (count == 3){
                    listOfButtons.get(i).setIcon(threeImg);
                } else if (count == 4){
                    listOfButtons.get(i).setIcon(fourImg);
                } else {
                    listOfButtons.get(i).setIcon(fiveImg);
                }
            }
        }
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
