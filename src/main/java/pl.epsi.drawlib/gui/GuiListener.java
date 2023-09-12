package pl.epsi.drawlib.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import pl.epsi.drawlib.gui.ExampleGui;

public class GuiListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        ExampleGui gui = new ExampleGui();
        int clickedSlot = event.getSlot();

        if (inventory.equals(gui.getInventory())) {
            if (clickedSlot == 10) {
                // this is so u can place items in the inv but only into the specific slot
                p.sendMessage("helo");
            } else {
                event.setCancelled(true);
            }
        }
    }

}
