package io.nebulamc.core.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CoreCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;

            if (args.length == 0){
                //Display Information Screen
                player.sendMessage("§b==========[§dCORE]§b==========");

                player.sendMessage("§b===========================");
                return true;
            }
        }

        return false;
    }
}
