package io.nebulamc.core.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import static io.nebulamc.core.Core.PREFIX;

public class ShulkerListener implements Listener {

    @EventHandler
    public void onHopperPlace(BlockPlaceEvent event) {
        if (event.getBlockPlaced().getType() == Material.HOPPER || event.getBlockPlaced().getType() == Material.HOPPER_MINECART) {
            if (event.getBlockAgainst().getType().toString().endsWith("SHULKER_BOX")) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(PREFIX + "You can not place hoppers going into shulkers!");
            }
        } else if (event.getBlockPlaced().getType().toString().endsWith("SHULKER_BOX")) {
            Location loc = event.getBlockPlaced().getLocation();
            Block b1 = loc.getBlock().getRelative(BlockFace.NORTH);
            Block b2 = loc.getBlock().getRelative(BlockFace.EAST);
            Block b3 = loc.getBlock().getRelative(BlockFace.SOUTH);
            Block b4 = loc.getBlock().getRelative(BlockFace.WEST);
            Block b5 = loc.getBlock().getRelative(BlockFace.UP);
            Block b6 = loc.getBlock().getRelative(BlockFace.DOWN);

            if (b1.getType() == Material.HOPPER || b2.getType() == Material.HOPPER || b3.getType() == Material.HOPPER || b4.getType() == Material.HOPPER || b5.getType() == Material.HOPPER || b6.getType() == Material.HOPPER || b1.getType() == Material.HOPPER_MINECART || b2.getType() == Material.HOPPER_MINECART || b3.getType() == Material.HOPPER_MINECART || b4.getType() == Material.HOPPER_MINECART || b5.getType() == Material.HOPPER_MINECART || b6.getType() == Material.HOPPER_MINECART) {
                event.getPlayer().sendMessage(PREFIX + "You can not place shulkers by hoppers!");
                event.setCancelled(true);
            }
        }
    }
}
