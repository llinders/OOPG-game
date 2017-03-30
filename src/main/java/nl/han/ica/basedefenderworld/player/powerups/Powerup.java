package nl.han.ica.basedefenderworld.player.powerups;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.UserInput.IKeyInput;
import nl.han.ica.basedefenderworld.player.Base;
import processing.core.PGraphics;

/**
 *
 */
public class Powerup extends GameObject implements IKeyInput {
    private Base base;
    private IPowerup currentPowerup;
    private HealthPowerup healthPowerup;
    private final int MAXPROGRESS = 5;

    public Powerup(Base base) {
        this.base = base;
        healthPowerup = new HealthPowerup(base);
    }

    public void powerup() {

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
