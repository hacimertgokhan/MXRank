package eu.mixeration.mxrank.events.askyblock;

import com.wasteofplastic.askyblock.events.IslandDeleteEvent;
import com.wasteofplastic.askyblock.events.IslandResetEvent;
import eu.mixeration.mxrank.MXRank;
import eu.mixeration.mxrank.config.MXConfig;
import eu.mixeration.mxrank.storage.MXStorage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Iterator;

public class A$IslandReset implements Listener {
    public A$IslandReset(MXRank mxRank) {
    }

    @EventHandler
    public void askyblockIslandReset(IslandResetEvent event) {
        if (!event.getPlayer().hasPermission("mxrank.cancelreset")) {
            Player player = event.getPlayer();
            String commands = MXConfig.getConfig().getString("mx-rank.reset-command");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commands.replace("<player>", player.getName()));
            MXStorage.getConfig().set("user-data." + player.getUniqueId() + ".priority", 0);
            MXStorage.getConfig().set("user-data." + player.getUniqueId() + ".finished-all-ranks", "false");
            MXStorage.saveConfig();
            Iterator var4 = MXConfig.getConfig().getStringList("messages.rank-reseted").iterator();

            while (var4.hasNext()) {
                String rankup = (String) var4.next();
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', rankup));
            }
        }
    }

    @EventHandler
    public void askyblockIslandBan(IslandDeleteEvent event) {
        Player player = Bukkit.getPlayer(event.getPlayerUUID());
        if (!player.hasPermission("mxrank.cancelreset")) {
            String commands = MXConfig.getConfig().getString("mx-rank.reset-command");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commands.replace("<player>", player.getName()));
            MXStorage.getConfig().set("user-data." + player.getUniqueId() + ".priority", 0);
            MXStorage.getConfig().set("user-data." + player.getUniqueId() + ".finished-all-ranks", "false");
            MXStorage.saveConfig();
            Iterator var4 = MXConfig.getConfig().getStringList("messages.rank-reseted").iterator();

            while (var4.hasNext()) {
                String rankup = (String) var4.next();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', rankup));
            }
        }
    }

}
