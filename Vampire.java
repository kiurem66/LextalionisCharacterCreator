public abstract class Vampire extends Character{

    int gen;

    public Vampire(){
        super();
        gen = 13;
    }

    public Vampire(Character c){
        super(c);
        if(c instanceof Vampire){
            gen = ((Vampire) c).gen;
        }else{
            gen = 13;
        }
    }

    public int getGen() {
        return gen;
    }

    public void setGen(int gen) {
        this.gen = gen;
    }

    @Override
    public int getRemainingPx() {
        int pxSpesi = 0;
        switch(gen){
            case 15: pxSpesi-=8; break;
            case 14: pxSpesi-=4; break;
            case 12: pxSpesi+=10; break;
            case 11: pxSpesi+=20; break;
            case 10: pxSpesi+=30; break;
        }
        //pregi, difetti e stili
        return super.getRemainingPx() - pxSpesi;
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
            case 12: base = 12; break;
            case 11: base = 14; break;
            case 10: base = 16; break;
            default: base = 10; break;
        }
        return base+super.getBlood();
    }

    @Override
    public int getWill() {
        int base = 0;
        switch(gen){
            case 15: base = 5; break;
            case 14: base = 6; break;
            case 12: base = 8; break;
            case 11: base = 9; break;
            case 10: base = 10; break;
            default: base = 7; break;
        }
        //pregi
        return base;
    }

    @Override
    public boolean toChoosInfl() {
        return false;
    }
    
}
