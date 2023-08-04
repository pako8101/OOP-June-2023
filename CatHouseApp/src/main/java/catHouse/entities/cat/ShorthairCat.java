package catHouse.entities.cat;

public class ShorthairCat extends BaseCat {
private static final int SHORT_HAIR_KG = 7;

    public ShorthairCat(String name, String breed, double price) {
        super(name, breed,SHORT_HAIR_KG, price);
    }

    @Override
    public void eating() {
      this.setKilograms(getKilograms()+1);
    }
}
