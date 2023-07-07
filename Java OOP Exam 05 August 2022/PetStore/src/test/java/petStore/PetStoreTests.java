package petStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PetStoreTests {
    private static final String SPECIE = "Dog";
    private static final int MAX_KG = 30;
    private static final double PRICE = 100;
    private PetStore petStore;
    private Animal animal;

    @Before
    public void setUp() {
        this.petStore = new PetStore();
        this.animal = new Animal(SPECIE, MAX_KG, PRICE);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_GetAnimals_ShouldReturn_UnmodifiableList() {
        List<Animal> animals = petStore.getAnimals();
        animals.remove(1);

    }

    @Test
    public void test_GetCount_ShouldReturnZero_WhenEmpty_And_One_When_SingleAnimalAdded() {
        Assert.assertEquals(0, petStore.getCount());
        petStore.addAnimal(animal);
        Assert.assertEquals(1, petStore.getCount());
    }
    @Test
    public void test_findAllAnimalsWithMaxKilograms_ShouldReturn_EmptyList_WhenNoSuchAnimlas(){
        petStore.addAnimal(animal);
        List<Animal>animals = petStore.findAllAnimalsWithMaxKilograms(MAX_KG+10);
        Assert.assertTrue(animals.isEmpty());

    }
    @Test
    public void test_findAllAnimalsWithMaxKilograms_ShouldReturn_OnlyThoseHeavier(){
        petStore.addAnimal(animal);
        petStore.addAnimal(new Animal("test",MAX_KG-2,PRICE));
        List<Animal>animals = petStore.findAllAnimalsWithMaxKilograms(MAX_KG-1);
        Assert.assertEquals(1,animals.size());
        Assert.assertEquals(animal.getSpecie(),animals.get(0).getSpecie());

    }
    @Test(expected = IllegalArgumentException.class)
    public void test_addAnimal_ShouldThrow_WhenAnimalIsNUll(){
        petStore.addAnimal(null);
    }
    @Test
    public void test_addAnimal_ShouldIncreaseCount(){
        petStore.addAnimal(animal);
        Assert.assertEquals(1,petStore.getCount());
    }
@Test
    public void test_GetTheMostExpensive_Animal_Should_Return_Null_WhenEmpty(){
        Animal animal = petStore.getTheMostExpensiveAnimal();
        Assert.assertNull(animal);
}
    @Test
    public void test_GetTheMostExpensive_Animal_Should_Return_MostExpensiveOne(){
        petStore.addAnimal(animal);
        petStore.addAnimal(new Animal(SPECIE,MAX_KG,PRICE-10));
        Animal animal1 = petStore.getTheMostExpensiveAnimal();
        Assert.assertEquals(animal.getPrice(),animal1.getPrice(),0.00);
    }
    @Test
    public void test_FindAllAnimalBySpecie_ShouldReturn_EmptyList_When_NoAnimals(){
        List<Animal>animals = petStore.findAllAnimalBySpecie(SPECIE);
        Assert.assertTrue(animals.isEmpty());
    }
    @Test
    public void test_FindAllAnimalBySpecie_ShouldReturn_OnlyTheRequired_Species(){
        petStore.addAnimal(animal);
        petStore.addAnimal(new Animal("Goat", MAX_KG,PRICE));
        List<Animal>animals = petStore.findAllAnimalBySpecie(SPECIE);
        Assert.assertEquals(1, animals.size());
        Assert.assertEquals(SPECIE,animals.get(0).getSpecie());
    }
}

