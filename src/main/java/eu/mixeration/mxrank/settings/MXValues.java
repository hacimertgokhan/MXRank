//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eu.mixeration.mxrank.settings;

import eu.mixeration.mxrank.config.MXConfig;

public class MXValues {
    public static String DEFAULT = MXConfig.getConfig().getString("mx-rank.default-rank");
    public static String PREFIX = MXConfig.getConfig().getString("mx-rank.messages.prefix").replaceAll("&", "§");
    public static String MENU_TITLE = MXConfig.getConfig().getString("mx-rank.menu.title").replaceAll("&", "§");

    public MXValues() {
    }
}
