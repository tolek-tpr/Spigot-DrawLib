package pl.epsi.drawlib.shapes;

import org.bukkit.Location;
import org.bukkit.util.Vector;
import pl.epsi.drawlib.Selection;

public class Line {

    protected Location start;
    protected Location end;

    public Line(final Location start, final Location end) {
        this.start = start;
        this.end = end;
    }

    public Selection select(final boolean filled) {
        final Vector current = start.toVector();
        final Vector terminal = end.toVector();
        final Vector unit = terminal.clone().subtract(current).normalize();
        final double d = current.distance(terminal);

        final Selection s = new Selection();
        for (double l = 0; l < d + 1; ++l) {
            s.add(new Location(start.getWorld(), current.getX(), current.getY(), current.getZ()));
            current.add(unit);
        }
        return s;
    }

}
