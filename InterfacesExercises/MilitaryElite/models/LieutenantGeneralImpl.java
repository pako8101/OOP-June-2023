package InterfacesExercises.MilitaryElite.models;

import InterfacesExercises.MilitaryElite.Interfaces.LieutenantGeneral;
import InterfacesExercises.MilitaryElite.Interfaces.Private;

import java.util.Set;

public class LieutenantGeneralImpl extends PrivateImpl implements LieutenantGeneral {
    private Set<Private> privates;

    public LieutenantGeneralImpl(String id, String firstName, String lastname,double salary) {
        super(id, firstName, lastname,salary);
        this.privates= getPrivates();
    }

    @Override
    public Set<Private> getPrivates() {
        return this.privates;
    }

    @Override
    public String toString() {
        return "LieutenantGeneralImpl{" +
                "privates=" + privates +
                '}';
    }
}
