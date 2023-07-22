package fairyShop.models;

public class Sleepy extends BaseHelper{
    public Sleepy(String name) {
        super(name, 50);
    }

    @Override
    public void work() {
        setEnergy(getEnergy()-5);
    }
}
