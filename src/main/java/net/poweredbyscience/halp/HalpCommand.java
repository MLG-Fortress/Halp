package net.poweredbyscience.halp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Lax on 3/12/2016.
 */
public class HalpCommand implements CommandExecutor {

    Halp plugin;

    public HalpCommand(Halp plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender.hasPermission("halpme.halp")) {
            if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("me")) {
                    sender.sendMessage("Report link: " + NSAConnector.upload(new InfoBuilder().Build("-")));
                }
                if (args[0].equalsIgnoreCase("all")) {
                    sender.sendMessage("Report link: " + NSAConnector.upload(new InfoBuilder().Build("k"))+".halp");
                }
//                if (args[0].equalsIgnoreCase("debug")) {
//                    new InfoBuilder().Debug();
//                }
            } else {
                sender.sendMessage("Usage: /halp <me,all>");
            }
        }
        return false;
    }
}
