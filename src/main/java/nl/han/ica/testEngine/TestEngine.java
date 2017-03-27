package nl.han.ica.testEngine;

import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import processing.core.PApplet;

public class TestEngine extends GameEngine {

    public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.testEngine.TestEngine"});
    }

    @Override
    public void setupGame(){
        createObjects();
        createView(800, 600);
    }

    @Override
    public void update(){

    }

    private void createObjects(){
        Skeleton skeleton = new Skeleton(new Sprite("nl/han/ica/basedefenderworld/data/animations/skeleton_walking.gif"), 8);
        addGameObject(skeleton, width/2, height/2);
    }

    private void createView(int width, int height){
        View view = new View(width, height);
        setView(view);
        size(width, height);
    }
}
