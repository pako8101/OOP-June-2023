package InterfacesExercises.MilitaryElite.models;

import InterfacesExercises.MilitaryElite.Interfaces.Commando;
import InterfacesExercises.MilitaryElite.Interfaces.Mission;

import java.util.Set;

public class CommandoImpl extends SpecialisedSoldierImpl implements Commando {
    private Set<Mission> missions;

    public CommandoImpl(String id, String firstName, String lastname) {
        super(id, firstName, lastname);
        this.missions = missions;
    }

    @Override
    public Set<Mission> getMissions() {
        return this.missions;
    }


    @Override
    public Set<Mission> missions() {
        return this.missions;
    }

    @Override
    public String toString() {
        return "CommandoImpl{" +
                "missions=" + missions +
                '}';
    }
}
