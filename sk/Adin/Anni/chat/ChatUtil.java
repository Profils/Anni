package sk.Adin.Anni.chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import sk.Adin.Anni.Anni;
import sk.Adin.Anni.Util;
import sk.Adin.Anni.bar.TitleAPI;
import sk.Adin.Anni.object.Boss;
import sk.Adin.Anni.object.GameTeam;
import sk.Adin.Anni.object.PlayerMeta;

public class ChatUtil {

    private static boolean roman = false;

    public static void setRoman(boolean b) {
        roman = b;
    }
    
    public static void broadcast(String message) {
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                message));
    }

    public static void nexusDestroyed(GameTeam attacker, GameTeam victim, Player p) {
    	String title = Anni.getInstance().getConfig().getString("NexusZnicenTitle").replace("&", "§").replace("%ZNICEN%", victim.coloredName()).replace("%KILLER%", attacker.color().toString() + p.getName()).replace("%TEAM%", attacker.coloredName());
    	String sub = Anni.getInstance().getConfig().getString("NexusZnicenSubTitle").replace("&", "§").replace("%ZNICEN%", victim.coloredName()).replace("%KILLER%", attacker.color().toString() + p.getName()).replace("%TEAM%", attacker.coloredName());
        broadcast("§8===============[ §eNexus уничтожен §8]===============");
        broadcast(attacker.color().toString() + p.getName() + " §7з команды §r" + attacker.coloredName() + " §7уничтожел " + victim.coloredName() + " §7Nexus!");
        broadcast("§8==============================================");
        if (Anni.getInstance().getConfig().getBoolean("EnableNexusTitle") == true) {
        	TitleAPI.AllTitle(title, sub);
        }
    }

    public static String nexusBreakMessage(Player breaker, GameTeam attacker,
            GameTeam victim) {
        return colorizeName(breaker, attacker) + " §7ломает Nexus команды "
                + victim.coloredName();
    }

    private static String colorizeName(Player player, GameTeam team) {
        return team.color() + player.getName();
    }

    public static void phaseMessage(int phase) {
    	String title = Anni.getInstance().getConfig().getString("PhaseTitle").replace("&", "§").replace("%PHASE%", translateRoman(phase));
    	String sub = Anni.getInstance().getConfig().getString("PhaseSubTitle").replace("&", "§").replace("%PHASE%", translateRoman(phase));
        broadcast("§8===========[ §eПрогресс §8]===========");
        broadcast(Util.getPhaseColor(phase) + "Фаза " + translateRoman(phase) + " §7началась!");
        switch (phase) {
        case 1:
            broadcast("§7Добываем ресурсы! " + translateRoman(2));
            if (Anni.getInstance().getConfig().getBoolean("EnablePhaseTitle") == true) {
            	TitleAPI.AllTitle(title, sub);
            }
            break;
        case 2:
            broadcast("§7Можно ломать Nexus!");
            broadcast("§7Boss заспавнился!");
            if (Anni.getInstance().getConfig().getBoolean("EnablePhaseTitle") == true) {
            	TitleAPI.AllTitle(title, sub);
            }
            break;
        case 3:
            broadcast("§bАлмазы §7появились в центре!");
            if (Anni.getInstance().getConfig().getBoolean("EnablePhaseTitle") == true) {
            	TitleAPI.AllTitle(title, sub);
            }
            break;
        case 4:
            if (Anni.getInstance().getConfig().getBoolean("EnablePhaseTitle") == true) {
            	TitleAPI.AllTitle(title, sub);
            }
            break;
        case 5:
            broadcast("§cДвойной урон по Nexus!");
            if (Anni.getInstance().getConfig().getBoolean("EnablePhaseTitle") == true) {
            	TitleAPI.AllTitle(title, sub);
            }
        }
        broadcast("§8================================");
    }

    public static void winMessage(GameTeam winner) {
    	String title = Anni.getInstance().getConfig().getString("EndGameTitle").replace("&", "§").replace("%TEAM%", winner.coloredName());
    	String sub = Anni.getInstance().getConfig().getString("EndGameSubTitle").replace("&", "§").replace("%TEAM%", winner.coloredName());
        broadcast("§8================[ §eКонец Игры§8 ]================");
        broadcast("§7Команда §r" + winner.coloredName() + " §7Выиграла! §cПерезапуск...");
        broadcast("§8=============================================");
        if (Anni.getInstance().getConfig().getBoolean("EnableEndTitle") == true) {
        	TitleAPI.AllTitle(title, sub);
        }
    }

    public static void bossDeath(Boss b, Player killer, GameTeam team) {
        broadcast("§8==========[ §eBoss Убит! §8]==========");
        broadcast("§7Boss §r" + b.getBossName() + " §7был убит " + colorizeName(killer, team));
        broadcast("§8===================================");
    }

    public static void bossRespawn(Boss b) {
        broadcast("§8================[ §eBoss §8]================");
        broadcast("§7Boss §r" + b.getBossName() + " §7заспавнился! Пора его замочить!");
        broadcast("§8========================================");
    }

    public static String formatDeathMessage(Player victim, Player killer,
            String original) {
        GameTeam killerTeam = PlayerMeta.getMeta(killer).getTeam();
        String killerColor = killerTeam != null ? killerTeam.color().toString()
                : ChatColor.DARK_PURPLE.toString();
        String killerName = killerColor + killer.getName() + ChatColor.GRAY;

        String message = ChatColor.GRAY + formatDeathMessage(victim, original);
        message = message.replace(killer.getName(), killerName);

        return message;
    }

    public static String formatDeathMessage(Player victim, String original) {
        GameTeam victimTeam = PlayerMeta.getMeta(victim).getTeam();
        String victimColor = victimTeam != null ? victimTeam.color().toString()
                : ChatColor.DARK_PURPLE.toString();
        String victimName = victimColor + victim.getName() + ChatColor.GRAY;

        String message = ChatColor.GRAY + original;
        message = message.replace(victim.getName(), victimName);

        if (message.contains("agrte")) {
            String[] arr = message.split("64369");
            message = arr[0];
        }

        return message.replace("был убит", "был убит");
    }

    public static String translateRoman(int number) {
        if (!roman)
            return String.valueOf(number);

        switch (number) {
        case 0:
            return "0";
        case 1:
            return "I";
        case 2:
            return "II";
        case 3:
            return "III";
        case 4:
            return "IV";
        case 5:
            return "V";
        case 6:
            return "VI";
        case 7:
            return "VII";
        case 8:
            return "VIII";
        case 9:
            return "IX";
        case 10:
            return "X";
        default:
            return String.valueOf(number);
        }
    }

    private ChatUtil() {
    }
}
