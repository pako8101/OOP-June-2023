package PolymorphWildFArm;

import java.text.DecimalFormat;

public abstract class Mammal extends Animal {
    private String livingRegion;

    public Mammal(String animalName, String animalType, double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight);
        this.livingRegion = livingRegion;
    }

    public String getLivingRegion() {
        return livingRegion;
    }

//    @Override
//    public void eat(Food food) {
//        if (food instanceof Vegetable) {
//            this.setFoodEaten(food.getQuantity());
//        } else {
//            String type = this.getClass().getSimpleName();
//            if (this instanceof Mouse) {
//                type = "Mice";
//            }
//            System.out.println((type.equalsIgnoreCase("mouse") ? "Mice" : type) + "s are not eating that type of food!");
//        }
  //  }

    protected void setLivingRegion(String livingRegion) {
        this.livingRegion = livingRegion;
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        // "{AnimalType} [{AnimalName}, {AnimalWeight}, {AnimalLivingRegion}, {FoodEaten}]".
        String template = "%s[%s, %s, %s, %d]";
        return String.format(template,
                this.getAnimalType(),
                this.getAnimalName(),
                decimalFormat.format(this.getAnimalWeight()),
                this.getLivingRegion(),
                this.getFoodEaten());
    }
}

