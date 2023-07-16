package ViceCity.src.main.java.viceCity.models.guns;

public class Rifle extends BaseGun{
    private static final int RIFLE_BULLETS_PER_BARREL = 50;
    private static final int RIFLE_TOTAL_BULLETS = 500;
    private static final int BULLETS_PER_SHOT = 5;
    public Rifle(String name) {
        super(name, RIFLE_BULLETS_PER_BARREL, RIFLE_TOTAL_BULLETS);
    }
    @Override
    public int fire() {
        if (getBulletsPerBarrel() == 0 && getTotalBullets() > 0) {
            reload();
        }

            setBulletsPerBarrel(getBulletsPerBarrel() - getBulletsPerShot());


        return getBulletsPerShot();
}

    @Override
    protected int getBulletsPerShot() {
        return BULLETS_PER_SHOT;
    }

    private void reload() {
        setTotalBullets(getTotalBullets() - RIFLE_BULLETS_PER_BARREL);
        setBulletsPerBarrel(RIFLE_BULLETS_PER_BARREL);

    }
}
