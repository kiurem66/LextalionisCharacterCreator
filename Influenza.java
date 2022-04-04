/**
 * Tipo mutabile che implementa Skill
 * Rappresenta un'influenza di un personaggio
 */
public class Influenza implements Skill{
    private String name;
    private int level;

    public Influenza(String name){
        this.name = name;
        this.level = 0;
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
        return 3;
    }

    @Override
    public void setLevel(int lv) {
        this.level = lv;
    }    

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Influenza)) return false;
        Influenza other = (Influenza)obj;
        return this.name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}