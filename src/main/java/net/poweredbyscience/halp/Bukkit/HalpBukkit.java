package net.poweredbyscience.halp.Bukkit;

import net.poweredbyscience.halp.NSAConnector;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * Created by Lax on 3/8/2016.
 */
public class HalpBukkit extends JavaPlugin {

    public static String service;

    public void onEnable() {
        saveDefaultConfig();
        service = getConfig().getString("service");
        NSAConnector.service = getConfig().getString("service");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender.hasPermission("halpme.halp")) {
            if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("me")) {
                    Bukkit.getScheduler().runTaskAsynchronously(this, () -> sender.sendMessage("Report link: " + NSAConnector.upload(new BukkitInfoBuilder().Build("-"), HalpBukkit.service)));
                }
                if (args[0].equalsIgnoreCase("all")) {
                    Bukkit.getScheduler().runTaskAsynchronously(this, () -> sender.sendMessage("Report link: " + NSAConnector.upload(new BukkitInfoBuilder().Build("k"), HalpBukkit.service)));
                }
                if (args[0].equalsIgnoreCase("log")) {
                    Bukkit.getScheduler().runTaskAsynchronously(this, () -> sender.sendMessage("Uploaded log: " + NSAConnector.upload(new File(Bukkit.getPluginManager().getPlugin("Halp").getDataFolder(),"../../logs/latest.log"), HalpBukkit.service)));
                }
//                if (args[0].equalsIgnoreCase("debug")) {
//                    new InfoBuilder().Debug();
//                }
            } else {
                sender.sendMessage("Usage: /halp <me,all,log>");
            }
        }
        return false;
    }
}
