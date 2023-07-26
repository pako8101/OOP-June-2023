package robots;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ServiceTests {
    @Test(expected = NullPointerException.class)
    public void testSetNameOnNull(){
        new Service(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameOnWhitespaces(){
        new Service("      ", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCapacityOnLessThanZero(){
        new Service("Mouse", -3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCatOnFullHouse(){
        Service service = new Service("ServiceRobot", 1);
        service.add(new Robot("Robot"));
        service.add(new Robot("Robot1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveUnknownRobot(){
        Service house = new Service("ServiceRobot", 1);
        house.remove("robot");
    }

    @Test
    public void testGetName(){
        Service service = new Service("ServiceRobot", 1);
        assertEquals("ServiceRobot", service.getName());
    }

    @Test
    public void testGetCapacity(){
        Service service = new Service("ServiceRobot", 6);
        assertEquals(6, service.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCatForSaleUnknownCat(){
        Service service = new Service("ServiceRobot", 1);
        service.forSale("Robot");
    }

    @Test
    public void testCatForSaleHungry(){
        Service service = new Service("ServiceRobot", 1);
        Robot robot = new Robot("robot");
        service.add(robot);
        service.forSale("robot");

        assertFalse(robot.isReadyForSale());
    }

    @Test
    public void testGetCountAfterAdding(){
        Service house = new Service("ServiceRobot", 1);
        Robot robot = new Robot("Robot");
        house.add(robot);

        assertEquals(1, house.getCount());
    }

    @Test
    public void testGetCountAfterRemoving(){
        Service house = new Service("ServiceRobot", 3);
        Robot kitten = new Robot("Kitten");
        Robot kitten2 = new Robot("Kitten2");
        Robot kitten3 = new Robot("Kitten3");
        house.add(kitten);
        house.add(kitten2);
        house.add(kitten3);
        house.remove("Kitten2");
        house.remove("Kitten3");

        assertEquals(1, house.getCount());
    }

    @Test
    public void testStatistics(){
        Service house = new Service("ServiceRobot", 3);
        house.add(new Robot("Kitten"));
        house.add(new Robot("Kitten2"));
        house.add(new Robot("Kitten3"));

        String expected = "The robot Kitten, Kitten2, Kitten3 is in the service ServiceRobot!";

        assertEquals(expected, house.report());
    }
}
