package InterfacesExercises.MilitaryElite.models;

import InterfacesExercises.MilitaryElite.Interfaces.Repair;

public class RepairImpl implements Repair {
String partName;
private  String hoursWorked;

    public RepairImpl(String partName, String hoursWorked) {
        this.partName = partName;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public String getPartName() {
        return this.partName;
    }

    @Override
    public String getHoursWorked() {
        return this.hoursWorked;
    }

    @Override
    public String toString() {
        return "RepairImpl{" +
                "partName='" + partName + '\'' +
                ", hoursWorked='" + hoursWorked + '\'' +
                '}';
    }
}
