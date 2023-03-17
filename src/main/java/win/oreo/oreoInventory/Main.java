package win.oreo.oreoInventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import win.oreo.oreoInventory.Inv.oreoInv;
import win.oreo.oreoInventory.test.testCommand;
import win.oreo.oreoInventory.test.testEvent;
import win.oreo.oreoInventory.util.oreoInvUtil;

import java.util.HashSet;
import java.util.Set;

public final class Main extends JavaPlugin {
    public static Set<oreoInv> oreoInvSet = new HashSet<>();

    @Override
    public void onEnable() {
        getCommand("test").setExecutor(new testCommand());
        Bukkit.getPluginManager().registerEvents(new testEvent(), this);
        Bukkit.getPluginManager().registerEvents(new oreoInvUtil(), this);
        DebugMsg("Plugin Enabled!");
    }

    @Override
    public void onDisable() {
        DebugMsg("Plugin Disabled!");
    }

    public static void DebugMsg(String str) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "<<DEBUG>> " + str);
    }
}
