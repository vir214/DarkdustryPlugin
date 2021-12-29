package pandorum.commands.server;

import arc.util.Log;

import static mindustry.Vars.netServer;

public class UnbanCommand {
    public static void run(final String[] args) {
        if (args[0].equalsIgnoreCase("all")) {
            netServer.admins.getBanned().each(ban -> netServer.admins.unbanPlayerID(ban.id));
            netServer.admins.getBannedIPs().each(ip -> netServer.admins.unbanPlayerIP(ip));
            Log.info("Все игроки разбанены...");
        } else if (netServer.admins.unbanPlayerID(args[0]) || netServer.admins.unbanPlayerIP(args[0])) {
            Log.info("Игрок успешно разбанен.");
        } else {
            Log.err("Игрок не был забанен!");
        }
    }
}
