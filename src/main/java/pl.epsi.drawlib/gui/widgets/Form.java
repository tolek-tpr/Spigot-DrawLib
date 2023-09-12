package pl.epsi.drawlib.gui.widgets;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class Form extends Widget {

    private int lines = 1;

    @Override
    public Widget configure(ConfigurationSection configuration) {
        final Form form = (Form) setText(configuration.get("text").toString());
        // some more stuff to do
        return form;
    }

    @Override
    public Widget addChild(final Widget w) {
        children.add(w);
        if (lines < children.size()) lines = children.size();
        return this;
    }

    public Inventory buildInventory() {
        final Inventory inv = Bukkit.createInventory(null, lines * 9, text);

        for (int line = 0; line < children.size(); ++line) {
            for (Map.Entry<Integer, ItemStack> entry : children.get(line).render().entrySet()) {
                final Integer slot = entry.getKey();
                final ItemStack stack = entry.getValue();
                inv.setItem((line * 9) + slot, stack);
            }
        }

        return inv;
    }

}
