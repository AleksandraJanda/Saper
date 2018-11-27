import javax.swing.*;

class Content {
    static JPanel setAppContent(){
        JPanel content = new JPanel();
        FieldButtons fieldButtons = new FieldButtons();
        JPanel field = fieldButtons.setFieldContent();
        content.setSize(Window.width,Window.height);
        content.setLayout(new BoxLayout(content,BoxLayout.Y_AXIS));
        content.add(field);
        content.setVisible(true);
        return content;
    }
}
