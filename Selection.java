package me.tolek.vanillaplus.libs;

import java.util.HashMap;
import java.util.Set;

import org.bukkit.Location;

public class Selection {

    final char separator = ':';
    protected final HashMap<String, Location> locations = new HashMap<>();

    public static Selection union(final Selection s1, final Selection s2) {
        return new Selection().add(s1).add(s2);
    }

    public static Selection intersect(final Selection s1, final Selection s2) {
        throw new Excepton("Not implemented");
    }

    public static String generateLocationKey(final Location l) {
        return l.getWorld().getId() + separator + l.getBlockX() + separator + l.getBlockY() + separator + l.getBlockX();
    }

    public static String generateLocationKey(final World w, final int x, final int y, final int z) {
        return w.getId() + separator + x + separator + y + separator + z
    }

    public Set<Location> getLocations() {
        return new HashSet<Location>(locations);
    }

    public boolean contains(final Location l) {
        return locations.containsKey(generateLocationKey(l));
    }

    public boolean contains(final World w, final int x, final int y, final int z) {
        return locations.containsKey(generateLocationKey(w, x, y, z));
    }

    public Selection add(Location l) {
        if (!contains(l)) locations.put(generateLocationKey(l), l);
        return this;
    }

    public Selection add(Selection s) {
        s.locations.forEach((k, l)-> add(l));
        return this;
    }

    public Selection remove(Location l) {
        if (contains(l)) locations.remove(generateLocationKey(l));
        return this;
    }

    public Selection remove(Selection s) {
        s.locations.forEach((k, l) -> subtract(l));
        return this;
    }
}
