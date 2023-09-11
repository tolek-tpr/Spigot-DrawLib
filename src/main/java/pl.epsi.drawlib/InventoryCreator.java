package pl.epsi.drawlib;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryCreator {

    public Map<String, String> readInventoryYamlFile(String inventoryFilename) {
        File f = new File("plugins/drawlib/guis/" + inventoryFilename);
        FileConfiguration inventoryData = YamlConfiguration.loadConfiguration(f);

        ConfigurationSection mainSection = inventoryData.getConfigurationSection("Inventory");
        String title = mainSection.getString("title");
        String titleColor = mainSection.getString("title-color");
        String slots = mainSection.getString("lines");

        Map<String, String> settings = new HashMap<>();

        if (title == null || titleColor == null || slots == null) return null;
        /*if (titleColor.equalsIgnoreCase("COLORIZE")) {             THIS IS FOR THE PART OF CODE THAT CREATES THE INVENTORY
            title = ChatColor.translateAlternateColorCodes('&', title);
        } else {
            title = ChatColor.getByChar(titleColor) + "" + title;
        }

        if (slotsString.equalsIgnoreCase("auto")) {
            // do sth
        } else {
            int slots = Integer.parseInt(slotsString);
            if (slots > 54) return null;
        }*/

        settings.put("title", title);
        settings.put("title-color", titleColor);
        settings.put("slots", slots);

        // TD: figure out how to do dynamic widgets

        return settings;
    }

}
