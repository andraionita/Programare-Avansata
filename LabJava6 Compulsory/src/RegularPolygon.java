/**
 * @author Andra Ionita grupa 2A7
 * In aceasta clasa am creat clasa care se ocupa cu crearea formelor in functie de coordonatele click-ului pe canvas si a numarului
 * de laturi, culorii si dimensiunii trimise ca input.
 */

import java.awt.*;


    public class RegularPolygon extends Polygon {
        public RegularPolygon(int x0, int y0, int radius, int sides) {
            double alpha = 2 * Math.PI / sides;
            for (int i = 0; i < sides; i++) {
                double x = x0 + radius * Math.cos(alpha * i);
                double y = y0 + radius * Math.sin(alpha * i);
                this.addPoint((int) x, (int) y);
            }
        }
    }

