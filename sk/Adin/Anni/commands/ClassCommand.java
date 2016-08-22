package sk.Adin.Anni.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import sk.Adin.Anni.Anni;
import sk.Adin.Anni.Util;

public class ClassCommand implements CommandExecutor {
    private Anni plugin;

    public ClassCommand(Anni plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String prefix = plugin.getConfig().getString("prefix").replace("&", "�");
        Player p = (Player) sender;
        
        if (args.length == 0) {
        	if (!(sender instanceof Player)) {
        		sender.sendMessage(prefix + " �c��� ������� ����� ������������ ������ �����!");
        	} else {
            	String namei = plugin.getConfig().getString("TitleClassMenu").replace("&", "�");
                Util.showClassSelector(p,
                        namei);
        	}
        }
        
        if (args.length == 1) {
        	if (!(sender instanceof Player)) {
        		sender.sendMessage(prefix + " �c��� ������� ����� ������������ ������ �����!");
        	} else {
        	sender.sendMessage(prefix + " �c�����: �e/class");
            }
        }
        return false;
    }
}
