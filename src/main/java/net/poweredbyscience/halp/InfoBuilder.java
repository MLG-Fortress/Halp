package net.poweredbyscience.halp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Lax on 3/12/2016.
 */
public class InfoBuilder {

    public String Build() {
        StringBuilder b = new StringBuilder();
        b.append("   __ __     __     __  ___   \n" +
                "  / // /__ _/ /__  /  |/  /__ \n" +
                " / _  / _ `/ / _ \\/ /|_/ / -_)\n" +
                "/_//_/\\_,_/_/ .__/_/  /_/\\__/ \n" +
                "           /_/  By: LaxWasHere                \n\n");
        b.append("Generated on " + new Timestamp(new java.util.Date().getTime())).append("\n\n");
        b.append("Server Log: " + NSAConnector.upload(new File(Bukkit.getPluginManager().getPlugin("Halp").getDataFolder(),"../../logs/latest.log"))).append("\n");
        b.append("server.properties: " + NSAConnector.upload(new File(Bukkit.getPluginManager().getPlugin("Halp").getDataFolder(),"../../server.properties"))).append("\n");
        b.append("spigot.yml: " + NSAConnector.upload(new File(Bukkit.getPluginManager().getPlugin("Halp").getDataFolder(),"../../spigot.yml"))).append("\n");
        b.append("paper.yml: " + NSAConnector.upload(new File(Bukkit.getPluginManager().getPlugin("Halp").getDataFolder(),"../../paper.yml"))).append("\n\n");
        b.append("Version: " + Bukkit.getServer().getVersion() + " (Implementing API version "+Bukkit.getServer().getBukkitVersion()).append(")\n");
        b.append("Memory: " + Runtime.getRuntime().freeMemory() / 1024 / 1024 + "MB/" + Runtime.getRuntime().maxMemory() / 1024 / 1024).append("MB\n");
        b.append("Uptime: " + new SimpleDateFormat("mm:ss:SSS").format(new Date(ManagementFactory.getRuntimeMXBean().getStartTime()))).append("\n");
        b.append("Java Version: " + System.getProperty("java.vendor") + " " + System.getProperty("java.version")).append("\n\n");
        b.append("Plugins: (" + Bukkit.getServer().getPluginManager().getPlugins().length + ") \n  ");
        for (Plugin p : Bukkit.getServer().getPluginManager().getPlugins()) {
            b.append(p.getName()).append("\n    ");
            b.append("Enabled: " + p.isEnabled()).append("\n    ");
            b.append("Version: " +p.getDescription().getVersion()).append("\n    ");
            b.append("Main: " + p.getDescription().getMain()).append("\n  ");
        }
        b.append("\n--- End of halp file ---");
        return b.toString();
    }
}