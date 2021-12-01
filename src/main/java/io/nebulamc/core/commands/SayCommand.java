package io.nebulamc.core.commands;

import io.nebulamc.core.Core;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class SayCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if(cmd.getLabel().equalsIgnoreCase("say")) {
        	if (args.length < 1) {
                sender.sendMessage("§cIncorrect Usage: §c/say [msg]");
                return true;
            }
        	Bukkit.broadcastMessage(Core.PREFIX + ChatColor.translateAlternateColorCodes('&', String.join(" ", args)));
        }
        return true;
    }
}
