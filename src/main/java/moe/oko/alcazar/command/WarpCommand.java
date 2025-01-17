package moe.oko.alcazar.command;

import moe.oko.alcazar.handler.WarpHandler;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

public class WarpCommand implements TabExecutor {
    WarpHandler handler;

    public WarpCommand (WarpHandler warpHandler) { handler = warpHandler; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to run this command!");
            return true;
        }

        if (args.length != 1)
            return false;
        var message = handler.load((Player) sender, args[0])
                ? (ChatColor.of("#986de0") + "Warping to " + ChatColor.of("#ffffff") + "%s" + ChatColor.of("#986de0") + "!").formatted(args[0])
                : (ChatColor.of("#ffffff") + "%s" + ChatColor.of("#986de0") + " not found.").formatted(args[0]);
        sender.sendMessage(message);

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        return switch (args.length) {
            case 1 -> handler.list();
            default -> null;
        };
    }
}
