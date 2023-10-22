package pl.epsi.drawlib.gui.widgets;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class Widget {

    final static String DEFAULT_NS = "pl.epsi.drawlib.gui.widget.";

    protected String text;
    protected List<Widget> children = new LinkedList<>();

    public static Class<Widget> forType(final String type) throws ClassNotFoundException {
        return (Class<Widget>) Class.forName(type.contains('.') ? type : DEFAULT_NS + type);
    }

    final public Widget() {}

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

    public Inventory toInventory() {
        throw new Exception("Rendering " + this.getClass().getName() + " as inventory not possible");
    }


}
