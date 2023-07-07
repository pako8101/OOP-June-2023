package ViceCity.src.main.java.viceCity.core;

import ViceCity.src.main.java.viceCity.common.ConstantMessages;
import ViceCity.src.main.java.viceCity.core.interfaces.Controller;
import ViceCity.src.main.java.viceCity.models.guns.Gun;
import ViceCity.src.main.java.viceCity.models.guns.Rifle;
import ViceCity.src.main.java.viceCity.models.neighbourhood.GangNeighbourhood;
import ViceCity.src.main.java.viceCity.models.neighbourhood.Neighbourhood;
import ViceCity.src.main.java.viceCity.models.players.CivilPlayer;
import ViceCity.src.main.java.viceCity.models.players.MainPlayer;
import ViceCity.src.main.java.viceCity.models.players.Player;

import java.util.*;
import java.util.stream.Collectors;

import static ViceCity.src.main.java.viceCity.common.ConstantMessages.*;

public class ControllerImpl implements Controller {
    private static final int TOMMY_MAX_HEALTH = 100;
    private static final int CIVIL_MAX_HEALTH = 50;
    private Player tommyVercetti;
    private Map<String,Player> civilPlayers;
    private Neighbourhood neighbourhood;
private Deque<Gun>guns;
    public ControllerImpl() {
        this.tommyVercetti = new MainPlayer();
        civilPlayers =new LinkedHashMap<>();
        guns = new ArrayDeque<>();
        neighbourhood = new GangNeighbourhood();
    }

    @Override
    public String addPlayer(String name) {
        Player playerToAdd = new CivilPlayer(name);
        civilPlayers.put(name,playerToAdd);
        return String.format(ConstantMessages.PLAYER_ADDED,playerToAdd);
    }

    @Override
    public String addGun(String type, String name) {
        Gun gun ;
        switch (type){
            case "Rifle":
                gun = new Rifle(name);
                break;
            case "Pistol":
                gun = new Rifle(name);
                break;
            default:
                return GUN_TYPE_INVALID;
        }
        guns.offer(gun);
        return String.format(GUN_ADDED,name,type);
    }

    @Override
    public String addGunToPlayer(String playerName) {
        Gun gunToAdd = guns.poll();
       if (gunToAdd==null){
           return GUN_QUEUE_IS_EMPTY;
       }
       if (playerName.equals("Vercetti")){
           tommyVercetti.getGunRepository().add(gunToAdd);
           return String.format(GUN_ADDED_TO_MAIN_PLAYER,gunToAdd.getName(),tommyVercetti.getName());
       }
       Player civilPlayer = civilPlayers.get(playerName);
       if (civilPlayer==null){
           return CIVIL_PLAYER_DOES_NOT_EXIST;
       }else {
           civilPlayer.getGunRepository().add(gunToAdd);
           return String.format(GUN_ADDED_TO_CIVIL_PLAYER,gunToAdd.getName(),playerName);
       }

    }

    @Override
    public String fight() {
        neighbourhood.action(tommyVercetti,civilPlayers.values());
        if (tommyVercetti.getLifePoints() == TOMMY_MAX_HEALTH&&
        civilPlayers.values().stream().allMatch(player -> player.getLifePoints()==CIVIL_MAX_HEALTH)){
                return FIGHT_HOT_HAPPENED;
            }
        List<Player> deadPlayers = civilPlayers.values()
                .stream().filter(player -> !player.isAlive())
                .collect(Collectors.toList());

        StringBuilder output = new StringBuilder(FIGHT_HAPPENED)
                .append(System.lineSeparator())
                .append(String.format(MAIN_PLAYER_LIVE_POINTS_MESSAGE,tommyVercetti.getLifePoints()))
                .append(System.lineSeparator())
                .append(String.format(MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE, deadPlayers.size()))
                .append(System.lineSeparator())
                .append(String.format(CIVIL_PLAYERS_LEFT_MESSAGE,civilPlayers.size() - deadPlayers.size()));
        for (Player deadPlayer : deadPlayers) {
            civilPlayers.remove(deadPlayer.getName());
        }
        return output.toString();
    }
}
