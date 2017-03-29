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

    public void triggerAlarm(String alarmName){
        round = calculateRound();
        spawnDelay = calculateSpawnDelay();
        currentEnemy = getRandomEnemy();
        PVector pos = calculateRandomEnemyPos(currentEnemy);
        spawnEnemy(currentEnemy, pos);
        startAlarm();
    }

    private void startAlarm(){
        Alarm alarm = new Alarm("Enemy Spawner", spawnDelay);
        alarm.addTarget(this);
        alarm.start();
    }

    private int calculateRound(){
        return (int) Math.sqrt(Enemy.getAmountOfEnemiesKilled() - 2) + 1;
    }

    private float calculateSpawnDelay(){
         return (float) (4 - 1.5 * Math.log(0.6 * round) + 1);
         //return (float) (4.5 - 2.4 * Math.log(0.09 * round) + 3;
    }

    private PVector calculateRandomEnemyPos(EnemyKind enemy){
        int y = rnd.nextInt(world.getHeight());
        switch (enemy) {
            case SKELETON:
                return new PVector(world.getWidth(), y);
            case SHIP:
                return new PVector(0, y);
        }
        return null;
    }

    private EnemyKind getRandomEnemy(){
        float rndFloat = rnd.nextFloat();
        if (rndFloat <= (0.80-0.2*round <= 0.5 ? 0.80-0.2*round : 0.5))
            return EnemyKind.SHIP; //TODO: skeleton van maken
        else
            return EnemyKind.SHIP;
    }
    
    private void spawnEnemy(EnemyKind currentEnemy, PVector pos){
        switch (currentEnemy){
            case SKELETON:
                //TODO: skeletons spawnen
                break;
            case SHIP:
                Sprite shipSprite = new Sprite("src/main/java/nl/han/ica/basedefenderworld/data/enemies/ship1.png");
                Ship ship = new Ship(world, shipSprite, 150, 1.2f, 30, (int)pos.x, (int)pos.y);
                world.addGameObject(ship, (int)pos.x, (int)pos.y);
                break;
        }
    }
}
