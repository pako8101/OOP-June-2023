package football.entities.field;

import football.entities.player.Player;
import football.entities.supplement.Supplement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static football.common.ConstantMessages.NOT_ENOUGH_CAPACITY;
import static football.common.ExceptionMessages.PLAYER_NAME_NULL_OR_EMPTY;

public class BaseField implements Field {
    private String name;
    private int capacity;
    private Collection<Supplement> supplements;
    private Collection<Player> players;

    public BaseField(String name, int capacity) {
        this.setName(name);
        this.setCapacity(capacity);
        this.supplements = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(PLAYER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public int getCapacity() {
        return this.capacity;
    }

    protected void setCapacity(int capacity) {
            this.capacity = capacity;

    }

    public void setSupplements(Collection<Supplement> supplements) {
        this.supplements = supplements;
    }

    public void setPlayers(Collection<Player> players) {
        this.players = players;
    }

    @Override
    public int sumEnergy() {
        return this.supplements.stream().mapToInt(Supplement::getEnergy).sum();

    }

    @Override
    public void addPlayer(Player player) {
        if (players.size() > this.capacity) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY);
        }
        this.players.add(player);
    }

    @Override
    public void removePlayer(Player player) {
        this.players.remove(player);
    }

    @Override
    public void addSupplement(Supplement supplement) {
        this.supplements.add(supplement);
    }

    @Override
    public void drag() {
        this.players.forEach(Player::stimulation);
    }

    @Override
    public String getInfo() {
        String output = players.isEmpty()
                ? "none"
                : players.stream().map(Player::getName)
                .collect(Collectors.joining(" "));
        return String.format("%s (%s): " + System.lineSeparator()
                + "Players: %s" + System.lineSeparator()
                + "Supplement: %d" + System.lineSeparator()
                + "Energy: %d", this.name, this.getClass().getSimpleName(), output, supplements.size(), sumEnergy());
    }

    @Override
    public Collection<Player> getPlayers() {
        return this.players;
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return this.supplements;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
