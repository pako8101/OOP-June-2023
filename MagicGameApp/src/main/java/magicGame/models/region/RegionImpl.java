package magicGame.models.region;

import magicGame.models.magicians.BlackWidow;
import magicGame.models.magicians.Magician;
import magicGame.models.magicians.Wizard;

import java.util.ArrayDeque;
import java.util.Collection;

public class RegionImpl implements Region {
    private ArrayDeque<Magician> wizards;
    private ArrayDeque<Magician> blackWidows;

    public RegionImpl() {
        this.wizards = new ArrayDeque<>();
        this.blackWidows = new ArrayDeque<>();
    }

    @Override
    public String start(Collection<Magician> magicians) {

        for (Magician magician : magicians) {
            if (magician instanceof Wizard) {
                wizards.offer(magician);
            } else if (magician instanceof BlackWidow) {
                blackWidows.offer(magician);
            } else {
                throw new IllegalStateException("Unexpected magician type " + magician.getClass().getSimpleName());
            }
        }


        while (!wizards.isEmpty() && !blackWidows.isEmpty()) {
            Magician wizard = wizards.peek();
            Magician blackWidow = blackWidows.peek();
            blackWidow.takeDamage(wizard.getMagic().fire());
            if (blackWidow.isAlive()) {
                wizard.takeDamage(blackWidow.getMagic().fire());
                if (!wizard.isAlive()) {
                    wizards.poll();
                }
            } else {
                blackWidows.poll();
            }

        }
        if (wizards.isEmpty()) {
            return "Wizards win ";
        }

        return "Black widows win";
    }
}
