package sk.Adin.Anni.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import sk.Adin.Anni.Anni;

public class TeamCommand implements CommandExecutor {
    private final Anni plugin;

    public TeamCommand(Anni plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	String prefix = plugin.getConfig().getString("prefix").replace("&", "�");
        if (args.length == 0)
            plugin.listTeams(sender);
        else {
            if (!(sender instanceof Player)) {
                sender.sendMessage(prefix + " �c��� ������� ����� ������������ ������ �����!");
            } else {
                plugin.joinTeam((Player) sender, args[0]);
            }
        }
        return true;
    }
}
