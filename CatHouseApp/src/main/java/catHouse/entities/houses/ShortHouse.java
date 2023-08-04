package catHouse.entities.houses;

public class ShortHouse extends BaseHouse{
    private static final int SHORT_CAPACITY = 15;
    public ShortHouse(String name) {
        super(name, SHORT_CAPACITY);
    }
}
