package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer{
    private static final double NATURAL_ENERGY = 60;
    private static final double NATURAL_EXPLORER_SEARCH_ENERGY = 7;
    public NaturalExplorer(String name) {
        super(name, NATURAL_ENERGY);
    }

    @Override
    public void search() {

    setEnergy(Math.max(0, getEnergy()-NATURAL_EXPLORER_SEARCH_ENERGY));
    }
}
