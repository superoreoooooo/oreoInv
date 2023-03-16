package win.oreo.oreoInventory.util;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import win.oreo.oreoInventory.Inv.oreoInv;

public class oreoInvClickEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private boolean isCancelled;
    private oreoInv inventory;
    private ItemStack clickedItem;
    private Player player;

    public oreoInv getInventory() {
        return inventory;
    }

    public ItemStack getClickedItem() {
        return clickedItem;
    }

    public Player getPlayer() {
        return player;
    }

    public oreoInvClickEvent(Player player, oreoInv inv, ItemStack item) {
        this.player = player;
        this.isCancelled = false;
        this.inventory = inv;
        this.clickedItem = item;
    }


    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.isCancelled = cancel;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
