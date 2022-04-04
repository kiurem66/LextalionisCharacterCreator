import java.util.Iterator;

public class Vampire extends Character{

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
        Iterator<Disciplina> itD = discIterator();
        while(itD.hasNext()){
            pxSpesi += itD.next().costCalc();
        }
        Iterator<Influenza> itI = inflIterator();
        while(itI.hasNext()){
            pxSpesi += itI.next().costCalc();
        }
        switch(gen){
            case 15: pxSpesi-=8; break;
            case 14: pxSpesi-=4; break;
            case 12: pxSpesi+=10; break;
            case 11: pxSpesi+=20; break;
            case 10: pxSpesi+=30; break;
        }
        //pregi, difetti e stili
        return getPx() - pxSpesi;
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
        //TODO chiedere al master eventuali altri metodi di aumento di punti sangue
        return base+robu+vici;
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
    
}
