package net.poweredbyscience.halp.Bungee;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.poweredbyscience.halp.NSAConnector;

import java.io.File;

/**
 * Created by Lax on 3/20/2017.
 */
public class BungeeCommand extends Command {

    HalpBungee plugin;

    public BungeeCommand(HalpBungee plugin) {
        super("bhalp");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("halpme.halp")) {
            if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("me")) {
                    sender.sendMessage("Report link: " + NSAConnector.upload(new BungeeInfoBuilder().Build(0), HalpBungee.service));
                }
                if (args[0].equalsIgnoreCase("all")) {
                    sender.sendMessage("Report link: " + NSAConnector.upload(new BungeeInfoBuilder().Build(1), HalpBungee.service));
                }
                if (args[0].equalsIgnoreCase("log")) {
                    sender.sendMessage("Uploaded Log: " + NSAConnector.upload(new File(ProxyServer.getInstance().getPluginsFolder(),"../proxy.log.0"), "GIST"));
                }
//                if (args[0].equalsIgnoreCase("debug")) {
//                    new InfoBuilder().Debug();
//                }
            } else {
                sender.sendMessage("Usage: /bhalp <me,all,log>");
            }
        }
    }
}
