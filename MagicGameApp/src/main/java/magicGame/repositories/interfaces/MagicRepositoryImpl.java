package magicGame.repositories.interfaces;

import magicGame.models.magics.Magic;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static magicGame.common.ExceptionMessages.INVALID_MAGIC;

public class MagicRepositoryImpl implements MagicRepository<Magic> {
    private Map<String, Magic> data = new LinkedHashMap<>();

    @Override
    public Collection<Magic> getData() {
        return Collections.unmodifiableCollection(data.values());
    }

    @Override
    public void addMagic(Magic model) {
        if (model == null) {
            throw new NullPointerException(INVALID_MAGIC);
        }
        this.data.put(model.getName(), model);
    }

    @Override
    public boolean removeMagic(Magic model) {
        return this.data.remove(model.getName()) != null;

    }

    @Override
    public Magic findByName(String name) {
        return data.values().stream().filter(m -> m.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
