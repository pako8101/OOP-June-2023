package christmasPastryShop.repositories;

import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.repositories.interfaces.CocktailRepository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class CocktailRepositoryImpl implements CocktailRepository<Cocktail> {
    private Map<String,Cocktail>cocktails;

    public CocktailRepositoryImpl() {
        this.cocktails = new LinkedHashMap<>();
    }

    @Override
    public Cocktail getByName(String name) {
        return this.cocktails.get(name);
    }

    @Override
    public Collection<Cocktail> getAll() {
        return this.cocktails.values();
    }

    @Override
    public void add(Cocktail cocktail) {
this.cocktails.put(cocktail.getBrand(),cocktail);
    }
}
