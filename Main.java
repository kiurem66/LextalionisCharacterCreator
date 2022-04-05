public class Main {
    public static void main(String[] args) {
        Character c = new Clan.Ventrue();
        c.searchDisc("Dominazione").incrLevel();
        c.searchInfl("Esercito").setLevel(3);
        System.out.println(c.getRemainingPx()); 
    }
}
