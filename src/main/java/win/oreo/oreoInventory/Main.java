package win.oreo.oreoInventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
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
