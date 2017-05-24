package particles.stars;


import javafx.scene.paint.Color;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.geometry.Vector;
import particles.ParticleSystemInstant;

import java.util.ArrayList;
import java.util.List;

public class MovingStars extends ParticleSystemInstant<Star> {

    @GadgetAnimation(start = true)
    Double time = 0.0;

    private Vector p, v;
    private double boundsR = 200;

    private double timeInterval = 0.001;

    private double tLast = 0;

    @Override
    public List<Star> generate(double timeFrom, double timeTo) {
        List<Star> stars = new ArrayList<>();


        stars.add(new Star(
                timeFrom,
                Vector.randomGaussian(5),
                Vector.randomGaussian(300),
                null,
                1,
                boundsR,
                Color.WHITE
        ));


        return stars;
    }
}
