package nl.han.ica.basedefenderworld.dashboard;


import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.basedefenderworld.BaseDefenderWorld;
import nl.han.ica.basedefenderworld.player.Base;
import processing.core.PApplet;
import processing.core.PGraphics;

public class Scoreboard extends GameObject {
    private BaseDefenderWorld world;
    private Dashboard dashboard;
    private Base base;

    public Scoreboard(Dashboard dashboard, Base base, BaseDefenderWorld world) {
        this.dashboard = dashboard;
        this.base = base;
        this.world = world;
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(PGraphics g) {
        g.textAlign(g.LEFT, g.TOP);
        tekenScoreBoard(g);
    }

    private void tekenScoreBoard(PGraphics g) {
        g.fill(244, 215, 188);
        g.rect(dashboard.getWidth() / 20 * 7.9f, dashboard.getHeight() / 4f, dashboard.getWidth() / 20 * 8.2f, dashboard.getHeight() / 10 * 2.3f);
        g.fill(101, 67, 33);
        g.textSize(dashboard.getHeight() / 10);
        System.out.println(base.getHealth());
        g.text("Scoreboard", dashboard.getWidth() / 2.4f, dashboard.getHeight() / 23);
        g.text("Health: ", dashboard.getWidth() / 4.2f, dashboard.getHeight() / 3.4f);
        g.rect(dashboard.getWidth() / 20 * 8, dashboard.getHeight() / 3.8f, PApplet.map(base.getHealth(), 0, base.getMaxHealth(), 0, dashboard.getWidth() / 20 * 8), dashboard.getHeight() / 10 * 2);
        g.text("Round: " + world.calculateRound(), dashboard.getWidth() / 4.2f, dashboard.getHeight() / 2.1f);
        g.text("Bulletdamage: " + base.getBulletDamage(), dashboard.getWidth() / 4.2f, dashboard.getHeight() / 1.7f);
    }
}
