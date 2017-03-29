package nl.han.ica.basedefenderworld.enemies;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.basedefenderworld.BaseDefenderWorld;
import processing.core.PApplet;

public abstract class Enemy extends SpriteObject implements ICollidableWithGameObjects {
    protected BaseDefenderWorld world;
    protected int maxHealth, health;
    protected float movementSpeed;
    //protected float attackSpeed;
    protected static int nEnemiesKilled;

    public Enemy(BaseDefenderWorld world, Sprite sprite, int health, float movementSpeed, float attackSpeed) {
        super(sprite);
        this.world = world;
        this.health = health;
        this.movementSpeed = movementSpeed;
        //this.attackSpeed = attackSpeed;
    }

    @Override
    public void update() {
        if (health <= 0){
            nEnemiesKilled++;
            System.out.println(nEnemiesKilled);
        }
    }

    @Override
    public void updateSprite(Sprite sprite){
        super.updateSprite(sprite);
    }

    public abstract void attack(GameObject g);

    public static int getAmountOfEnemiesKilled() {
        return nEnemiesKilled;
    }
}
