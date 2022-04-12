import javax.swing.*;
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
    JPanel proConPanel;


    private class SkillElement extends JPanel {   
        private JSpinner spinner;
        private JLabel label;
        private boolean disc;

        public SkillElement(Skill s, boolean clan){
            super(new GridLayout(1,3));
            disc = false;
            if(s instanceof Disciplina)
                disc = true;
            JPanel wrap = new JPanel();
            super.add(wrap);
            if(!clan){
                JButton remove = new JButton("-");
                remove.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        delete();
                        updateDisciplines();
                        updateInfluencies();
                        updateBloodWillPx();
                    }
                });
                remove.setMaximumSize(new Dimension(10,10));
                wrap.add(remove);
            }else{
                JCheckBox first = new JCheckBox();
                first.setSelected(s.isFirstLevelFree());
                first.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        if(disc){
                            character.searchDisc(label.getText()).setFirstLevelFree(first.isSelected());
                        }else{
                            character.searchInfl(label.getText()).setFirstLevelFree(first.isSelected());
                        }
                        updateBloodWillPx();
                    }
                });
                wrap.add(first);
            }
            label = new JLabel(s.getName(), SwingConstants.LEFT);
            super.add(label);
            SpinnerModel model = new SpinnerNumberModel(s.getLevel(), 0, 5, 1);     
            spinner = new JSpinner(model);
            spinner.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e){
                    update();
                }
            });
            wrap = new JPanel();
            wrap.add(spinner);
            super.add(wrap);
        }

        void update(){
            Skill s = null;
            if(disc){
                s = character.searchDisc(label.getText());
            }else{
                s = character.searchInfl(label.getText());
            }
            s.setLevel(((Integer)spinner.getValue()));
            updateBloodWillPx();
        }

        void delete(){
            if(disc){
                character.removeDisc(character.searchDisc(label.getText()));
            }else{
                character.removeInfl(character.searchInfl(label.getText()));
            }
            updateDisciplines();
            updateInfluencies();
        }
    }

    private class ProConElement extends JPanel{
        JLabel label;
        JLabel cost;
        public ProConElement(ProCon p){
            super(new GridLayout(1, 3));
            GridLayout gr = (GridLayout)super.getLayout();
            gr.setHgap(3);
            JPanel wrap = new JPanel();
            super.add(wrap);
            JButton remove = new JButton("-");
            if(p.isClan()){
                remove.setEnabled(false);
            }
            remove.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    delete();
                    updateDisciplines();
                    updateInfluencies();
                    updateBloodWillPx();
                }
            });
            remove.setMaximumSize(new Dimension(10,10));
            wrap.add(remove);
            label = new JLabel(p.nome(), SwingConstants.LEFT);
            super.add(label);
            gr = new GridLayout(1, 2);
            gr.setVgap(5);
            wrap = new JPanel(gr);
            if(p.costo() > 0){
                cost = new JLabel("Pre", SwingConstants.CENTER);
            }else{
                cost = new JLabel("Dif", SwingConstants.CENTER);
            }
            wrap.add(cost);
            cost = new JLabel(""+Math.abs(p.costo()), SwingConstants.RIGHT);
            wrap.add(cost);
            super.add(wrap);
        }

        void delete(){
            character.removeProCon(character.searchProCon(label.getText()));
            updateProCon();
        }
    }

    Disciplina selectDiscipline(){
        String[] list = {"Animalità", "Ascendente", "Auspex", "Chimerismo", "Daimonion", "Demenza",
                        "Dominazione", "Necromanzia", "Ottenebramento", "Potenza", "Proteide",
                        "Quietus", "Robustezza", "Sanguinis", "Serpentis", "Taumaturgia",
                        "Valeren", "Velocità", "Vicissitudine"};
        JComboBox<String> jComboBox = new JComboBox<String>(list);
        JOptionPane.showMessageDialog(window, jComboBox, "Seleziona Disciplina", JOptionPane.QUESTION_MESSAGE);
        String sel = (String)(jComboBox.getSelectedItem());

        Disciplina d =null;
        switch(sel){
            case "Animalità": d = new Disciplina.Animalità(); break;
            case "Ascendente": d = new Disciplina.Ascendente(); break;
            case "Auspex": d = new Disciplina.Auspex(); break;
            case "Chimerismo": d = new Disciplina.Chimerismo(); break;
            case "Daimonion": d = new Disciplina.Daimonion(); break;
            case "Demenza": d = new Disciplina.Demenza(); break;
            case "Dominazione": d = new Disciplina.Dominazione(); break;
            case "Necromanzia": d = new Disciplina.Necromanzia(); break;
            case "Ottenebramento": d = new Disciplina.Ottenebramento(); break;
            case "Potenza": d = new Disciplina.Potenza(); break;
            case "Proteide": d = new Disciplina.Proteide(); break;
            case "Quietus": d = new Disciplina.Quietus(); break;
            case "Robustezza": d = new Disciplina.Robustezza(); break;
            case "Sanguinis": d = new Disciplina.Sanguinis(); break;
            case "Serpentis": d = new Disciplina.Serpentis(); break;
            case "Taumaturgia": d = new Disciplina.Taumaturgia(); break;
            case "Valeren": d = new Disciplina.Valeren(); break;
            case "Velocità": d = new Disciplina.Velocità(); break;
            case "Vicissitudine": d = new Disciplina.Vicissitudine(); break;
        }
        d.setClan(false);
        return d;
    }

    Influenza selectInfl(boolean clan){
        String[] list = {"Accademiche", "Alta Società", "Clero", "Crimine", "Esercito"
        ,               "Ghoul", "Medicina", "Media", "Mentore", "Occulto", "Politica",
                        "Risorse", "Sicurezza", "Sopravvivenza"};
        JComboBox<String> jComboBox = new JComboBox<String>(list);
        JOptionPane.showMessageDialog(window, jComboBox, "Seleziona Disciplina", JOptionPane.QUESTION_MESSAGE);
        String sel = (String)(jComboBox.getSelectedItem());
        Influenza i = new Influenza(sel);
        i.setClan(clan);
        return i;
    }

    ProCon selectProCon(boolean invert){
        String p = "Inserire nome pregio";
        if(invert){
            p = "Inserire nome difetto";
        }
        String nome = JOptionPane.showInputDialog(window, p);
        SpinnerNumberModel sModel = new SpinnerNumberModel(0, 0, 100, 1);
        JSpinner spinner = new JSpinner(sModel);
        JOptionPane.showMessageDialog(window, spinner, "Inserire costo", JOptionPane.QUESTION_MESSAGE);
        int cost = (Integer)(spinner.getValue());
        if(invert) cost *=-1;
        return new ProCon(nome, cost);
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
    public Gui(Character chara){
        this.character = chara;
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
                if(e.getStateChange() != ItemEvent.SELECTED) return;
                int gen = 0;
                if(character.isVampire()){
                    gen = ((Vampire) character).getGen();
                }
                String sel = (String)((JComboBox<String>)e.getSource()).getSelectedItem();
                character = ClanSelector.charSel(ClanSelector.get(sel));
                if(character.isVampire()){
                    ((Vampire) character).setGen(gen);
                }
                updateDetails();
                updateBloodWillPx();
                updateDisciplines();
                updateBloodWillPx();
                if(character.toChoosInfl()){
                    character.addInfluenza(selectInfl(true));
                }
                updateInfluencies();
                genPanel.setVisible(character.isVampire());
                genPanel.revalidate();
                genPanel.repaint();
                
                
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
        JButton addDisc = new JButton("Aggiungi Disciplina");
        addDisc.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Disciplina d = selectDiscipline();
                character.addDisciplina(d);
                updateDisciplines();
            }
        });
        discPanelWrap.add(addDisc, BorderLayout.SOUTH);
        JPanel wrap = new JPanel();
        wrap.add(discPanelWrap);
        skillBody.add(wrap);
        updateDisciplines();

        JPanel inflPanelWrap = new JPanel(new BorderLayout());
        inflPanelWrap.add(new JLabel("Influenze", SwingConstants.CENTER), BorderLayout.NORTH);
        inflPanel = new JPanel(new GridLayout(10, 1));
        inflPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        inflPanelWrap.add(inflPanel, BorderLayout.CENTER);
        JButton addInfl = new JButton("Aggiungi Influenza");
        addInfl.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Influenza i = selectInfl(false);
                character.addInfluenza(i);
                updateInfluencies();
            }
        });
        inflPanelWrap.add(addInfl, BorderLayout.SOUTH);
        wrap = new JPanel();
        wrap.add(inflPanelWrap);
        skillBody.add(wrap);
        updateInfluencies();

        JPanel proConPanelWrap = new JPanel(new BorderLayout());
        proConPanelWrap.add(new JLabel("Pregi/difetti", SwingConstants.CENTER), BorderLayout.NORTH);
        proConPanel = new JPanel(new GridLayout(10, 1));
        proConPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        proConPanelWrap.add(proConPanel, BorderLayout.CENTER);
        JPanel bupan = new JPanel(new BorderLayout());
        JButton addPro = new JButton("Aggiungi Pregio");
        addPro.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ProCon p = selectProCon(false);
                character.addProCon(p);
                updateProCon();
                updateBloodWillPx();
            }
        });
        JButton addCon = new JButton("Aggiungi Difetto");
        addCon.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ProCon p = selectProCon(true);
                character.addProCon(p);
                updateProCon();
                updateBloodWillPx();
            }
        });
        bupan.add(addPro, BorderLayout.NORTH);
        bupan.add(addCon, BorderLayout.SOUTH);
        proConPanelWrap.add(bupan, BorderLayout.SOUTH);
        wrap = new JPanel();
        wrap.add(proConPanelWrap);
        skillBody.add(wrap);
        updateProCon();

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
        ((GridLayout) discPanel.getLayout()).setVgap(5);
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
        ((GridLayout) inflPanel.getLayout()).setVgap(5);
        panel.revalidate();
        panel.repaint();
    }

    void updateProCon(){
        proConPanel.removeAll();
        Iterator<ProCon> it = character.pIterator();
        while(it.hasNext()){
            ProCon p = it.next();
            proConPanel.add(new ProConElement(p));
        }
        ((GridLayout) proConPanel.getLayout()).setVgap(5);
        panel.revalidate();
        panel.repaint();
    }

    void updateDetails(){
        character.setName(nome.getText());
        character.setSentiero(sentiero.getText());
        character.setFazione(fazione.getText());
    }

    void updateBloodWillPx(){
        bloodWill.setText("Sangue: "+character.getBlood()+" Will: "+character.getWill());
        pxlab.setText("Px rimanenti: " + character.getRemainingPx());
        panel.revalidate();
        panel.repaint();
    }

    public static void main(String[] args) {
        new Gui(new Clan.Assamita());    
    }
}
