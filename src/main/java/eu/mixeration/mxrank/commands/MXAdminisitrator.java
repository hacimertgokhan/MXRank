package eu.mixeration.mxrank.commands;

import eu.mixeration.mxrank.MXRank;
import eu.mixeration.mxrank.config.MXConfig;
import eu.mixeration.mxrank.menu.MXRankMenu;
import eu.mixeration.mxrank.settings.MXValues;
import eu.mixeration.mxrank.storage.MXStorage;
import eu.mixeration.mxrank.utils.MXInteger;
import eu.mixeration.mxrank.utils.MXString;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Iterator;

public class MXAdminisitrator implements CommandExecutor {
    public MXAdminisitrator(MXRank mxRank) {
    }

    public void help(Player player) {
        Iterator var2 = MXConfig.getConfig().getStringList("mx-rank.messages.help").iterator();

        while (var2.hasNext()) {
            String messages = (String) var2.next();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messages));
        }

    }

    /**
     * @deprecated
     */
    @Deprecated
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();
            if (command.getName().equalsIgnoreCase("MXRank")) {
                if (player.hasPermission("mxrank.manager")) {
                    if (args.length != 0 && !args[0].equalsIgnoreCase("help")) {
                        if (args.length == 1) {
                            if (args[0].equalsIgnoreCase("Reload")) {
                                MXConfig.reloadConfig();
                                MXConfig.saveConfig();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', MXString.message("reloaded")));
                            } else if (args[0].equalsIgnoreCase("Menu")) {
                                MXRankMenu.openInventory(player);
                            } else {
                                this.help(player);
                            }
                        } else if (args.length == 2) {
                            Player target = Bukkit.getPlayer(args[1]);
                            if (args[0].equalsIgnoreCase("about")) {
                                if (target == null) {
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', MXString.message("not-online")));
                                } else {
                                    for (String message : MXConfig.getConfig().getStringList("mx-rank.messages.about-player")) {
                                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message
                                                .replace("<player>", target.getPlayer().getName())
                                                .replace("<isPlayerFinishAllRanks>", MXStorage.getConfig().getString("user-data." + target + ".finished-all-ranks"))
                                                .replace("<rank>", MXStorage.getConfig().getString("user-data." + target + ".current-rank"))
                                                .replace("<priority>", MXStorage.getConfig().getString("user-data." + target + ".priority"))));
                                    }
                                }
                            } else if (args[0].equalsIgnoreCase("reset")) {
                                if (target == null) {
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', MXString.message("not-online")));
                                } else {
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', MXString.message("rank-reseted")));
                                    String commands = MXConfig.getConfig().getString("mx-rank.reset-command");
                                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commands.replace("<player>", player.getName()));
                                    MXStorage.getConfig().set("user-data." + player.getUniqueId().toString() + ".name", player.getName());
                                    MXStorage.getConfig().set("user-data." + player.getUniqueId().toString() + ".current-rank", MXValues.DEFAULT);
                                    MXStorage.getConfig().set("user-data." + player.getUniqueId().toString() + ".priority", "0");
                                    MXStorage.getConfig().set("user-data." + player.getUniqueId().toString() + ".finished-all-ranks", "false");
                                    MXStorage.saveConfig();
                                }
                            } else {
                                this.help(player);
                            }
                        } else if (args.length == 3) {
                            Player target = Bukkit.getPlayer(args[1]);
                            if (args[0].equalsIgnoreCase("setpriority")) {
                                if (target == null) {
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', MXString.message("not-online")));
                                } else {
                                    if (MXInteger.isInt(args[2])) {
                                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', MXString.message("not-integer")));
                                    } else {
                                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', MXString.message("priority-changed")));
                                        MXStorage.getConfig().set("user-data." + player.getUniqueId().toString() + ".priority", args[2]);
                                        MXStorage.saveConfig();
                                    }
                                }
                            } else {
                                this.help(player);
                            }
                        } else {
                            this.help(player);
                        }
                    } else {
                        this.help(player);
                    }
                } else {
                    MXRankMenu.openInventory(player);
                }
            }
        } else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', MXString.message("only-in-game")));
        }

        return true;
    }
}
