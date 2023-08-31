package pl.epsi.drawlib.transformations;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.function.UnaryOperator;

public class Move implements Transformation {

    private final Vector offset;

    public Move(Vector offset) {
        this.offset = offset;
    }

    public Move(Location offset) {
        this.offset = offset.toVector();
    }

    public UnaryOperator<Location> getTransformer() {
        return l -> l.add(offset);
    }

}
