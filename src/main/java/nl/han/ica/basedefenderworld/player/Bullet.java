package nl.han.ica.basedefenderworld.player;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.basedefenderworld.BaseDefenderWorld;
import processing.core.PGraphics;

public class Bullet extends GameObject implements ICollidableWithGameObjects {
    private BaseDefenderWorld world;
    private int radius, damage;

    public Bullet(BaseDefenderWorld world, int radius, int damage) {
        this.world = world;
        this.radius = radius;
        this.damage = damage;
    }

    public void update() {
    }

    public void draw(PGraphics g) {
        g.fill(0, 0, 200);
        g.ellipse(getX(), getY(), radius, radius);
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        System.out.println("wow");
        for (GameObject gameObject : collidedGameObjects) {
            if (gameObject instanceof Base || gameObject instanceof Barrel)
                System.out.println("wow");
        }
    }
}