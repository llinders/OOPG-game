package nl.han.ica.basedefenderworld.player;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PConstants;
import processing.core.PGraphics;

public class Bullet extends GameObject {
    private int radius, damage;

    public Bullet(int radius, int damage) {
        this.radius = radius;
        this.damage = damage;
        setHeight(1);
        setWidth(1);
    }


    public void update() {

    }

    public int getDamage() {
        return damage;
    }

    public void draw(PGraphics g) {
        g.stroke(60);
        g.fill(180);
        g.ellipseMode(PConstants.CENTER);
        g.ellipse(getX(), getY(), radius, radius);
    }
}