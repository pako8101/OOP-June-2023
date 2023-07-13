package spaceStation.core;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static spaceStation.common.ConstantMessages.*;
import static spaceStation.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private AstronautRepository astronautRepository;
    private PlanetRepository planetRepository;
    private int exploredPlanets;

    public ControllerImpl() {
        this.astronautRepository = new AstronautRepository();
        this.planetRepository = new PlanetRepository();
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        Astronaut astronaut;
        switch (type) {
            case "Biologist":
                astronaut = new Biologist(astronautName);
                break;
            case "Geodesist":
                astronaut = new Geodesist(astronautName);
                break;
            case "Meteorologist":
                astronaut = new Meteorologist(astronautName);
                break;
            default:
                throw new IllegalArgumentException(ASTRONAUT_INVALID_TYPE);
        }
        this.astronautRepository.add(astronaut);
        return String.format(ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        List<String> itemList = Arrays.asList(items);
        Planet planet = new PlanetImpl(planetName);
        planet.getItems().addAll(itemList);
        this.planetRepository.add(planet);
        return String.format(PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        //  this.astronautRepository.getModels().stream().noneMatch(as->as.getName().equals(astronautName));
        Astronaut astronaut = astronautRepository.findByName(astronautName);
        if (astronaut == null) {
            throw new IllegalArgumentException(String.format(ASTRONAUT_DOES_NOT_EXIST, astronautName));
        } else {
            this.astronautRepository.remove(astronaut);
            return String.format(ASTRONAUT_RETIRED, astronautName);
        }

    }

    @Override
    public String explorePlanet(String planetName) {
        List<Astronaut> suitableAstronauts = astronautRepository
                .getModels().stream().filter(as -> as.getOxygen() > 60)
                .collect(Collectors.toList());
        if (suitableAstronauts.isEmpty()) {
            throw new IllegalArgumentException(PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }
        int countBefore = suitableAstronauts.size();
        Mission mission = new MissionImpl();
        Planet planet = this.planetRepository.findByName(planetName);
        mission.explore(planet, suitableAstronauts);
        exploredPlanets++;
        int countAfter = suitableAstronauts.size();
        return String.format(PLANET_EXPLORED, planetName, countBefore - countAfter);
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(REPORT_PLANET_EXPLORED,exploredPlanets)).append(System.lineSeparator());
        sb.append(REPORT_ASTRONAUT_INFO).append(System.lineSeparator());

        this.astronautRepository.getModels().forEach(as->{
            sb.append(String.format(REPORT_ASTRONAUT_NAME,as.getName())).append(System.lineSeparator());
            sb.append(String.format(REPORT_ASTRONAUT_OXYGEN,as.getOxygen())).append(System.lineSeparator());
            if (as.getBag().getItems().isEmpty()){
                sb.append(String.format(REPORT_ASTRONAUT_BAG_ITEMS,"none")).append(System.lineSeparator());
            }else {
                Collection<String> items = as.getBag().getItems();
                sb.append(String.format(REPORT_ASTRONAUT_BAG_ITEMS,String.join(REPORT_ASTRONAUT_BAG_ITEMS_DELIMITER,items))).append(System.lineSeparator());
            }

        });

        return sb.toString();
    }
}
