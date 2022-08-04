//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eu.mixeration.mxrank.utils;

import eu.mixeration.mxrank.config.MXConfig;
import eu.mixeration.mxrank.settings.MXValues;

public class MXString {
    public MXString() {
    }

    public static String message(String message) {
        return MXConfig.getConfig().getString("mx-rank.messages." + message).replace("<prefix>", MXValues.PREFIX);
    }
}
