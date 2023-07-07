package zoo.entities.animals;

public class TerrestrialAnimal extends BaseAnimal{
    private static final double TERRA_WEIGHT = 5.5;
    public TerrestrialAnimal(String name, String kind, double price) {
        super(name, kind, TERRA_WEIGHT, price);
    }

    @Override
    public void eat() {
        setKg(getKg() + 5.7);
    }
}
