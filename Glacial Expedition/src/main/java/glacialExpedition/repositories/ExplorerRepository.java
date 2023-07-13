package glacialExpedition.repositories;

import glacialExpedition.models.explorers.Explorer;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExplorerRepository implements Repository<Explorer> {
    Map<String, Explorer> explorerMap;

    public ExplorerRepository(){
        this.explorerMap = new LinkedHashMap<>();
    }
    @Override
    public Collection<Explorer> getCollection() {
        return Collections.unmodifiableCollection(explorerMap.values());
    }

    @Override
    public void add(Explorer entity) {
        explorerMap.put(entity.getName(), entity);
    }

    @Override
    public boolean remove(Explorer entity) {
        return explorerMap.remove(entity.getName()) != null;

    }

    @Override
    public Explorer byName(String name) {
        return explorerMap.get(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        explorerMap.values().forEach(ex->sb.append(ex).append(System.lineSeparator()));
        return sb.toString();
    }
}

