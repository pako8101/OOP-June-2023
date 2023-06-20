package EncapsulationPizzaCalories;

public class Dough {
    private String flourType;
    private String bakingTechnique;
    private double weight;

    public Dough(String flourType, String bakingTechnique, double weight) {
        setFlourType(flourType);
        setBakingTechnique(bakingTechnique);
        setWeight(weight);
    }

    public void setFlourType(String flourType) {
        if (flourType.equals("White") || flourType.equals("Wholegrain")) {
            this.flourType = flourType;
        } else {
            throw new IllegalArgumentException("Invalid type of dough.");
        }

    }

   private void setBakingTechnique(String bakingTechnique) {
        switch (bakingTechnique) {
            case "Crispy":
            case "Chewy":
            case "Homemade":
                this.bakingTechnique = bakingTechnique;
                break;
            default:
                throw new IllegalArgumentException("Invalid type of dough.");
        }

    }

    private void setWeight(double weight) {
        if (weight > 0 && weight <= 200) {
            this.weight = weight;
        } else {
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }

    }

    public double calculateCalories() {
        double florTypeCoefficient = 0;
        if (flourType.equals("White")) {
            florTypeCoefficient = 1.5;
        } else if (flourType.equals("Wholegrain")) {
            florTypeCoefficient = 1;

        }
        double bakingTechniqueCoefficient = 0;
        if (bakingTechnique.equals("Crispy")) {
            bakingTechniqueCoefficient = 0.9;
        } else if (bakingTechnique.equals("Chewy")) {
            bakingTechniqueCoefficient = 1.1;

        } else if (bakingTechnique.equals("Homemade")) {
            bakingTechniqueCoefficient = 1;

        }
//        •	White – 1.5;
//•	Wholegrain – 1.0;
//•	Crispy – 0.9;
//•	Chewy – 1.1;
//•	Homemade – 1.0;

        return 2 * this.weight * florTypeCoefficient * bakingTechniqueCoefficient;
    }
}
