package bank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BankTests {
    private Client client1;
    private Client client2;
    private Bank newBank;

    @Before
    public void setUp() {
        client1 = new Client("Miro");
        client2 = new Client("Gosho");
        newBank = new Bank("Union", 5000);
    }

    @Test
    public void testShouldCreateBankSuccess() {
        Bank bank = new Bank("Soft", 300);
        assertEquals("Soft", bank.getName());
        assertEquals(300, bank.getCapacity());
        assertEquals(0, bank.getCount());
    }
    @Test(expected = NullPointerException.class)
    public void testCreateShouldThrowExceptionIfNameIsEmpty() {
        new Bank("", 3);
    }
    @Test(expected = NullPointerException.class)
    public void testCreateShouldThrowExceptionIfNameIsWhiteSpice() {
        new Bank(" ", 3);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCreateShouldThrowExceptionIfCapacityIsNegative() {
        new Bank("Boika", -3);
    }

    @Test
    public void testAddSuccess() {
        newBank.addClient(client1);
        assertEquals(1, newBank.getCount());
        newBank.addClient(client2);
        assertEquals(2, newBank.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldThrowIfNoCapacity() {
        Bank bank = new  Bank("Soft", 1);
        bank.addClient(client1);
        bank.addClient(client2);
    }
    @Test
    public void removeClientSuccess(){
        newBank.addClient(client1);
        newBank.addClient(client2);
        assertEquals(2,newBank.getCount());
        newBank.removeClient(client1.getName());
        assertEquals(1,newBank.getCount());
        assertEquals("Gosho", client2.getName());
    }
    @Test(expected = IllegalArgumentException.class)
    public void notRemoveClientSuccess(){
        newBank.addClient(client1);
       newBank.removeClient(client2.getName());
       assertEquals(1,newBank.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void toRemoveClientIsNullThrow(){
        newBank.removeClient(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void toRemoveClientIsEmptyThrow(){
        newBank.removeClient(" ");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistingClient(){

        newBank.removeClient("Ivan");
    }
    @Test
    public void clientSuccessLoanWithdrawal(){
        newBank.addClient(client1);
        Client clientForWithdrawal = newBank.loanWithdrawal(client1.getName());
        assertEquals(client1,clientForWithdrawal);
    }
    @Test
    public void clientSuccessLoanWithdrawalCorrect(){
        newBank.addClient(client1);
       newBank.loanWithdrawal("Miro");
       assertFalse(client1.isApprovedForLoan());
        assertEquals(client1,newBank.loanWithdrawal("Miro"));
    }
    @Test(expected = IllegalArgumentException.class)
    public void clientLoanWithdrawalNullThrow(){
        Client client  = new Client(null);
       newBank.loanWithdrawal(client.getName());
    }
    @Test(expected = IllegalArgumentException.class)
    public void clientLoanWithdrawalNonExisting(){

        newBank.loanWithdrawal("ivan");
    }
    @Test
    public void testStatistics(){
        newBank.addClient(client1);
        newBank.addClient(client2);
        assertEquals("The client Miro, Gosho is at the Union bank!", newBank.statistics());
    }
}
