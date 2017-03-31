package nl.han.ica.basedefenderworld.player.powerups;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.UserInput.IKeyInput;
import nl.han.ica.basedefenderworld.player.Base;
import processing.core.PGraphics;

import java.util.ArrayList;

/**
 * Handles the powerup progress
 * Keeps track of whether a key is pressed and a powerup is available and executes the powerup() function
 */
public class PowerupHandler extends GameObject implements IKeyInput {
    private Base base;
    private ArrayList<IPowerup> powerups;
    private IPowerup currentPowerup;
    private IPowerup healthPowerup, regenPowerup, damagePowerup, reloadPowerup;
    private final int MAXPROGRESS = 8;

    public PowerupHandler(Base base) {
        this.base = base;
        healthPowerup = new HealthPowerup(base);
        regenPowerup = new RegenPowerup(base);
        damagePowerup = new DamagePowerup(base);
        reloadPowerup = new ReloadPowerup(base);
        powerups = new ArrayList<>(5);
        powerups.add(healthPowerup);
        powerups.add(regenPowerup);
        powerups.add(damagePowerup);
        powerups.add(reloadPowerup);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics g) {
    }

    @Override
    public void keyPressed(int keyCode, char key) {
        if (base.getUnclaimedPowerups() > 0) {
            switch (keyCode) {
                case '1':
                    currentPowerup = healthPowerup;
                    break;
                case '2':
                    currentPowerup = regenPowerup;
                    break;
                case '3':
                    currentPowerup = damagePowerup;
                    break;
                case '4':
                    currentPowerup = reloadPowerup;
                    break;
                default:
                    return; //if another key is pressed do nothing
            }
            if (currentPowerup.getProgress() < MAXPROGRESS) {
                currentPowerup.powerup();
                base.increaseUsedPowerups();
            }
        }
    }

    public ArrayList<IPowerup> getPowerups() {
        return powerups;
    }

    @Override
    public void keyReleased(int keyCode, char key) {

    }
    public int getUnclaimedPowerUps(){
        return base.getUnclaimedPowerups();
    }
}
