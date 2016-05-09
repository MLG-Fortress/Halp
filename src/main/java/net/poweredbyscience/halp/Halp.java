package net.poweredbyscience.halp;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Lax on 3/8/2016.
 */
public class Halp extends JavaPlugin {

    public static String service;

    public void onEnable() {
        saveDefaultConfig();
        service = getConfig().getString("service");
        getCommand("halp").setExecutor(new HalpCommand(this));
    }
}
