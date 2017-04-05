package nl.han.ica.basedefenderworld.player.powerups;

import nl.han.ica.basedefenderworld.player.Base;

public class RegenPowerup implements IPowerup {
    private Base base;
    private int progress;
    private final float DECREASEMENT = 0.08f;

    public RegenPowerup(Base base) {
        this.base = base;
        progress = 1;
    }

    @Override
    public void powerup() {
        progress++;
        base.setRegenTime(base.getRegenTime() - DECREASEMENT);
        base.decreaseUnclaimedPowerups();
    }

    @Override
    public int getProgress() {
        return progress;
    }
}
