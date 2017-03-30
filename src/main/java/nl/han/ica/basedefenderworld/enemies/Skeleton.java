package nl.han.ica.basedefenderworld.enemies;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.basedefenderworld.BaseDefenderWorld;
import nl.han.ica.basedefenderworld.player.Base;
import nl.han.ica.basedefenderworld.player.Bullet;

import java.util.List;

public class Skeleton extends Enemy implements IAlarmListener {
    private float attackDelay, angle;
    private boolean attacking, attackDelayExpired;
    private final int DAMAGE = 1;

    public Skeleton(BaseDefenderWorld world, Sprite sprite, int health, float movementSpeed, float attackDelay, int x, int y) {
        super(world, sprite, health, movementSpeed);
        this.attackDelay = attackDelay;
        maxHealth = health;
        setX(x);
        setY(y);
        angle = getAngleFrom(world.getWidth() / 2, world.getHeight() / 2);
        setSpeed(movementSpeed);
        setDirection(angle);
        startAlarm();
    }

    @Override
    public void update() {
        if (health <= 0) {
            nEnemiesKilled++;
            world.deleteGameObject(this);
        }
    }

    @Override
    public void triggerAlarm(String alarmName) {
        attackDelayExpired = true;
        startAlarm();
    }

    private void startAlarm() {
        Alarm alarm = new Alarm("Attack delay", attackDelay);
        alarm.addTarget(this);
        alarm.start();
    }

    @Override
    public void attack(GameObject g) {
        if (attackDelayExpired) {
            attackDelayExpired = false;
            ((Base) g).setHealth(((Base) g).getHealth() - DAMAGE);
        }
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g : collidedGameObjects) {
            if (g instanceof Bullet) {
                health -= ((Bullet) g).getDamage();
                world.deleteGameObject(g);
            } else if (g instanceof Base) {
                attackMode(g);
            }
        }
    }

    /**
     * Set the skeleton in attacking mode, which means the animation gets updated and the skeleton no longer moves
     * @param g the GameObject which the skeleton is currently attacking
     */
    private void attackMode(GameObject g){
        setSpeed(0);
        attack(g);
        if (!attacking) {
            attacking = true;
            setSprite(new Sprite("nl/han/ica/basedefenderworld/data/animations/skeleton_attacking.gif"));
        }
    }
}
