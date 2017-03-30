package nl.han.ica.basedefenderworld;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

/**
 * @author Ralph Niels
 *         Wordt gebruikt om een tekst te kunnen afbeelden
 */
public class TextObject extends GameObject {
    Dashboard dashboard;
    private String text;
    private String[] upgradeNamen = {"Health upgrade: ", "Damage upgrade:", "ReloadTime Upgrade:", "asdf", "asdf"};
    public TextObject(String text, Dashboard dashboard) {
        this.text = text;
        this.dashboard = dashboard;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics g) {
        g.textAlign(g.LEFT, g.TOP);
        g.textSize(50);
        g.text(text, getX(), getY());
        int[][] upgradeProgress = {{0, 4}, {1, 2}, {2, 3},{3,4},{5,5}};
        tekenUpgrades(g, upgradeProgress);
    }

    public void tekenUpgrades(PGraphics g, int[][] upgradeProgress) {
        for (int i = 0; i < upgradeProgress.length; i++) {
            g.fill(101,67,33);
            g.textSize(20);
            g.text(upgradeNamen[i],dashboard.getWidth()/3, 20*i);
            for (int j = 0; j < upgradeProgress[i][1]; j++) {
                g.textSize(12);

                g.fill(255, 0, 0);
                g.ellipse( 10+20 * j, 10+i*20, 20, 20);
            }
            if (upgradeProgress[i][1] < 5) {
                for (int k = upgradeProgress[i][1]; k < 5; k++) {
                    g.fill(255);
                    g.ellipse(10+20 * k, 10+i*20, 20, 20);
                }

            }
        }
    }
}