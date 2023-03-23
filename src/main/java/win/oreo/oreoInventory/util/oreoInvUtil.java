package win.oreo.oreoInventory.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import win.oreo.oreoInventory.Inv.oreoInv;
import win.oreo.oreoInventory.Main;
import win.oreo.oreoInventory.util.Item.EnchObj;

import java.util.*;

public class oreoInvUtil implements Listener {
    public static Set<oreoInv> oreoInvSet = new HashSet<>();

    public oreoInv create(String invName, int size, HashMap<Integer, ItemStack> map) {
        return create(new oreoInv(invName, size, map));
    }

    public oreoInv create(oreoInv inv) {
        oreoInvSet.add(inv);
        return inv;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getClickedInventory() != null) {
            for (oreoInv inv : oreoInvSet) {
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
        for (oreoInv inv : oreoInvSet) {
            if (inv.getInventory().equals(e.getInventory())) {
                if (e.getInventory().getSize() > Collections.min(e.getRawSlots())) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        oreoInvSet.removeIf(in -> in.getInventory().equals(e.getInventory()));
    }

    public ItemStack createItem(Material itemMaterial, String itemName, String[] lore, EnchObj[] enchObjs, ItemFlag... flags) {
        ItemStack itemStack = new ItemStack(itemMaterial);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(itemName);
        meta.setLore(Arrays.stream(lore).toList());
        for (EnchObj obj : enchObjs) {
            itemStack.addUnsafeEnchantment(obj.getEnchantment(), obj.getLvl());
        }

        meta.addItemFlags(flags);

        itemStack.setItemMeta(meta);

        return itemStack;
    }
}
