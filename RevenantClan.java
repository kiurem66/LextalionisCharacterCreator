public class RevenantClan {
    private RevenantClan(){}

    private static class Revenant extends Ghoul{
        public Revenant(){
            ProCon p = new ProCon("Difetto di clan", 0);
            p.setClan(true);
            addProCon(p);
        }
    }

    public static class Bratovich extends Revenant{
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

    public static class Grimaldi extends Revenant{
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

    public static class Obertus extends Revenant{
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

    public static class Zantosa extends Revenant{
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
