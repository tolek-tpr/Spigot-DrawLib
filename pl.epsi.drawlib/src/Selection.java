import org.bukkit.Location;
import org.bukkit.World;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class Selection {

    static final char separator = ':';
    protected final HashMap<String, Location> locations = new HashMap<>();

    public static Selection union(final Selection s1, final Selection s2) {
        return new Selection().add(s1).add(s2);
    }

    public static Selection intersect(final Selection s1, final Selection s2) {
        final Selection s = new Selection();
        s1.getLocations().forEach(l -> {
            if (s2.contains(l)) s.add(l);
        });
        return s;
    }

    public static String generateLocationKey(final Location l) {
        return /*l.getWorld().getUID().toString() + */" " + separator + l.getBlockX() + separator + l.getBlockY() + separator + l.getBlockZ();
    }

    public static String generateLocationKey(final World w, final int x, final int y, final int z) {
        return /*w.getUID().toString() + */" " + separator + x + separator + y + separator + z;
    }

    public Set<Location> getLocations() {
        return new HashSet<Location>(locations.values());
    }

    public boolean isEmpty() {
        return locations.isEmpty();
    }

    public int size() {
        return locations.size();
    }

    public boolean contains(final Location l) {
        return locations.containsKey(generateLocationKey(l));
    }

    public Selection add(Location l) {
        final String k = generateLocationKey(l);
        if (!locations.containsKey(k)) locations.put(k, l);
        return this;
    }

    public Selection add(Selection s) {
        s.locations.forEach((k, l) -> add(l));
        return this;
    }

    public Selection remove(Location l) {
        if (contains(l)) locations.remove(generateLocationKey(l));
        return this;
    }

    public Selection remove(Selection s) {
        s.locations.forEach((k, l) -> remove(l));
        return this;
    }

    public Selection consume(Consumer<Location> consumer) {
        locations.forEach((k, l) -> consumer.accept(l));
        return this;
    }

    public Selection transform(UnaryOperator<Location> transformer) {
        final Selection target = new Selection();
        locations.forEach((k, l) -> target.add(transformer.apply(l)));
        return target;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        locations.forEach((k, l) -> { sb.append(k + " "); });
        return sb.toString();
    }
}
