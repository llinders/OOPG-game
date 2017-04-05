package nl.han.ica.basedefenderworld.player;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import processing.core.PApplet;
import processing.core.PGraphics;

public class Cannon extends SpriteObject {
    private int mouseX, mouseY;
    private float rotatiehoek;

    public Cannon() {
        this(new Sprite("src/main/java/nl/han/ica/basedefenderworld/data/Cannon.png"));
        rotatiehoek = 45;
    }

    private Cannon(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void update() {
        rotatiehoek = getAngleFrom(mouseX, mouseY);
    }

    @Override
    public void draw(PGraphics g) {
        g.pushMatrix();
        g.translate(getCenterX(), getCenterY());
        g.rotate(PApplet.radians(rotatiehoek));
        g.image(getImage(), -width / 2, -height / 2);
        g.popMatrix();
    }

    public static float getRotationInRadians(float rotationInDegrees) {
        float rotationInRadians = (float) (PApplet.radians(rotationInDegrees) % Math.PI);
        rotationInRadians = (float) (((rotationInRadians > Math.PI * 0.5 && rotationInRadians < Math.PI * 1)
                || (rotationInRadians > Math.PI * 1.5 && rotationInRadians < Math.PI * 2)) ? Math.PI - rotationInRadians
                : rotationInRadians);
        return rotationInRadians;
    }

    @Override

    public float getWidth() {
        // met dank aan
        // http://stackoverflow.com/questions/10392658/calculate-the-bounding-boxs-x-y-height-and-width-of-a-rotated-element-via-jav
        float rotationInRadians = getRotationInRadians(rotatiehoek);
        return (float) (Math.sin(rotationInRadians) * height +
                Math.cos(rotationInRadians) * width);
    }

    @Override
    public float getHeight() {
        float rotationInRadians = getRotationInRadians(rotatiehoek);
        return (float) (Math.sin(rotationInRadians) * width + Math.cos(rotationInRadians) * height);
    }

    @Override
    public float getX() {
        return -(getWidth() / 2) + getCenterX();
    }

    @Override
    public float getY() {
        return -(getHeight() / 2) + getCenterY();
    }

    @Override
    public void mouseMoved(int x, int y) {
        mouseX = x;
        mouseY = y;
    }

    @Override
    public void mouseDragged(int x, int y, int button) {
        mouseX = x;
        mouseY = y;
    }
}

