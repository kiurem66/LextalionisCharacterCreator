import javax.swing.*;
import java.awt.*;

public class Gui {
    private Character character;
    private JFrame window;
    JTextField nome;
    JTextField sentiero;
    JTextField fazione;
    JComboBox<String> clan;
    public Gui(){
        window = new JFrame("LexTalionis Character Creator");
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon("media/sabbat.jpg");
        window.setIconImage(img.getImage());
        window.setSize(800, 600);
        JPanel panel = new JPanel(new BorderLayout());
        window.add(panel);
        JPanel internPanel = new JPanel();
        panel.add(internPanel, BorderLayout.CENTER);

        internPanel.add(new JLabel("Nome "));
        nome = new JTextField();
        nome.setColumns(10);
        internPanel.add(nome);

        internPanel.add(new JLabel("Sentiero "));
        sentiero = new JTextField();
        sentiero.setColumns(10);
        internPanel.add(sentiero);


        internPanel.add(new JLabel("Fazione "));
        fazione = new JTextField();
        fazione.setColumns(10);
        internPanel.add(fazione);

        buildList();
        internPanel.add(new JLabel("Clan "));
        internPanel.add(clan);

        panel.revalidate();
        panel.repaint();
    }

    void buildList(){
        String clanlist[] = new String[23];
        for(int i=0; i<23; i++){
            clanlist[i] = ClanSelector.get(i);
        }
        clan = new JComboBox<String>(clanlist);
    }

    public static void main(String[] args) {
        new Gui();    
    }
}
