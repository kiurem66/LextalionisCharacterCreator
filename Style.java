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

}
