package InterfacesExercises.MilitaryElite.models;

import InterfacesExercises.MilitaryElite.Enums.MissionState;
import InterfacesExercises.MilitaryElite.Interfaces.Mission;

public class MissionImpl  implements Mission {

    private String codename;
    private MissionState missionState;

    public MissionImpl(String codename, MissionState missionState) {
        this.codename = codename;
        this.missionState = missionState;
    }

    @Override
    public String getCodename() {
        return this.codename;
    }

    @Override
    public MissionState getState() {
        return this.getState();
    }

    @Override
    public void completeMission() {
this.missionState= missionState.finished;
    }

    @Override
    public String toString() {
        return "MissionImpl{" +
                "codename='" + codename + '\'' +
                ", missionState=" + missionState +
                '}';
    }
}

