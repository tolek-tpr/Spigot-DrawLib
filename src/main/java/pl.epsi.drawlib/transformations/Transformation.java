package pl.epsi.drawlib.transformations;

import org.bukkit.Location;

import java.util.function.UnaryOperator;

public interface Transformation {

    UnaryOperator<Location> getTransformer();

}
