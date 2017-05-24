package particles.stars;


import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.geometry.Vector;

public class StarsTest implements Drawing {

    @GadgetAnimation(start = true)
    Double time = 0.0;

    MovingStars ms = new MovingStars();

    @Override
    public void draw(View view) {
        DrawingUtils.clear(view, Color.hsb(0, 0, 0.1));
        ms.draw(view, time);
    }

    public StarsTest() {
        DrawingApplication.launch(this, new Vector(800, 600), true, false,
                this, MovingStars.class);
    }

    public static void main(String[] args) {
        new StarsTest();
    }

}
