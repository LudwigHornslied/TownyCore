package io.nebulamc.core.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CoreCommand implements CommandExecutor {

    // Doesn't need player check
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;

        // Display Information Screen
        player.sendMessage("§b==========[§dCORE]§b==========");

        player.sendMessage("§b===========================");
        return true;
    }
}
