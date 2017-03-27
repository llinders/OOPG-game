package nl.han.ica.testEngine;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import processing.core.PGraphics;

public class Skeleton extends AnimatedSpriteObject {
    private TestEngine engine;

    public Skeleton(TestEngine engine) {
        this(new Sprite("src/main/java/nl/han/ica/basedefenderworld/data/animations/skeleton_walking.gif"), 8);
        this.engine = engine;
    }

    public Skeleton(Sprite sprite, int totalFrames) {
        super(sprite, totalFrames);
    }

    @Override
    public void update(){
        nextFrame();
    }

    @Override
    public void draw(PGraphics g){

    }
}
