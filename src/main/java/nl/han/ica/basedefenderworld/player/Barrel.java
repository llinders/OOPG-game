package nl.han.ica.basedefenderworld.player;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PVector;

import java.util.List;

public class Barrel extends GameObject implements ICollidableWithGameObjects {
    private float x, y, length;
    private int mouseX, mouseY;
    PVector loc;

    public Barrel(int length, PVector loc) {
        this.length = length;
        this.loc = loc;
    }

    public void update(){
    }

    public void draw(PGraphics g){
        float angle = (float) Math.atan2(mouseY - loc.y, mouseX - loc.x);
        x = length * (float) Math.cos(angle) + loc.x;
        y = length * (float) Math.sin(angle) + loc.y;
        g.stroke(0);
        g.strokeWeight(20);
        g.strokeCap(PConstants.SQUARE);
        g.line(loc.x, loc.y, x, y);
        g.noStroke();
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {

    }

    @Override
    public void mouseMoved(int x, int y){
        mouseX = x;
        mouseY = y;
    }

    @Override
    public void mouseDragged(int x, int y, int button){
        mouseX = x;
        mouseY = y;
    }


}
