import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

class FieldButtons {
    private static List<JButton> listOfButtons = new ArrayList<>();
    private static List<List<Integer>> allButtonNumberLists = new ArrayList<>();
    private static Map<Integer,ImageIcon> mapOfImages = new HashMap<>();
    private static int numberOfButtons = 100;
    private static ImageIcon bombImg = new ImageIcon("files/bomb.png");
    private static ImageIcon emptyImg = new ImageIcon("files/empty.png");
    private static ImageIcon oneImg = new ImageIcon("files/one.png");
    private static ImageIcon twoImg = new ImageIcon("files/two.png");
    private static ImageIcon threeImg = new ImageIcon("files/three.png");
    private static ImageIcon fourImg = new ImageIcon("files/four.png");
    private static ImageIcon fiveImg = new ImageIcon("files/five.png");

    JPanel setFieldContent(){
        int ii = 10;
        int gap = 0;
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
        int buttonSize = 10;
        JButton button = new JButton();
        button.setBorder(new LineBorder(Color.black));
        button.setBackground(Color.gray);
        button.setPreferredSize(new Dimension(buttonSize, buttonSize));

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
        int numberOfBombs = 15;
        Random random = new Random();
        for(int i = 0; i<numberOfBombs; i++){
            int randomButtonNumber = random.nextInt(numberOfButtons);
            JButton button = listOfButtons.get(randomButtonNumber);
            mapOfImages.put(randomButtonNumber,bombImg);
            button.addActionListener(bomb);
        }
    }
    private void findButtonNumbers(){
        List<Integer> buttonNumbers;
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
    }

    private void selectImage(){
        findButtonNumbers();
        for(int i = 0; i<allButtonNumberLists.size(); i++){
            int count = 0;
            for(int j = 1; j<allButtonNumberLists.get(i).size(); j++){
                if(mapOfImages.containsKey(allButtonNumberLists.get(i).get(j))
                        &&mapOfImages.get(allButtonNumberLists.get(i).get(j))==bombImg){
                    count++;
                }
            }
            ImageIcon imageIcon = emptyImg;
            ActionListener action = empty;
            if(count==1){
                imageIcon = oneImg;
                action = one;
            } else if(count==2){
                imageIcon = twoImg;
                action = two;
            } else if(count==3){
                imageIcon = threeImg;
                action = three;
            } else if(count==4){
                imageIcon = fourImg;
                action = four;
            } else if(count==5){
                imageIcon = fiveImg;
                action = five;
            }
            if(!mapOfImages.containsKey(i)) {
                mapOfImages.put(i,imageIcon);
                listOfButtons.get(i).addActionListener(action);
            }
        }
    }
    private ActionListener empty = e -> {
        JButton button = (JButton)e.getSource();
        button.setIcon(emptyImg);
        int id = listOfButtons.indexOf(button);
        List<Integer> aaa = allButtonNumberLists.get(id);
        Set<Integer> set = new TreeSet<>(aaa);
        for(Integer y: aaa){
            if(mapOfImages.get(y)==emptyImg) {
                aaa = allButtonNumberLists.get(y);
                set.addAll(aaa);
            }
        }
        //System.out.println(set);
        for(Integer x : set){
            if(mapOfImages.get(x)!=bombImg) {
                listOfButtons.get(x).setIcon(mapOfImages.get(x));
            }
        }
    };
    private ActionListener one = e -> {
        JButton button = (JButton)e.getSource();
        button.setIcon(oneImg);
    };
    private ActionListener two = e -> {
        JButton button = (JButton)e.getSource();
        button.setIcon(twoImg);
    };
    private ActionListener three = e -> {
        JButton button = (JButton)e.getSource();
        button.setIcon(threeImg);
    };
    private ActionListener four = e -> {
        JButton button = (JButton)e.getSource();
        button.setIcon(fourImg);
    };
    private ActionListener five = e -> {
        JButton button = (JButton)e.getSource();
        button.setIcon(fiveImg);
    };
    private ActionListener bomb = e -> {
        JButton button = (JButton)e.getSource();
        button.setIcon(bombImg);
    };
}
