package gifts;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class GiftFactoryTests {

    private GiftFactory giftFactory;
    private Gift gift1;
    private Gift gift2;

    @Before
    public void setUp() {
        this.giftFactory = new GiftFactory();
        this.gift1 = new Gift("doll", 5.5);
        this.gift2 = new Gift("bear", 7.7);
    }

    @Test
    public void getCount() {
        giftFactory.createGift(gift1);
        giftFactory.createGift(gift2);
        assertEquals(2, giftFactory.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateSameGiftThrow() {
        giftFactory.createGift(gift1);
        giftFactory.createGift(gift1);
        assertEquals(1, giftFactory.getCount());
    }

    @Test
    public void testCreateGift() {
        giftFactory.createGift(gift1);
        assertEquals(1, giftFactory.getCount());
        assertEquals("doll", gift1.getType());

    }

    @Test
    public void testRemoveSuccessfully() {
        giftFactory.createGift(gift1);
        assertEquals(1, giftFactory.getCount());
        giftFactory.removeGift("doll");
        assertEquals(0, giftFactory.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void testGiftNullThrowException() {
        giftFactory.createGift(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testExceptionCreateEqualNames(){
        Gift newGift = new Gift("doll", 5.5);
        giftFactory.createGift(this.gift1);
        this.giftFactory.createGift(newGift);
    }

    @Test
    public void testRemoveNonExistingPresent() {
        giftFactory.createGift(gift1);
        assertEquals(1, giftFactory.getCount());
        giftFactory.removeGift("bear");
        assertEquals(1, giftFactory.getCount());
    }
    @Test(expected = NullPointerException.class)
    public void testRemoveNullGiftThrow() {
        giftFactory.removeGift(null);
    }
    @Test(expected = NullPointerException.class)
    public void testRemoveEmptyGiftThrow() {
        giftFactory.removeGift("          ");
    }
    @Test
    public void testGetPresentWithLeastMagic() {
        giftFactory.createGift(gift1);
        giftFactory.createGift(gift2);
        Gift giftWithLessMagic = giftFactory.getPresentWithLeastMagic();
        assertEquals(giftWithLessMagic, giftFactory.getPresentWithLeastMagic());
    }

    @Test
    public void testGetPresents() {
        giftFactory.createGift(gift1);
        assertEquals(1, giftFactory.getCount());
        Collection<Gift> gifts = giftFactory.getPresents();
        assertEquals(1, gifts.size());
    }

    @Test
    public void testGetPresentMagicAndType() {

        assertEquals("doll", gift1.getType());
        assertEquals(5.5, gift1.getMagic(), 0.00001);

    }

    @Test
    public void testGetPresent() {
        Gift giftNew = new Gift("car",0.1);
        giftFactory.createGift(this.gift2);
        giftFactory.createGift(giftNew);
        assertEquals(2, giftFactory.getCount());
        assertEquals("doll", gift1.getType());

        assertEquals(this.gift2, this.giftFactory.getPresent("bear"));
    }

    @Test
    public void testGetCountGifts() {
        assertEquals(0, giftFactory.getCount());
    }

    @Test
    public void testGetValidGiftFactory() {
    GiftFactory giftFactory1 = new GiftFactory();
    Gift gift = new Gift("barbi", 3);
    giftFactory1.createGift(gift);

    }
    @Test(expected = UnsupportedOperationException.class)
    public void testModifiedCollectionThrow(){
        this.giftFactory.createGift(gift1);
        this.giftFactory.getPresents().remove(gift1);
    }

}

