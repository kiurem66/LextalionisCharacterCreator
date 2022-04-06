import javax.swing.*;

public class Gui {
    private Character character;
    private JFrame window;
    public Gui(){
        window = new JFrame("LexTalionis Character Creator");
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon("sabbat.jpg");
        window.setIconImage(img.getImage());
        window.setSize(800, 600);
    }

    public static void main(String[] args) {
        new Gui();    
    }
}
