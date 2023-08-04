package bankSafe;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
public class BankVaultTest {
    private BankVault bankVault;

    @Before
    public void setUp() {
        this.bankVault = new BankVault();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiableCollectionThrow() {
        bankVault.getVaultCells().clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFailIfNotExistingVault() throws OperationNotSupportedException {
        bankVault.addItem("bira", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddVaultIfAddSecondTimeSameVault() throws OperationNotSupportedException {
        Item item = new Item("misho", "cell");
        bankVault.addItem("A1", item);
        bankVault.addItem("A1", item);
    }

    @Test
    public void testAddVaultIfAddReturnCorrectMessage() throws OperationNotSupportedException {
        Item item = new Item("misho", "cell");
        String message = "Item:cell saved successfully!";
        String actual = bankVault.addItem("A1", item);
        assertEquals(message, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveFailIfNotExistingVault() throws OperationNotSupportedException {
        bankVault.addItem("misa", null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveFailIfDifferentItemInSameVault() throws OperationNotSupportedException {
        Item item = new Item("misho", "cell");
        Item itemOther = new Item("bobi", "cell_new");
        bankVault.addItem("A1", item);
        bankVault.removeItem("A1", itemOther);
    }
    @Test
    public void testRemoveItemReturnCorrectMessage() throws OperationNotSupportedException {
        Item item = new Item("misho", "cell");
        bankVault.addItem("A1", item);
        String message = "Remove item:cell successfully!";
        String actual = bankVault.removeItem("A1", item);
        assertEquals(message, actual);
    }
    @Test
    public void testRemoveItemSetVaultEmpty() throws OperationNotSupportedException {
        Item item = new Item("misho", "cell");
        bankVault.addItem("A1", item);
        bankVault.removeItem("A1", item);
        assertNull(bankVault.getVaultCells().get("A1"));
    }
}