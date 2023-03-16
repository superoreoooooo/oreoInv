package win.oreo.oreoInventory.test;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import win.oreo.oreoInventory.Inv.oreoInv;
import win.oreo.oreoInventory.Main;
import win.oreo.oreoInventory.util.oreoInvClickEvent;

public class testEvent implements Listener {
    @EventHandler
    public void onClick(oreoInvClickEvent e) {
        Player player = e.getPlayer();
        ItemStack is = e.getClickedItem();
        oreoInv oreoInv = e.getInventory();

        Main.DebugMsg("Player : " + player.getName() + " Inv : " + oreoInv.getInvName() + " Item : " + is.getType().name());
    }
}
