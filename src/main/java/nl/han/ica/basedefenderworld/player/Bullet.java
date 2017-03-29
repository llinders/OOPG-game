package nl.han.ica.basedefenderworld.player;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.basedefenderworld.BaseDefenderWorld;
import processing.core.PConstants;
import processing.core.PGraphics;

public class Bullet extends GameObject {
    private BaseDefenderWorld world;
    //final private static float FRICTION = 0.008f;
    private int radius, damage;

    public Bullet(BaseDefenderWorld world, int radius, int damage) {
        this.world = world;
        this.radius = radius;
        this.damage = damage;

        setHeight(radius);
        setWidth(radius);

        //setFriction(FRICTION);
    }

    public void update() {

    }

    public int getDamage() {
        return damage;
    }

    public void draw(PGraphics g) {
        g.noStroke();
        g.fill(110,255,0);
        g.ellipseMode(PConstants.CENTER);
        g.ellipse(getX(), getY(), radius, radius);
    }
}