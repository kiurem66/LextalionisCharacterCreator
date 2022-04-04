public class InfluenzaNoClan extends Influenza{
    
    public InfluenzaNoClan(String name) {
        super(name);
    }

    public int getBaseCost(){
        return 4;
    }

    public int costCalc(){
        if(getLevel() == 0) return 0;
        Influenza i = new Influenza("");
        i.setLevel(getLevel());
        return i.costCalc()+1;
    }
}
