package simulations;


import javafx.scene.paint.Color;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.geometry.Vector;

public class BouncingBoxesTicks implements Drawing {

    final Vector boxP = new Vector(-300, -200);
    final Vector boxD = new Vector(600, 400);

    @GadgetAnimation(start = true)
    double time = 0;

    int nBoxes = 16;
    Box[] boxes;
    Vector r =  new Vector(10);

    double timeTickInterval = 0.001;

    @GadgetBoolean
    Boolean showVelocities = false;

    @GadgetBoolean
    Boolean switchToCircles = false;

    class Box {
        double t;
        Vector p, v;
        Color c;

        public Box(Vector p, Vector v, Color c) {
            this.t = 0;
            this.p = p;
            this.v = v;
            this.c = c;
        }

        public Vector getPosition(double time) {
            while (t + timeTickInterval <= time) {
                if (p.x < 10) v = new Vector(-v.x, v.y);
                if (p.x > boxD.x - 10) v = new Vector(-v.x, v.y);
                if (p.y < 10) v = new Vector(v.x, -v.y);
                if (p.y > boxD.y - 10) v = new Vector(v.x, -v.y);

                p = p.add(v.mul(timeTickInterval));
                t += timeTickInterval;
            }

            return p;
        }

        public Vector getVelocity() {
            return this.v;
        }

    }

    @Override
    public void init() {
        boxes = new Box[nBoxes];
        for (int i = 0; i < nBoxes; i++) {
            boxes[i] = new Box(
                    Vector.randomInRect(boxD),
                    Vector.randomGaussian(100),
                    Color.hsb(360 * i / nBoxes, 0.7, 0.9)
            );
        }
    }

    @Override
    public void draw(View view) {
        DrawingUtils.clear(view, Color.hsb(0, 0, 0.3));
        view.setFill(Color.hsb(0, 0, 0, 0.3));
        view.fillRect(boxP, boxD);

        view.setStroke(Color.WHITE);
        for (Box box : boxes) {
            Vector p = boxP.add(box.getPosition(time));
            Vector v = box.getVelocity();

            view.setFill(box.c);

            if (switchToCircles)
                view.fillCircleCentered(p, r.x);
            else
                view.fillRectCentered(p, r);

            if (showVelocities) {
                DrawingUtils.drawArrow(view, p, v.mul(0.3));
            }
        }
    }

    public static void main(String[] args) {
        Drawing.launch(800, 600);
    }

}
