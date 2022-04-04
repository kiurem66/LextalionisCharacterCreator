public class Vampire extends Character{

    int gen;

    public int getGen() {
        return gen;
    }

    public void setGen(int gen) {
        this.gen = gen;
    }

    @Override
    public int getRemainingPx() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isVampire() {
        return true;
    }

    @Override
    public int getBlood() {
        int base=0;
        switch(gen){
            case 15: base = 8; break;
            case 14: base = 9; break;
            case 13: base = 10; break;
            case 12: base = 12; break;
            case 11: base = 14; break;
            case 10: base = 16; break;
            default: base = 10; break;
        }
        int robu = 0;
        if(isInDisc("Robustezza")){
            switch(searchDisc("Robustezza").getLevel()){
                case 1: robu = 5;
                case 2: robu = 10;
                case 3: robu = 15;
                case 4: robu = 25;
                case 5: robu = 35;
                default: robu = 0;
            }
        }
        int vici = 0;
        if(isInDisc("Vicissitudine")){
            vici = searchDisc("Vicissitudine").getLevel();
            if(vici == 1) vici = 0;
        }
        return base+robu+vici;
    }

    @Override
    public int getWill() {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
