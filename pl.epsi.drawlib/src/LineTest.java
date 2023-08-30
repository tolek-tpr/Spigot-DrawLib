import org.bukkit.Location;
import org.bukkit.World;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LineTest {

    @Mock
    private World world;

    @Test public void select() {
        final Line l = new Line(new Location(world, 0, 0, 0), new Location(world, 3, 6, 0));
        final Selection s = l.select(false);
        assertEquals(7, s.size());
        assertTrue(s.contains(new Location(world, 0, 0, 0)));
        assertTrue(s.contains(new Location(world, 0, 1, 0)));
        assertTrue(s.contains(new Location(world, 1, 2, 0)));
        assertTrue(s.contains(new Location(world, 1, 3, 0)));
        assertTrue(s.contains(new Location(world, 2, 4, 0)));
        assertTrue(s.contains(new Location(world, 2, 5, 0)));
        assertTrue(s.contains(new Location(world, 3, 6, 0)));
    }

}
