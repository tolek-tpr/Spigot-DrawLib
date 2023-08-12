Spigot DrawLib
==============

Spigot DrawLib allows you to draw shapes in Minecraft as well as transform
and animate those shapes. Below you will find a few examples of what this gem
is capable of.

How it works
------------

1. Design a shape. It could be as simple as line, circle or square, but it could
   go as far as a shape composed of multiple simple shapes. You can keep composing
   shapes endlessly.

```Java
Circle c = new Circle(player.getLocation(), 15, 10);
```

2. Get a selection of blocks circumscribing the designed shape.

```Java
Selection s = Circle.select();
```

4. Paint something on the selection. You can paint something on the selected shape,
   but also erase the drawing and revert the map to its original state.

```Java
s.paint(Block.COBBLESTONE);
```

7. Transform or animate the selection. You can keep transforming, drawing,
   and erasing shapes infinitely.

