package nl.han.ica.basedefenderworld;

import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.IPersistence;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.basedefenderworld.enemies.EnemySpawner;
import nl.han.ica.basedefenderworld.player.Barrel;
import nl.han.ica.basedefenderworld.player.Base;
import processing.core.PApplet;
import processing.core.PVector;

public class BaseDefenderWorld extends GameEngine {
    private IPersistence persistence;
    private EnemySpawner enemySpawner;
    private Base base;
    private Barrel barrel;

    public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.basedefenderworld.BaseDefenderWorld"});
    }

    @Override
    public void setupGame() {
        int width = 800;
        int height = 600;

        createView(width, height);
        createObjects();

        setFPSCounter(true);
    }

    @Override
    public void update() {
    }

    private void createView(int width, int height) {
        View view = new View(width, height);
        view.setBackground(240, 240, 240);
        setView(view);
        size(width, height);
    }

    private void createObjects() {
        base = new Base(this, 100);
        barrel = new Barrel(100, new PVector(400, 300));
        addGameObject(base, getWidth() / 2, getHeight() / 2);
        addGameObject(barrel, getWidth() / 2, getHeight() / 2);
        System.out.println(getWidth());
        System.out.println(getHeight());
    }
}
