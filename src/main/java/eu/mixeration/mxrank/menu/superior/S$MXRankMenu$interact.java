package eu.mixeration.mxrank.menu.superior;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import eu.mixeration.mxrank.MXRank;
import eu.mixeration.mxrank.config.MXConfig;
import eu.mixeration.mxrank.settings.MXValues;
import eu.mixeration.mxrank.storage.MXStorage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

import java.util.Iterator;

public class S$MXRankMenu$interact implements Listener {
    public S$MXRankMenu$interact(MXRank plugin) {
    }

    @Deprecated
    @EventHandler
    public void drag(InventoryDragEvent event) {
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', MXValues.MENU_TITLE))) {
            event.setCancelled(true);
        }

    }

    @Deprecated
    @EventHandler
    public void click(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', MXValues.MENU_TITLE))) {
            Iterator var3 = MXConfig.getConfig().getConfigurationSection("mx-rank.player-ranks").getKeys(false).iterator();

            while (true) {
                while (true) {
                    int neededLevel;
                    String groupname;
                    String display;
                    String next_groupname;
                    String command;
                    int nextLevel;
                    int priority;
                    boolean lastrank;
                    int playerPriority;
                    boolean playerBoolean;
                    label43:
                    do {
                        while (var3.hasNext()) {
                            String groups = (String) var3.next();
                            neededLevel = MXConfig.getConfig().getInt("mx-rank.player-ranks." + groups + ".level");
                            groupname = MXConfig.getConfig().getString("mx-rank.player-ranks." + groups + ".group-name");
                            display = MXConfig.getConfig().getString("mx-rank.player-ranks." + groups + ".display");
                            next_groupname = MXConfig.getConfig().getString("mx-rank.player-ranks." + groups + ".next-group-name");
                            command = MXConfig.getConfig().getString("mx-rank.player-ranks." + groups + ".run-command");
                            nextLevel = MXConfig.getConfig().getInt("mx-rank.player-ranks." + next_groupname + ".level");
                            priority = MXConfig.getConfig().getInt("mx-rank.player-ranks." + groups + ".priority");
                            lastrank = MXConfig.getConfig().getBoolean("mx-rank.player-ranks." + groups + ".last-rank");
                            playerPriority = MXStorage.getConfig().getInt("user-data." + player.getUniqueId() + ".priority");
                            playerBoolean = MXStorage.getConfig().getBoolean("user-data." + player.getUniqueId() + ".finished-all-ranks");
                            if (event.getCurrentItem() != null) {
                                continue label43;
                            }

                            event.setCancelled(true);
                        }

                        return;
                    } while (!event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', display)));

                    if (!playerBoolean) {
                        event.setCancelled(true);
                        int calculatedPriority = playerPriority + 1;
                        SuperiorPlayer superiorPlayer = SuperiorSkyblockAPI.getPlayer(player);
                        Island island = superiorPlayer.getIsland();
                        if (!superiorPlayer.hasIsland()) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', MXConfig.getConfig().getString("messages.has-not-island")));
                            return;
                        }

                        if (island.getIslandLevel().intValueExact() < neededLevel) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', MXConfig.getConfig().getString("messages.not-enough-level").replace("<level>", String.valueOf(neededLevel)).replace("<your>", String.valueOf(SuperiorSkyblockAPI.getPlayer(player).getIsland().getIslandLevel().intValueExact()))));
                            return;
                        }

                        if (calculatedPriority != priority) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', MXConfig.getConfig().getString("messages.not-next-rank")));
                            return;
                        }

                        Iterator var18;
                        String rankup;
                        if (!lastrank) {
                            var18 = MXConfig.getConfig().getStringList("messages.rank-up").iterator();

                            while (var18.hasNext()) {
                                rankup = (String) var18.next();
                                rankup = rankup.replace("<next>", next_groupname).replace("<new>", groupname).replace("<level>", String.valueOf(neededLevel)).replace("<your>", String.valueOf(SuperiorSkyblockAPI.getPlayer(player).getIsland().getIslandLevel().intValue())).replace("<nextgrouplevel>", String.valueOf(nextLevel));
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', rankup));
                            }

                            MXStorage.getConfig().set("user-data." + player.getUniqueId() + ".priority", priority);
                            MXStorage.saveConfig();
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("<player>", player.getName()));
                        } else {
                            var18 = MXConfig.getConfig().getStringList("mx-rank.messages.last-rank").iterator();

                            while (var18.hasNext()) {
                                rankup = (String) var18.next();
                                rankup = rankup.replace("<next>", next_groupname).replace("<new>", groupname).replace("<level>", String.valueOf(neededLevel)).replace("<your>", String.valueOf(SuperiorSkyblockAPI.getPlayer(player).getIsland().getIslandLevel().intValue()));
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', rankup));
                            }

                            MXStorage.getConfig().set("user-data." + player.getUniqueId() + ".priority", priority);
                            MXStorage.getConfig().set("user-data." + player.getUniqueId() + ".finished-all-ranks", "true");
                            MXStorage.saveConfig();
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("<player>", player.getName()));
                        }
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', MXConfig.getConfig().getString("messages.finished-all-ranks")));
                    }
                }
            }
        }
    }
}
