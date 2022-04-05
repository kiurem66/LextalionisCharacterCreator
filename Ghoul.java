public class Ghoul extends Character{

    @Override
    public boolean isVampire() {
        return false;
    }

    @Override
    public int getWill() {
        return 4+super.getWill();
        //pregi
    }

    @Override
    public boolean toChoosInfl() {
        return true;
    }
    
    @Override
    public String getClan() {
        return "Ghoul";
    }
}
