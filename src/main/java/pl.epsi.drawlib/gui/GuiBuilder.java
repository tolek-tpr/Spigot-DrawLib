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

    public static Inventory readInventoryYamlFile(final String inventoryFile, final String inventoryName) {
        File f = new File("plugins/drawlib/guis/" + inventoryFile);
        FileConfiguration inventoryData = YamlConfiguration.loadConfiguration(f);
        ConfigurationSection section = inventoryData.getConfigurationSection(inventoryName);
        System.out.print(section);
        return ((Form) parseYamlNode(section)).buildInventory();

    }

    public static Widget parseYamlNode(final ConfigurationSection configuration) {
        List<String> sections = configuration.getStringList("widgets");
        final Map<String, String> settings = new HashMap<>();

        try {
            Widget w = Widget.forType(settings.get("type")).newInstance().configure(configuration);
            sections.forEach(section -> w.addChild(parseYamlNode((ConfigurationSection) configuration.get(section))));
            return w;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            return new Memo().setText("Could not initialize widget type " + settings.get("name"));
        }
    }

}
