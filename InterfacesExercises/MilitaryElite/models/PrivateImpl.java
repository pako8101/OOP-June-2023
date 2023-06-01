package InterfacesExercises.MilitaryElite.models;

import InterfacesExercises.MilitaryElite.Interfaces.Private;

public class PrivateImpl extends SoldierImpl implements Private {
private double salary;
    public PrivateImpl(String id, String firstName, String lastname,double salary) {
        super(id, firstName, lastname);
        this.salary = salary;
    }

    @Override
    public Double getSalary() {
        return this.salary=salary;
    }

    @Override
    public String toString() {
        return "PrivateImpl{" +
                "salary=" + salary +
                '}';
    }
}
