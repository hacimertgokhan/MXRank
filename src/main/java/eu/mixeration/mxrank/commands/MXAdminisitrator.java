package eu.mixeration.mxrank.commands;

import eu.mixeration.mxrank.MXRank;
import eu.mixeration.mxrank.config.MXConfig;
import eu.mixeration.mxrank.menu.MXRankMenu;
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
