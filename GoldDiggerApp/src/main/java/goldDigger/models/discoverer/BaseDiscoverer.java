package goldDigger.models.discoverer;

import goldDigger.common.ConstantMessages;
import goldDigger.models.museum.BaseMuseum;
import goldDigger.models.museum.Museum;

import static goldDigger.common.ConstantMessages.*;
import static goldDigger.common.ExceptionMessages.DISCOVERER_ENERGY_LESS_THAN_ZERO;
import static goldDigger.common.ExceptionMessages.DISCOVERER_NAME_NULL_OR_EMPTY;

public abstract class BaseDiscoverer implements Discoverer {
    private String name;
    private double energy;
    private Museum museum;

    public BaseDiscoverer(String name, double energy) {
        this.setName(name);
        this.setEnergy(energy);
        this.museum = new BaseMuseum();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(DISCOVERER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    private void setEnergy(double energy) {
        if (energy < 0) {
            throw new IllegalArgumentException(DISCOVERER_ENERGY_LESS_THAN_ZERO);
        }
        this.energy = energy;
    }

    private void setMuseum(Museum museum) {
        this.museum = museum;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getEnergy() {
        return this.energy;
    }

    @Override
    public boolean canDig() {
        return this.energy > 0;
    }

    @Override
    public Museum getMuseum() {
        return this.museum;
    }

    @Override
    public void dig() {
//        this.energy -= 15;
//        if (this.energy < 0) {
//            this.energy = 0;
//        }
        setEnergy(Math.max(getEnergy()-15,0));
    }

    @Override
    public String toString() {
        String exhibits = this.museum.getExhibits().isEmpty() ? "None"
                : String.join(FINAL_DISCOVERER_MUSEUM_EXHIBITS_DELIMITER,this.museum.getExhibits());


//                String exhibits = String.join(FINAL_DISCOVERER_MUSEUM_EXHIBITS_DELIMITER,this.museum.getExhibits());
//        if (this.museum.getExhibits().isEmpty()){
//            exhibits = "None";
//        }
        return String.format(FINAL_DISCOVERER_NAME, this.name) + System.lineSeparator() +
                String.format(FINAL_DISCOVERER_ENERGY,this.energy) + System.lineSeparator() +
                        String.format(FINAL_DISCOVERER_MUSEUM_EXHIBITS,exhibits).trim();
    }
}
