package christmasPastryShop.entities.delicacies;

public class Stolen extends BaseDelicacy{
    private static final  int InitialStolenPortion   = 250;
    public Stolen(String name, double price) {
        super(name, InitialStolenPortion , price);
    }
}
