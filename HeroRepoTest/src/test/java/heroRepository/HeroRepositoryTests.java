package heroRepository;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class HeroRepositoryTests {
    private HeroRepository heroRepository;
    private Hero hero;
private static final int highest = 10;
    @Before
    public void setUp(){
        this.heroRepository = new HeroRepository();
        this.hero = new Hero("Pako", 2);
    }
    @Test(expected = NullPointerException.class)
    public void testCreateHeroIsNull(){
       this.heroRepository.create(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCreateHeroIsDuplicateName(){
        this.heroRepository.create(this.hero);
        this.heroRepository.create(this.hero);
    }
    @Test
    public void testCreateSuccessfulHero(){
        assertEquals(0,this.heroRepository.getCount());
        this.heroRepository.create(this.hero);
        assertEquals(1,this.heroRepository.getCount());
        Hero createdHero = this.heroRepository.getHero("Pako");
        assertEquals(createdHero.getName(),this.hero.getName());
        assertEquals(createdHero.getLevel(),this.hero.getLevel());
    }
    @Test(expected = NullPointerException.class)
    public void testRemoveWithNullName(){
        this.heroRepository.remove(null);
    }
    @Test(expected = NullPointerException.class)
    public void testRemoveWithEmptyName(){
        this.heroRepository.remove(" ");
    }

    @Test
    public void testRemoveSuccessfulHero(){
        assertEquals(0, this.heroRepository.getCount());
        this.heroRepository.create(this.hero);
        assertEquals(1, this.heroRepository.getCount());
        this.heroRepository.remove("Pako");
        assertEquals(0, this.heroRepository.getCount());
    }
    @Test
    public void testHerroWithHighestLevel(){
       Hero hero1 = new Hero("Boko", 3);
        Hero hero2 = new Hero("Ceko", 6);
        Hero hero3 = new Hero("Muko", 4);
        this.heroRepository.create(hero1);
        this.heroRepository.create(hero2);
        this.heroRepository.create(hero3);
        assertEquals(3, this.heroRepository.getHeroes().size());
        Hero heroesHighest = this.heroRepository.getHeroWithHighestLevel();
        assertEquals(hero2.getLevel(), heroesHighest.getLevel());
    }
}
