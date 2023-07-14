package magicGame.models.magics;

import magicGame.common.ExceptionMessages;

import static magicGame.common.ExceptionMessages.*;

public abstract class MagicImpl implements Magic{
    private String name;
    private int bulletsCount;

    public MagicImpl(String name, int bulletsCount) {
        this.setName(name);
        this.setBulletsCount(bulletsCount);
    }

    private void setName(String name) {
        if (name==null|| name.trim().isEmpty()){
            throw new NullPointerException(INVALID_MAGIC_NAME);
        }
        this.name = name;
    }

    private void setBulletsCount(int bulletsCount) {
        if (bulletsCount<=0){
            throw new IllegalArgumentException(INVALID_MAGIC_BULLETS_COUNT);
        }
        this.bulletsCount = bulletsCount;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getBulletsCount() {
        return this.bulletsCount;
    }

    @Override
    public int fire() {
        return 0;
    }
    protected int doFire(int bullets){
        if (this.getBulletsCount()<bullets){
            return 0;
        }
        this.bulletsCount-=bullets;
        return bullets;
    }
}
