/**
 * @author kyuRAM
 * 
 * Rappresenta una generica skill acquistabile con i pxù
 * 
 * Le skill possono essere: discipline, influenze, stili di combattimento etc..
 */

public interface Skill {
    /**
     * Restituisce il nome della skill
     * @return il nome della skill
     */
    public String getName();


    /**
     * Restituisce il livello della skill
     * @return il livello della skill
     */
    public int getLevel();

    /**
     * Modifica il livello della skill
     * @param lv il nuovo livello della skill
     */
    public void setLevel(int lv);

    /**
     * Restituisce il costo base della skill
     * @return il costo base della skill
     */
    public int getBaseCost();

    /**
     * Calcola il costo totale della skill al livello attuale
     * 
     * Implementazione di default: ogni livello costa lv*costoBase, 
     * questo poi si somma al costo di tutti i livelli precedenti 
     * 
     * @return il costo totale in px della skill
     */
    default int costCalc(){
        int cost = 0;
        for(int i=1; i<=getLevel(); i++){
            cost += i*getBaseCost();
        }
        return cost;
    }

    default void incrLevel(){
        setLevel(getLevel()+1);
    }
}
