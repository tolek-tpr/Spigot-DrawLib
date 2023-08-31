package pl.epsi.drawlib;

import org.bukkit.Location;
import org.bukkit.Material;

import pl.epsi.drawlib.shapes.Circle;
import pl.epsi.drawlib.shapes.Line;
import pl.epsi.drawlib.shapes.Square;

public class Draw {

    public static Selection circle(final Location center, final int radius, final Material m) {
        return new Circle(center, radius).select(false).stroke(m);
    }

    public static Selection line(final Location start, final Location end, final Material m) {
        return new Line(start, end).select(true).stroke(m);
    }
    
    public static Selection square(final Location center, final int length, final Material m) {
        return new Square(center, length).select(false).stroke(m);
    }

}
