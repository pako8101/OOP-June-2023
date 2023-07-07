package ViceCity.src.main.java.viceCity.models.players;

import ViceCity.src.main.java.viceCity.models.guns.Gun;
import ViceCity.src.main.java.viceCity.repositories.interfaces.Repository;

public interface Player {
    String getName();

    int getLifePoints();

    boolean isAlive();

    Repository<Gun> getGunRepository();

    void takeLifePoints(int points);
}
