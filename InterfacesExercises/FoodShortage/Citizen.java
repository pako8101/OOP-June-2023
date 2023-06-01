package InterfacesExercises.FoodShortage;

public class Citizen implements Person,Birthable,Identifiable,Buyer{
private final String name;
private final int age;
private final String id;
private final String birthDate;
private  int food;

    public int getFood() {
        return food;
    }

    public Citizen(String name, int age, String id, String birthDate) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.birthDate = birthDate;
        this.food = 0;

    }

    @Override
    public void buyFood() {
        this.food+=10;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public String getBirthDate() {
        return this.birthDate;
    }

    @Override
    public String getId() {
        return this.birthDate;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
