package pl.epsi.drawlib.shapes;

import org.bukkit.Location;
import pl.epsi.drawlib.Selection;

public class Arc implements Shape {

    protected Location center;
    protected int radius;
    protected float startAngle;
    protected float endAngle;
    protected float step;

    public Arc(final Location center, final int radius, final float startAngle, final float endAngle, final float step) {
        this.center = center;
        this.radius = radius;
        this.startAngle = startAngle < endAngle ? startAngle : endAngle;
        this.endAngle = startAngle < endAngle ? endAngle : startAngle;
        this.step = step;
    }

    public Selection select(boolean filled) {
        final Selection selection = new Selection();
        for (float alpha = startAngle; alpha < endAngle; alpha += step) {
            final double x = radius * Math.sin(Math.toRadians(alpha));
            final double z = radius * Math.cos(Math.toRadians(alpha));
            selection.add(new Location(center.getWorld(), center.getBlockX() + 0.5 + x, center.getY(), center.getBlockZ() + 0.5 + z));
        }
        return selection;
    }
}
