package nl.han.ica.basedefenderworld;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.IPersistence;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.basedefenderworld.enemies.EnemySpawner;
import nl.han.ica.basedefenderworld.player.Base;
import nl.han.ica.basedefenderworld.player.Cannon;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

import java.awt.*;

public class BaseDefenderWorld extends GameEngine {
    private TextObject dashboardText;
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
        createDashboard((int) (width / 3.5), (int) (height / 4.6));
        createObjects();
    }

    @Override
    public void update() {
        refreshDashboardText();
    }

    /**
     * Creates the current view for the game
     * @param width  set the width of the application's frame
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
        int baseSize = height / 34 * 13; //base exists of 14 tiles each 32 pixels width/height
        Base base = new Base(this, baseSize);
        Cannon cannon = new Cannon();
        addGameObject(cannon, getWidth() / 2 - cannon.getWidth()/3.5f, getHeight() / 2 - cannon.getHeight()/2.5f, 3);
        addGameObject(base, getWidth() / 2 - baseSize / 2, getHeight() / 2 - baseSize / 2, 1);
        EnemySpawner enemySpawner = new EnemySpawner(this);
    }

    private void createDashboard(int dashboardWidth, int dashboardHeight) {
        Dashboard dashboard = new Dashboard(getWidth()/2-dashboardWidth/2, getHeight()-dashboardHeight-height/25, dashboardWidth, dashboardHeight);
        dashboardText = new TextObject("", dashboard);
        Sprite background;
        background = new Sprite("nl/han/ica/basedefenderworld/data/Bord.png");
        dashboard.setBackgroundImage(background);
        dashboard.addGameObject(dashboardText);
        addDashboard(dashboard);

    }

    private void refreshDashboardText() {
        dashboardText.setText("Ronde: ");
    }

}
