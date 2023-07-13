package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.Collection;
import java.util.List;

public class MissionImpl implements Mission{
    @Override
    public void explore(Planet planet, List<Astronaut> astronauts) {
        for (int index = 0; index < astronauts.size(); index++) {
            Astronaut astronaut1 = astronauts.get(index);
            for (int item = 0; item < planet.getItems().size(); item++) {
                String currentItem = planet.getItems().get(item);
                astronaut1.getBag().getItems().add(currentItem);
                planet.getItems().remove(currentItem);
                item--;
                astronaut1.breath();
                if (!astronaut1.canBreath()){
                    astronauts.remove(astronaut1);
                    item--;
                    break;
                }
            }

        }
    }
}
