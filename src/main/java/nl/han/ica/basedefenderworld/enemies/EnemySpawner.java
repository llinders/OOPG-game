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
        round = 1;
        spawnDelay = calculateSpawnDelay();

        startAlarm();
    }

    /**
     * Gets triggered when the spawn delay for enemies has expired
     * All calculations are performed, an enemy gets spawned and a new alarms starts
     *
     * @param alarmName
     */
    public void triggerAlarm(String alarmName) {
        if (world.getThreadState() == false) {
            round = world.calculateRound();
            spawnDelay = calculateSpawnDelay();
            currentEnemy = getRandomEnemy();
            PVector pos = calculateRandomEnemyPos(currentEnemy);
            spawnEnemy(currentEnemy, pos);
            startAlarm();
        }
    }

    private void startAlarm() {
        Alarm alarm = new Alarm("Enemy Spawner", spawnDelay);
        alarm.addTarget(this);
        alarm.start();
    }

    /**
     * Calculate the spawn delay of the enemies based on the round you are currently in
     *
     * @return the spawn delay in seconds
     */
    private float calculateSpawnDelay() {
        return (float) (4 - 1.5 * Math.log(0.6 * round) + 1);
        //return (float) (4.5 - 2.4 * Math.log(0.09 * round) + 3;
    }

    /**
     * Calculate the position where the next enemy needs to be spawned
     *
     * @param enemy an enemy from the EnemyKind enum for which the position needs to be calculated
     * @return a random position on the side of the map (ship on the left (water), skeletons on the right (land))
     */
    private PVector calculateRandomEnemyPos(EnemyKind enemy) {
        int x;
        int y;
        switch (enemy) {
            case SKELETON:
                int randomSkeleton = rnd.nextInt(2);
                switch (randomSkeleton) {
                    case 0:
                        x = rnd.nextInt(200) + world.getWidth();
                        y = rnd.nextInt(world.getHeight() - 200);
                        break;
                    case 1:
                        x = world.getWidth() - rnd.nextInt((int) (world.getWidth() / 3.5));
                        y = -rnd.nextInt(200);
                        break;
                    default:
                        x = 0;
                        y = 0;
                }
                return new PVector(x, y);
            case SHIP:
                int randomShip = rnd.nextInt(3);
                switch (randomShip) {
                    case 0:
                        x = -100;
                        y = rnd.nextInt(world.getHeight());
                        break;
                    case 1:
                        x = rnd.nextInt((int) (world.getWidth() / 4.5));
                        y = -100;
                        break;
                    case 2:
                        x = rnd.nextInt((int) (world.getWidth() / 4.5));
                        y = world.getHeight();
                        break;
                    default:
                        x = 0;
                        y = 0;
                }
                return new PVector(x, y);
        }
        return null;
    }

    /**
     * Return a random enemy based on chances
     *
     * @return A random enemy from the EnemyKind enum
     */
    private EnemyKind getRandomEnemy() {
        float rndFloat = rnd.nextFloat();
        if (rndFloat <= (1 / (round * 0.22) >= 0.45 ? 1 / (round * 0.22) : 0.45))
            return EnemyKind.SKELETON;
        else
            return EnemyKind.SHIP;
    }

    /**
     * Handles the spawning process of enemies
     *
     * @param currentEnemy the enemy that currently has to be spawned
     * @param pos          the posistion the enemy has to be spawned
     */
    private void spawnEnemy(EnemyKind currentEnemy, PVector pos) {
        if (world.paused)
            return;
        switch (currentEnemy) {
            case SKELETON:
                if (rnd.nextFloat() <= 0.04)
                    spawnSkeletonWave(); //small chance to spawn a big wave of skeletons
                Sprite skeletonSprite = new Sprite("src/main/java/nl/han/ica/basedefenderworld/data/animations/skeleton_walking.gif");
                Skeleton skeleton = new Skeleton(world, skeletonSprite, 20, 1.1f * (round / 40 + 1), (int) pos.x, (int) pos.y);
                world.addGameObject(skeleton, (int) pos.x, (int) pos.y);
                break;
            case SHIP:
                Sprite shipSprite = new Sprite("src/main/java/nl/han/ica/basedefenderworld/data/enemies/ship1.png");
                Ship ship = new Ship(world, shipSprite, 150, 0.6f * (round / 40 + 1), (int) pos.x, (int) pos.y);
                world.addGameObject(ship, (int) pos.x, (int) pos.y);
                break;
        }
    }

    private void spawnSkeletonWave() {
        Sprite skeletonSprite = new Sprite("src/main/java/nl/han/ica/basedefenderworld/data/animations/skeleton_walking.gif");
        int waveSize = rnd.nextInt(round + 4) + 2;
        for (int i = 0; i < waveSize; i++) {
            PVector pos = calculateRandomEnemyPos(EnemyKind.SKELETON);
            Skeleton skeleton = new Skeleton(world, skeletonSprite, 20, 1.1f, (int) pos.x, (int) pos.y);
            world.addGameObject(skeleton, (int) pos.x, (int) pos.y);
        }
    }
}
