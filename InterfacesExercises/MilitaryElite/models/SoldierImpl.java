package InterfacesExercises.MilitaryElite.models;

import InterfacesExercises.MilitaryElite.Interfaces.Soldier;

public abstract class SoldierImpl implements Soldier {
private String id;
private String firstName;
private String lastname;
    public SoldierImpl(String id, String firstName, String lastname) {
        this.id = id;
        this.firstName= firstName;
        this.lastname = lastname;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getFirstName() {
        return null;
    }

    @Override
    public String getLastName() {
        return null;
    }

    @Override
    public String toString() {
        return "SoldierImpl{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
