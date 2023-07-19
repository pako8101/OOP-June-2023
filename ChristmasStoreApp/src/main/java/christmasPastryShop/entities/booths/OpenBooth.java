package christmasPastryShop.entities.booths;

public class OpenBooth extends BaseBooth{
    private static final double pricePerPerson = 2.50;
    public OpenBooth(int boothNumber, int capacity) {
        super(boothNumber, capacity, pricePerPerson);
    }
}
