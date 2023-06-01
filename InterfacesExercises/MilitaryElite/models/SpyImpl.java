package InterfacesExercises.MilitaryElite.models;

import InterfacesExercises.MilitaryElite.Interfaces.Spy;

public class SpyImpl extends SoldierImpl implements Spy {
private String codeNumber;

    public SpyImpl(String id, String firstName, String lastname, String codeNumber) {
        super(id, firstName, lastname);
        this.codeNumber = codeNumber;

    }

    @Override
    public String getColdNumber() {
        return this.codeNumber;
    }

    @Override
    public String toString() {
        return "SpyImpl{" +
                "codeNumber='" + codeNumber + '\'' +
                '}';
    }
}
