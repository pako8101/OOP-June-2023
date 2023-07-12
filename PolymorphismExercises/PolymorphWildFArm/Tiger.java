package PolymorphWildFArm;

public class Tiger extends Felime {
    public Tiger(String animalName, String animalType, double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    public void makeSound() {
        System.out.println("ROAAR!!!");
    }

    @Override
    public void eat(Food food) {
        if (food instanceof Vegetable) {
         throw new IllegalArgumentException("Tigers are not eating that type of food!");

        }
        super.eat(food);
    }
}
