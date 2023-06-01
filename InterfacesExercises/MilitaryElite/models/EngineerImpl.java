package InterfacesExercises.MilitaryElite.models;

import InterfacesExercises.MilitaryElite.Interfaces.Engineer;
import InterfacesExercises.MilitaryElite.Interfaces.Mission;
import InterfacesExercises.MilitaryElite.Interfaces.Repair;

import java.util.Set;

public class EngineerImpl extends SpecialisedSoldierImpl implements Engineer {
    private Set<Repair> repairs;
    private Set<Mission> missions;

    public EngineerImpl(String id, String firstName, String lastname) {
        super(id, firstName, lastname);
        this.missions = getMissions();
        this.repairs = getRepairs();
    }

    @Override
    public Set<Repair> getRepairs() {
        return this.repairs;
    }

    @Override
    public Set<Mission> getMissions() {
        return this.missions;
    }

    @Override
    public String toString() {
        return "EngineerImpl{" +
                "repairs=" + this.repairs +
                '}';
    }
}
