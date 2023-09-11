package pl.epsi.drawlib;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import pl.epsi.drawlib.transformations.Transformation;

import java.util.function.Consumer;

public class Animation {

    private final Plugin plugin;
    private final Transformation transformation;
    private final long duration;
    private boolean doLoop = false;

    private Consumer<Selection> before = (s -> { s.restore(); });
    private Consumer<Selection> after = (s -> { });

    private Animation next;
    private Selection selection;
    private int scheduledId;

    public Animation(final Plugin plugin, final Transformation transformation, final long duration) {
        this.plugin = plugin;
        this.transformation = transformation;
        this.duration = duration;
    }

    public Animation then(final Transformation transformation, final long duration) {
        return next == null
            ? next = new Animation(plugin, transformation, duration).beforeFrame(before).afterFrame(after)
            : next.then(transformation, duration);
    }

    public Animation beforeFrame(final Consumer<Selection> worker) {
        before = worker;
        return this;
    }

    public Animation afterFrame(final Consumer<Selection> worker) {
        after = worker;
        return this;
    }

    public Animation loop() {
        doLoop = true;
        return this;
    }

    public void run(final Selection s) {
        selection = new Selection().add(s);
        scheduledId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            before.accept(selection);
            selection = selection.transform(transformation);
            after.accept(selection);
            // TODO: probably some kind of counter
        }, 0, 20 * duration);
        // TODO: invoke next animation passing the resulting selection
        // TODO: loop from beginning if doLoop and no next animation
    }
}
