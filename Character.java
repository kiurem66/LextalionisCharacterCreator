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

    HashSet<Disciplina> setDiscipline = new HashSet<Disciplina>();
    HashSet<Influenza> setInfluenze = new HashSet<Influenza>();
    //pregi
    //stili

    public Character(){
        name = "";
        px  = 30;
        sentiero = "";
        fazione = "";
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
        Disciplina d = new DisciplinaNoClan(name);
        d.setLevel(level);
        setDiscipline.add(d);
    }

    public void addInfluenza(String nome, int level){
        Influenza i = new InfluenzaNoClan(nome);
        i.setLevel(level);
        setInfluenze.add(i);
    }

    public Iterator<Disciplina> discIterator(){
        return setDiscipline.iterator();
    }

    public Iterator<Influenza> inflIterator(){
        return setInfluenze.iterator();
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

    public abstract int getRemainingPx();

    public abstract boolean isVampire();

    public abstract int getBlood();

    public abstract int getWill();
}