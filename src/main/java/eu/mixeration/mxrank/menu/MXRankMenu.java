package eu.mixeration.mxrank.menu;

import eu.mixeration.mxrank.config.MXConfig;
import eu.mixeration.mxrank.settings.MXValues;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Iterator;
import java.util.List;

public class MXRankMenu {
    private static Inventory inv = null;

    public MXRankMenu() {
    }

    @Deprecated
    public static void openInventory(HumanEntity ent) {
        inv = Bukkit.createInventory(null, 54, MXValues.MENU_TITLE);
        loadItems();
        ent.openInventory(inv);
    }

    @Deprecated
    public static void loadItems() {
        Iterator var0 = MXConfig.getConfig().getConfigurationSection("mx-rank.player-ranks").getKeys(false).iterator();

        while (var0.hasNext()) {
            String ranks = (String) var0.next();
            String material = MXConfig.getConfig().getString("mx-rank.player-ranks." + ranks + ".material");
            String display = MXConfig.getConfig().getString("mx-rank.player-ranks." + ranks + ".display");
            int slot = MXConfig.getConfig().getInt("mx-rank.player-ranks." + ranks + ".slot");
            ItemStack item = new ItemStack(Material.getMaterial(material), 1);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', display));
            item.setItemMeta(meta);
            List<String> lores = MXConfig.getConfig().getStringList("mx-rank.player-ranks." + ranks + ".lore");

            for (int i = 0; i < lores.size(); ++i) {
                lores.set(i, ChatColor.translateAlternateColorCodes('&', lores.get(i)));
            }

            meta.setLore(lores);
            item.setItemMeta(meta);
            inv.setItem(slot, item);
        }

    }
}
