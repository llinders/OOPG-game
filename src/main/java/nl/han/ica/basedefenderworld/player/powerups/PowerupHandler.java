package nl.han.ica.basedefenderworld.player.powerups;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.UserInput.IKeyInput;
import nl.han.ica.basedefenderworld.player.Base;
import processing.core.PGraphics;

/**
 * Handles the powerup progress
 * Keeps track of whether a key is pressed and a powerup is available and executes the powerup() function
 */
public class PowerupHandler extends GameObject implements IKeyInput {
    private Base base;
    private IPowerup currentPowerup;
    private HealthPowerup healthPowerup;
    private final int MAXPROGRESS = 8;

    public PowerupHandler(Base base) {
        this.base = base;
        healthPowerup = new HealthPowerup(base);
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
                    break;
                case '3':
                    break;
                default:
                    return;
            }
            if (currentPowerup.getProgress() < MAXPROGRESS) {
                currentPowerup.powerup();
                base.increaseUsedPowerups();
            }
        }
    }

    @Override
    public void keyReleased(int keyCode, char key) {

    }
}
