package InterfacesExercises.MilitaryElite.models;

import InterfacesExercises.MilitaryElite.Enums.Corps;
import InterfacesExercises.MilitaryElite.Interfaces.Mission;

import java.util.Set;

public abstract class SpecialisedSoldierImpl extends SoldierImpl {
    private Corps corps;

    public Corps getCorps() {
        return this.corps;
    }

    public SpecialisedSoldierImpl(String id, String firstName, String lastname) {
        super(id,firstName,lastname);

    }

    @Override
    public String toString() {
        return "SpecialisedSoldierImpl{" +
                "corps=" + getCorps().name() +
                '}';
    }

    public abstract Set<Mission> getMissions();
}
