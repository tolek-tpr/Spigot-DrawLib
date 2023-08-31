package pl.epsi.drawlib.shapes;

import org.bukkit.Location;
import org.bukkit.World;
import pl.epsi.drawlib.Selection;

public class Circle implements Shape {

    protected Location center;
    protected int radius;

    public Circle(final Location center, final int radius) {
        this.center = center;
        this.radius = radius;
    }

    public Location getLocationOnCircle(final float yaw, final float pitch) {
        final double pitchInDeg = Math.abs(Math.cos(Math.toRadians(pitch)));
        final double x = center.getX() - radius * Math.sin(Math.toRadians(yaw)) * pitchInDeg;
        final double z = center.getZ() + radius * Math.cos(Math.toRadians(yaw)) * pitchInDeg;
        return new Location(center.getWorld(), x, center.getY(), z, yaw, pitch);
    }

    public Selection select(boolean filled) {
        final Selection selection = new Selection();
        final double cx = center.getBlockX() + 0.5;
        final double cy = center.getBlockY() + 0.5;
        final double cz = center.getBlockZ() + 0.5;
        final World world = center.getWorld();
        final float step = 45 / radius;
        for (float alpha = 0; alpha <= 90; alpha += step) {
            for (int r = filled ? 0 : radius; r <= radius; ++r) {
                final double dx = r * Math.sin(Math.toRadians(alpha));
                final double dz = r * Math.cos(Math.toRadians(alpha));
                selection.add(new Location(world, cx + dx, cy, cz - dz))
                        .add(new Location(world, cx - dx, cy, cz - dz))
                        .add(new Location(world, cx + dx, cy, cz + dz))
                        .add(new Location(world, cx - dx, cy, cz + dz));
            }
        }
        return selection;
    }
}
