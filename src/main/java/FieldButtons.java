import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

class FieldButtons {
    private static int buttonSize = 10;
    private static List<JButton> listOfButtons = new ArrayList<>();
    private static List<Integer> bombButtons = new ArrayList<>();
    private static List<Integer> buttonNumbers = new ArrayList<>();
    private static Map<Integer,ImageIcon> mapOfImages = new HashMap<>();
    private static int numberOfButtons = 100;
    private static int index = 0;
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
        //button.setIcon(emptyImg);
        //setRevealButton(button);
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
            int randomButtonNumber = random.nextInt(numberOfButtons);
            JButton button = listOfButtons.get(randomButtonNumber);
            //button.setIcon(bombImg);
            bombButtons.add(randomButtonNumber);
            mapOfImages.put(randomButtonNumber,bombImg);
        }
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
            if(!mapOfImages.containsKey(i)) {
                index = i;
                if (count == 0) {
                    mapOfImages.put(i,emptyImg);
                    //listOfButtons.get(i).addActionListener(empty);
                    index = i;
                    addEmptyAction(index);
                    //listOfButtons.get(i).setIcon(emptyImg);
                } else if (count == 1) {
                    mapOfImages.put(i,oneImg);
                    listOfButtons.get(i).addActionListener(one);
                    listOfButtons.get(i).setIcon(oneImg);
                } else if (count == 2) {
                    mapOfImages.put(i,twoImg);
                    listOfButtons.get(i).setIcon(twoImg);
                } else if (count == 3){
                    mapOfImages.put(i,threeImg);
                    listOfButtons.get(i).setIcon(threeImg);
                } else if (count == 4){
                    mapOfImages.put(i,fourImg);
                    listOfButtons.get(i).setIcon(fourImg);
                } else {
                    mapOfImages.put(i,fiveImg);
                    listOfButtons.get(i).setIcon(fiveImg);
                }
            } else {
                listOfButtons.get(i).addActionListener(bomb);
            }
        }
    }
    /*
    private void setRevealButton(JButton button){
        button.addMouseListener(makeVisible);
    }*/

    private ActionListener bomb = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("BUUUUUM!");
            //System.out.println(e.getActionCommand());
            JButton button = (JButton)e.getSource();
            button.setIcon(bombImg);
        }
    };
    private void addEmptyAction(int index){
        listOfButtons.get(index).addActionListener(empty);
    }
    private ActionListener empty = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("empty");
            listOfButtons.get(index).setIcon(mapOfImages.get(index));
        }
    };
    private ActionListener one = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("one");

        }
    };
    private MouseListener makeVisible = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            //System.out.println("Button clicked!");
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
