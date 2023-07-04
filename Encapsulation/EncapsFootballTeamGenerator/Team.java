package EncapsFootballTeamGenerator;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Player> players;

    public Team(String name) {
        this.setName(name);
        this.players = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (this.name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("A name should not be empty.");

        }
            this.name = name;

    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }
    public void removePlayer(String name){
       boolean isRemove = this.players.removeIf(p->p.getName().equals(name));
       if (!isRemove){
           String message = String.format("Player %s is not in %s team.", name, this.name);
           throw new IllegalArgumentException(message);

       }
    }
    public double getRating(){
        return this.players
                .stream()
                .mapToDouble(Player::overallSkillLevel)
                .average()
                .orElse(0);
    }
}
