package InterfacesExercises.BirthdayCelebrations;

public class Robot implements Identifiable{
    private String id;
    private String Model;

    public Robot(String id, String Model) {
        this.id = id;
        this.Model = Model;

    }

    @Override
    public String getId() {
        return this.id;
    }

    public String getModel() {
        return this.Model;
    }
}
