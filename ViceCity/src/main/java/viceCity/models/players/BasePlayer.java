package ViceCity.src.main.java.viceCity.models.players;

import ViceCity.src.main.java.viceCity.common.ExceptionMessages;
import ViceCity.src.main.java.viceCity.models.guns.Gun;
import ViceCity.src.main.java.viceCity.repositories.interfaces.GunRepository;
import ViceCity.src.main.java.viceCity.repositories.interfaces.Repository;

public abstract class BasePlayer implements Player {
    private String name;
    private int lifePoints;
    private Repository<Gun> gunRepository;

    public BasePlayer(String name, int lifePoints) {
        this.setName(name);
        this.setLifePoints(lifePoints);
        this.gunRepository = new GunRepository();

    }

    @Override
    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.NAME_NULL);
        }
        this.name = name;
    }

    @Override
    public int getLifePoints() {
        return this.lifePoints;
    }

    private void setLifePoints(int lifePoints) {
        if (lifePoints < 0) {
            throw new IllegalArgumentException(ExceptionMessages.PLAYER_LIFE_POINTS_LESS_THAN_ZERO);
        }
        this.lifePoints = lifePoints;
    }

    @Override
    public Repository<Gun> getGunRepository() {
        return gunRepository;
    }

    @Override
    public boolean isAlive() {
        return this.lifePoints > 0;
    }

    @Override
    public void takeLifePoints(int points) {
//        this.lifePoints = this.lifePoints - points;
//        if (this.lifePoints<0){
//            this.lifePoints = 0;
//        }
        this.lifePoints = Math.max(0,this.lifePoints-points);
    }
}
