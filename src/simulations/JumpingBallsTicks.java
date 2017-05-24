package simulations;


import javafx.scene.paint.Color;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.geometry.Vector;

public class JumpingBallsTicks implements Drawing {

    final Vector boxP = new Vector(-400, -200);
    final Vector boxD = new Vector(800, 400);

    int numberOfBalls = 16;
    Ball[] balls;

    double r = 10;

    @GadgetAnimation(start = true)
    Double time = 0.0;

    double timeTickInterval = 0.001;
    Vector g = new Vector(0, -500);

    private class Ball {
        double t;
        Vector p, v;
        Color color;

        public Ball(Vector p, Vector v, Color color) {
            this.t = 0;
            this.p = p;
            this.v = v;
            this.color = color;
        }

        public Vector getPosition(double time) {
            while (t + timeTickInterval <= time) {
                if (v.y < 0 && p.y < 10) v = new Vector(v.x, -v.y);

                p = p.add(v.mul(timeTickInterval)).add(g.mul(timeTickInterval*timeTickInterval/2));
                v = v.add(g.mul(timeTickInterval));
                t += timeTickInterval;
            }

            return p;
        }

        public Vector getVelocity() { return this.v; }

    }

    @Override
    public void init() {
        balls = new Ball[numberOfBalls];
        for (int i = 0; i < numberOfBalls; i++) {
            balls[i] = new Ball(
                    Vector.randomInRect(boxD),
                    Vector.randomGaussian(40),
                    Color.hsb(360 * i / numberOfBalls, 0.7, 0.9)
            );
        }
    }

    @Override
    public void draw(View view) {
        DrawingUtils.clear(view, Color.hsb(0, 0, 0.2));
        view.setFill(Color.hsb(0, 0, 0, 0.3));
        view.fillRect(boxP, boxD);

        for (Ball b : balls) {
            Vector p = boxP.add(b.getPosition(time));
            Vector v = b.getVelocity();
            Color c = b.color;

            view.setFill(c);
            view.fillCircleCentered(p, r);
        }

    }

    public static void main(String[] args) {
        Drawing.launch(800, 600);
    }

}
