package magicGame.models.region;

import magicGame.models.magicians.Magician;

import java.util.Collection;
import java.util.List;

public interface Region {
    String start(Collection<Magician> magicians);

}
