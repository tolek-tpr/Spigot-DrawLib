package pl.epsi.drawlib.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

public class ExampleGui {

    private static Inventory inv;

    private String colorize(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public void registerGui() {
        if (inv != null) return;
        inv = GuiBuilder.readInventoryYamlFile("inventory.yml", "exampleForm");
    }

    public Inventory getInventory() {
        return inv;
    }

}
