public class InfluenzaNoClan extends Influenza{
    
    public InfluenzaNoClan(String name) {
        super(name);
    }

    public int costCalc(){
        int i = super.costCalc();
        if(i == 0) return 0;
        return i+1;
    }
}
