package goldDigger.core;

import goldDigger.models.discoverer.Anthropologist;
import goldDigger.models.discoverer.Archaeologist;
import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.discoverer.Geologist;
import goldDigger.models.operation.Operation;
import goldDigger.models.operation.OperationImpl;
import goldDigger.models.spot.Spot;
import goldDigger.models.spot.SpotImpl;
import goldDigger.repositories.DiscovererRepository;
import goldDigger.repositories.SpotRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static goldDigger.common.ConstantMessages.*;
import static goldDigger.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private DiscovererRepository discovererRepository;
    private SpotRepository spotRepository;
    private Operation operation;
    private static int inspectionCount;

    public ControllerImpl() {
        this.discovererRepository = new DiscovererRepository();
        this.spotRepository = new SpotRepository();
        this.operation = new OperationImpl();
    }

    @Override
    public String addDiscoverer(String kind, String discovererName) {
        Discoverer discoverer = null;
        if (kind.equals("Anthropologist")) {
            discoverer = new Anthropologist(discovererName);
        } else if (kind.equals("Archaeologist")) {
            discoverer = new Archaeologist(discovererName);
        } else if (kind.equals("Geologist")) {
            discoverer = new Geologist(discovererName);
        } else {
            throw new IllegalArgumentException(DISCOVERER_INVALID_KIND);
        }
        this.discovererRepository.add(discoverer);
        return String.format(DISCOVERER_ADDED, kind, discovererName);
    }


    @Override
    public String addSpot(String spotName, String... exhibits) {
        Spot spot = new SpotImpl(spotName);
        List<String> exhibit = Arrays.asList(exhibits);
        spot.getExhibits().addAll(exhibit);
        spotRepository.add(spot);
        return String.format(SPOT_ADDED, spotName);
    }

    @Override
    public String excludeDiscoverer(String discovererName) {
        if (discovererRepository.byName(discovererName) == null) {
            throw new IllegalArgumentException(String.format(DISCOVERER_DOES_NOT_EXIST, discovererName));
        }
        boolean removed = discovererRepository.remove(discovererRepository.byName(discovererName));
        if (!removed) {
            throw new IllegalArgumentException(String.format(DISCOVERER_DOES_NOT_EXIST, discovererName));
        }
        return String.format(DISCOVERER_EXCLUDE, discovererName);


    }

//Discoverer discoverer = null;
//        if (discovererName.equals("Anthropologist")) {
//            discoverer = new Anthropologist(discovererName);
//        } else if (discovererName.equals("Archaeologist")) {
//            discoverer = new Archaeologist(discovererName);
//        } else if (discovererName.equals("Geologist")) {
//            discoverer = new Geologist(discovererName);
//        } else {
//            throw new IllegalArgumentException(String.format(DISCOVERER_DOES_NOT_EXIST,discovererName));
//        }
//        discovererRepository.remove(discoverer);
//        return String.format(DISCOVERER_EXCLUDE,discovererName);


    @Override
    public String inspectSpot(String spotName) {
        List<Discoverer> discoverers = this.discovererRepository.getCollection().stream()
                .filter(d -> d.getEnergy() > 45)
                .collect(Collectors.toList());
        if (discoverers.isEmpty()) {
            throw new IllegalArgumentException(SPOT_DISCOVERERS_DOES_NOT_EXISTS);
        }
        Spot spot = this.spotRepository.byName(spotName);
        Operation operation = new OperationImpl();
        operation.startOperation(spot, discoverers);
        long excluded = discoverers.stream().filter(d -> d.getEnergy() == 0).count();
        inspectionCount++;
        return String.format(INSPECT_SPOT, spotName, excluded);
//        Collection<Discoverer> collection = this.discovererRepository.getCollection();
//        List<Discoverer> goingToMission = new ArrayList<>();
//        List<Discoverer> excluded = new ArrayList<>();
//        for (Discoverer discoverer : collection) {
//            if (discoverer.getEnergy()<=45) {
//                excluded.add(discoverer);
//            } else {
//                goingToMission.add(discoverer);
//            }
//        }
//        if (goingToMission.isEmpty()) {
//            throw new IllegalArgumentException(SPOT_DISCOVERERS_DOES_NOT_EXISTS);
//        }
//        inspectionCount++;
//
//        return String.format(INSPECT_SPOT, spotName, excluded.size());
    }

    @Override
    public String getStatistics() {
        String discoverers = this.discovererRepository.getCollection()
                .stream()
                .map(Discoverer::toString)
                .collect(Collectors.joining("\n"));

        return String.format(FINAL_SPOT_INSPECT, inspectionCount) + System.lineSeparator() +
                FINAL_DISCOVERER_INFO + System.lineSeparator() + discoverers;

    }
}
