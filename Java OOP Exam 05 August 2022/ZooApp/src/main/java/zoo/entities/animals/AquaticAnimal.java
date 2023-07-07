package zoo.entities.animals;

public class AquaticAnimal extends BaseAnimal {
    private static final double AQUA_WEIGHT = 2.5;

    public AquaticAnimal(String name, String kind, double price) {
        super(name, kind, AQUA_WEIGHT, price);
    }

    @Override
    public void eat() {
        setKg(getKg() + 7.5);
    }
}
