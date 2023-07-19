package christmasPastryShop.repositories;

import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.repositories.interfaces.DelicacyRepository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class DelicacyRepositoryImpl implements DelicacyRepository<Delicacy> {
    private Map<String,Delicacy> delicacy;

    public DelicacyRepositoryImpl() {
        this.delicacy = new LinkedHashMap<>();
    }

    @Override
    public Delicacy getByName(String name) {
        return this.delicacy.get(name);
    }

    @Override
    public Collection<Delicacy> getAll() {
        return this.delicacy.values();
    }

    @Override
    public void add(Delicacy delicacy) {
this.delicacy.put(delicacy.getName(), delicacy);
    }
}
