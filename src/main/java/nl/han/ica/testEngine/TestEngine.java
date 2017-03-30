package nl.han.ica.testEngine;

import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import processing.core.PApplet;

public class TestEngine extends GameEngine {

    public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.testEngine.TestEngine"});
    }

    @Override
    public void setupGame(){
        createView(800, 600);
        createObjects();
    }

    @Override
    public void update(){

    }

    private void createObjects(){
        Player1 player = new Player1();
        addGameObject(player, 300, 200);
    }

    private void createView(int width, int height){
        View view = new View(width, height);
        view.setBackground(220, 220, 220);
        setView(view);
        size(width, height);
    }
}
