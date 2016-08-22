package sk.Adin.Anni.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import sk.Adin.Anni.Anni;

public class AnniCommand implements CommandExecutor {
    private Anni plugin;

    public AnniCommand(Anni plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String prefix = plugin.getConfig().getString("prefix").replace("&", "§");
        
        if (args.length == 0) {
            sender.sendMessage(prefix + " §eNexus §cv" + plugin.getDescription().getVersion() + " §7by .");
            sender.sendMessage(prefix + " §eProfil");
            sender.sendMessage("§8=-=-=-=-=-=[ §eÊîìàíäû§8 ]=-=-=-=-=-=");
            sender.sendMessage(prefix + " §e/anni §7- §ainfo");
            sender.sendMessage(prefix + " §e/anni start§7- §aStart");
            sender.sendMessage(prefix + " §e/stats §7- §astats");
            sender.sendMessage(prefix + " §e/team §7- §ainfo o team");
            sender.sendMessage(prefix + " §e/team <team> §7- §ateam");
        }
        
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("start")) {
                if (sender.hasPermission("anni.command.start")) {
                    if (!plugin.startTimer()) {
                        sender.sendMessage(prefix + " §cÇàïóñêàåì èãğó!");
                    } else {
                        sender.sendMessage(prefix + " §aáûëà âûáğàíà!");
                    }
                } else sender.sendMessage(prefix + " §cÂû íå èìååòå ïğàâà íà ıòó êîìàíäó!");
            }
        }
        
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("stop")) {
                if (sender.hasPermission("anni.command.start")) {
                    if (!plugin.resetGame()) {
                        sender.sendMessage(prefix + " §cÑòîï èãğà!");
                    }
                } else sender.sendMessage(prefix + " §cÂû íå èìååòå ïğàâà íà ıòó êîìàíäó!");
            }
        }
        return false;
    }
}
