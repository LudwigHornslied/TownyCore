package io.nebulamc.core.commands;

import io.nebulamc.core.Core;
import io.nebulamc.core.PermissionNodes;
import io.nebulamc.core.util.LocationUtil;
import io.nebulamc.core.util.Translation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RTPCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // Check they've supplied the right number of arguments
        if(args.length < 3) {
            return true;
        }

        // Parse the arguments
        Player victim = Bukkit.getPlayer(args[0]);
        if(victim == null) {
            sender.sendMessage(Core.PREFIX + Translation.get("player_not_exist"));
            return true;
        }

        double x, z;
        try {
            x = Double.parseDouble(args[1]);
            z = Double.parseDouble(args[2]);
        } catch(NumberFormatException e) {
            sender.sendMessage(Core.PREFIX + Translation.get("invalid_number"));
            return true;
        }


        for(int i = 0; i < 20; i++) {
            // Get a random offset within 2000 blocks
            double angle = Math.random() * 360;
            double distance = Math.random() * 2000;

            double x_off = Math.sin(angle) * distance;
            double z_off = Math.cos(angle) * distance;

            Location loc = new Location(victim.getWorld(), x + x_off, 62.0, z + z_off);
            for(int j = 0; j < 20; j++) {
                if (!LocationUtil.isSafe(loc)) {
                    loc.add(0, 2, 0);
                } else {
                    victim.teleport(loc);
                    sender.sendMessage(Core.PREFIX + Translation.get("teleported_safe"));
                    return true;
                }
            }
        }

        sender.sendMessage(Core.PREFIX + Translation.get("not_teleported_safe"));
        return true;
    }
}
