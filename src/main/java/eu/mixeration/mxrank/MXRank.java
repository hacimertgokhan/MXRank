//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eu.mixeration.mxrank;

import eu.mixeration.mxrank.commands.MXAdminisitrator;
import eu.mixeration.mxrank.config.MXConfig;
import eu.mixeration.mxrank.events.MXJoin;
import eu.mixeration.mxrank.events.askyblock.A$IslandReset;
import eu.mixeration.mxrank.events.superior.S$IslandReset;
import eu.mixeration.mxrank.menu.askyblock.A$MXRankMenu$interact;
import eu.mixeration.mxrank.menu.superior.S$MXRankMenu$interact;
import eu.mixeration.mxrank.settings.MXApi;
import eu.mixeration.mxrank.storage.MXStorage;
import eu.mixeration.mxrank.utils.MXLoad;
import eu.mixeration.mxrank.utils.MXVault;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

import static eu.mixeration.mxrank.settings.MXApi.getAPI;

public final class MXRank extends JavaPlugin {
    public static final Logger mx$logger = Logger.getLogger("Minecraft");
    private final MXConfig _config = new MXConfig(this, "config.yml");
    private final MXStorage _storage = new MXStorage(this, "storage.yml", "player-data");

    public MXRank() {
    }

    public void whichApiWillUse() {
        if (getAPI().equalsIgnoreCase("SuperiorSkyBlock")) {
            Bukkit.getPluginManager().registerEvents(new S$MXRankMenu$interact(this), this);
            Bukkit.getPluginManager().registerEvents(new S$IslandReset(this), this);
        } else if (getAPI().equalsIgnoreCase("ASkyBlock")) {
            Bukkit.getPluginManager().registerEvents(new A$IslandReset(this), this);
            Bukkit.getPluginManager().registerEvents(new A$MXRankMenu$interact(this), this);
        }
    }

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new MXJoin(this), this);
        MXLoad.loadAPI();
        MXConfig.create();
        MXStorage.create();
        if (!MXVault.setupEconomy()) {
            MXVault.log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", this.getDescription().getName()));
            this.getServer().getPluginManager().disablePlugin(this);
        } else {
            MXVault.setupPermissions();
            mx$logger.info(String.format("[%s] Selected rank-up API: (%s)", this.getDescription().getName(), MXApi.getAPI()));
            this.getCommand("MXRank").setExecutor(new MXAdminisitrator(this));
            whichApiWillUse();
        }
    }
}
