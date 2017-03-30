package nl.han.ica.basedefenderworld.player;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.basedefenderworld.BaseDefenderWorld;
import nl.han.ica.basedefenderworld.enemies.Enemy;
import nl.han.ica.basedefenderworld.player.powerups.Powerup;
import processing.core.PGraphics;

import java.util.ArrayList;

public class Base extends GameObject implements IAlarmListener {
    private BaseDefenderWorld world;
    private Powerup powerup;
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private int size, health, maxHealth, bulletDamage, unclaimedPowerups, usedPowerups;
    private int mouseX, mouseY;
    private float bulletSpeed, reloadTime, regenTime;
    private boolean firing, gunIsReloaded;


    public Base(BaseDefenderWorld world, int size) {
        this.world = world;
        this.size = size;
        health = 100;
        maxHealth = health;
        bulletDamage = 28;
        bulletSpeed = 7.8f;
        regenTime = 30.0f;
        reloadTime = 0.75f;
        gunIsReloaded = true;
        powerup = new Powerup(this);
        world.addGameObject(powerup);

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
        if (Enemy.getAmountOfEnemiesKilled() % 15 == 0){ //every 15 kills you get a powerup
            unclaimedPowerups = Enemy.getAmountOfEnemiesKilled()/15-usedPowerups;
        }
    }

    @Override
    public void draw(PGraphics g) {
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
    public void mousePressed(int x, int y, int button) {
        if (gunIsReloaded) {
            fireBullet(x, y);
            gunIsReloaded = false;
        }
        firing = true;
        mouseX = x;
        mouseY = y;
    }

    @Override
    public void mouseReleased(int x, int y, int button) {
        firing = false;
    }

    @Override
    public void mouseDragged(int x, int y, int button) {
        mouseX = x;
        mouseY = y;
    }

    /**
     * Fires a bullet towards the given coordinates
     *
     * @param x X position
     * @param y Y position
     */
    private void fireBullet(int x, int y) {
        setX(world.width / 2 - size / 2);
        float angle = getAngleFrom(x, y);
        Bullet bullet = new Bullet(size / 25, bulletDamage);
        world.addGameObject(bullet, world.width / 2, world.height / 2, 0);
        bullets.add(bullet);
        bullet.setDirection(angle);
        bullet.setSpeed(bulletSpeed);
    }

    public void increaseUsedPowerups(){
        usedPowerups++;
    }

    public void decreaseUnclaimedPowerups(){
        unclaimedPowerups--;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getUnclaimedPowerups() {
        return unclaimedPowerups;
    }

    public void setUnclaimedPowerups(int unclaimedPowerups) {
        this.unclaimedPowerups = unclaimedPowerups;
    }

    public void setUsedPowerups(int usedPowerups) {
        this.usedPowerups = usedPowerups;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
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
