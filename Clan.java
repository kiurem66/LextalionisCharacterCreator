public final class Clan {

    private Clan(){};

    public static class Assamita extends Vampire{
        public Assamita(){
            super();
            addDisciplina("Oscurazione", 0);
            addDisciplina("Quietus", 0);
            addDisciplina("Velocità", 0);
            addInfluenza("Esercito", 0);
            addInfluenza("Sicurezza", 0);
            addInfluenza("Sopravvivenza", 0);
        }
    }

    public static class Baali extends Vampire{
        public Baali(){
            super();
            addDisciplina("Ascendente", 0);
            addDisciplina("Daimonion", 0);
            addDisciplina("Oscurazione", 0);
            addInfluenza("Accademiche", 0);
            addInfluenza("Occulto", 0);
            addInfluenza("Sopravvivenza", 0);
        }
    }

    public static class Brujah extends Vampire{
        public Brujah(){
            super();
            addDisciplina("Ascendente", 0);
            addDisciplina("Potenza", 0);
            addDisciplina("Velocità", 0);
            addInfluenza("Crimine", 0);
            addInfluenza("Politica", 0);
            addInfluenza("Sopravvivenza", 0);
        }
    }

    public static class CountryGangrel extends Vampire{
        public CountryGangrel(){
            super();
            addDisciplina("Animalità", 0);
            addDisciplina("Proteide", 0);
            addDisciplina("Robustezza", 0);
            addInfluenza("Mentore", 0);
            addInfluenza("Sicurezza", 0);
            addInfluenza("Sopravvivenza", 0);
        }
    }

    public static class CityGangrel extends Vampire{
        public CityGangrel(){
            super();
            addDisciplina("Oscurazione", 0);
            addDisciplina("Proteide", 0);
            addDisciplina("Velocità", 0);
            addInfluenza("Crimine", 0);
            addInfluenza("Media", 0);
            addInfluenza("Medicina", 0);
        }
    }

    public static class BloodBrothers extends Vampire{
        private int number;

        public BloodBrothers(){
            super();
            addDisciplina("Potenza", 0);
            addDisciplina("Robustezza", 0);
            addDisciplina("Sanguinis", 0);
            addInfluenza("Sopravvivenza", 0);
            number = 2;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getWill(){
            int base = super.getWill();
            return base + number;
        }
    }

    public static class Giovanni extends Vampire{
        public Giovanni(){
            super();
            addDisciplina("Dominazione", 0);
            addDisciplina("Necromanzia", 0);
            addDisciplina("Potenza", 0);
            addInfluenza("Medicina", 0);
            addInfluenza("Occulto", 0);
            addInfluenza("Risorse", 0);
        }
    }

    public static class Lasombra extends Vampire{
        public Lasombra(){
            super();
            addDisciplina("Dominazione", 0);
            addDisciplina("Ottenebramento", 0);
            addDisciplina("Potenza", 0);
            addInfluenza("Clero", 0);
            addInfluenza("Politica", 0);
            addInfluenza("Mentore", 0);
        }
    }

    public static class Malkavian extends Vampire{
        public Malkavian(){
            super();
            addDisciplina("Auspex", 0);
            addDisciplina("Demenza", 0);
            addDisciplina("Oscurazione", 0);
            addInfluenza("Accademiche", 0);
            addInfluenza("Media", 0);
            addInfluenza("Medicina", 0);
        }
    }

    public static class Nosferatu extends Vampire{
        public Nosferatu(){
            super();
            addDisciplina("Animalità", 0);
            addDisciplina("Oscurazione", 0);
            addDisciplina("Potenza", 0);
            addInfluenza("Media", 0);
            addInfluenza("Sopravvivenza", 0);
            addInfluenza("Sicurezza", 0);
        }
    }

    public static class Pander extends Vampire{
        public Pander(){
            super();
            addDisciplina("Animalità", 0);
            addDisciplina("Ascendente", 0);
            addDisciplina("Auspex", 0);
            addDisciplina("Dominazione", 0);
            addDisciplina("Oscurazione", 0);
            addDisciplina("Potenza", 0);
            addDisciplina("Robustezza", 0);
            addDisciplina("Velocità", 0);
            addInfluenza("Sopravvivenza", 0);
        }

        @Override
        public void addDisciplina(String nome, int level) {
            Disciplina d = new DisciplinaPander(nome);
            d.setLevel(level);
            setDiscipline.add(d);
        }

        @Override
        public boolean toChoosInfl() {
            return true;
        }
    }

    public static class Ravnos extends Vampire{
        //Chi cazzo ha mai giocato un Ravnos?
        public Ravnos(){
            super();
            addDisciplina("Animalità", 0);
            addDisciplina("Chimerismo", 0);
            addDisciplina("Robustezza", 0);
            addInfluenza("Crimine", 0);
            addInfluenza("Occulto", 0);
            addInfluenza("Sopravvivenza", 0);
        }
    }

    public static class Salubre extends Vampire{
        public Salubre(){
            super();
            addDisciplina("Auspex", 0);
            addDisciplina("Robustezza", 0);
            addDisciplina("Valeren", 0);
            addInfluenza("Esercito", 0);
            addInfluenza("Occulto", 0);
            addInfluenza("Sopravvivenza", 0);
        }
    }

    public static class SetFollower extends Vampire{
        public SetFollower(){
            super();
            addDisciplina("Ascendente", 0);
            addDisciplina("Oscurazione", 0);
            addDisciplina("Serpentis", 0);
            addInfluenza("Alta società", 0);
            addInfluenza("Clero", 0);
            addInfluenza("Crimine", 0);
        }
    }

    public static class LightSerpent extends Vampire{
        public LightSerpent(){
            super();
            addDisciplina("Ascendente", 0);
            addDisciplina("Oscurazione", 0);
            addDisciplina("Potenza", 0);
            addInfluenza("Crimine", 0);
            addInfluenza("Occulto", 0);
            addInfluenza("Sopravvivenza", 0);
        }
    }

    public static class Toreador extends Vampire{
        public Toreador(){
            super();
            addDisciplina("Ascendente", 0);
            addDisciplina("Auspex", 0);
            addDisciplina("Velocità", 0);
            addInfluenza("Accademiche", 0);
            addInfluenza("Alta società", 0);
            addInfluenza("Media", 0);
        }
    }

    public static class Tremere extends Vampire{
        public Tremere(){
            super();
            addDisciplina("Auspex", 0);
            addDisciplina("Dominazione", 0);
            addDisciplina("Taumaturgia", 0);
            addInfluenza("Accademiche", 0);
            addInfluenza("Alta società", 0);
            addInfluenza("Occulto", 0);
        }
    }

    public static class Tzimisce extends Vampire{
        public Tzimisce(){
            super();
            addDisciplina("Animalità", 0);
            addDisciplina("Auspex", 0);
            addDisciplina("Vicissitudine", 0);
            addInfluenza("Accademiche", 0);
            addInfluenza("Medicine", 0);
            addInfluenza("Occulto", 0);
        }
    }

    public static class Ventrue extends Vampire{
        public Ventrue(){
            super();
            addDisciplina("Ascendente", 0);
            addDisciplina("Dominazione", 0);
            addDisciplina("Robustezza", 0);
            addInfluenza("Esercito", 0);
            addInfluenza("Politica", 0);
            addInfluenza("Risorse", 0);
        }
    }


}
