package EncapsFootballTeamGenerator;

public class Player {
    private String name;
    private int endurance;
    private int sprint;
    private int dribble;
    private int passing;
    private int shooting;

    public Player(String name, int endurance, int sprint, int dribble, int passing, int shooting) {
        this.setName(name);
        this.setEndurance(endurance);
        this.setSprint(sprint);
        this.setDribble(dribble);
        this.setPassing(passing);
        this.setShooting(shooting);
    }

    public double overallSkillLevel() {
        return (this.endurance + this.sprint + this.dribble + this.passing + this.shooting) / 5.0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {

            throw new IllegalArgumentException("A name should not be empty.");

        }
            this.name = name;

    }

    public void setEndurance(int endurance) {
        validateState(endurance, "Endurance");
            this.endurance = endurance;
        }



    public void setSprint(int sprint) {
        validateState(sprint, "Sprint");
            this.sprint = sprint;
        }



    public void setDribble(int dribble) {
        validateState(dribble, "Dribble");
            this.dribble = dribble;
        }



    public void setPassing(int passing) {
        validateState(passing, "Passing");
            this.passing = passing;
        }



    public void setShooting(int shooting) {
        validateState(shooting, "Shooting");

            this.shooting = shooting;
        }


    private void validateState(int statValue, String statName) {
        boolean isInValid = statValue < 0 || statValue > 100;
        if (isInValid) {

            throw  new IllegalArgumentException(String.format("%s should be between 0 and 100.%n", statName));
        }

    }
}
