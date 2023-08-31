package pl.epsi.drawlib.transformations;

import org.bukkit.Location;
import org.bukkit.World;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;

public class CompositeTest {

    @Mock
    private World world;

    @Test public void transform() {
        final Composite t = new Composite()
                .add(new Move(new Location(world, 1, 0, 0)))
                .add(new Move(new Location(world, 1, 2, 2)))
                .add(new Move(new Location(world, 1, 2, 3)));
        final Location l = t.getTransformer().apply(new Location(world, 0, 0, 0));
        assertEquals(new Location(world, 3, 4, 5).toVector(), l.toVector());
    }

}