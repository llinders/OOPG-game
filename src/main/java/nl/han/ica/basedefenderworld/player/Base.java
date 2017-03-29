package nl.han.ica.basedefenderworld.player;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.basedefenderworld.BaseDefenderWorld;
import nl.han.ica.basedefenderworld.enemies.Ship;
import processing.core.PGraphics;

import java.util.ArrayList;
import java.util.List;

public class Base extends GameObject implements ICollidableWithGameObjects, IAlarmListener {
    private BaseDefenderWorld world;
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private int size, health, bulletDamage, unclaimedPowerups, mouseX, mouseY;
    private float bulletSpeed, reloadTime, regenTime;
    private boolean firing, gunIsReloaded;


    public Base(BaseDefenderWorld world, int size) {
        this.world = world;
        this.size = size;
        health = 100;
        bulletDamage = 28;
        bulletSpeed = 7.8f;
        regenTime = 30.0f;
        reloadTime = 0.75f;
        gunIsReloaded = true;
        setWidth(size);
        setHeight(size);
        startAlarm();
    }

    @Override
    public void update() {
        //check if any of the bullets are out of the world, if so remove them
        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet bullet = bullets.get(i);
            if (bullet.getY() > world.getHeight() || bullet.getY() < 0 || bullet.getX() < 0 || bullet.getX() > world.getWidth()) {
                world.deleteGameObject(bullet);
                bullets.remove(bullet);
            }
        }
    }

    @Override
    public void draw(PGraphics g) {
        /*g.noStroke();
        g.fill(255, 0, 0);
        g.ellipseMode(PConstants.CORNER);
        g.ellipse(getX(), getY(), size, size);*/
    }

    /**
     * Starts an alarm to delay the amount of bullets you can shoot per second
     */
    private void startAlarm() {
        Alarm alarm = new Alarm("Fire delay", reloadTime);
        alarm.addTarget(this);
        alarm.start();
    }

    @Override
    public void triggerAlarm(String alarmName) {
        if (firing && gunIsReloaded)
            fireBullet(mouseX, mouseY);
        gunIsReloaded = true;
        startAlarm();
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g:collidedGameObjects) {
            if (g instanceof Ship) {
                g.setSpeed(0);
            }

        }
    }

    @Override
    public void mousePressed(int x, int y, int button) {
        if (gunIsReloaded){
            fireBullet(x, y);
            gunIsReloaded = false;

        }
        firing = true;
        mouseX = x;
        mouseY = y;
    }
    
    @Override
    public void mouseReleased(int x, int y, int button){
        firing = false;
    }

    @Override
    public void mouseDragged(int x, int y, int button){
        mouseX = x;
        mouseY = y;
    }

    /**
     * Fires a bullet towards the given coordinates
     * @param x X position
     * @param y Y position
     */
    private void fireBullet(int x, int y){
        setX(world.width/2-size/2);
        float angle = getAngleFrom(x,y);
        Bullet bullet = new Bullet(world, size/25, bulletDamage);
        world.addGameObject(bullet, world.width/2, world.height/2, 0);
        bullets.add(bullet);
        bullet.setDirection(angle);
        bullet.setSpeed(bulletSpeed);
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setBulletDamage(int bulletDamage) {
        this.bulletDamage = bulletDamage;
    }

    public void setBulletSpeed(float bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }

    public void setReloadTime(float reloadTime) {
        this.reloadTime = reloadTime;
    }

    public void setRegenTime(float regenTime) {
        this.regenTime = regenTime;
    }
}
