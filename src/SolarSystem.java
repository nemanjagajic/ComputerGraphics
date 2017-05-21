import javafx.scene.paint.Color;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.geometry.Vector;

public class SolarSystem implements Drawing {

    @GadgetDouble(min = 90, max = 400)
    Double l1 = 100.0;

    @GadgetDouble(min = 90, max = 400)
    Double l2 = 210.0;

    @GadgetDouble(min = 90, max = 400)
    Double l3 = 290.0;

    @GadgetAnimation(min = 0, max = 2 * Math.PI, start = true, loop = true, speed = 1.2)
    Double phi1 = 0.0;

    @GadgetAnimation(min = 0, max = 2 * Math.PI, start = true, loop = true, speed = 0.4)
    Double phi2 = 0.0;

    @GadgetAnimation(min = 0, max = 2 * Math.PI, start = true, loop = true, speed = 0.8)
    Double phi3 = 0.0;

    Vector p0 = Vector.ZERO;

    Integer lineWidth = 1;

    Boolean lineVisible = true;

    @Override
    public void draw(View view) {
        DrawingUtils.clear(view, Color.hsb(0, 0, 0.2));

        Vector p1 = Vector.polar(l1, phi1);
        Vector p2 = Vector.polar(l2, phi2);
        Vector p3 = Vector.polar(l3, phi3);

        if (!lineVisible) {
            lineWidth = 0;
        } else {
            lineWidth = 1;
        }

        // Drawing lines
        view.setStroke(Color.WHITE);
        view.setLineWidth(lineWidth);
        view.strokeLine(p0, p1);
        view.strokeLine(p0, p2);
        view.strokeLine(p0, p3);

        // Drawing circles
        view.setFill(Color.hsb(45, 1, 1));
        view.fillCircleCentered(p0, 40);

        view.setFill(Color.hsb(120, 0.5, 0.8));
        view.fillCircleCentered(p1, 15);

        view.setFill(Color.hsb(240, 0.5, 0.8));
        view.fillCircleCentered(p2, 28);

        view.setFill(Color.hsb(30, 0.5, 0.8));
        view.fillCircleCentered(p3, 21);
    }

    public static void main(String[] args) {
        Drawing.launch(700, 700);
    }
}
