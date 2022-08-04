package eu.mixeration.mxrank.events;

import eu.mixeration.mxrank.MXRank;
import eu.mixeration.mxrank.settings.MXValues;
import eu.mixeration.mxrank.storage.MXStorage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class MXJoin implements Listener {
    public MXJoin(MXRank mxRank) {
    }

    @EventHandler(
            priority = EventPriority.HIGH
    )
    public void createPlayerData(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (MXStorage.getConfig().getString("user-data." + player.getUniqueId().toString()) == null) {
            MXStorage.getConfig().set("user-data." + player.getUniqueId().toString() + ".name", player.getName());
            MXStorage.getConfig().set("user-data." + player.getUniqueId().toString() + ".current-rank", MXValues.DEFAULT);
            MXStorage.getConfig().set("user-data." + player.getUniqueId().toString() + ".priority", "0");
            MXStorage.getConfig().set("user-data." + player.getUniqueId().toString() + ".finished-all-ranks", "false");
            MXStorage.saveConfig();
        }

    }
}
