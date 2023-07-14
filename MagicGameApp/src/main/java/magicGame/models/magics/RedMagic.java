package magicGame.models.magics;

public class RedMagic extends MagicImpl{

    public RedMagic(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
    return doFire(1);
    }
}
