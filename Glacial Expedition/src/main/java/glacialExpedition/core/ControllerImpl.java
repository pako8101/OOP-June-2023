package glacialExpedition.core;

import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.explorers.GlacierExplorer;
import glacialExpedition.models.explorers.NaturalExplorer;
import glacialExpedition.models.mission.Mission;
import glacialExpedition.models.mission.MissionImpl;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.StateRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static glacialExpedition.common.ConstantMessages.*;
import static glacialExpedition.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private ExplorerRepository explorerRepository;
    private StateRepository stateRepository;
    private int exploreStates;

    public ControllerImpl() {
        this.explorerRepository = new ExplorerRepository();
        this.stateRepository = new StateRepository();
        this.exploreStates = 0;
    }

    @Override
    public String addExplorer(String type, String explorerName) {
        Explorer explorer;
        switch (type) {
            case "AnimalExplorer":
                explorer = new AnimalExplorer(explorerName);
                break;
            case "GlacierExplorer":
                explorer = new GlacierExplorer(explorerName);
                break;
            case "NaturalExplorer":
                explorer = new NaturalExplorer(explorerName);
                break;
            default:
                throw new IllegalArgumentException(EXPLORER_INVALID_TYPE);


        }
        explorerRepository.add(explorer);
        return String.format(EXPLORER_ADDED, type, explorerName);

    }

    @Override
    public String addState(String stateName, String... exhibits) {
        State state = new StateImpl(stateName);
//        for (String exhibitToBeAdded : exhibits) {
//            state.getExhibits().add(exhibitToBeAdded);
//        }
        Collections.addAll(state.getExhibits(), exhibits);
        stateRepository.add(state);
        return String.format(STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
        Explorer explorerToRemove = explorerRepository.byName(explorerName);
        if (explorerToRemove == null) {
            throw new IllegalArgumentException(String.format(EXPLORER_DOES_NOT_EXIST, explorerName));
        }
        explorerRepository.remove(explorerToRemove);
        return String.format(EXPLORER_RETIRED, explorerName);
    }

    @Override
    public String exploreState(String stateName) {
        List<Explorer> eligibleExplorers = explorerRepository.getCollection().stream()
                .filter(explorer -> explorer.getEnergy() > 0)
                .collect(Collectors.toList());
        if (eligibleExplorers.isEmpty()) {
            throw new IllegalArgumentException(STATE_EXPLORERS_DOES_NOT_EXISTS);
        }
        State stateToExplore = stateRepository.byName(stateName);
        Mission mission = new MissionImpl();
        mission.explore(stateToExplore, eligibleExplorers);
        long countOfTiredExplorers = eligibleExplorers.stream().filter(explorer -> explorer.getEnergy() == 0).count();
        exploreStates++;
        return String.format(STATE_EXPLORER, stateName, countOfTiredExplorers);
    }

    @Override
    public String finalResult() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(FINAL_STATE_EXPLORED, exploreStates));
        sb.append(System.lineSeparator());
        sb.append(FINAL_EXPLORER_INFO);
        sb.append(System.lineSeparator());
        sb.append(explorerRepository.toString());
        return sb.toString().trim();
//        for (Explorer explorer : explorerRepository.getCollection()) {
//            sb.append(String.format(FINAL_EXPLORER_NAME,explorer.getName()));
//            sb.append(System.lineSeparator());
//            sb.append(String.format(FINAL_EXPLORER_ENERGY,explorer.getEnergy()));
//            sb.append(System.lineSeparator());
//            if (explorer.getSuitcase().getExhibits().isEmpty()){
//                sb.append(String.format(FINAL_EXPLORER_SUITCASE_EXHIBITS,"None"));
//
//            }else {
//
//            }

    }
}
