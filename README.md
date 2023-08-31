Spigot DrawLib
==============

Spigot DrawLib allows you to draw shapes in Minecraft as well as transform
and animate those shapes. Below you will find a few examples of what this gem
is capable of.

Quick examples
--------------

You can use the facade class Draw to draw basic shapes. It's dead simple:
```Java
import pl.epsi.drawlib.*;

Draw.circle(player.getLocation(), 15, Material.STONE);
Draw.square(player.getLocation(), 10, Material.STONE);
```

Advanced usage
--------------

1. Design a shape. It could be as simple as line, circle or square, but it could
   go as far as a shape composed of multiple simple shapes. You can keep composing
   shapes endlessly.
```Java
Circle circle = new Circle(player.getLocation(), 15);
```

2. Get a selection of blocks circumscribing the designed shape.
```Java
Selection selection = circle.select(true);
```

3. Stroke. You can paint block or particle on the selected shape,
but also erase the drawing and revert the map to its original state.
```Java
selection.stroke(Material.STONE);
selection.erase();
selection.sprinkle(Particle.FIREWORKS_SPARK, 1);
selection.restore();
```

4. Transform the selection, e.g. move up 1 block rotate 45 degrees around player,
then redraw in new location.
```Java
Transformation transformation = new Composite().
        .add(new Move(0, 1, 0))
        .add(new Rotate(player.getLocation, 45));
selection = selection.transform(transformation);
selection.stroke(Material.STONE);
```

5. Or continuously animate the selection for 5 seconds, looped.
```Java
Transformation moveUp = new Move(0, -1, 0);
Transformation moveDown = new Move(0, 1, 0);
Transformation moveEast = new Move(0, 0, -1);
Transformation moveWest = new Move(0, 0, 1);
new Animation(plugin, moveUp, 5)
        .beforeFrame(s -> s.restore())
        .afterFrame(s -> s.stroke(Material.STONE))
        .loop()
        .then(moveEast, 5)
        .then(moveDown, 5)
        .then(moveWest, 5)
        .run(selection);
```
