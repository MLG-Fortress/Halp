package net.poweredbyscience.halp.Bungee;

import net.md_5.bungee.api.plugin.Plugin;
import net.poweredbyscience.halp.Bungee.BungeeCommand;
import net.poweredbyscience.halp.NSAConnector;

/**
 * Created by Lax on 3/20/2017.
 */
public class HalpBungee extends Plugin {

    public static String service;

    public void onEnable() {
        service = "HASTE";
        NSAConnector.service = "HASTE";
        getProxy().getPluginManager().registerCommand(this, new BungeeCommand(this));
    }

}
