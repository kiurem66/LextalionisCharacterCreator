public abstract class Style implements Skill{
    protected int level;

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int lv) {
        this.level = lv;
    }

    @Override
    public int getBaseCost() {
        return 3;
    }

    @Override
    public int costCalc() {
        switch(level){
            case 1: return 3;
            case 2: return 8;
            case 3: return 15;
            default: return 0;
        }
    }

    @Override
    public boolean isFirstLevelFree() {
        return false;
    }

    @Override
    public void setFirstLevelFree(boolean free) {}

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Style)) return false;
        return ((Style)obj).getName().equals(getName());
    }
    
    public static class Coltelli extends Style{
        public Coltelli(){
            level = 1;
        }
        
        @Override
        public String getName() {
            return "Coltelli";
        }
    }

    public static class Duellista extends Style{
        public Duellista(){
            level = 1;
        }
        
        @Override
        public String getName() {
            return "Duellista";
        }
    }

    public static class Lancio extends Style{
        public Lancio(){
            level = 1;
        }
        
        @Override
        public String getName() {
            return "Armi da lancio";
        }
    }

    public static class Desperado extends Style{
        public Desperado(){
            level = 1;
        }
        
        @Override
        public String getName() {
            return "Desperado";
        }
    }

    public static class Cinematografico extends Style{
        public Cinematografico(){
            level = 1;
        }
        
        @Override
        public String getName() {
            return "Stile cinematografico";
        }
    }

    public static class Marziali extends Style{
        public Marziali(){
            level = 1;
        }
        
        @Override
        public String getName() {
            return "Arti Marziali";
        }
    }

    public static class Acrobatico extends Style{
        public Acrobatico(){
            level = 1;
        }
        
        @Override
        public String getName() {
            return "Combattimento Acrobatico";
        }
    }

    public static class Sporco extends Style{
        public Sporco(){
            level = 1;
        }
        
        @Override
        public String getName() {
            return "Gioco Sporco";
        }
    }

    public static class Libre extends Style{
        public Libre(){
            level = 1;
        }
        
        @Override
        public String getName() {
            return "Lucha Libre";
        }
    }

    public static class Strada extends Style{
        public Strada(){
            level = 1;
        }
        
        @Override
        public String getName() {
            return "Rissa da Strada";
        }
    }

}
