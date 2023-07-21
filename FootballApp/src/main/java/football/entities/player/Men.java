package football.entities.player;

public class Men extends BasePlayer{
    private static final double kg = 85.50;
    public Men(String name, String nationality, int strength) {
        super(name, nationality, kg, strength);
    }

    @Override
    public void stimulation() {
        setStrength(getStrength()+145);
    }
}
