package magicGame.models.magicians;

import magicGame.models.magics.Magic;

import static magicGame.common.ExceptionMessages.*;

public abstract class MagicianImpl implements Magician {
    private String username;
    private int health;
    private int protection;
    private boolean isAlive;
    private Magic magic;

    public MagicianImpl(String username, int health, int protection, Magic magic) {
        this.setUsername(username);
        this.setHealth(health);
        this.setProtection(protection);
        this.setMagic(magic);
    }

    private void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new NullPointerException(INVALID_MAGICIAN_NAME);
        }
        this.username = username;
    }

    private void setHealth(int health) {
        if (health <= 0) {
            throw new IllegalArgumentException(INVALID_MAGICIAN_HEALTH);
        }
        this.health = health;
    }

    public void setProtection(int protection) {
        if (protection<=0){
            throw new IllegalArgumentException(INVALID_MAGICIAN_PROTECTION);
        }
        this.protection = protection;
    }

    private void setAlive(boolean alive) {
        if (this.getHealth()>0){
            isAlive = alive;
        }

    }

    private void setMagic(Magic magic) {
        if (magic==null){
            throw new NullPointerException(INVALID_MAGIC_NAME);
        }
        this.magic = magic;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public int getProtection() {
        return this.protection;
    }

    @Override
    public Magic getMagic() {
        return this.magic;
    }

    @Override
    public boolean isAlive() {
        return this.isAlive;
    }

    @Override
    public void takeDamage(int points) {
        if (points>this.protection){
            int damageToHealth = points - this.protection;
            this.health -= damageToHealth;
            this.protection = 0;

        }else {
            this.protection-=points;
        }
        if (this.health<=0){
            this.health = 0;
            this.protection = 0;
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +" : " + getUsername() + System.lineSeparator()+
                "Health:" + getHealth() +System.lineSeparator()+
                "Protection: "+ getProtection() + System.lineSeparator()+
                "Magic: " + getMagic().getName();
    }
}
