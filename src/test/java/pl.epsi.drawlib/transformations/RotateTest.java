package pl.epsi.drawlib.transformations;

import org.bukkit.Location;
import org.bukkit.World;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RotateTest {

    @Mock
    private World world;

    @Test public void transform() {
        final Rotate t = new Rotate(new Location(world, 0, 0, 0), 90);
        final Location l = t.getTransformer().apply(new Location(world, 10, 0, 0));
        assertNotEquals(new Location(world, 10, 0, 0).toVector(), l.toVector());
        assertEquals(new Location(world, 0, 0, 10).toVector(), l.toVector());
    }

}