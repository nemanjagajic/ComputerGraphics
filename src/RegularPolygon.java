import javafx.scene.paint.Color;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.geometry.Vector;

public class RegularPolygon implements Drawing {

    Integer n = 5;

    @GadgetDouble(min = 0, max = 300)
    Double r = 200.0;

    @GadgetDouble(min = 0, max = 2 * Math.PI)
    Double alpha = 0.0;

    @Override
    public void draw(View view) {
        DrawingUtils.clear(view, Color.hsb(0, 0, 0.2));
        view.setStroke(Color.GRAY);
        view.setLineWidth(2.0);

        for (int i = 0; i < n; i++) {
            view.strokeLine(getVertex(i), getVertex(i + 1));
        }
    }

    Vector getVertex(int i) { return Vector.polar(r, alpha + i * 2 * Math.PI / n); }

    public static void main(String[] args) { Drawing.launch(500, 500); }

}
