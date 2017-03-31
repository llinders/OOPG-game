package nl.han.ica.basedefenderworld.player.powerups;

import nl.han.ica.basedefenderworld.player.Base;

public class DamagePowerup implements IPowerup {
    private Base base;
    private int progress;
    private final int INCREASEMENT = 15;

    public DamagePowerup(Base base) {
        this.base = base;
        progress = 1;
    }

    @Override
    public void powerup(){
        progress++;
        int damage = base.getBulletDamage();
        base.setBulletDamage(damage + (damage * INCREASEMENT/100));
        base.decreaseUnclaimedPowerups();
    }

    @Override
    public int getProgress(){
        return progress;
    }
}
