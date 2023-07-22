package fairyShop.models;

import java.util.ArrayList;
import java.util.Collection;

import static fairyShop.common.ExceptionMessages.HELPER_NAME_NULL_OR_EMPTY;

public abstract class BaseHelper implements Helper {

    private String name;
    private int energy;
    private Collection<Instrument> instruments;

    public BaseHelper(String name, int energy) {
        this.setEnergy(energy);
        this.setName(name);
        this.instruments = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(HELPER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    protected void setEnergy(int energy) {
if (energy<0){
    throw  new IllegalArgumentException("Cannot create a Helper with negative energy!");
}
        this.energy = energy;
    }

    private void setInstruments(Collection<Instrument> instruments) {
        this.instruments = instruments;
    }

    @Override
    public void work() {

        if (this.energy-10 < 0) {
            this.energy = 0;
        }
        setEnergy(getEnergy() - 10);
    }

    @Override
    public void addInstrument(Instrument instrument) {
        this.instruments.add(instrument);
    }

    @Override
    public boolean canWork() {
        return this.energy>0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getEnergy() {

        return this.energy;
    }

    @Override
    public Collection<Instrument> getInstruments() {
        return this.instruments;
    }
}
