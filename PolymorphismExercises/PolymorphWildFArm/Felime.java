package PolymorphWildFArm;

public class Felime extends Mammal{
    public Felime(String animalName, String animalType, double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    public void makeSound() {

    }

    @Override
    public void eat(Food food) {

    }
}
