package PolymorphWildFArm;

public abstract class Animal {
    private String animalName;
    private String animalType;
    private double animalWeight;
    private int foodEaten;

    public Animal(String animalName, String animalType, double animalWeight) {
        this.animalName = animalName;
        this.animalType = animalType;
        this.animalWeight = animalWeight;

    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getAnimalType() {
        return animalType;
    }

    protected void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public double getAnimalWeight() {
        return animalWeight;
    }

    protected void setAnimalWeight(double animalWeight) {
        this.animalWeight = animalWeight;
    }

    public int getFoodEaten() {
        return foodEaten;
    }

    protected void setFoodEaten(int foodEaten) {
        this.foodEaten += foodEaten;
    }

    public abstract void makeSound();


    public void eat(Food food){
        this.foodEaten+= food.getQuantity();
    }
}
