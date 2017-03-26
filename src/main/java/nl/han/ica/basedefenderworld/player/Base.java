package nl.han.ica.basedefenderworld.player;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.basedefenderworld.BaseDefenderWorld;
import processing.core.PConstants;
import processing.core.PGraphics;

import java.util.ArrayList;
import java.util.List;

public class Base extends GameObject implements ICollidableWithGameObjects, IAlarmListener {
    private BaseDefenderWorld world;
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private int size, health, bulletDamage, unclaimedPowerups, mouseX, mouseY;
    private float bulletSpeed, reloadTime, regenTime;
    private boolean firing;

    public Base(BaseDefenderWorld world, int size) {
        this.world = world;
        this.size = size;
        health = 100;
        bulletDamage = 5;
        bulletSpeed = 7.8f;
        regenTime = 30.0f;
        reloadTime = 0.75f;

        setWidth(size);
        setHeight(size);

        startAlarm();
    }

    @Override
    public void update() {
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
        g.noStroke();
        g.fill(255, 0, 0);
        g.ellipseMode(PConstants.CORNER);
        g.ellipse(getX(), getY(), size, size);
    }

    private void startAlarm() {
        Alarm alarm = new Alarm("Fire delay",reloadTime);
        alarm.addTarget(this);
        alarm.start();
    }

    @Override
    public void triggerAlarm(String alarmName) {
        if (firing)
            fireBullet(mouseX, mouseY);
        startAlarm();
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {

    }

    @Override
    public void mousePressed(int x, int y, int button) {
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

    private void fireBullet(int x, int y){
        float angle = getAngleFrom(x, y);
        Bullet bullet = new Bullet(world, size/10, bulletDamage);
        world.addGameObject(bullet, getX() + size / 2, getY() + size / 2, 0);
        bullets.add(bullet);
        bullet.setDirection(angle);
        bullet.setSpeed(bulletSpeed);

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
