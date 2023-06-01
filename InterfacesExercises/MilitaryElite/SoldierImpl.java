package InterfacesExercises.MilitaryElite;

import InterfacesExercises.MilitaryElite.Interfaces.Soldier;

public abstract class SoldierImpl implements Soldier {
    private String id;
    private String firstName;
    private String lastname;

    public SoldierImpl(String id, String firstName, String lastname) {
        this.id = id;
        this.firstName = firstName;
        this.lastname = lastname;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastname;
    }
}
