package farmville;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FarmvilleTests {
    private Animal chicken;
    private Animal lion;
    private Farm testFarm;

    @Before
    public void setup() {
        chicken = new Animal("chicken", 1);
        lion = new Animal("lion", 10);
        testFarm = new Farm("Softuni", 15);
    }

    @Test
    public void testShouldCreateFarmSuccess() {
        Farm farm = new Farm("Softuni", 15);
        assertEquals("Softuni", farm.getName());
        assertEquals(15, farm.getCapacity());
        assertEquals(0, farm.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void testCreateShouldThrowExceptionIfNameIsNull() {
        new Farm(null, 3);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateShouldThrowExceptionIfNameIsEmpty() {
        new Farm("", 3);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateShouldThrowExceptionIfNameIsWhiteSpice() {
        new Farm(" ", 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateShouldThrowExceptionIfCapacityIsNegative() {
        new Farm("Boika", -3);
    }

    @Test
    public void testAddSuccess() {
        testFarm.add(lion);
        assertEquals(1, testFarm.getCount());

        testFarm.add(chicken);
        assertEquals(2, testFarm.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldThrowIfNoCapacity() {
        Farm farm = new Farm("Softuni", 1);
        farm.add(lion);
        assertEquals(1, farm.getCount());
        farm.add(chicken);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldThrowIfDuplicate() {
        testFarm.add(lion);
        testFarm.add(lion);
    }
    @Test
    public void removeAnimal(){
        testFarm.add(lion);
        assertTrue(testFarm.remove(lion.getType()));
        assertEquals(0,testFarm.getCount());

    }
    @Test
    public void notRemoveAnimal(){
        testFarm.add(lion);
        assertFalse(testFarm.remove(chicken.getType()));
        assertEquals(1,testFarm.getCount());

    }
}
