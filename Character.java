import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author kyuRAM
 * 
 * Rappresenta la scheda di un personaggio di LexTallionis
 */
public abstract class Character{    
    private String name;
    private int px;
    private String sentiero;
    private String fazione;

    protected HashSet<Disciplina> setDiscipline = new HashSet<Disciplina>();
    protected HashSet<Influenza> setInfluenze = new HashSet<Influenza>();
    protected HashSet<Style> setStili = new HashSet<Style>();
    //pregi
    //stili

    public Character(){
        name = "";
        px  = 30;
        sentiero = "";
        fazione = "";
    }

    public Character(Character character){
        this.name = character.name;
        this.px = character.px;
        this.sentiero = character.sentiero;
        this.fazione = character.fazione;
    }

    public int getPx() {
        return px;
    }

    public String getName() {
        return name;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFazione() {
        return fazione;
    }

    public String getSentiero() {
        return sentiero;
    }

    public void setFazione(String fazione) {
        this.fazione = fazione;
    }

    public void setSentiero(String sentiero) {
        this.sentiero = sentiero;
    }

    public void addDisciplina(String nome, int level){
        Disciplina d = new Disciplina(name);
        d.setLevel(level);
        setDiscipline.add(d);
    }

    public void addDisciplinaNoClan(String nome, int level){
        Disciplina d = new DisciplinaNoClan(name);
        d.setLevel(level);
        setDiscipline.add(d);
    }

    public void addInfluenza(String nome, int level){
        Influenza i = new Influenza(nome);
        i.setLevel(level);
        setInfluenze.add(i);
    }

    public void addInfluenzaNoClan(String nome, int level){
        Influenza i = new InfluenzaNoClan(nome);
        i.setLevel(level);
        setInfluenze.add(i);
    }

    public void addStile(Style s){
        setStili.add(s);
    }

    public Iterator<Disciplina> discIterator(){
        return setDiscipline.iterator();
    }

    public Iterator<Influenza> inflIterator(){
        return setInfluenze.iterator();
    }

    public Iterator<Style> sIterator(){
        return setStili.iterator();
    }

    public Disciplina searchDisc(String nome){
        for(Disciplina d : setDiscipline){
            if(d.getName().equals(nome)){
                return d;
            }
        }
        throw new NoSuchElementException("Disciplina non presente");
    }

    public boolean isInDisc(String nome){
        Disciplina d = new Disciplina(nome);
        return setDiscipline.contains(d);
    }

    public Influenza searchInfl(String nome){
        for(Influenza i : setInfluenze){
            if(i.getName().equals(nome)){
                return i;
            }
        }
        throw new NoSuchElementException("Influenza non presente");
    }

    public boolean isInInfl(String nome){
        Influenza i = new Influenza(nome);
        return setInfluenze.contains(i);
    }

    public int getRemainingPx(){
        int pxSpesi = 0;
        Iterator<Disciplina> itD = discIterator();
        while(itD.hasNext()){
            pxSpesi += itD.next().costCalc();
        }
        Iterator<Influenza> itI = inflIterator();
        while(itI.hasNext()){
            pxSpesi += itI.next().costCalc();
        }
        //pregi, difetti e stili
        return getPx() - pxSpesi;
    }

    public abstract boolean isVampire();

    public int getBlood(){
        int robu = 0;
        if(isInDisc("Robustezza")){
            switch(searchDisc("Robustezza").getLevel()){
                case 1: robu = 5;
                case 2: robu = 10;
                case 3: robu = 15;
                case 4: robu = 25;
                case 5: robu = 35;
                default: robu = 0;
            }
        }
        int vici = 0;
        if(isInDisc("Vicissitudine")){
            vici = searchDisc("Vicissitudine").getLevel();
            if(vici == 1) vici = 0;
        }
        return robu+vici;
    }

    public void removeDisc(Disciplina d){
        if(d instanceof DisciplinaNoClan){
            setDiscipline.remove(d);
        }
    }

    public void removeInfl(Influenza i){
        if(i instanceof InfluenzaNoClan){
            setInfluenze.remove(i);
        }
    }

    public void removeStil(Style s){
        setStili.remove(s);
    }

    public abstract int getWill();

    public abstract boolean toChoosInfl();

    public abstract String getClan();
}