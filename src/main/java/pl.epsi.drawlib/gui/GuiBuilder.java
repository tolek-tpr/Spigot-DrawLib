package pl.epsi.drawlib.gui;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import pl.epsi.drawlib.gui.widgets.Form;
import pl.epsi.drawlib.gui.widgets.Memo;
import pl.epsi.drawlib.gui.widgets.Widget;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuiBuilder {

    public static Inventory fromFile(final String file, final String inventoryName) {
        final File f = new File(file);
        final FileConfiguration inventoryData = YamlConfiguration.loadConfiguration(f);
        final ConfigurationSection section = inventoryData.getConfigurationSection(inventoryName);
        System.out.print(section);
        return fromNode(section, "Form").toInventory();
    }

    private static Widget fromNode(final ConfigurationSection configuration, final String type) {
        try {
            final Widget widget = Widget.forType(type).newInstance().configure(configuration);
            configuration.getStringList("items").forEach(
                    item -> widget.addChild(fromNode(configuration.get(item)))
            );
            return widget;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            return new Memo().setText("should not initialize widget type " + type);
        }
    }

    public static Widget fromNode(final ConfigurationSection configuration) {
        final String type = configuration.get("type").toString();
        return fromNode(configuration, type);
    }

}
