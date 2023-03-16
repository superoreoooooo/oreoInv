package win.oreo.oreoInventory.test;

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
        if (sender instanceof Player player) {
            if (sender.hasPermission("administrators")) {
                player.sendMessage("Command Executed!");
                if (args.length > 0) {
                    openTestInv(player);
                }
            }
        }
        return false;
    }

    public void openTestInv(Player player) {
        ItemStack is = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta mt = is.getItemMeta();

        List<String> lores = new ArrayList<>();
        lores.add(0, ChatColor.WHITE + "test");
        mt.setLore(lores);
        mt.setDisplayName(ChatColor.AQUA + "TEST");
        mt.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
        is.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 255);
        is.setItemMeta(mt);

        HashMap<Integer, ItemStack> invData = new HashMap<>();
        invData.put(4, is);

        oreoInv oreoinv = util.create(ChatColor.AQUA + "TEST", 9, invData);
        oreoinv.init();

        player.openInventory(oreoinv.getInventory());
    }
}
