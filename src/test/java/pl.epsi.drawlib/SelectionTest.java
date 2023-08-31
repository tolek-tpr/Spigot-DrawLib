package pl.epsi.drawlib;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SelectionTest {

    @Mock
    private World world;

    @Test public void union_emptySelections() {
        assertTrue(Selection.union(new Selection(), new Selection()).getLocations().isEmpty());
    }

    @Test public void union_nonEmptySelections() {
        final Selection s1 = new Selection().add(new Location(world, 1, 1, 1));
        final Selection s2 = new Selection().add(new Location(world, 2, 2, 2));
        final Selection s = Selection.union(s1, s2);
        assertEquals(2, s.getLocations().size());
        assertTrue(s.contains(new Location(world, 1, 1, 1)));
        assertTrue(s.contains(new Location(world, 2, 2, 2)));
    }

    @Test public void intersect_emptySelections() {
        assertTrue(Selection.intersect(new Selection(), new Selection()).isEmpty());
    }

    @Test public void intersect_overlappingSelections() {
        final Selection s1 = new Selection().add(new Location(world, 1, 1, 1)).add(new Location(world, 2, 2, 2));
        final Selection s2 = new Selection().add(new Location(world, 2, 2, 2)).add(new Location(world, 3, 3, 3));
        final Selection s = Selection.intersect(s1, s2);
        assertEquals(1, s.getLocations().size());
        assertTrue(s.contains(new Location(world, 2, 2, 2)));
    }

    @Test public void intersect_disjointSelections() {
        final Selection s1 = new Selection().add(new Location(world, 1, 1, 1)).add(new Location(world, 2, 2, 2));
        final Selection s2 = new Selection().add(new Location(world, 3, 3, 3)).add(new Location(world, 4, 4, 4));
        assertTrue(Selection.intersect(s1, s2).isEmpty());
    }

    @Test public void addingAndRemovingLocations() {
        final Selection s = new Selection();
        assertTrue(s.isEmpty());
        assertEquals(0, s.size());
        assertFalse(s.contains(new Location(world, 1, 1, 1)));

        s.add(new Location(world, 1, 1, 1)); // add first location
        assertFalse(s.isEmpty());
        assertEquals(1, s.size());
        assertTrue(s.contains(new Location(world, 1, 1, 1)));

        s.add(new Location(world, 1, 1, 1)); // add location already in selection
        assertEquals(1, s.size());

        s.remove(new Location(world, 2, 2, 2)); // remove location not in selection
        assertFalse(s.isEmpty());
        assertEquals(1, s.size());
        assertFalse(s.contains(new Location(world, 2, 2, 2)));

        s.remove(new Location(world, 1, 1, 1)); // remove location to empty selection
        assertTrue(s.isEmpty());
        assertEquals(0, s.size());
        assertFalse(s.contains(new Location(world, 1, 1, 1)));
    }

    @Test public void addingAndRemovingSelections() {
        final Selection s = new Selection().add(new Location(world, 1, 1, 1));
        assertEquals(1, s.size());

        // add selection with locations 1:1:1 and 2:2:2
        s.add(new Selection().add(new Location(world, 1, 1, 1)).add(new Location(world, 2, 2, 2)));
        assertEquals(2, s.size());

        // remove selection with locations 1:1:1 and 3:3:3
        s.remove(new Selection().add(new Location(world, 1, 1, 1)).add(new Location(world, 3, 3, 3)));
        assertEquals(1, s.size());
    }

    @Test public void stroke() {
        final Location l = new Location(world, 1, 1, 1);
        l.getBlock().setType(Material.AIR);

        final Selection s = new Selection().add(l);
        s.stroke(Material.STONE);

        assertEquals(Material.AIR, l.getBlock().getType());
        assertEquals(Material.STONE, s.getOriginalMaterialAt(l));
    }
}