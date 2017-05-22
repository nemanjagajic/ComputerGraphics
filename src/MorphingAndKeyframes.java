import javafx.scene.paint.Color;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.NoGadget;
import mars.geometry.Vector;

public class MorphingAndKeyframes implements Drawing {

    @NoGadget
    final Vector fieldB = new Vector(-400, -300);

    @NoGadget
    final Vector fieldD = new Vector(800, 600);

    final int maxStates = 100;
    final int maxVerticies = 100;

    Vector[][] polygons;
    double[] hues;

    Boolean showKeyFrames = true;

    Integer nStates = 3;
    Integer nVerticies = 3;

    final double stateDuration = 1.0;

    @GadgetAnimation(start = true)
    Double time = 0.0;

    @Override
    public void init() {
        polygons = new Vector[maxStates][maxVerticies];
        hues = new double[maxStates];

        for (int k = 0; k < maxStates; k++) {
            for (int i = 0; i < maxVerticies; i++) {
                polygons[k][i] = Vector.randomInRect(fieldB, fieldD);
            }
            hues[k] = Math.random() * 360;
        }
    }

    @Override
    public void draw(View view) {
        DrawingUtils.clear(view, Color.hsb(0, 0, 0.2));

        if (showKeyFrames) {
            view.setStroke(Color.GRAY);
            view.setLineDashes(4);
            view.setLineWidth(1);

            for (int k = 0; k < nStates; k++) {
                view.strokePolygon(nVerticies, polygons[k]);
            }
        }

        int k0 = (int)(time / stateDuration) % nStates; // current state
        int k1 = (k0 + 1) % nStates; // next state

        double t = time % stateDuration;

        Vector[] polygon = new Vector[nVerticies];
        for (int i = 0; i < nVerticies; i++) {
            polygon[i] = Vector.lerp(polygons[k0][i], polygons[k1][i], t);
        }

        // Drawing keyframes
        Color c0 = Color.hsb(hues[k0], 0.7, 0.7, 0.7);
        Color c1 = Color.hsb(hues[k1], 0.7, 0.7, 0.7);
        view.setFill(c0.interpolate(c1, t));
        view.fillPolygon(polygon);
    }

    public static void main(String[] args) {
        Drawing.launch(800, 600);
    }
}
