package pl.epsi.drawlib.gui.widgets;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class Button extends Widget {

    public Map<Integer, ItemStack> render() {
        final Map<Integer, ItemStack> slots = new HashMap<>();
        final ItemStack stack = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        stack.getItemMeta().setDisplayName(text);
        slots.put(0, stack);
        return slots;
    }

}
