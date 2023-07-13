package spaceStation.models.astronauts;

public class Biologist extends  BaseAstronaut{
    private static final int oxygen = 70;
    public Biologist(String name) {
        super(name, oxygen);
    }

    @Override
    public void breath() {
        this.setOxygen(this.getOxygen()-5);
    }
}
