package spaceStation.repositories;

import spaceStation.models.astronauts.Astronaut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AstronautRepository implements Repository<Astronaut> {
    private List<Astronaut> astronauts;

    public AstronautRepository() {
        this.astronauts = new ArrayList<>();
    }

    @Override
    public List<Astronaut> getModels() {
        return Collections.unmodifiableList(this.astronauts);
    }

    @Override
    public void add(Astronaut model) {
      if (this.astronauts.stream().noneMatch(astronaut -> astronaut.getName().equals(model.getName()))) {
          this.astronauts.add(model);
      }
    }

    @Override
    public boolean remove(Astronaut model) {
        return this.astronauts.remove(model);
    }

    @Override
    public Astronaut findByName(String name) {

        return this.astronauts.stream().filter(astronaut -> astronaut.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
