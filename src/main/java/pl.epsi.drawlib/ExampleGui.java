package pl.epsi.drawlib;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

public class ExampleGui {

    private static Inventory inv;
    private int slots = 54;
    private String title = "title";

    private String colorize(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public void registerGui() {
        if (inv != null) return;
        inv = Bukkit.createInventory(null, slots, title);
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    public void setTitle(String title) {
        this.title = colorize(title);
    }

    public Inventory getInventory() {
        return inv;
    }

    public void setItems(HashMap<Integer, ItemStack> items) {
        items.keySet().forEach((Integer key) -> {
            if (key < slots) return;
            inv.setItem(key, items.get(key));
        });
    }

    public void setItems(List<ItemStack> items) {
        items.forEach((ItemStack item) -> {
            if (items.size() > slots) return;
            inv.addItem(item);
        });
    }

}
