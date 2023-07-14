package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GarageTests {
    private final static int EXAMPLE_MAX_SPEED = 250;
    private Garage garage;
    private Car porsche;
    private Car mercedes;
    private Car audi;

    @Before
    public void setup() {
        this.garage = new Garage();
        porsche = new Car("Porshe", 300, 1200);
        mercedes = new Car("Mercedes", 200, 1500);
        audi = new Car("Audi", 220, 1000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCarShouldThrow() {
        garage.addCar(null);
    }

    @Test
    public void testAddCarSuccessfully() {
        garage.addCar(porsche);
        Assert.assertEquals(1, garage.getCount());
        garage.addCar(mercedes);
        Assert.assertEquals(2, garage.getCount());
    }

    @Test
    public void testGetCarsSuccessfully() {
        garage.addCar(porsche);
        List<Car> carsInGarage = garage.getCars();
        Assert.assertEquals(1, garage.getCount());
        Assert.assertEquals(porsche.getBrand(), carsInGarage.get(0).getBrand());

    }

    @Test
    public void testGetFastestCar() {
        garage.addCar(porsche);
        garage.addCar(mercedes);
        garage.addCar(audi);
        List<Car> carsWithSpeedAboveValue = garage.findAllCarsWithMaxSpeedAbove(EXAMPLE_MAX_SPEED);
        Assert.assertEquals(porsche.getBrand(), carsWithSpeedAboveValue.get(0).getBrand());

    }

    @Test
    public void testGetTheMostExpensiveCar() {
        garage.addCar(porsche);
        garage.addCar(mercedes);
        garage.addCar(audi);
        Car mostExpensiveCar = garage.getTheMostExpensiveCar();
        Assert.assertEquals(mercedes.getBrand(), mostExpensiveCar.getBrand());

    }

    @Test
    public void testFindAllCarsByBrand() {
        garage.addCar(porsche);
        garage.addCar(porsche);
        garage.addCar(audi);
        List<Car> cars = garage.findAllCarsByBrand(porsche.getBrand());
        Assert.assertEquals(2,cars.size());
    }
}