/**
 * Tipo mutabile che implementa Skill
 * Rappresenta una disciplina di un personaggio
 */
public class Disciplina implements Skill{
    private String name;
    private int level;

    public Disciplina(String name){
        this.name = name;
        this.level  = 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getBaseCost() {
        return 6;
    }

    @Override
    public void setLevel(int lv) {
        this.level = lv;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Disciplina)) return false;
        Disciplina other = (Disciplina)obj;
        return this.name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}