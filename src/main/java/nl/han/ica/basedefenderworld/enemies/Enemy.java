package nl.han.ica.basedefenderworld.enemies;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.basedefenderworld.BaseDefenderWorld;

public abstract class Enemy extends SpriteObject implements ICollidableWithGameObjects {
    protected BaseDefenderWorld world;
    protected Sprite sprite;
    protected int maxHealth, health;
    protected float movementSpeed;
    protected static int nEnemiesKilled;

    public Enemy(BaseDefenderWorld world, Sprite sprite, int health, float movementSpeed) {
        super(sprite);
        this.world = world;
        this.health = health;
        this.movementSpeed = movementSpeed;
    }

    @Override
    public void update() {
        if (health <= 0) {
            nEnemiesKilled++;
        }
    }

    public abstract void attack(GameObject g);

    public static int getAmountOfEnemiesKilled() {
        return nEnemiesKilled;
    }
}
