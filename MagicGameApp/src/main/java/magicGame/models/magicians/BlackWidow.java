package magicGame.models.magicians;

import magicGame.models.magics.Magic;

public class BlackWidow extends MagicianImpl{
    public BlackWidow(String username, int health, int protection, Magic magic) {
        super(username, health, protection, magic);
    }

    @Override
    public void setProtection(int protection) {
        super.setProtection(protection);
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public int getHealth() {
        return super.getHealth();
    }

    @Override
    public int getProtection() {
        return super.getProtection();
    }

    @Override
    public Magic getMagic() {
        return super.getMagic();
    }

    @Override
    public boolean isAlive() {
        return super.isAlive();
    }

    @Override
    public void takeDamage(int points) {
        super.takeDamage(points);
    }
}
