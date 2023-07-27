package robotService.entities.supplements;

public abstract class BaseSupplement implements Supplement {
    private int hardness;
    private double price;

    public BaseSupplement(int hardness, double price) {
        this.hardness = hardness;
        this.price = price;
    }

    private void setHardness(int hardness) {
        this.hardness = hardness;
    }

    private void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int getHardness() {
        return this.hardness;
    }

    @Override
    public double getPrice() {
        return this.price;
    }
}
