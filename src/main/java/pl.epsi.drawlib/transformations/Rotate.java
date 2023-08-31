package pl.epsi.drawlib.transformations;

import org.bukkit.Location;

import java.util.function.UnaryOperator;

public class Rotate implements Transformation {

    final private Location center;
    final private float angle;

    public Rotate(final Location center, final float angle) {
        this.center = center;
        this.angle = angle;
    }

    public UnaryOperator<Location> getTransformer() {
        return l -> new Location(
                l.getWorld(),
                center.getX() + (l.getX() - center.getX()) * Math.cos(Math.toRadians(angle)) - (l.getZ() - center.getZ()) * Math.sin(Math.toRadians(angle)),
                l.getY(),
                center.getZ() + (l.getX() - center.getX()) * Math.sin(Math.toRadians(angle)) + (l.getZ() - center.getZ()) * Math.cos(Math.toRadians(angle))
        );

    }

}
