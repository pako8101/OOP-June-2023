package toyStore;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ToyStoryTest {
   private ToyStore toyStore;
   @Before
   public void setUp(){
      this.toyStore = new ToyStore();
   }
   @Test(expected = UnsupportedOperationException.class)
   public void testGetToyShouldReturnUnmodifiableCollection(){
      toyStore.getToyShelf().clear();
   }
   @Test(expected = IllegalArgumentException.class)
   public void testAddShouldFailIfInvalidToy() throws OperationNotSupportedException {
     toyStore.addToy("invalid", null);
   }
   @Test(expected = IllegalArgumentException.class)
   public void testAddShouldFailIfExitingToy() throws OperationNotSupportedException {
      Toy toy = new Toy("pipo","test");
      toyStore.addToy("A",toy);
      toyStore.addToy("A",toy);
   }
   @Test
   public void testAddToyShouldReturnCorrectMessage() throws OperationNotSupportedException {
      Toy toy = new Toy("pipo","test");
      String message = "Toy:test placed successfully!";
      String actual = toyStore.addToy("A",toy);
      assertEquals(message,actual);
   }
@Test(expected = IllegalArgumentException.class)
   public void testRemoveToyShouldFailIfIsInvalid(){
      toyStore.removeToy("invalid", null);
}
   @Test(expected = IllegalArgumentException.class)
   public void testRemoveToysShouldFailIfDifferentToyOnSameToyStore() throws OperationNotSupportedException {
      Toy toy = new Toy("pipo","test");
      Toy toyOther = new Toy("bobi","test_new");
      toyStore.addToy("A",toy);
      toyStore.removeToy("A",toyOther);
   }
   @Test
   public void testRemoveToyReturnCorrectMessage() throws OperationNotSupportedException {
      Toy toy = new Toy("pipo","test");
      toyStore.addToy("A",toy);
      String message = "Remove toy:test successfully!";
      String actual = toyStore.removeToy("A",toy);
      assertEquals(message,actual);
   }
   @Test
   public void testRemoveToysShouldSetToyStoreToNull() throws OperationNotSupportedException {
      Toy toy = new Toy("pipo","test");
      toyStore.addToy("A",toy);
      toyStore.removeToy("A",toy);
    Toy emptyStore = toyStore.getToyShelf().get("A");
    assertNull(emptyStore);

   }
}