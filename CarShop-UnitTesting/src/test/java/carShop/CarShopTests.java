package carShop;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CarShopTests {
    private CarShop carShop;
    private Car car1;
    private Car car2;
    private Car car3;

    @Before
    public void setUp() {
        this.carShop = new CarShop();
       this.car1 = new Car("porshe", 400, 5000);
        this.car2 = new Car("audi", 500, 7000);
        this.car3 = new Car("honda", 600, 3000);
    }


    @Test
    public void testCreateShopAndGetCount() {

        carShop.add(car1);
        assertEquals(1, carShop.getCount());
    }
    @Test(expected = NullPointerException.class)
    public void testCreateCarNullThrowException() {

        carShop.add(null);
    }
    @Test
    public void testRemoveCar() {

        carShop.add(car1);
        carShop.add(car2);
        assertEquals(2, carShop.getCount());
        carShop.remove(car2);
        assertEquals(1, carShop.getCount());
    }
    @Test
    public void testRemoveNonExistingCar() {

        carShop.add(car1);
        carShop.add(car2);
        assertEquals(2, carShop.getCount());
        carShop.remove(car3);
        assertEquals(2, carShop.getCount());
    }
    @Test
    public void testGetCar() {
        carShop.add(car1);
        List<Car> cars = carShop.getCars();
        assertEquals(1, carShop.getCount());
        assertEquals(car1.getModel(), cars.get(0).getModel());
    }
    @Test
    public void testFindCarsWithMAxHorsePower() {
        carShop.add(car1);
        carShop.add(car2);
        carShop.add(car3);
        List<Car> carsWithMaxHorse = carShop.findAllCarsWithMaxHorsePower(550);
        assertEquals(car3.getHorsePower(), carsWithMaxHorse.get(0).getHorsePower());
    }
    @Test
    public void testFindMostLuxuryCar() {
        carShop.add(car1);
        carShop.add(car2);
        carShop.add(car3);
       Car carWithMostLux = carShop.getTheMostLuxuryCar();
        assertEquals(car2.getModel(), carWithMostLux.getModel());
    }
    @Test
    public void testFindAllCarByModel(){
        carShop.add(car1);
        carShop.add(car1);
        carShop.add(car3);
        List<Car> carsByModel = carShop.findAllCarByModel(car1.getModel());
        assertEquals(2, carsByModel.size());

    }
}

