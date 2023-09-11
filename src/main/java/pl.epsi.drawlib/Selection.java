package pl.epsi.drawlib;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import pl.epsi.drawlib.transformations.Transformation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class Selection {

    static final char separator = ':';

    protected final HashMap<String, Location> locations = new HashMap<>();
    protected final HashMap<String, Material> materials = new HashMap<>();

    public static Selection union(final Selection s1, final Selection s2) {
        return new Selection().add(s1).add(s2);
    }

    public static Selection intersect(final Selection s1, final Selection s2) {
        final Selection s = new Selection();
        s1.locations.forEach((k, l) -> {
            if (s2.locations.containsKey(k)) s.add(l, s2.materials.containsKey(k) ? s2.materials.get(k) : s1.materials.get(k));
        });
        return s;
    }

    public static String generateLocationKey(final Location l) {
        return String.valueOf(l.getBlockX()) + separator + l.getBlockY() + separator + l.getBlockZ();
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

    public Selection add(final Location l) {
        final String k = generateLocationKey(l);
        if (!locations.containsKey(k)) {
            locations.put(k, l);
            materials.put(k, l.getBlock().getType());
        }
        return this;
    }

    public Selection add(final Location l, final Material m) {
        final String k = generateLocationKey(l);
        if (!locations.containsKey(k)) {
            locations.put(k, l);
            materials.put(k, m);
        }
        return this;
    }

    public Selection add(final Selection s) {
        s.locations.forEach((k, l) -> add(l, s.locations.get(k).getBlock().getType()));
        return this;
    }

    public Selection remove(final Location l) {
        if (contains(l)) locations.remove(generateLocationKey(l));
        return this;
    }

    public Selection remove(final Selection s) {
        s.locations.forEach((k, l) -> remove(l));
        return this;
    }

    public Selection stroke(final Material m) {
        locations.forEach((k, l) -> l.getBlock().setType(m));
        return this;
    }

    public Selection sprinkle(final Particle p, final int amount) {
        locations.forEach((k, l) -> l.getWorld().spawnParticle(p, l, amount));
        return this;
    }

    public Selection erase() {
        locations.forEach((k, l) -> l.getBlock().setType(Material.AIR));
        return this;
    }

    public Selection restore() {
        locations.forEach((k, l) -> l.getBlock().setType(materials.get(k)));
        return this;
    }

    public Material getOriginalMaterialAt(final Location l) {
        return materials.get(generateLocationKey(l));
    }

    public Selection consume(final Consumer<Location> consumer) {
        locations.forEach((k, l) -> consumer.accept(l));
        return this;
    }

    public Selection transform(final UnaryOperator<Location> transformer) {
        final Selection target = new Selection();
        locations.forEach((k, l) -> target.add(transformer.apply(l)));
        return target;
    }

    public Selection transform(final Transformation transformation) {
        return transform(transformation.getTransformer());
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        locations.forEach((k, l) -> { sb.append(k).append(" "); });
        return sb.toString();
    }
}
