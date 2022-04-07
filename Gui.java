import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

public class Gui {
    private Character character;
    private JFrame window;
    JTextField nome;
    JTextField sentiero;
    JTextField fazione;
    JPanel genPanel;
    
    JSpinner gen;
    JSpinner px;

    JMenuBar menuBar;
    JMenu file;
    String filepath = null;
    JPanel discPanel;
    JPanel inflPanel;

    public JPanel skillElement(Skill s, boolean clan){
        GridLayout gr = new GridLayout(1,3);
        JPanel p = new JPanel(gr);
        JButton remove = new JButton("-");
        remove.setMaximumSize(new Dimension(10,10));
        //remove.setMargin(new Insets(0, 0, 0, 0));
        JPanel wrap = new JPanel();
        wrap.add(remove);
        p.add(wrap);
        if(clan){
            remove.setEnabled(false);
        }
        JLabel l = new JLabel(s.getName(), SwingConstants.LEFT);
        p.add(l);
        SpinnerModel model = new SpinnerNumberModel(s.getLevel(), 0, 5, 1);     
        JSpinner spinner = new JSpinner(model);
        wrap = new JPanel();
        wrap.add(spinner);
        p.add(wrap);
        return p;
    }

    JComboBox<String> clan;
    public Gui(Character character){
        this.character = character;
        window = new JFrame("LexTalionis Character Creator");
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon("media/sabbat.jpg");
        window.setIconImage(img.getImage());
        window.setSize(800, 600);
        JPanel panel = new JPanel(new BorderLayout());
        window.add(panel);

        JPanel northGrid = new JPanel(new GridLayout(2,3));
        panel.add(northGrid, BorderLayout.NORTH);
        
        JPanel internPanel = new JPanel();
        internPanel.add(new JLabel("Nome "));
        nome = new JTextField();
        nome.setColumns(15);
        internPanel.add(nome);
        northGrid.add(internPanel);


        internPanel = new JPanel();
        internPanel.add(new JLabel("Sentiero "));
        sentiero = new JTextField();
        sentiero.setColumns(20);
        internPanel.add(sentiero);
        northGrid.add(internPanel);


        internPanel = new JPanel();
        internPanel.add(new JLabel("Fazione "));
        fazione = new JTextField();
        fazione.setColumns(15);
        internPanel.add(fazione);
        northGrid.add(internPanel);


        SpinnerModel model = new SpinnerNumberModel(30, 1, 1000, 1);     
        px = new JSpinner(model);
        internPanel = new JPanel();
        internPanel.add(new JLabel("Px "));
        internPanel.add(px);
        northGrid.add(internPanel);


        internPanel = new JPanel();
        buildList();
        internPanel.add(new JLabel("Clan "));
        internPanel.add(clan);
        northGrid.add(internPanel);

        genPanel = new JPanel();
        genPanel.add(new JLabel("Generazione "));
        SpinnerModel genModel = new SpinnerNumberModel(13, 10, 15, 1);    
        gen = new JSpinner(genModel);
        genPanel.add(gen);
        northGrid.add(genPanel);


        menuBar = new JMenuBar();
        file = new JMenu("File");
        menuBar.add(file);
        JMenuItem menuItem = new JMenuItem("Salva");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        file.add(menuItem);
        menuItem = new JMenuItem("Salva con nome");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
        file.add(menuItem);

        window.setJMenuBar(menuBar);

        JPanel skillBody = new JPanel(new GridLayout(1, 3)); //to_adjust

        panel.add(skillBody, BorderLayout.CENTER);

        JPanel discPanelWrap = new JPanel(new BorderLayout());
        discPanelWrap.add(new JLabel("Discipline", SwingConstants.CENTER), BorderLayout.NORTH);
        discPanel = new JPanel(new GridLayout(10, 1));
        discPanelWrap.add(discPanel, BorderLayout.CENTER);
        JPanel wrap = new JPanel();
        wrap.add(discPanelWrap);
        skillBody.add(wrap);
        updateDisciplines();

        JPanel inflPanelWrap = new JPanel(new BorderLayout());
        inflPanelWrap.add(new JLabel("Influenze", SwingConstants.CENTER), BorderLayout.NORTH);
        inflPanel = new JPanel(new GridLayout(10, 1));
        inflPanelWrap.add(inflPanel, BorderLayout.CENTER);
        wrap = new JPanel();
        wrap.add(inflPanelWrap);
        skillBody.add(wrap);
        updateInfluencies();

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

    void updateDisciplines(){
        discPanel.removeAll();
        Iterator<Disciplina> it = character.discIterator();
        while(it.hasNext()){
            Disciplina d = it.next();
            JPanel e = skillElement(d, d.isClan());
            discPanel.add(e);
        }
        ((GridLayout) discPanel.getLayout()).setVgap(10);
    }

    void updateInfluencies(){
        inflPanel.removeAll();
        Iterator<Influenza> it = character.inflIterator();
        while(it.hasNext()){
            Influenza i = it.next();
            JPanel e = skillElement(i, i.isClan());
            inflPanel.add(e);
        }
        ((GridLayout) inflPanel.getLayout()).setVgap(10);
    }

    public static void main(String[] args) {
        new Gui(new Clan.Assamita());    
    }
}
