package catHouse.entities.cat;

public class LonghairCat extends BaseCat{
    private static final int LONG_HAIR_KG = 9;
    public LonghairCat(String name, String breed, double price) {
        super(name, breed,LONG_HAIR_KG, price);
    }

    @Override
    public void eating() {
        this.setKilograms(getKilograms()+3);
    }
}
