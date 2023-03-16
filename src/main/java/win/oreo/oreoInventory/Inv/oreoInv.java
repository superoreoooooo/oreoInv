package win.oreo.oreoInventory.Inv;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import static win.oreo.oreoInventory.Main.DebugMsg;

public class oreoInv implements InventoryHolder {
    private Inventory inv;
    private String invName;
    private int size;
    private HashMap<Integer, ItemStack> invData;

    public oreoInv(String invName, int size, HashMap<Integer, ItemStack> invData) {
        this.invName = invName;
        this.size = size;
        this.invData = invData;
        init();
    }

    public void init() {
        inv = Bukkit.createInventory(this, getSize(), getInvName());
        invData.keySet().forEach(integer -> {
            inv.setItem(integer, invData.get(integer));
            DebugMsg("index : " + integer + " item : " + invData.get(integer).getType().name());
        });
    }

    public String getInvName() {
        return invName;
    }

    public int getSize() {
        return size;
    }

    public HashMap<Integer, ItemStack> getInvData() {
        return invData;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inv;
    }
}
