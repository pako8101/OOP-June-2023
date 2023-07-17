package goldDigger.models.discoverer;

import goldDigger.models.museum.Museum;

public class Anthropologist extends BaseDiscoverer{
    private static final int energy = 40;

    public Anthropologist(String name) {
        super(name, energy);
    }
}
