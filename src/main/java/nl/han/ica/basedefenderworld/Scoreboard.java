package nl.han.ica.basedefenderworld;


import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

public class Scoreboard extends GameObject {
    private Dashboard dashboard;

    public Scoreboard(Dashboard dashboard){

    }
    @Override
    public void update() {
    }

    @Override
    public void draw(PGraphics g) {
        g.textAlign(g.LEFT, g.TOP);
        g.textSize(50);
    }
}
