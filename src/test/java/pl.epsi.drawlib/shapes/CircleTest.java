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

public class CircleTest {

    @Mock
    private World world;

    @Test public void getLocationInCircle() {
        final Circle c = new Circle(new Location(world, 0, 0, 0), 100);

        final Location north = c.getLocationOnCircle(0,0);
        assertEquals(0, north.getBlockX());
        assertEquals(0, north.getBlockX());
        assertEquals(0, north.getBlockX());

        final Location northEast = c.getLocationOnCircle(45,0);
        assertEquals(-71, northEast.getBlockX());
        assertEquals(-71, northEast.getBlockX());
        assertEquals(-71, northEast.getBlockX());

        final Location northEastUp45Deg = c.getLocationOnCircle(45,45);
        assertEquals(-50, northEastUp45Deg.getBlockX());
        assertEquals(-50, northEastUp45Deg.getBlockX());
        assertEquals(-50, northEastUp45Deg.getBlockX());

    }

    @Test public void selectNotFilled() {
        final Circle c = new Circle(new Location(world, 0, 0, 0), 20);
        final Selection s = c.select(false);
        Assert.assertEquals(112, s.size());
        assertFalse(s.contains(new Location(world, 0, 0, 0)));
        assertTrue(s.contains(new Location(world, -20, 0, 0)));
        assertTrue(s.contains(new Location(world, 20, 0, 0)));
        assertTrue(s.contains(new Location(world, 0, 0, -20)));
        assertTrue(s.contains(new Location(world, 0, 0, 20)));
    }

}