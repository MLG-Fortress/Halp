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
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender.hasPermission("halpme.halp")) {
            sender.sendMessage("Report link: " + NSAConnector.upload(new InfoBuilder().Build())+".halp");
        }
        return false;
    }
}
