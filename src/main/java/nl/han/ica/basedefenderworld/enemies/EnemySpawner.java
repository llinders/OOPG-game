package nl.han.ica.basedefenderworld.enemies;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.basedefenderworld.BaseDefenderWorld;
import processing.core.PVector;

import java.util.Random;

public class EnemySpawner implements IAlarmListener {
    private BaseDefenderWorld world;
    private EnemyKind currentEnemy;
    private int round;
    private float spawnDelay;
    private Random rnd;

    public EnemySpawner(BaseDefenderWorld world) {
        this.world = world;
        rnd = new Random();
        round = 7;
        spawnDelay = calculateSpawnDelay();

        startAlarm();
    }

    /**
     * Gets triggered when the spawn delay for enemies has expired
     * All calculations are performed, an enemy gets spawned and a new alarms starts
     * @param alarmName
     */
    public void triggerAlarm(String alarmName) {
        round = calculateRound();
        spawnDelay = calculateSpawnDelay();
        currentEnemy = getRandomEnemy();
        PVector pos = calculateRandomEnemyPos(currentEnemy);
        spawnEnemy(currentEnemy, pos);
        startAlarm();
    }

    private void startAlarm() {
        Alarm alarm = new Alarm("Enemy Spawner", spawnDelay);
        alarm.addTarget(this);
        alarm.start();
    }

    /**
     * Calculate the round you are currently in based on how many enemies have died
     * @return the round number
     */
    private int calculateRound() {
        return (int) Math.sqrt(Enemy.getAmountOfEnemiesKilled() - 2) + 1;
    }

    /**
     * Calculate the spawn delay of the enemies based on the round you are currently in
     * @return the spawn delay in seconds
     */
    private float calculateSpawnDelay() {
        return (float) (4 - 1.5 * Math.log(0.6 * round) + 1);
        //return (float) (4.5 - 2.4 * Math.log(0.09 * round) + 3;
    }

    /**
     * Calculate the position where the next enemy needs to be spawned
     * @param enemy an enemy from the EnemyKind enum for which the position needs to be calculated
     * @return a random position on the side of the map (ship on the left (water), skeletons on the right (land))
     */
    private PVector calculateRandomEnemyPos(EnemyKind enemy) {
        int y;
        int x;
        switch (enemy) {
            case SKELETON:
                int randomSkeleton = rnd.nextInt(2);
                if(randomSkeleton == 0) {
                    y = rnd.nextInt(world.getHeight() - 200);
                    return new PVector(world.getWidth(), y);
                }else{
                    x = world.getWidth()-rnd.nextInt((int)(world.getWidth()/3.5));
                    return new PVector(x,0);
                }
            case SHIP:
                int randomShip = rnd.nextInt(3);
                if(randomShip == 0) {
                    y = rnd.nextInt(world.getHeight());
                    return new PVector(0, y);
                }else if(randomShip == 1) {
                    y = 0;
                    x = rnd.nextInt((int)(world.getWidth()/4.5));
                    return new PVector(x, y);
                }else{
                    y = world.getHeight();
                    x = rnd.nextInt((int)(world.getWidth()/4.5));
                    return new PVector(x,y);
            }
        }
        return null;
    }

    /**
     * Return a random enemy based on chances
     * @return A random enemy from the EnemyKind enum
     */
    private EnemyKind getRandomEnemy() {
        float rndFloat = rnd.nextFloat();
        System.out.println(round);
        if (rndFloat <= (1/(round*0.2) >= 0.4 ? 1/(round*0.2) : 0.4))
            return EnemyKind.SKELETON;
        else
            return EnemyKind.SHIP;
    }

    /**
     * Handles the spawning process of enemies
     * @param currentEnemy the enemy that currently has to be spawned
     * @param pos the posistion the enemy has to be spawned
     */
    private void spawnEnemy(EnemyKind currentEnemy, PVector pos) {
        switch (currentEnemy) {
            case SKELETON:
                Sprite skeletonSprite = new Sprite("src/main/java/nl/han/ica/basedefenderworld/data/animations/skeleton_walking.gif");
                Skeleton skeleton = new Skeleton(world, skeletonSprite, 20, 1, (int) pos.x, (int) pos.y);
                world.addGameObject(skeleton, (int) pos.x, (int) pos.y);
                break;
            case SHIP:
                Sprite shipSprite = new Sprite("src/main/java/nl/han/ica/basedefenderworld/data/enemies/ship1.png");
                Ship ship = new Ship(world, shipSprite, 150, 0.8f, (int) pos.x, (int) pos.y);
                world.addGameObject(ship, (int) pos.x, (int) pos.y);
                break;
        }
    }
}
