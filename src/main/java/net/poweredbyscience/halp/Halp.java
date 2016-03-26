package net.poweredbyscience.halp;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Lax on 3/8/2016.
 */
public class Halp extends JavaPlugin {

    public void onEnable() {
        getCommand("halp").setExecutor(new HalpCommand(this));
    }
}
