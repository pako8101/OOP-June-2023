package football;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FootballTeamTests {
    private Footballer footballer1;
    private Footballer footballer2;
    private FootballTeam footballTeam;

    @Before
    public void setUp() {
        this.footballTeam = new FootballTeam("Team A", 11);
        this.footballer1 = new Footballer("Mesi");
        this.footballer2 = new Footballer("Alex");
    }

    @Test
    public void createTeamAndGetCount() {
        footballTeam.addFootballer(footballer1);
        assertNotNull(footballTeam.getName());
        assertEquals(1, footballTeam.getCount());
        assertEquals("Team A", footballTeam.getName());
    }

    @Test(expected = NullPointerException.class)
    public void createNullThrowException() {
        new FootballTeam(null, 12);
    }

    @Test(expected = NullPointerException.class)
    public void testEmptyNameShouldThrow() {
        new FootballTeam(" ", 11);
    }

    @Test
    public void testGetCountOFVacantPositions() {
        assertEquals(11, footballTeam.getVacantPositions());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowIfTeamIsFull() {
        FootballTeam footballTeam1 = new FootballTeam("Team B", 1);
        footballTeam1.addFootballer(footballer1);
        footballTeam1.addFootballer(footballer2);

    }

    @Test
    public void testRemoveSuccessfullyFootballer() {
        FootballTeam footballTeam1 = new FootballTeam("Team B", 5);
        footballTeam1.addFootballer(footballer1);
        footballTeam1.addFootballer(footballer2);
        assertNotNull(footballTeam1.getName());
        assertEquals(2, footballTeam1.getCount());
        footballTeam1.removeFootballer("Mesi");
        assertEquals(1, footballTeam1.getCount());
        assertNotNull(footballTeam1.getName());
    }
    @Test
    public void testRemoveFootballerRemaining() {
        FootballTeam footballTeam1 = new FootballTeam("Team B", 5);
        footballTeam1.addFootballer(footballer1);
        assertNotNull(footballTeam1.getName());
        assertEquals(1, footballTeam1.getCount());
        footballTeam1.removeFootballer("Mesi");
        assertEquals(0, footballTeam1.getCount());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveUnexcitingFootballerShouldThrow() {
        footballTeam.addFootballer(footballer1);
        footballTeam.addFootballer(footballer2);
        footballTeam.removeFootballer("Memo");
    }

    @Test
    public void testSuccessfullySellFootballer() {
        FootballTeam footballTeam1 = new FootballTeam("Team B", 5);
        footballTeam1.addFootballer(footballer1);
        footballTeam1.addFootballer(footballer2);
        assertEquals(2, footballTeam1.getCount());
        footballTeam1.footballerForSale("Mesi");
        assertFalse("Mesi", footballer1.isActive());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSellFootballerShouldTrowIfNoSuchPlayer() {
        FootballTeam footballTeam1 = new FootballTeam("Team B", 5);
        footballTeam1.footballerForSale("Misho");
//        footballTeam1.addFootballer(footballer1);
//        footballTeam1.addFootballer(footballer2);
//        assertEquals(2, footballTeam1.getCount());
//        footballTeam1.footballerForSale("Momo");
//        assertFalse("Momo", footballer1.isActive());
    }

    @Test
    public void testGetStatistics(){
        footballTeam.addFootballer(footballer1);
        footballTeam.addFootballer(footballer2);
        String expected = "The footballer Mesi, Alex is in the team Team A.";
        assertEquals(expected,footballTeam.getStatistics());
    }


}
