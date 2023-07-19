package christmasPastryShop.repositories;

import christmasPastryShop.entities.booths.interfaces.Booth;
import christmasPastryShop.repositories.interfaces.BoothRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class BoothRepositoryImpl implements BoothRepository<Booth> {
    private Map<Integer,Booth> booths;

    public BoothRepositoryImpl() {
        this.booths = new LinkedHashMap<>();
    }

    @Override
    public Booth getByNumber(int number) {
        return this.booths.get(number);
    }

    @Override
    public Collection<Booth> getAll() {
        return this.booths.values();
    }

    @Override
    public void add(Booth booth) {
this.booths.put(booth.getBoothNumber(),booth);
    }
}
