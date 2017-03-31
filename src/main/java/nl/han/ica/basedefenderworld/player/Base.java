package nl.han.ica.basedefenderworld.player;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.basedefenderworld.BaseDefenderWorld;
import nl.han.ica.basedefenderworld.enemies.Enemy;
import processing.core.PGraphics;

import java.util.ArrayList;

public class Base extends GameObject implements IAlarmListener {
    private BaseDefenderWorld world;
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
        regenTime = 1.8f;
        reloadTime = 0.8f;
        gunIsReloaded = true;

        setWidth(size);
        setHeight(size);
        startAlarmFireDelay();
        startAlarmRegenDelay();
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
        if (Enemy.getAmountOfEnemiesKilled() % 16 == 0){ //every 16 kills you get a powerup
            unclaimedPowerups = Enemy.getAmountOfEnemiesKilled()/16-usedPowerups;
        }

    }

    @Override
    public void draw(PGraphics g) {
        if (health <= 0){
            world.pauseGame();
            g.textSize(70);
            g.fill(0);
            g.text("Game over", world.getWidth()/2 + 3, world.getHeight()/2 + 3);
            g.fill(255);
            g.text("Game over", world.getWidth()/2, world.getHeight()/2);
        }
    }

    /**
     * Starts an alarm to delay the amount of bullets you can shoot per second
     */
    private void startAlarmFireDelay() {
        Alarm alarm = new Alarm("Fire delay", reloadTime);
        alarm.addTarget(this);
        alarm.start();
    }

    /**
     * Starts an alarm to delay the regeneration of your health
     */
    private void startAlarmRegenDelay(){
        Alarm alarm = new Alarm("Regen delay", regenTime);
        alarm.addTarget(this);
        alarm.start();
    }

    @Override
    public void triggerAlarm(String alarmName) {
        switch (alarmName){
            case "Fire delay":
                if (firing && gunIsReloaded)
                    fireBullet(mouseX, mouseY);
                gunIsReloaded = true;
                startAlarmFireDelay();
                break;
            case "Regen delay":
                regen();
                startAlarmRegenDelay();
                break;
        }
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

    public void regen(){
        final int REGENAMOUNT = 1;
        if (health + REGENAMOUNT >= maxHealth)
            return;
        health += REGENAMOUNT;
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

    public int getBulletDamage() {
        return bulletDamage;
    }

    public float getBulletSpeed() {
        return bulletSpeed;
    }

    public float getReloadTime() {
        return reloadTime;
    }

    public float getRegenTime() {
        return regenTime;
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
