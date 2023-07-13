package glacialExpedition.models.explorers;

public class GlacierExplorer extends BaseExplorer {
    private static final double GLACIER_ENERGY = 40;

    public GlacierExplorer(String name) {
        super(name, GLACIER_ENERGY);
    }
}
