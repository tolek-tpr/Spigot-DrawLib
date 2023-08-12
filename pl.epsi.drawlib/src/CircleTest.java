import org.bukkit.Location;
import org.bukkit.World;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;

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
        assertEquals(-71, northEast.getBlockX());
        assertEquals(-71, northEast.getBlockX());
        assertEquals(-71, northEast.getBlockX());

    }

    @Test public void selectNotFilled() {
        final Selection s = new Circle(new Location(world, 0, 0, 0), 20).select(false);
        // System.out.println(s.toString());
        assertEquals(112, s.size());
    }

}