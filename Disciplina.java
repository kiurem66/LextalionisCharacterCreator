import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Tipo mutabile che implementa Skill
 * Rappresenta una disciplina di un personaggio
 */
public abstract class Disciplina implements Skill{
    private String name;
    private int level;
    private boolean firstFree;
    private boolean clan;
    private boolean pander;

    public static record Power(String nome, int livello, String costo, String test, String raggio,String durata, String limite){}

    private ArrayList<Power> listaPoteri;

    public Disciplina(String name, boolean pander){
        this.name = name;
        this.level  = 0;
        firstFree = false;
        clan = true;
        this.pander = pander;
    }

    public Disciplina(String name){
        this(name, false);
    }

    public void setClan(boolean clan) {
        this.clan = clan;
    }

    public boolean isClan() {
        return clan;
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
        if(!clan) return 9;
        if(pander) return 7;
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

    @Override
    public boolean isFirstLevelFree() {
        return firstFree;
    }

    public void setFirstLevelFree(boolean firstFree) {
        this.firstFree = firstFree;
    }

    protected void addPotere(Power p){
        listaPoteri.add(p);
    }

    public Iterator<Power> iterator(){
        return new Iterator<Disciplina.Power>() {
            int i=0;

            @Override
            public boolean hasNext() {
                if(i>=listaPoteri.size()) return false;
                if(listaPoteri.get(i).livello() > level) return false;
                return true;
            }

            @Override
            public Disciplina.Power next() {
                if(!hasNext()) throw new NoSuchElementException();
                return listaPoteri.get(i++);
            }
            
        };
    }

    public static class Animalità extends Disciplina{
        public Animalità(){
            this(false);
        }

        public Animalità(boolean pander) {
            super("Animalità", pander);
            addPotere(new Power("Sussurri ferini", 1, "1/0W", "Test", "Dirette vicinanze", "Colloquio", "1 volta a scena"));
        }
        
    }

    public static class Ascendente extends Disciplina{
        public Ascendente(){
            this(false);
        }

        public Ascendente(boolean pander) {
            super("Ascendente", pander);
            addPotere(new Power("", 1, "", "Test", "", "", ""));
        }
    }

    public static class Auspex extends Disciplina{
        public Auspex(){
            this(false);
        }

        public Auspex(boolean pander) {
            super("Ascendente", pander);
            addPotere(new Power("", 1, "", "Test", "", "", ""));
        }
    }

    public static class Chimerismo extends Disciplina{
        public Chimerismo(){
            this(false);
        }

        public Chimerismo(boolean pander) {
            super("Chimerismo", pander);
            addPotere(new Power("", 1, "", "Test", "", "", ""));
        }
    }

    public static class Daimonion extends Disciplina{
        public Daimonion(){
            this(false);
        }

        public Daimonion(boolean pander) {
            super("Daimonion", pander);
            addPotere(new Power("", 1, "", "Test", "", "", ""));
        }
    }

    public static class Demenza extends Disciplina{
        public Demenza(){
            this(false);
        }

        public Demenza(boolean pander) {
            super("Demenza", pander);
            addPotere(new Power("", 1, "", "Test", "", "", ""));
        }
    }

    public static class Dominazione extends Disciplina{
        public Dominazione(){
            this(false);
        }

        public Dominazione(boolean pander) {
            super("Dominazione", pander);
            addPotere(new Power("", 1, "", "Test", "", "", ""));
        }
    }

    public static class Necromanzia extends Disciplina{
        public Necromanzia(){
            this(false);
        }

        public Necromanzia(boolean pander) {
            super("Necromanzia", pander);
            addPotere(new Power("", 1, "", "Test", "", "", ""));
        }
    }

    public static class Oscurazione extends Disciplina{
        public Oscurazione(){
            this(false);
        }

        public Oscurazione(boolean pander) {
            super("Oscurazione", pander);
            addPotere(new Power("", 1, "", "Test", "", "", ""));
        }
    }

    public static class Ottenebramento extends Disciplina{
        public Ottenebramento(){
            this(false);
        }

        public Ottenebramento(boolean pander) {
            super("Ottenebramento", pander);
            addPotere(new Power("", 1, "", "Test", "", "", ""));
        }
    }

    public static class Potenza extends Disciplina{
        public Potenza(){
            this(false);
        }

        public Potenza(boolean pander) {
            super("Potenza", pander);
            addPotere(new Power("", 1, "", "Test", "", "", ""));
        }
    }

    public static class Proteide extends Disciplina{
        public Proteide(){
            this(false);
        }

        public Proteide(boolean pander) {
            super("Proteide", pander);
            addPotere(new Power("", 1, "", "Test", "", "", ""));
        }
    }

    public static class Quietus extends Disciplina{
        public Quietus(){
            this(false);
        }

        public Quietus(boolean pander) {
            super("Quietus", pander);
            addPotere(new Power("", 1, "", "Test", "", "", ""));
        }
    }

    public static class Robustezza extends Disciplina{
        public Robustezza(){
            this(false);
        }

        public Robustezza(boolean pander) {
            super("Robustezza", pander);
            addPotere(new Power("", 1, "", "Test", "", "", ""));
        }
    }

    public static class Sanguinis extends Disciplina{
        public Sanguinis(){
            this(false);
        }

        public Sanguinis(boolean pander) {
            super("Sanguinis", pander);
            addPotere(new Power("", 1, "", "Test", "", "", ""));
        }
    }

    public static class Serpentis extends Disciplina{
        public Serpentis(){
            this(false);
        }

        public Serpentis(boolean pander) {
            super("Serpentis", pander);
            addPotere(new Power("", 1, "", "Test", "", "", ""));
        }
    }

    public static class Taumaturgia extends Disciplina{
        public Taumaturgia(){
            this(false);
        }

        public Taumaturgia(boolean pander) {
            super("Taumaturgia del Sangue", pander);
            addPotere(new Power("", 1, "", "Test", "", "", ""));
        }
    }

    public static class Valeren extends Disciplina{
        public Valeren(){
            this(false);
        }

        public Valeren(boolean pander) {
            super("Valeren", pander);
            addPotere(new Power("", 1, "", "Test", "", "", ""));
        }
    }

    public static class Velocità extends Disciplina{
        public Velocità(){
            this(false);
        }

        public Velocità(boolean pander) {
            super("Velocità", pander);
            addPotere(new Power("", 1, "", "Test", "", "", ""));
        }
    }
    
    public static class Vicissitudine extends Disciplina{
        public Vicissitudine(){
            this(false);
        }

        public Vicissitudine(boolean pander) {
            super("Vicissitudine", pander);
            addPotere(new Power("", 1, "", "Test", "", "", ""));
        }
    }
}