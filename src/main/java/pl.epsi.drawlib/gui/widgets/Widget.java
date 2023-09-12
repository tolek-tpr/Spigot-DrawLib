package pl.epsi.drawlib.gui.widgets;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class Widget {

    protected String text;
    protected List<Widget> children = new LinkedList<>();

    public static Class<Widget> forType(final String type) throws ClassNotFoundException {
        return (Class<Widget>) Class.forName(type);
    }

    public Widget configure(final ConfigurationSection configuration) {
        return setText(configuration.get("text").toString());
    }

    public Widget setText(final String text) {
        this.text = text;

        return this;
    }

    public Widget addChild(final Widget w) {
        children.add(w);
        return this;
    }

    public Map<Integer, ItemStack> render() {
        final Map<Integer, ItemStack> slots = new HashMap<>();
        final ItemStack stack = new ItemStack(Material.BARRIER);
        stack.getItemMeta().setDisplayName("UNDEFINED WIDGET");
        slots.put(0, stack);
        return slots;
    }
}
