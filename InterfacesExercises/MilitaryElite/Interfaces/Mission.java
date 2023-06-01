package InterfacesExercises.MilitaryElite.Interfaces;

import InterfacesExercises.MilitaryElite.Enums.MissionState;

public interface Mission {
    String getCodename();
    MissionState getState();
    void  completeMission();
}
