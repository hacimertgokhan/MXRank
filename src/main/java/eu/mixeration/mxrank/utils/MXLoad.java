//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eu.mixeration.mxrank.utils;

import eu.mixeration.mxrank.MXRank;
import eu.mixeration.mxrank.settings.MXApi;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class MXLoad {
    public MXLoad() {
    }

    public static void loadAPI() {
        Plugin superiorSkyblock = Bukkit.getPluginManager().getPlugin("SuperiorSkyblock2");
        Plugin aSkyBlock = Bukkit.getPluginManager().getPlugin("ASkyBlock");
        if (superiorSkyblock == null) {
            if (aSkyBlock == null) {
                MXRank.mx$logger.severe(String.format("[%s] No api found, Using VaultAPI...", MXRank.getPlugin(MXRank.class).getDescription().getName()));
                MXApi.setAPI("Vault");
            }
        } else {
            MXApi.setAPI("SuperiorSkyBlock");
        }

        if (aSkyBlock != null) {
            if (superiorSkyblock == null) {
                MXApi.setAPI("ASkyBlock");
            } else {
                MXRank.mx$logger.severe(String.format("[%s] 2 API plugins detected. You need a single api to use the plugin...", MXRank.getPlugin(MXRank.class).getDescription().getName()));
            }
        }

    }
}
