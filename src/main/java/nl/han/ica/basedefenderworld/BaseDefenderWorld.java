package nl.han.ica.basedefenderworld;

import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.IPersistence;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.basedefenderworld.enemies.EnemySpawner;
import nl.han.ica.basedefenderworld.player.Base;
import nl.han.ica.basedefenderworld.player.Cannon;
import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;

public class BaseDefenderWorld extends GameEngine {
    private IPersistence persistence;
    private EnemySpawner enemySpawner;

    public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.basedefenderworld.BaseDefenderWorld"});
    }

    @Override
    public void setupGame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        createView(width, height);
        createObjects();
    }

    @Override
    public void update() {
    }

    /**
     * Creates the current view for the game
     * @param width set the width of the application's frame
     * @param height set the height of the application's frame
     */
    private void createView(int width, int height) {
        int imgW = 1920, imgH = 1088; //change these accordingly to the width and height of the 'tmp' PImage
        PImage tmp = loadImage("nl/han/ica/basedefenderworld/data/world_map.png");
        PImage background = createImage(width, height, RGB);
        background.copy(tmp, 0, 0, imgW, imgH, 0, 0, width, height);
        View view = new View(width, height);
        view.setBackground(background);
        setView(view);
        size(width, height);
    }

    private void createObjects() {
        int baseSize = height/34*13; //base exists of 14 tiles each 32 pixels width/height
        int barrelSize = baseSize/6;
        Base base = new Base(this, baseSize);
        //Barrel barrel = new Barrel(barrelSize, new PVector(getWidth()/2, getHeight()/2));
        Cannon cannon = new Cannon();
        addGameObject(cannon, getWidth()/2-22, getHeight()/2-31, 3);
        addGameObject(base, getWidth() /2 - baseSize/2, getHeight()/2 - baseSize/2, 1);
        //addGameObject(barrel, getWidth()/2, getHeight()/2, 2);
        EnemySpawner enemySpawner = new EnemySpawner(this);

    }
}
