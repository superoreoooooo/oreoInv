package win.oreo.oreoInventory.test;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import win.oreo.oreoInventory.Inv.oreoInv;
import win.oreo.oreoInventory.Main;
import win.oreo.oreoInventory.util.oreoInvUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class testCommand implements CommandExecutor {
    private final oreoInvUtil util;

    public testCommand() {
        this.util = new oreoInvUtil();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission("administrators")) {
            sender.sendMessage("Command Executed!");
            if (args.length > 0) {
                switch (args[0]) {
                    case "open" -> openTestInv((Player) sender);
                    case "list" -> {
                        Main.DebugMsg("list");
                        Main.oreoInvSet.forEach(oreoInv -> Bukkit.getConsoleSender().sendMessage(oreoInv.getInvName()));
                        Main.DebugMsg("list");
                    }
                }
            }
        }
        return false;
    }

    public void openTestInv(Player player) {
        ItemStack is = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta mt = is.getItemMeta();

        List<String> lore = new ArrayList<>();
        lore.add(0, ChatColor.WHITE + "test");
        mt.setLore(lore);
        mt.setDisplayName(ChatColor.AQUA + "TEST");
        mt.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
        is.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 255);
        is.setItemMeta(mt);

        ItemStack i2 = new ItemStack(Material.NETHER_STAR);

        HashMap<Integer, ItemStack> invData = new HashMap<>();
        invData.put(4, is);
        invData.put(0, i2);

        oreoInv oreoinv = util.create(ChatColor.AQUA + "TEST", 9, invData);

        player.openInventory(oreoinv.getInventory());
    }
}
