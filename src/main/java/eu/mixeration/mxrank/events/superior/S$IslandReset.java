package eu.mixeration.mxrank.events.superior;

import com.bgsoftware.superiorskyblock.api.events.IslandBanEvent;
import com.bgsoftware.superiorskyblock.api.events.IslandDisbandEvent;
import com.bgsoftware.superiorskyblock.api.events.IslandKickEvent;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import eu.mixeration.mxrank.MXRank;
import eu.mixeration.mxrank.config.MXConfig;
import eu.mixeration.mxrank.storage.MXStorage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Iterator;

public class S$IslandReset implements Listener {
    public S$IslandReset(MXRank mxRank) {
    }

    @EventHandler
    public void islandReset(IslandDisbandEvent disband) {
        if (!disband.getPlayer().asPlayer().hasPermission("mxrank.cancelreset")) {
            SuperiorPlayer player = disband.getPlayer();
            String commands = MXConfig.getConfig().getString("mx-rank.reset-command");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commands.replace("<player>", player.getName()));
            MXStorage.getConfig().set("user-data." + player.getUniqueId() + ".priority", 0);
            MXStorage.getConfig().set("user-data." + player.getUniqueId() + ".finished-all-ranks", "false");
            MXStorage.saveConfig();
            Iterator var4 = MXConfig.getConfig().getStringList("messages.rank-reseted").iterator();

            while (var4.hasNext()) {
                String rankup = (String) var4.next();
                disband.getPlayer().asPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', rankup));
            }
        }

    }

    @EventHandler
    public void islandKick(IslandKickEvent disband) {
        if (!disband.getTarget().asPlayer().hasPermission("mxrank.cancelreset")) {
            SuperiorPlayer player = disband.getTarget();
            String commands = MXConfig.getConfig().getString("mx-rank.reset-command");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commands.replace("<player>", player.getName()));
            MXStorage.getConfig().set("user-data." + player.getUniqueId() + ".priority", 0);
            MXStorage.getConfig().set("user-data." + player.getUniqueId() + ".finished-all-ranks", "false");
            MXStorage.saveConfig();
            Iterator var4 = MXConfig.getConfig().getStringList("messages.rank-reseted").iterator();

            while (var4.hasNext()) {
                String rankup = (String) var4.next();
                disband.getPlayer().asPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', rankup));
            }
        }

    }

    @EventHandler
    public void islandBan(IslandBanEvent disband) {
        if (!disband.getTarget().asPlayer().hasPermission("mxrank.cancelreset")) {
            SuperiorPlayer target = disband.getTarget();
            String commands = MXConfig.getConfig().getString("mx-rank.reset-command");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commands.replace("<player>", target.getName()));
            MXStorage.getConfig().set("user-data." + target.getUniqueId() + ".priority", 0);
            MXStorage.getConfig().set("user-data." + target.getUniqueId() + ".finished-all-ranks", "false");
            MXStorage.saveConfig();
            Iterator var4 = MXConfig.getConfig().getStringList("messages.rank-reseted").iterator();

            while (var4.hasNext()) {
                String rankup = (String) var4.next();
                disband.getPlayer().asPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', rankup));
            }
        }

    }
}
