package io.nebulamc.core.commands;

import io.nebulamc.core.Core;
import io.nebulamc.core.PermissionNodes;
import io.nebulamc.core.combat.CombatHandler;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CombatTag implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player && sender.hasPermission(PermissionNodes.COMBATTAG)) {
            if (args.length == 0) {
                sender.sendMessage("§7[§bCombatTag§7]: §eIncorrect Usage: §ePlease specific a usage");
                sender.sendMessage("§7[§bCombatTag§7]: §eSee /combattag help");
            } else {
                switch (args[0]) {
                    case "help":
                        sender.sendMessage("§7[§bCombatTag§7]: §eHelp Command");
                        sender.sendMessage("§7[§bCombatTag§7]: §e/combattag tag <username>");
                        sender.sendMessage("§7[§bCombatTag§7]: §e/combattag untag <username>");
                        break;
                    case "tag":
                        if(args.length == 2){
                            if(CombatHandler.isTagged(Objects.requireNonNull(Bukkit.getPlayer(args[1])))){
                                sender.sendMessage("§7[§bCombatTag§7]: §cPlayer is already tagged.");
                            } else {
                                CombatHandler.apply(Bukkit.getPlayer(args[1]));
                                sender.sendMessage("§7[§bCombatTag§7]: §e" + Objects.requireNonNull(Bukkit.getPlayer(args[1])).getName() + " has been tagged.");
                            }
                        } else {
                            sender.sendMessage("§7[§bCombatTag§7]: §eIncorrect Usage: §e/combattag tag <username>");
                        }
                        break;
                    case "untag":
                        if(args.length == 2){
                            if(CombatHandler.isTagged(Objects.requireNonNull(Bukkit.getPlayer(args[1])))){
                                CombatHandler.remove(Objects.requireNonNull(Bukkit.getPlayer(args[1])));
                                Bukkit.removeBossBar(new NamespacedKey(Core.getInstance(), "CombatLog_" + Objects.requireNonNull(Bukkit.getPlayer(args[1])).getName()));
                                sender.sendMessage("§7[§bCombatTag§7]: §e" + Objects.requireNonNull(Bukkit.getPlayer(args[1])).getName() + " has been untagged.");
                            } else {
                                sender.sendMessage("§7[§bCombatTag§7]: §cPlayer not found or they are not tagged");
                            }
                        } else {
                            sender.sendMessage("§7[§bCombatTag§7]: §eIncorrect Usage: §e/combattag tag <username>");
                        }
                        break;

                }
            }

        } else {
            Bukkit.getLogger().info(Core.PREFIX + "You need to be a player to execute this command.");
        }
        return true;
    }
}
