package ViceCity.src.main.java.viceCity.models.players;



public class CivilPlayer extends BasePlayer {
    private static final int MAIN_LIFE_POINTS = 50;

    public CivilPlayer(String name) {
        super(name, MAIN_LIFE_POINTS);
    }

}
