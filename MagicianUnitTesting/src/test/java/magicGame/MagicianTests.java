package magicGame;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MagicianTests {
    @Test
    public void test() {
        Magician magician = new Magician("Username", 42);
        assertEquals("Username", magician.getUsername());
        assertEquals(42, magician.getHealth());
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameShouldFailWhenNameIsNull() {
        new Magician(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameShouldFailWhenNameIsEmpty() {
        new Magician(" ", 10);
    }

    @Test
    public void testSetUsernameShouldSetCorrectName() {
        Magician magician = new Magician("test", 10);

        assertEquals("test", magician.getUsername());
    }

    @Test
    public void testAddMagic() {
        Magician magician = new Magician("test", 10);
        magician.addMagic(new Magic("boiko", 5));
        assertNotNull(magician.getMagic("boiko"));
        assertEquals("boiko", magician.getMagic("boiko").getName());
        assertEquals(5, magician.getMagic("boiko").getBullets());
    }

    @Test(expected = NullPointerException.class)
    public void testAddNullMagicThrowException() {
        Magician magician = new Magician("test", 10);
        magician.addMagic(null);

    }

    @Test
    public void testGetMagic() {
        Magician magician = new Magician("test", 10);
        Magic magic = new Magic("boiko", 5);
        magician.addMagic(magic);
        assertEquals(magic, magician.getMagic("boiko"));
    }

    @Test
    public void testRemoveMagic() {
        Magician magician = new Magician("test", 10);
        Magic magic1 = new Magic("boiko", 5);
        Magic magic2 = new Magic("bobo", 12);
        magician.addMagic(magic1);
        magician.addMagic(magic2);
        assertNotNull(magician.getMagic("boiko"));
        assertTrue(magician.removeMagic(magic1));
        List<Magic> magicList = magician.getMagics();
        assertEquals(1, magicList.size());
        assertNull(magician.getMagic(magic1.getName()));
    }

    @Test
    public void testRemoveMagicRemaining() {
        Magician magician = new Magician("test", 10);
        Magic magic = new Magic("boiko", 5);
        magician.addMagic(magic);
        assertNotNull(magician.getMagic("boiko"));
        assertTrue(magician.removeMagic(magic));
        List<Magic> magicList = magician.getMagics();
        assertEquals(0, magicList.size());
        assertNull(magician.getMagic(magic.getName()));
    }

    @Test
    public void testRemoveMagicNotFound() {
        Magician magician = new Magician("test", 10);
        Magic magic = new Magic("boiko", 5);
        magician.addMagic(magic);
        assertNotNull(magician.getMagic("boiko"));
        assertFalse(magician.removeMagic(new Magic("non existing", 2)));
        assertEquals(1, magician.getMagics().size());
    }

    @Test
    public void testGetMagics() {
        Magician magician = new Magician("test", 10);
        Magic magic1 = new Magic("boiko", 5);
        Magic magic2 = new Magic("pepo", 9);
        magician.addMagic(magic1);
        magician.addMagic(magic2);
        assertEquals(magic1, magician.getMagic("boiko"));
        assertEquals(magic2, magician.getMagic("pepo"));
        List<Magic> magicList = magician.getMagics();
        assertEquals(2, magicList.size());
        assertEquals(magic1, magicList.get(0));
        assertEquals(magic2, magicList.get(1));
    }

    @Test
    public void testGetMagicsOne() {
        Magician magician = new Magician("test", 10);
        Magic magic1 = new Magic("boiko", 5);
        magician.addMagic(magic1);
        assertEquals(magic1, magician.getMagic("boiko"));
        List<Magic> magicList = magician.getMagics();
        assertEquals(1, magicList.size());
        assertEquals(magic1, magicList.get(0));

    }

    @Test
    public void testGetMagicsNone() {
        Magician magician = new Magician("test", 10);
        List<Magic> magicList = magician.getMagics();
        assertEquals(0, magicList.size());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetHealthBelowZero() {
        new Magician("test", -1);
    }

    @Test
    public void testGetHealth() {
        Magician magician = new Magician("test", 10);
        assertEquals(10, magician.getHealth());

    }

    @Test
    public void testTakeDamageHealthLargerThenDamage() {
        Magician magician = new Magician("test", 10);
        magician.takeDamage(5);
        assertEquals(5, magician.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void testTakeDamageZeroHealthThrows() {
        Magician magician = new Magician("test", 0);
        magician.takeDamage(13);

    }
    @Test
    public void testTakeDamageHealthLessThenDamageSetsLessThenZero() {
        Magician magician = new Magician("test", 13);
        magician.takeDamage(14);
assertEquals(0,magician.getHealth());
    }
    @Test
    public void testTakeDamageZeroHealthEqualToDamage() {
        Magician magician = new Magician("test", 13);
        magician.takeDamage(13);
        assertEquals(0,magician.getHealth());
    }
    @Test
    public void testTakeDamageNotChangeHealth() {
        Magician magician = new Magician("test", 13);
        magician.takeDamage(0);
        assertEquals(13,magician.getHealth());
    }
}
