import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;


import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class Gui {
    public Character character;
    JFrame window;
    JTextField nome;
    JTextField sentiero;
    JTextField fazione;
    JPanel genPanel;
    JLabel bloodWill;
    JPanel panel;
    JLabel pxlab;
    
    JSpinner gen;
    JSpinner px;

    JMenuBar menuBar;
    JMenu file;
    String filepath = null;
    JPanel discPanel;
    JPanel inflPanel;


    private class SkillElement extends JPanel {
        private JButton remove;   
        private JSpinner spinner;
        private JLabel label;

        public SkillElement(Skill s, boolean clan){
            super(new GridLayout(1,3));
            remove = new JButton("-");
            remove.setMaximumSize(new Dimension(10,10));
            JPanel wrap = new JPanel();
            wrap.add(remove);
            super.add(wrap);
            if(clan){
                remove.setEnabled(false);
            }
            label = new JLabel(s.getName(), SwingConstants.LEFT);
            super.add(label);
            SpinnerModel model = new SpinnerNumberModel(s.getLevel(), 0, 5, 1);     
            spinner = new JSpinner(model);
            wrap = new JPanel();
            wrap.add(spinner);
            super.add(wrap);
        }

        public JButton getRemove() {
            return remove;
        }

        public JSpinner getSpinner() {
            return spinner;
        }

        public JLabel getLabel() {
            return label;
        }

    }

    void save(){
        if(character.getRemainingPx() < 0){
            JOptionPane.showMessageDialog(window, "I px sono minori di 0, rinuncia a qualche abilità prima di salvare la scheda");
            return;
        }
        if(filepath == null){
            saveName();
            return;
        }
        try{
            XLS.export(character, filepath);
        }catch(IOException e){
            JOptionPane.showMessageDialog(window, "Errore nel salvataggio del file, provare altra poszione", "Save Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    void saveName(){
        if(character.getRemainingPx() < 0){
            JOptionPane.showMessageDialog(window, "I px sono minori di 0, rinuncia a qualche abilità prima di salvare la scheda");
            return;
        }
        try{
            JFileChooser jf = new JFileChooser();
            jf.addChoosableFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    } else {
                        return f.getName().toLowerCase().endsWith(".xlsx");
                    }
                }

                @Override
                public String getDescription() {
                    return "Documento excel";
                }
                
            });
            jf.setAcceptAllFileFilterUsed(false);
            jf.setSelectedFile(new File(character.getName()+".xlsx"));
            int result = jf.showSaveDialog(window);
            if(result == JFileChooser.CANCEL_OPTION){
                return;
            }
            filepath = jf.getSelectedFile().getAbsolutePath();
            if(!filepath.toLowerCase().endsWith(".xlsx")){
                filepath+=".xlsx";
            }
            XLS.export(character, filepath);
        }catch(IOException e){
            JOptionPane.showMessageDialog(window, "Errore nel salvataggio del file, provare altra poszione", "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    JComboBox<String> clan;
    public Gui(Character character){
        this.character = character;
        window = new JFrame("LexTalionis Character Creator");
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon("media/sabbat.jpg");
        window.setIconImage(img.getImage());
        window.setSize(1280, 1024);
        panel = new JPanel(new BorderLayout());
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
        px.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                int p =  (Integer)(((JSpinner)e.getSource()).getValue());
                character.setPx(p);
                updateBloodWillPx();
            }
        });
        internPanel = new JPanel();
        internPanel.add(new JLabel("Px "));
        internPanel.add(px);
        northGrid.add(internPanel);


        internPanel = new JPanel();
        buildList();
        clan.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                System.out.println("called " + (String)((JComboBox<String>)e.getSource()).getSelectedItem());
                int gen = 0;
                if(character.isVampire()){
                    gen = ((Vampire) character).getGen();
                }
                String sel = (String)((JComboBox<String>)e.getSource()).getSelectedItem();
                switch(sel){
                    case "Assamita": Gui.this.character = new Clan.Assamita(); break;
                    case "Baali": Gui.this.character = new Clan.Baali();
                }
                updateDetails();
                updateBloodWillPx();
                updateDisciplines();
                updateInfluencies();
                if(character.isVampire()){
                    ((Vampire) character).setGen(gen);
                }
                
            }
        });
        internPanel.add(new JLabel("Clan "));
        internPanel.add(clan);
        northGrid.add(internPanel);

        genPanel = new JPanel();
        genPanel.add(new JLabel("Generazione "));
        SpinnerModel genModel = new SpinnerNumberModel(13, 10, 15, 1);    
        gen = new JSpinner(genModel);
        gen.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                int g = (Integer)(((JSpinner)e.getSource()).getValue());
                ((Vampire) character).setGen(g);
                updateBloodWillPx();
            }
            
        });
        genPanel.add(gen);
        northGrid.add(genPanel);


        menuBar = new JMenuBar();
        file = new JMenu("File");
        menuBar.add(file);
        JMenuItem menuItem = new JMenuItem("Salva");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDetails();
                save();
            }
        });
        file.add(menuItem);
        menuItem = new JMenuItem("Salva con nome");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDetails();
                saveName();
            }
        });
        file.add(menuItem);

        window.setJMenuBar(menuBar);

        JPanel skillBody = new JPanel(new GridLayout(1, 3)); //to_adjust

        panel.add(skillBody, BorderLayout.CENTER);

        JPanel discPanelWrap = new JPanel(new BorderLayout());
        discPanelWrap.add(new JLabel("Discipline", SwingConstants.CENTER), BorderLayout.NORTH);
        discPanel = new JPanel(new GridLayout(10, 1));
        discPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        discPanelWrap.add(discPanel, BorderLayout.CENTER);
        JPanel wrap = new JPanel();
        wrap.add(discPanelWrap);
        skillBody.add(wrap);
        updateDisciplines();

        JPanel inflPanelWrap = new JPanel(new BorderLayout());
        inflPanelWrap.add(new JLabel("Influenze", SwingConstants.CENTER), BorderLayout.NORTH);
        inflPanel = new JPanel(new GridLayout(10, 1));
        inflPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        inflPanelWrap.add(inflPanel, BorderLayout.CENTER);
        wrap = new JPanel();
        wrap.add(inflPanelWrap);
        skillBody.add(wrap);
        updateInfluencies();


        bloodWill = new JLabel("Sangue: 10 Will: 7", SwingConstants.CENTER);
        JPanel bpan = new JPanel(new BorderLayout());
        bpan.add(bloodWill, BorderLayout.NORTH);
        pxlab = new JLabel("Px rimanenti: " + character.getRemainingPx(), SwingConstants.CENTER);
        bpan.add(pxlab);
        panel.add(bpan, BorderLayout.SOUTH);

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
            JPanel e = new SkillElement(d, d.isClan());
            discPanel.add(e);
        }
        ((GridLayout) discPanel.getLayout()).setVgap(10);
        panel.revalidate();
        panel.repaint();
    }

    void updateInfluencies(){
        inflPanel.removeAll();
        Iterator<Influenza> it = character.inflIterator();
        while(it.hasNext()){
            Influenza i = it.next();
            JPanel e = new SkillElement(i, i.isClan());
            inflPanel.add(e);
        }
        ((GridLayout) inflPanel.getLayout()).setVgap(10);
        panel.revalidate();
        panel.repaint();
    }

    void updateDetails(){
        character.setName(nome.getText());
        character.setSentiero(sentiero.getText());
        character.setFazione(fazione.getText());
    }

    void updateBloodWillPx(){
        bloodWill.setText("Blood: "+character.getBlood()+" Will: "+character.getWill());
        pxlab.setText("Px rimanenti: " + character.getRemainingPx());
        panel.revalidate();
        panel.repaint();
    }

    public static void main(String[] args) {
        new Gui(new Clan.Assamita());    
    }
}
