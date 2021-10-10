package io.nebulamc.core.commands;

import io.nebulamc.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Boat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class TransportationCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getLogger().info(Core.PREFIX + "You need to be a player to execute this command.");
            return true;
        }

        Player player = (Player) sender;

        Vehicle vehicle;
        if(cmd.getLabel().equalsIgnoreCase("boat")) {
            if(!player.getLocation().add(0.0, -1.0, 0.0).getBlock().getType().toString().endsWith("ICE")) {
                sender.sendMessage(Core.PREFIX + "§cYou must be on an ice block to run this command.");
                return true;
            }
            vehicle = (Boat) player.getWorld().spawnEntity(player.getLocation(), EntityType.BOAT);
        } else {
            if(!player.getLocation().getBlock().getType().toString().endsWith("RAIL")) {
                sender.sendMessage(Core.PREFIX + "§cYou must be on railroad to run this command.");
                return true;
            }
            vehicle = (Minecart) player.getWorld().spawnEntity(player.getLocation(), EntityType.MINECART);
        }
        vehicle.addPassenger(player);
        vehicle.getPersistentDataContainer().set(new NamespacedKey(Core.getInstance(), "CustomVehicle"), PersistentDataType.BYTE, (byte) 0);

        return true;
    }
}
