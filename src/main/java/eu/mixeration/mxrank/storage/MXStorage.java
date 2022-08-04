//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eu.mixeration.mxrank.storage;

import eu.mixeration.mxrank.MXRank;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class MXStorage {
    private static MXRank plugin;
    private static String path;
    private static String folderpath;
    private static File file;
    private static FileConfiguration config;

    public MXStorage(MXRank plugin, String path, String folder) {
        MXStorage.plugin = plugin;
        MXStorage.path = path;
        folderpath = folder;
        file = null;
        config = null;
    }

    public static void create() {
        file = new File(plugin.getDataFolder() + File.separator + folderpath, path);
        if (!file.exists()) {
            getConfig().options().copyDefaults(true);
            saveConfig();
        }

    }

    public static FileConfiguration getConfig() {
        if (config == null) {
            reloadConfig();
        }

        return config;
    }

    public static void reloadConfig() {
        if (config == null) {
            file = new File(plugin.getDataFolder() + File.separator + folderpath, path);
        }

        config = YamlConfiguration.loadConfiguration(file);

        try {
            Reader defaultConfigStream = new InputStreamReader(plugin.getResource(path), StandardCharsets.UTF_8);
            if (defaultConfigStream != null) {
                YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(defaultConfigStream);
                config.setDefaults(defaultConfig);
            }
        } catch (NullPointerException var2) {
            var2.printStackTrace();
        }

    }

    public static void saveConfig() {
        try {
            config.save(file);
        } catch (IOException var1) {
            var1.printStackTrace();
        }

    }

    public void saveDefaultConfig() {
        if (file == null) {
            file = new File(plugin.getDataFolder() + File.separator + folderpath, path);
        }

        if (!file.exists()) {
            plugin.saveResource(path, false);
        }

    }

    public String getPath() {
        return path;
    }

    public String getFolderpath() {
        return folderpath;
    }
}
