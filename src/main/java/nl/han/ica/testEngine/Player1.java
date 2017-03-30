package nl.han.ica.testEngine;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

import java.util.List;

/**
 * @author Ralph Niels
 * De spelerklasse (het paarse visje)
 */
public class Player1 extends SpriteObject implements ICollidableWithGameObjects {

    /**
     * Constructor
     * @param //world Referentie naar de wereld
     */
    public Player1() {
        super(new Sprite("src/main/java/nl/han/ica/basedefenderworld/data/animations/skeleton_walking.gif"));

    }

    @Override
    public void update() {
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> g){

    }
}
