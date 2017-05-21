import javafx.scene.paint.Color;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.geometry.Vector;

public class MovingRectangle implements Drawing {

    @GadgetAnimation(min = -300, max = 300, speed = 100, start = true, loop = true)
    Double x = -300.0;

    @GadgetDouble(min = 10, max = 100)
    Double size = 30.0;

    @Override
    public void draw(View view) {
        DrawingUtils.clear(view, Color.hsb(0, 0, 0.2));

        Vector c = Vector.UNIT_X.mul(x);
        Vector r = Vector.DIAGONAL.mul(size);

        view.setStroke(Color.hsb(240, 0.5, 0.7));
        view.setLineWidth(3.0);
        view.strokeRectCentered(c, r);
    }

    public static void main(String[] args) { Drawing.launch(700, 300); }

}
