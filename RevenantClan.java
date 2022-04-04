public class RevenantClan {
    private RevenantClan(){}

    public static class Bratovich extends Ghoul{
        public Bratovich(){
            super();
            addDisciplina("Animalità", 0);
            addDisciplina("Potenza", 0);
            addDisciplina("Vicissitudine", 0);
            addInfluenza("Sopravvivenza", 0);
            addInfluenza("Medicina", 0);
        }
    }

    public static class Grimaldi extends Ghoul{
        public Grimaldi(){
            super();
            addDisciplina("Dominazione", 0);
            addDisciplina("Robustezza", 0);
            addDisciplina("Vicissitudine", 0);
            addInfluenza("Sopravvivenza", 0);
            addInfluenza("Velocità", 0);
        }
    }

    public static class Obertus extends Ghoul{
        public Obertus(){
            super();
            addDisciplina("Auspex", 0);
            addDisciplina("Oscurazione", 0);
            addDisciplina("Vicissitudine", 0);
            addInfluenza("Accademiche", 0);
            addInfluenza("Occulto", 0);
        }
    }

    public static class Zantosa extends Ghoul{
        public Zantosa(){
            super();
            addDisciplina("Ascendente", 0);
            addDisciplina("Auspex", 0);
            addDisciplina("Vicissitudine", 0);
            addInfluenza("Alta società", 0);
            addInfluenza("Risorse", 0);
        }
    }
}
