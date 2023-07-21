package football.entities.supplement;

public abstract class BaseSupplement implements Supplement{
    private int energy;
    private double price;

    public BaseSupplement(int energy, double price) {
        this.setEnergy(energy);
        this.setPrice(price);
    }

    protected void setEnergy(int energy) {
        this.energy = energy;
    }

    private void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int getEnergy() {
        return this.energy;
    }

    @Override
    public double getPrice() {
        return this.price;
    }
}
