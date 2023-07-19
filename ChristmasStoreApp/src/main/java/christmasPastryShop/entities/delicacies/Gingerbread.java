package christmasPastryShop.entities.delicacies;

public class Gingerbread extends  BaseDelicacy{
    private static final  int InitialGingerbreadPortion  = 200;
    public Gingerbread(String name, double price) {
        super(name, InitialGingerbreadPortion , price);
    }
}
