package pl.epsi.drawlib.transformations;

import org.bukkit.Location;

import java.util.LinkedList;
import java.util.List;
import java.util.function.UnaryOperator;

public class Composite implements Transformation {

    private final List<Transformation> transformations = new LinkedList<>();

    public Composite add(Transformation t) {
        transformations.add(t);
        return this;
    }

    public UnaryOperator<Location> getTransformer() {
        return l -> {
            for (Transformation t : transformations) l = t.getTransformer().apply(l);
            return l;
        };
    }
}
