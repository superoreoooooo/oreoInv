package win.oreo.oreoInventory.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;
import win.oreo.oreoInventory.Inv.oreoInv;
import win.oreo.oreoInventory.Main;

import java.util.*;

public class oreoInvUtil implements Listener {

    public oreoInv create(String invName, int size, HashMap<Integer, ItemStack> map) {
        oreoInv inv = new oreoInv(invName, size, map);
        Main.oroeInvSet.add(inv);
        return inv;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getClickedInventory() != null) {
            for (oreoInv inv : Main.oroeInvSet) {
                if (inv.getInventory().equals(e.getClickedInventory()) || inv.getInventory().equals(e.getInventory())) {
                    e.setCancelled(true);
                    ItemStack item = inv.getInvData().get(e.getSlot());
                    if (item != null && item.getType() != Material.AIR) {
                        Bukkit.getPluginManager().callEvent(new oreoInvClickEvent((Player) e.getWhoClicked(), inv, item));
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDrag(InventoryDragEvent e) {
        for (oreoInv inv : Main.oroeInvSet) {
            if (inv.getInventory().equals(e.getInventory())) {
                if (e.getInventory().getSize() > Collections.min(e.getRawSlots())) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Main.oroeInvSet.removeIf(in -> in.getInventory().equals(e.getInventory()));
    }
}
