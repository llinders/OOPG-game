package nl.han.ica.waterworld;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import processing.core.PGraphics;

public class Skeleton extends AnimatedSpriteObject {

    public Skeleton() {
        super(new Sprite("src/main/java/nl/han/ica/basedefenderworld/data/animations/skeleton_attacking.gif"), 1);
    }

    @Override
    public void update(){
    }

    @Override
    public void draw(PGraphics g){

    }
}
