package shopAndGoods;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class ShopTest {
  private  Shop shop;
  @Before
public void setUp(){
    this.shop = new Shop();

  }
  @Test(expected = UnsupportedOperationException.class)
  public void testGetShelvesShouldReturnUnmodifiableCollection(){
    shop.getShelves().clear();
  }
  @Test(expected = IllegalArgumentException.class)
  public void testAddGoodsShouldFailForInvalidShelve() throws OperationNotSupportedException {
    shop.addGoods("invalid", null);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testAddGoodsShouldFailForExistingGood() throws OperationNotSupportedException {
   Goods goods = new Goods("test", "test_code");
    shop.addGoods("Shelves1", goods);
    shop.addGoods("Shelves1", goods);
  }
  @Test
  public void testAddGoodsShouldReturnCorrectMessageOnAddition() throws OperationNotSupportedException {
    Goods goods = new Goods("test_good", "test_code");
    String expected = "Goods: test_code is placed successfully!";
     String actual = shop.addGoods("Shelves1", goods);
     assertEquals(expected,actual);
  }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsShouldFailForInvalidShelve() throws OperationNotSupportedException {
        shop.removeGoods("invalid", null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsShouldFailForDifferentGoodOnTheSameShelf() throws OperationNotSupportedException {
        Goods goods = new Goods("test", "test_code");
        Goods goodOther = new Goods("test_good_other", "test_code_other");
        shop.addGoods("Shelves1", goods);
        shop.removeGoods("Shelves1", goodOther);
    }
    @Test
    public void testRemoveGoodsShouldReturnCorrectMessage() throws OperationNotSupportedException {
        Goods goods = new Goods("test_good", "test_code");
        shop.addGoods("Shelves1", goods);
        String expected = "Goods: test_code is removed successfully!";
        String actual = shop.removeGoods("Shelves1", goods);
        assertEquals(expected,actual);
    }
    @Test
    public void removeGoodsShouldSetTheShelveValueToNull() throws OperationNotSupportedException {
        Goods goods = new Goods("test_good", "test_code");
        shop.addGoods("Shelves1", goods);
        shop.removeGoods("Shelves1", goods);
         Goods emptySlot = shop.getShelves().get("Shelves1");
        assertNull(emptySlot);
    }
}