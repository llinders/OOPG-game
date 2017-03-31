package nl.han.ica.basedefenderworld.dashboard;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.basedefenderworld.player.powerups.IPowerup;
import nl.han.ica.basedefenderworld.player.powerups.PowerupHandler;
import processing.core.PGraphics;

import java.util.ArrayList;

public class PowerupBoard extends GameObject {
    private Dashboard dashboard;
    private final String[] UPGRADENAMEN = {"[1] Health:", "[2] Regen time:", "[3] Damage:", "[4] Reload time:"};
    private ArrayList<IPowerup> powerups;
    private ArrayList<Integer> powerupProgress;
    private PowerupHandler powerupHandler;

    public PowerupBoard(Dashboard dashboard, PowerupHandler powerupHandler) {
        this.dashboard = dashboard;
        powerups = powerupHandler.getPowerups();
        powerupProgress = new ArrayList<>(5);
        this.powerupHandler = powerupHandler;
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(PGraphics g) {
        g.textAlign(g.LEFT, g.TOP);
        tekenUpgrades(g);
    }

    private void updatePowerupProgress() {
        for (int i = powerupProgress.size()-1; i >= 0; i--) {
            powerupProgress.remove(i);
        }
        for (IPowerup powerup : powerups) {
            powerupProgress.add(powerup.getProgress());
        }
    }

    private void tekenUpgrades(PGraphics g) {
        updatePowerupProgress();
        int radius = (int) dashboard.getHeight() / 15;
        int nPowerups = powerupProgress.size();
        g.fill(101, 67, 33);
        g.textSize(dashboard.getHeight() / 10);
        g.text("Power-up Points: " + powerupHandler.getUnclaimedPowerUps(), dashboard.getWidth() / 3.3f, dashboard.getHeight() / 23 - radius / 2);
        for (int i = 0; i < nPowerups; i++) {
            g.fill(101, 67, 33);
            g.textSize(dashboard.getHeight() / 11);
            g.text(UPGRADENAMEN[i], dashboard.getWidth() / 4.8f, dashboard.getHeight() / 23 * (2.4f * i + 3.3f));
            for (int j = 0; j < powerupProgress.get(i); j++) {
                g.fill(101, 67, 33);
                g.ellipse(dashboard.getWidth() / 1.8f + radius * j * 1.2f, dashboard.getHeight() / 23 * (2.4f * i + 3.8f) + radius / 2, radius, radius);
            }
            if (powerupProgress.get(i) < 8) {
                for (int k = powerupProgress.get(i); k < 8; k++) {
                    g.fill(244, 215, 188);
                    g.ellipse(dashboard.getWidth() / 1.8f + radius * k * 1.2f, dashboard.getHeight() / 23 * (2.4f * i + 3.8f) + radius / 2, radius, radius);
                }
            }
        }
    }
}