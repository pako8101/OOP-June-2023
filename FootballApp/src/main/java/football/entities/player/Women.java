package football.entities.player;

public class Women extends BasePlayer {
    private static final double kg = 60.00;

    public Women(String name, String nationality, int strength) {
        super(name, nationality, kg, strength);
    }

    @Override
    public void stimulation() {
       setStrength(getStrength()+115);
    }
}
