package pl.epsi.drawlib.shapes;

import org.bukkit.Location;
import pl.epsi.drawlib.Selection;

public class Square {

    protected Location center;
    protected int length;

    public Square(final Location center, final int length) {
        this.center = center;
        this.length = length;
    }
    public Selection select(boolean filled) {
        final int x = center.getBlockX();
        final int y = center.getBlockY();
        final int z = center.getBlockZ();
        final double h = length / 2;

        final Selection s = new Selection();
        s.add(new Location(center.getWorld(), x - h, y, z - h))
                .add(new Location(center.getWorld(), x - h, y, z + h))
                .add(new Location(center.getWorld(), x + h, y, z - h))
                .add(new Location(center.getWorld(), x + h, y, z + h));
        for (double d = -h + 1; d < h; ++d)
            s.add(new Location(center.getWorld(), x + d, y, z - h))
                    .add(new Location(center.getWorld(), x + d, y, z + h))
                    .add(new Location(center.getWorld(), x + h, y, z + d))
                    .add(new Location(center.getWorld(), x - h, y, z + d));

        return s;
    }
}
