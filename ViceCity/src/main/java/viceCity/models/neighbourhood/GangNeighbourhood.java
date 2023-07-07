package ViceCity.src.main.java.viceCity.models.neighbourhood;

import ViceCity.src.main.java.viceCity.models.guns.Gun;
import ViceCity.src.main.java.viceCity.models.players.Player;
import ViceCity.src.main.java.viceCity.repositories.interfaces.Repository;

import java.util.ArrayDeque;
import java.util.Collection;

public class GangNeighbourhood implements Neighbourhood {


    @Override
    public void action(Player tommyVercetti, Collection<Player> civilPlayers) {
        Repository<Gun> tommyGunRepo = tommyVercetti.getGunRepository();
        ArrayDeque<Gun> tommyGuns = new ArrayDeque<>(tommyGunRepo.getModels());
        ArrayDeque<Player> players = new ArrayDeque<>(civilPlayers);

        Player player = players.poll();
        Gun gun = tommyGuns.poll();
        while (player != null && gun != null) {
            while (gun.canFire() && player.isAlive()) {
                int shot = gun.fire();
                player.takeLifePoints(shot);
            }
            if (gun.canFire()) {
                player = players.poll();
            } else {
                gun = tommyGuns.poll();
            }
        }
        for (Player civilPlayer : players) {
            if (civilPlayer.isAlive()) {
                Repository<Gun> civilPlayerGunRepo = civilPlayer.getGunRepository();
                ArrayDeque<Gun> civilPlayerGuns = new ArrayDeque<>(civilPlayerGunRepo.getModels());
                Gun civilPlayerGun = civilPlayerGuns.poll();
                while (civilPlayerGun != null) {
                    while (civilPlayerGun.canFire() && tommyVercetti.isAlive()) {
                        int shot = civilPlayerGun.fire();
                        tommyVercetti.takeLifePoints(shot);

                    }
                    if (!tommyVercetti.isAlive()) {
                        return;
                    }
                   civilPlayerGun= civilPlayerGuns.poll();
                }
            }

        }
    }
}
