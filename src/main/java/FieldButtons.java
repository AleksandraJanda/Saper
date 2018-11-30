import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

class FieldButtons implements MouseListener{
    static List<JButton> listOfButtons = new ArrayList<>();
    static List<List<Integer>> allButtonNumberLists = new ArrayList<>();
    private static List<JButton> bombButtons = new ArrayList<>();
    static Map<Integer,ImageIcon> mapOfImages = new HashMap<>();
    private static int numberOfButtons = 100;
    protected static ImageIcon bombImg = new ImageIcon("src/bomb.png");
    private static ImageIcon emptyImg = new ImageIcon("src/empty.png");
    private static ImageIcon oneImg = new ImageIcon("src/one.png");
    private static ImageIcon twoImg = new ImageIcon("src/two.png");
    private static ImageIcon threeImg = new ImageIcon("src/three.png");
    private static ImageIcon fourImg = new ImageIcon("src/four.png");
    private static ImageIcon fiveImg = new ImageIcon("src/five.png");
    private static ImageIcon questionImg = new ImageIcon("src/question.png");
    private static ImageIcon checkImg = new ImageIcon("src/check.png");

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
            button.addMouseListener(this);
            panel.add(button);
        }
    }
    private void setBombs(){
        int numberOfBombs = 15;
        Random random = new Random();
        for(int i = 0; i<=numberOfBombs; i++){
            int randomButtonNumber = random.nextInt(numberOfButtons);
            JButton button = listOfButtons.get(randomButtonNumber);
            mapOfImages.put(randomButtonNumber,bombImg);
            button.addActionListener(bomb);
            bombButtons.add(button);
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
    private List<Integer> getButtonsToReveal(Set<Integer> set, int id, int turn){
        List<Integer> aaa = allButtonNumberLists.get(id);
        for(Integer x: aaa){
            if(mapOfImages.get(x)==emptyImg) {
                aaa = allButtonNumberLists.get(x);
                set.addAll(aaa);
                for(Integer y: aaa){
                    if(mapOfImages.get(y)==emptyImg) {
                        aaa = allButtonNumberLists.get(y);
                        set.addAll(aaa);
                        if (turn <= 12) {
                            turn++;
                            aaa = getButtonsToReveal(set, y, turn);
                        }
                    }
                }
            }
        }
        return aaa;
    }
    private ActionListener empty = e -> {
        JButton button = (JButton)e.getSource();
        button.setIcon(emptyImg);
        int id = listOfButtons.indexOf(button);
        List<Integer> aaa = allButtonNumberLists.get(id);
        Set<Integer> set = new TreeSet<>(aaa);
        set.addAll(getButtonsToReveal(set,id,0));
        for(Integer x : set){
            if(mapOfImages.get(x)!=bombImg&&listOfButtons.get(x).getIcon()!=questionImg) {
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
        for(JButton x: listOfButtons){
            x.removeActionListener(empty);
            x.removeActionListener(one);
            x.removeActionListener(two);
            x.removeActionListener(three);
            x.removeActionListener(four);
            x.removeActionListener(five);
            x.removeMouseListener(this);
        }
        int count = 0;
        for(JButton x: bombButtons){
            if(x.getIcon()==questionImg){
                count++;
                x.setIcon(checkImg);
            } else {
                x.setIcon(bombImg);
            }
        }
        Menu.gameStatus.setText("-----Game over!----- Score: "+count+"/15");
    };
    @Override
    public void mouseClicked(MouseEvent e) {
        int count = 0;
        if(e.getButton()== MouseEvent.BUTTON3) {
            JButton button = (JButton) e.getSource();
            if (button.getIcon() == null) {
                button.setIcon(questionImg);
                count = 0;
                for (JButton x : bombButtons) {
                    if (x.getIcon() == questionImg) {
                        count++;
                    }
                }
            }
            if (count == 15) {
                Menu.gameStatus.setText("-----You won!-----");
                for (JButton x : bombButtons) {
                    x.setIcon(checkImg);
                }
            }
        }
        if(e.getButton()== MouseEvent.BUTTON2){
            JButton button = (JButton)e.getSource();
            if(button.getIcon()==questionImg) {
                button.setIcon(null);
            }
        }
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
