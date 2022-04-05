public class RevenantClan {
    private RevenantClan(){}

    public static class Bratovich extends Ghoul{
        public Bratovich(){
            super();
            addDisciplina(new Disciplina.Animalità());
            addDisciplina(new Disciplina.Potenza());
            addDisciplina(new Disciplina.Vicissitudine());
            addInfluenza(new Influenza("Sopravvivenza"));
            addInfluenza(new Influenza("Medicina"));
        }

        @Override
        public String getClan() {
            return "Bratovich";
        }
    }

    public static class Grimaldi extends Ghoul{
        public Grimaldi(){
            super();
            addDisciplina(new Disciplina.Dominazione());
            addDisciplina(new Disciplina.Robustezza());
            addDisciplina(new Disciplina.Vicissitudine());
            addInfluenza(new Influenza("Sopravvivenza"));
            addInfluenza(new Influenza("Velocità"));
        }

        @Override
        public String getClan() {
            return "Grimaldi";
        }
    }

    public static class Obertus extends Ghoul{
        public Obertus(){
            super();
            addDisciplina(new Disciplina.Auspex());
            addDisciplina(new Disciplina.Oscurazione());
            addDisciplina(new Disciplina.Vicissitudine());
            addInfluenza(new Influenza("Accademiche"));
            addInfluenza(new Influenza("Occulto"));
        }

        @Override
        public String getClan() {
            return "Obertus";
        }
    }

    public static class Zantosa extends Ghoul{
        public Zantosa(){
            super();
            addDisciplina(new Disciplina.Ascendente());
            addDisciplina(new Disciplina.Auspex());
            addDisciplina(new Disciplina.Vicissitudine());
            addInfluenza(new Influenza("Alta società"));
            addInfluenza(new Influenza("Risorse"));
        }

        @Override
        public String getClan() {
            return "Zantosa";
        }
    }
}
