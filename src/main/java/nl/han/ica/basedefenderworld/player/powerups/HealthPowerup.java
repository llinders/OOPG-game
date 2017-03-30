package nl.han.ica.basedefenderworld.player.powerups;

import nl.han.ica.basedefenderworld.player.Base;

/**
 * A powerup for the maximum health of Base
 * When the function powerup() is called the maximum health of Base is increased by INCREASEMENT
 */
public class HealthPowerup implements IPowerup {
    private Base base;
    private int progress;
    private final int INCREASEMENT = 10; //increasement of maximum health in percentages

    public HealthPowerup(Base base) {
        this.base = base;
    }

    @Override
    public void powerup() {
        progress++;
        int maxHealth = base.getMaxHealth();
        base.setMaxHealth(maxHealth + (maxHealth / 100 * INCREASEMENT));
        base.setHealth(maxHealth);
        base.decreaseUnclaimedPowerups();
        System.out.println(maxHealth);
    }

    @Override
    public int getProgress() {
        return progress;
    }
}
