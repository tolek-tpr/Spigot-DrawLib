package pl.epsi.drawlib.shapes;

import org.bukkit.Location;
import org.bukkit.World;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import pl.epsi.drawlib.Selection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class SquareTest {

    @Mock
    private World world;

    @Test public void selectUnfilled() {
        final Square sq = new Square(new Location(world, 0, 0, 0), 2);
        final Selection s = sq.select(false);
        Assert.assertEquals(8, s.size());
        assertTrue(s.contains(new Location(world, -1, 0, -1)));
        assertTrue(s.contains(new Location(world, 0, 0, -1)));
        assertTrue(s.contains(new Location(world, 1, 0, -1)));
        assertTrue(s.contains(new Location(world, -1, 0, 0)));
        assertFalse(s.contains(new Location(world, 0, 0, 0)));
        assertTrue(s.contains(new Location(world, 1, 0, 0)));
        assertTrue(s.contains(new Location(world, -1, 0, 1)));
        assertTrue(s.contains(new Location(world, 0, 0, 1)));
        assertTrue(s.contains(new Location(world, 1, 0, 1)));
    }

}
