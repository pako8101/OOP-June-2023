package spaceStation.models.astronauts;

public class Meteorologist extends BaseAstronaut{
    private static final int oxygen = 90;

    public Meteorologist(String name) {
        super(name, oxygen);
    }
}
