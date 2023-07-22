package fairyShop.models;

public class Happy extends BaseHelper{
    public Happy(String name) {
        super(name, 100);
    }
    @Override
    public void work() {
        setEnergy(getEnergy()-5);
    }
}
