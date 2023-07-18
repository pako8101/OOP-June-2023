package archeologicalExcavations;


import org.junit.Test;

import static org.junit.Assert.*;

public class ExcavationTests {
    @Test(expected = NullPointerException.class)
    public void testWhenNameIsnull() {
        new Excavation(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testWhenNameIsEmpty() {
        new Excavation("   ", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWhenNegativeCapacity() {
        new Excavation("soho", -1);
    }

    @Test
    public void testWhenValidZeroCapacity() {
        new Excavation("soho", 0);
    }

    @Test
    public void testWhenValidNameAndCapacity() {
        new Excavation("soho", 5);
    }

    @Test
    public void testGetName() {
        Excavation valid = new Excavation("soho", 10);
        assertEquals("soho", valid.getName());
    }

    @Test
    public void testAddArcheologist() {
        Excavation valid = new Excavation("soho", 10);
        Archaeologist archaeologist = new Archaeologist("pesho", 7);
        valid.addArchaeologist(archaeologist);
        assertEquals("pesho", archaeologist.getName());
        assertEquals(1, valid.getCount());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddArcheologistThrowIfMoreThenCapacity() {
        Excavation valid = new Excavation("soho", 1);
        Archaeologist archaeologist1 = new Archaeologist("pesho", 7);
        Archaeologist archaeologist2 = new Archaeologist("miro", 6);
        valid.addArchaeologist(archaeologist1);
        valid.addArchaeologist(archaeologist2);


    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddArcheologistThrowIfExist() {
        Excavation valid = new Excavation("soho", 3);
        Archaeologist archaeologist1 = new Archaeologist("pesho", 7);
        valid.addArchaeologist(archaeologist1);
        valid.addArchaeologist(archaeologist1);

    }

    @Test
    public void testRemoveArcheologist() {
        Excavation valid = new Excavation("soho", 5);
        Archaeologist archaeologist1 = new Archaeologist("pesho", 7);
        Archaeologist archaeologist2 = new Archaeologist("miro", 5);
        valid.addArchaeologist(archaeologist1);
        valid.addArchaeologist(archaeologist2);
        boolean result = valid.removeArchaeologist(archaeologist1.getName());
       assertEquals(1, valid.getCount());
assertTrue(result);
    }

    @Test
    public void testRemoveArcheologistFalseOfMissingName() {
        Excavation valid = new Excavation("soho", 5);
        Archaeologist archaeologist1 = new Archaeologist("pesho", 7);

        valid.addArchaeologist(archaeologist1);

        boolean result = valid.removeArchaeologist("miro");
        assertFalse(result);
        assertEquals(1, valid.getCount());

    }
    @Test
    public void testRemoveArcheologistOnEmptyExcavation() {
        Excavation valid = new Excavation("soho", 5);
       boolean resul =  valid.removeArchaeologist("miti");
        assertFalse(resul);
    }

    @Test
    public void testGetCapacity() {
        Excavation valid = new Excavation("soho", 10);
        assertEquals(10, valid.getCapacity());

    }

    @Test
    public void testGetCount() {
        Excavation valid = new Excavation("soho", 10);
        assertEquals(0, valid.getCount());

        Archaeologist archaeologist1 = new Archaeologist("pesho", 7);
        valid.addArchaeologist(archaeologist1);
        assertEquals(1, valid.getCount());


    }

}
