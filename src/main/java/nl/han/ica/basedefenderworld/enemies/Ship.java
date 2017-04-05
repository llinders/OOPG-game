package nl.han.ica.basedefenderworld.enemies;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.basedefenderworld.BaseDefenderWorld;
import nl.han.ica.basedefenderworld.player.Base;
import nl.han.ica.basedefenderworld.player.Bullet;
import processing.core.PApplet;
import processing.core.PGraphics;

import java.util.List;

public class Ship extends Enemy {
    private float angle;
    private int shipNr;
    private final int DAMAGE = 15;

    public Ship(BaseDefenderWorld world, Sprite sprite, int health, float movementSpeed, int x, int y) {
        super(world, sprite, health, movementSpeed);
        maxHealth = health;

        setX(x);
        setY(y);
        angle = getAngleFrom(world.getWidth() / 2, world.getHeight() / 2);

        setSpeed(movementSpeed);
        setDirection(angle);
    }

    /**
     * Makes the ship attack the GameObject.
     *
     * @param g the GameObject which the ship is currently attacking.
     */
    @Override
    public void attack(GameObject g) {
        ((Base) g).setHealth(((Base) g).getHealth() - DAMAGE);
    }

    @Override
    public void update() {
        if (health <= 0) {
            nEnemiesKilled++;
            world.deleteGameObject(this);
        } else if (health < maxHealth / 3) {
            if (shipNr != 3) {
                shipNr = 3;
                setSprite(new Sprite("src/main/java/nl/han/ica/basedefenderworld/data/enemies/ship3.png"));
            }
        } else if (health < maxHealth / 3 * 2) {
            if (shipNr != 2) {
                shipNr = 2;
                setSprite(new Sprite("src/main/java/nl/han/ica/basedefenderworld/data/enemies/ship2.png"));
            }
        }
    }

    @Override
    public void draw(PGraphics g) {
        g.pushMatrix();
        g.translate(getCenterX(), getCenterY());
        g.rotate(PApplet.radians(angle + 180));
        g.image(getImage(), -width / 2, -height / 2);
        g.popMatrix();
    }

    private float getRotationInRadians(float rotationInDegrees) {
        float rotationInRadians = (float) (PApplet.radians(rotationInDegrees) % Math.PI);
        rotationInRadians = (float) (((rotationInRadians > Math.PI * 0.5 && rotationInRadians < Math.PI * 1)
                || (rotationInRadians > Math.PI * 1.5 && rotationInRadians < Math.PI * 2)) ? Math.PI - rotationInRadians
                : rotationInRadians);
        return rotationInRadians;
    }

    @Override
    public float getWidth() {
        // met dank aan
        // http://stackoverflow.com/questions/10392658/calculate-the-bounding-boxs-x-y-height-and-width-of-a-rotated-element-via-jav
        float rotationInRadians = getRotationInRadians(angle);
        return (float) (Math.sin(rotationInRadians) * height +
                Math.cos(rotationInRadians) * width);
    }

    @Override
    public float getHeight() {
        float rotationInRadians = getRotationInRadians(angle);
        return (float) (Math.sin(rotationInRadians) * width + Math.cos(rotationInRadians) * height);
    }

    @Override
    public float getX() {
        return -(getWidth() / 2) + getCenterX();
    }

    @Override
    public float getY() {
        return -(getHeight() / 2) + getCenterY();
    }


    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g : collidedGameObjects) {
            if (g instanceof Bullet) {
                health -= ((Bullet) g).getDamage();
                world.deleteGameObject(g);
            } else if (g instanceof Base) {
                setSpeed(0);
                attack(g);
                world.deleteGameObject(this);
            }
        }
    }
}

