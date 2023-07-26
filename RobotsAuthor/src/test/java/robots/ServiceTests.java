package robots;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceTests {
    private Robot robot1;
    private Robot robot2;
    private Robot robot3;
    private Service service;

    @Before
    public void setUp() {

        this.robot1 = new Robot("R1");
        this.robot2 = new Robot("R2");
        this.robot3 = new Robot("R3");
        this.service = new Service("work", 15);
    }

    @Test
    public void testGetNameOfService() {
        this.service.add(robot1);
        assertEquals("work", service.getName());

    }

    @Test
    public void testGetNameOfRobot() {
        assertEquals("R1", robot1.getName());
    }

    @Test(expected = NullPointerException.class)
    public void testGetNameOfRobotIfNullThrow() {
        Service service1 = new Service(null, 1);

    }

    @Test(expected = NullPointerException.class)
    public void testGetNameOfRobotIsEmptyThrow() {
        Service service1 = new Service("      ", 1);

    }
    @Test(expected = IllegalArgumentException.class)
    public void testRobotForSaleUnknownRobot(){
        Service service = new Service("ServiceRobot", 1);
        service.forSale("Robot");
    }
    @Test
    public void testRobotForSaleIfNot(){
        Service service = new Service("ServiceRobot", 1);
        Robot robot = new Robot("robot");
        service.add(robot);
        service.forSale("robot");

        assertFalse(robot.isReadyForSale());
    }
    @Test
    public void testGetCapacity() {
        assertEquals(15, service.getCapacity());
    }

    @Test
    public void testGetCountOfRobots() {
        this.service.add(robot1);
        this.service.add(robot2);
        assertEquals(2, service.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCapacityOnLessThanZero(){
        new Service("Robot", -3);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddRobotOnFullHouse(){
        Service service = new Service("ServiceRobot", 1);
        service.add(new Robot("Robot"));
        service.add(new Robot("Robot1"));
    }
    @Test
    public void testGetCountOfRobotsAfterRemove() {
        this.service.add(robot1);
        this.service.add(robot2);
        service.remove("R1");
        assertEquals(1, service.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddRobotThrowIfCapacityIsNotEnough() {
        Service service1 = new Service("mos", 1);
        service1.add(robot1);
        service1.add(robot2);

    }

    @Test
    public void testRemoveOfRobots() {
        this.service.add(robot1);
        this.service.add(robot2);
        assertEquals(2, service.getCount());
        this.service.remove("R1");
        assertNotEquals(service.getCount(), 2);
    }
    @Test
    public void testRemoveOfRobotsResultEmptyService() {
        this.service.add(robot1);
        this.service.add(robot2);
        assertEquals(2, service.getCount());
        this.service.remove("R1");
        this.service.remove("R2");
        assertEquals(service.getCount(), 0);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveOfRobotIfNullThrow() {
        this.service.remove(null);

    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveUnknownRobot(){
        Service serviceRobot = new Service("ServiceRobot", 1);
        serviceRobot.remove("robot");
    }
    @Test
    public void testRobotForSale() {
        this.service.add(robot3);
        this.service.add(robot2);
        assertEquals(2, service.getCount());
        service.forSale("R3");
        robot3.setReadyForSale(robot2.isReadyForSale());
        assertTrue(robot3.isReadyForSale());

    }
    @Test(expected = IllegalArgumentException.class)
    public void testReadyForSaleOfRobotIfNullThrow() {
        this.service.forSale(null);

    }
    @Test
    public void testReport(){
        service.add(robot1);
        service.add(robot2);
        service.add(robot3);
        assertEquals("The robot R1, R2, R3 is in the service work!",service.report());
    }

}
