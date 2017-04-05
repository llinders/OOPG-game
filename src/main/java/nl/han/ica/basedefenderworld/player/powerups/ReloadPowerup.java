package nl.han.ica.basedefenderworld.player.powerups;

import nl.han.ica.basedefenderworld.player.Base;

public class ReloadPowerup implements IPowerup {
    private Base base;
    private int progress;
    private final float DECREASEMENT = 0.04f;

    public ReloadPowerup(Base base) {
        this.base = base;
        progress = 1;
    }

    @Override
    public void powerup() {
        progress++;
        base.setReloadTime(base.getReloadTime() - DECREASEMENT);
        base.decreaseUnclaimedPowerups();
    }

    @Override
    public int getProgress() {
        return progress;
    }
}
