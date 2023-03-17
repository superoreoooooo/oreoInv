package win.oreo.oreoInventory.util.Item;

import org.bukkit.enchantments.Enchantment;

public class EnchObj {
    private final Enchantment enchantment;
    private final int lvl;

    public EnchObj(Enchantment enchantment, int lvl) {
        this.enchantment = enchantment;
        this.lvl = lvl;
    }

    public Enchantment getEnchantment() {
        return enchantment;
    }

    public int getLvl() {
        return lvl;
    }
}
