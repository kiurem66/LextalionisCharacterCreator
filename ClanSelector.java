import java.util.Iterator;

public class ClanSelector {
    private ClanSelector(){}

    public static Character charSel(int i){
        switch(i){
            case 1: return new Clan.Assamita();
            case 2: return new Clan.Baali();
            case 3: return new Clan.Brujah();
            case 4: return new Clan.CountryGangrel();
            case 5: return new Clan.CityGangrel();
            case 6: return new Clan.BloodBrothers();
            case 7: return new Clan.Giovanni();
            case 8: return new Clan.Lasombra();
            case 9: return new Clan.Malkavian();
            case 10: return new Clan.Nosferatu();
            case 11: return new Clan.Pander();
            case 12: return new Clan.Ravnos();
            case 13: return new Clan.Salubre();
            case 14: return new Clan.SetFollower();
            case 15: return new Clan.LightSerpent();
            case 16: return new Clan.Toreador();
            case 17: return new Clan.Tremere();
            case 18: return new Clan.Tzimisce();
            case 19: return new Clan.Ventrue();
            case 20: return new RevenantClan.Bratovich();
            case 21: return new RevenantClan.Grimaldi();
            case 22: return new RevenantClan.Obertus();
            case 23: return new RevenantClan.Zantosa();
            default: throw new IllegalArgumentException();
        }
    }


    public static String get(int i){
        switch(i){
            case 0: return "Assamita";
            case 1: return "Baali";
            case 2: return "Brujah";
            case 3: return "Gangrel di Campagna";
            case 4: return "Gangrel di Città";
            case 5: return "Gemelli di Sangue";
            case 6: return "Giovanni";
            case 7: return "Lasombra";
            case 8: return "Malkavian";
            case 9: return "Nosferatu";
            case 10: return "Pander";
            case 11: return "Ravnos";
            case 12: return "Salubre";
            case 13: return "Seguace del Set";
            case 14: return "Serpente della Luce";
            case 15: return "Toreador";
            case 16: return "Tremere";
            case 17: return "Tzimisce";
            case 18: return "Ventrue\n";
            case 19: return "Bratovich";
            case 20: return "Grimaldi";
            case 21: return "Obertus";
            case 22: return "Zantosa\n";
        }
        return null;
    }

    public static Iterator<String> iterator(){
        return new Iterator<String>() {
            int i=0;

            @Override
            public boolean hasNext() {
                return i<23;
            }

            @Override
            public String next() {
                return get(i++);
            }
        };
    }
}
