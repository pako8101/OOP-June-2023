package ViceCity.src.main.java.viceCity.models.neighbourhood;

import ViceCity.src.main.java.viceCity.models.players.Player;

import java.util.Collection;

public interface Neighbourhood {
    void action(Player mainPlayer, Collection<Player> civilPlayers);
}
