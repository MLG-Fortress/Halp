package net.poweredbyscience.halp.Bungee;

import net.md_5.bungee.api.ProxyServer;
import net.poweredbyscience.halp.NSAConnector;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Created by Lax on 4/28/2017.
 */
public class BungeeInfoBuilder {

    public String Build(int k) {
        StringBuilder b = new StringBuilder();
        b.append("   __ __     __     __  ___   \n" +
                "  / // /__ _/ /__  /  |/  /__ \n" +
                " / _  / _ `/ / _ \\/ /|_/ / -_)\n" +
                "/_//_/\\_,_/_/ .__/_/  /_/\\__/ \n" +
                "           /_/  By: LaxWasHere \uD83C\uDF2E               \n\n");
        b.append("Generated on " + new Timestamp(new java.util.Date().getTime())).append("\n\n");
        b.append("Bungee Log: " + NSAConnector.upload(new File(ProxyServer.getInstance().getPluginsFolder(),"../proxy.log.0"), "GIST")).append("\n"); //File might be too large
        b.append("Bungee Config: " + NSAConnector.upload(new File(ProxyServer.getInstance().getPluginsFolder(),"../config.yml"), HalpBungee.service)).append("\n");
        b.append("modules.yml: " + NSAConnector.upload(new File(ProxyServer.getInstance().getPluginsFolder(),"../modules.yml"), HalpBungee.service)).append("\n");
        b.append("waterfall.yml: " + NSAConnector.upload(new File(ProxyServer.getInstance().getPluginsFolder(),"../waterfall.yml"), HalpBungee.service)).append("\n\n");
        b.append("Version: " + "This server is running BungeeCord version "+ProxyServer.getInstance().getVersion()).append("\n");
        b.append("Memory: " + Runtime.getRuntime().freeMemory() / 1024 / 1024 + "MB/" + Runtime.getRuntime().maxMemory() / 1024 / 1024).append("MB\n");
        b.append("Uptime: " + new SimpleDateFormat("mm:ss:SSS").format(new Date(ManagementFactory.getRuntimeMXBean().getStartTime()))).append("\n");
        b.append("Java Version: " + System.getProperty("java.vendor") + " " + System.getProperty("java.version")).append("\n\n");
        b.append("Plugins: (" + ProxyServer.getInstance().getPluginManager().getPlugins().size() + ") \n  ");

        for (net.md_5.bungee.api.plugin.Plugin p : ProxyServer.getInstance().getPluginManager().getPlugins()) {
            b.append(p.getDescription().getName()).append("\n    ");
            // b.append("Enabled: " + p.getDescription()).append("\n    ");
            b.append("Version: " +p.getDescription().getVersion()).append("\n    ");
            b.append("Main: " + p.getDescription().getMain()).append("\n     ");
            if(k == 1) { //probably a bad idea.
                try {
                    Files.walk(Paths.get(p.getDataFolder().getCanonicalPath()))
                            .filter(Files::isRegularFile)
                            .map(Path::toFile)
                            .collect(Collectors.toList()).stream().filter(file -> file.getName().endsWith(".yml") || file.getName().endsWith(".log")).forEach(file -> b.append(file.getName() + ": " + NSAConnector.upload(file, HalpBungee.service)).append("\n     "));
                } catch (IOException e) {
                    b.append("No files" + "\n  ");
                }
                b.append("\n   ");
            } else {
                b.append("\n   ");
            }
        }
        b.append("\n--- End of halp file ---");
        return b.toString();
    }
}
