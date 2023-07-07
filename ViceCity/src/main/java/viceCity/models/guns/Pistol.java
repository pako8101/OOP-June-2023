package ViceCity.src.main.java.viceCity.models.guns;

public class Pistol extends BaseGun {
    private static final int PISTOL_BULLETS_PER_BARREL = 10;
    private static final int PISTOL_TOTAL_BULLETS = 100;
    private static final int BULLETS_PER_SHOT = 1;

    public Pistol(String name) {
        super(name, PISTOL_BULLETS_PER_BARREL, PISTOL_TOTAL_BULLETS);
    }

    @Override
    public int fire() {

        if (getBulletsPerBarrel() == 0 && getTotalBullets()>0) {
            reload();
        }
        if (getBulletsPerBarrel() > 0) {
            setBulletsPerBarrel(getBulletsPerBarrel() - getBulletsPerShot());

        }
        return getBulletsPerShot();
    }

    @Override
    protected int getBulletsPerShot() {
        return BULLETS_PER_SHOT;
    }

    private void reload() {
        setTotalBullets(getTotalBullets() - PISTOL_BULLETS_PER_BARREL);
        setBulletsPerBarrel(PISTOL_BULLETS_PER_BARREL);

    }
}
