package nl.han.ica.basedefenderworld.player;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.basedefenderworld.BaseDefenderWorld;
import processing.core.PConstants;
import processing.core.PGraphics;

public class Bullet extends GameObject implements ICollidableWithGameObjects {
    private BaseDefenderWorld world;
    final private float FRICTION = 0.012f;
    private int radius, damage;

    public Bullet(BaseDefenderWorld world, int radius, int damage) {
        this.world = world;
        this.radius = radius;
        this.damage = damage;

        setHeight(radius);
        setWidth(radius);

        setFriction(FRICTION);
    }

    public void update() {

    }

    public void draw(PGraphics g) {
        g.noStroke();
        g.fill(0, 0, 200);
        g.ellipseMode(PConstants.CENTER);
        g.ellipse(getX(), getY(), radius, radius);
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject gameObject : collidedGameObjects) {

        }
    }

}