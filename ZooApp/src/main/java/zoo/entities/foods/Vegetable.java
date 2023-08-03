package zoo.entities.foods;

public class Vegetable extends BaseFood {
    private static final int VEGE_CALORIES = 50;
    private static final double VEGE_PRICE = 5;

    public Vegetable() {
        super(VEGE_CALORIES, VEGE_PRICE);
    }

}
