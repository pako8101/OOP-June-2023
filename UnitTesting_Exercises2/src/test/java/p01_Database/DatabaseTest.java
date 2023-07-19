package p01_Database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {
    public Database database;
    private static final Integer[] NUMBERS = {7, 45, 34, 12, 98, 23};

    @Before
    public void prepareDatabase() throws OperationNotSupportedException {
        database = new Database(NUMBERS);
    }


    @Test
    public void testConstructorHasCreatedValidObject() {
        Integer[] elements = database.getElements();

        Assert.assertArrayEquals("Arrays are not the same!", elements, NUMBERS);

        Assert.assertEquals("Count of elements is incorrect!", elements.length, NUMBERS.length);

        for (int index = 0; index < elements.length; index++) {
            Assert.assertEquals("Arrays are not the same!", elements[index], NUMBERS[index]);

        }
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowWheMoreThenSixteenElements() throws OperationNotSupportedException {
        Integer[] numbers = new Integer[17];
        new Database(numbers);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowWheLessThenOneElement() throws OperationNotSupportedException {
        Integer[] numbers = new Integer[0];
        new Database(numbers);
    }

    @Test
    public void testAddShouldAddElement() throws OperationNotSupportedException {
        database.add(67);
        Integer[] elements = database.getElements();
        Assert.assertEquals("Invalid add operation!", elements.length, NUMBERS.length + 1);
        Assert.assertEquals(elements[elements.length - 1], Integer.valueOf(67));
    }
    @Test(expected = OperationNotSupportedException.class)
    public void testAddShouldThrowNullParam() throws OperationNotSupportedException {
        database.add(null);
    }
    @Test
    public void testRemoveLastElement() throws OperationNotSupportedException {
        database.remove();
        Integer[] elements = database.getElements();
        Assert.assertEquals(elements.length,NUMBERS.length-1);
        Assert.assertEquals(elements[elements.length-1],Integer.valueOf(98));
    }
    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveThrowEmptyDatabase() throws OperationNotSupportedException {
        for (int i = 0; i < NUMBERS.length; i++) {
            database.remove();
        }
        database.remove();
    }
}
