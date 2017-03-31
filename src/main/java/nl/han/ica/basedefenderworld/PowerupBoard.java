package nl.han.ica.basedefenderworld;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.basedefenderworld.player.powerups.HealthPowerup;
import processing.core.PGraphics;

/**
 * @author Ralph Niels
 *         Wordt gebruikt om een tekst te kunnen afbeelden
 */
public class PowerupBoard extends GameObject {
    Dashboard dashboard;
    private final String[] UPGRADENAMEN = {"[1] Health: ", "[2] Damage:", "[3] Reload Time:", "asdf", "asdf"};
    int powerUpsProgress[][] = {{0,2},{1,3},{2,3},{3,4},{4,5}};

    public PowerupBoard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    @Override
    public void update() {
        //powerUpsProgress[0][1] = HealthPowerup.getProgress();
    }

    @Override
    public void draw(PGraphics g) {
        g.textAlign(g.LEFT, g.TOP);
        g.textSize(50);
        tekenUpgrades(g, powerUpsProgress);
    }

    private void tekenUpgrades(PGraphics g, int[][] powerUpsProgress) {
        int radius = (int)dashboard.getHeight()/15;
        g.fill(101,67,33);
        g.textSize(dashboard.getHeight()/10);
        g.text("Powerups:",dashboard.getWidth()/2.3f, dashboard.getHeight()/23-radius/2);
        for (int i = 0; i < powerUpsProgress.length; i++) {
            g.fill(101,67,33);
            g.textSize(dashboard.getHeight()/11);
            g.text(UPGRADENAMEN[i],dashboard.getWidth()/4.8f, dashboard.getHeight()/23*(2.4f*i+3.3f));
            for (int j = 0; j < powerUpsProgress[i][1]; j++) {
                g.ellipse( dashboard.getWidth()/1.8f + radius * j * 1.2f, dashboard.getHeight()/23*(2.4f*i+3.8f)+radius/2, radius, radius);
            }
            if (powerUpsProgress[i][1] < 5) {
                for (int k = powerUpsProgress[i][1]; k < 5; k++) {
                    g.fill(244, 215, 188);
                    g.ellipse( dashboard.getWidth()/1.8f + radius * k * 1.2f, dashboard.getHeight()/23*(2.4f*i+3.8f)+radius/2, radius, radius);
                }

            }
        }
    }
}