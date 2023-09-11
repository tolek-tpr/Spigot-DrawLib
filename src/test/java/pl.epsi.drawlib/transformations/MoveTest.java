package pl.epsi.drawlib.transformations;

import org.bukkit.Location;
import org.bukkit.World;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MoveTest {

    @Mock
    private World world;

    @Test public void transform() {
        final Move t = new Move(new Location(world, 0, 3, 5));
        final Location l = t.getTransformer().apply(new Location(world, 0, 0, 0));
        assertNotEquals(new Location(world, 0, 0, 0).toVector(), l.toVector());
        assertEquals(new Location(world, 0, 3, 5).toVector(), l.toVector());
    }

}