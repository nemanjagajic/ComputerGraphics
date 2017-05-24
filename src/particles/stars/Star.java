package particles.stars;


import javafx.scene.paint.Color;
import mars.drawingx.drawing.View;
import mars.geometry.Vector;
import particles.Particle;

public class Star extends Particle {

    private double t0;
    private Vector p0, v0, a;
    private double r;
    private double boundsR;
    private Color color;

    public Star(double t0, Vector p0, Vector v0, Vector a, double r, double boundsR, Color c) {
        this.t0 = t0;
        this.p0 = p0;
        this.v0 = v0;
        this.a = a;
        this.r = r;
        this.boundsR = boundsR;
        this.color = c;
    }

    private Vector getPosition(double time) {
        double timeAlive = time - t0;
        return p0.add(v0.mul(timeAlive));
    }

    @Override
    public void draw(View view, double time) {
        Vector p = getPosition(time);
        view.setFill(color);
        view.fillCircleCentered(p, r);
    }

    @Override
    public boolean isAlive(double time) {
        Vector pos = getPosition(time);
        return Math.sqrt(pos.x * pos.x + pos.y * pos.y) <= boundsR;
    }

}
