package io.nebulamc.core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

import static io.nebulamc.core.Core.PREFIX;

public class ShulkerListener implements Listener {

    @EventHandler
    public void onInventoryMove(InventoryClickEvent event) {
        if (event.getInventory().getType() == InventoryType.SHULKER_BOX) {
            if(event.getClick().isKeyboardClick()){
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getUniqueId()).sendMessage(PREFIX + "Hotkey movement is disabled for shulkers.");
            }
            if (event.getCurrentItem().getType() == Material.GOLDEN_APPLE || event.getCurrentItem().getType() == Material.ENCHANTED_GOLDEN_APPLE || event.getCurrentItem().getType() == Material.EXPERIENCE_BOTTLE || event.getCurrentItem().getType() == Material.POTION || event.getCurrentItem().getType() == Material.SPLASH_POTION || event.getCurrentItem().getType() == Material.LINGERING_POTION || event.getCurrentItem().getType() == Material.GOLD_NUGGET || event.getCurrentItem().getType() == Material.GOLD_INGOT || event.getCurrentItem().getType() == Material.GOLD_ORE || event.getCurrentItem().getType() == Material.DEEPSLATE_GOLD_ORE || event.getCurrentItem().getType() == Material.GOLD_BLOCK || event.getCurrentItem().getType() == Material.RAW_GOLD || event.getCurrentItem().getType() == Material.RAW_GOLD_BLOCK) {
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getUniqueId()).sendMessage(PREFIX + "You can not move that item into shulkers!");
            }
        }
    }



    @EventHandler
    public void onHopperPlace(BlockPlaceEvent event) {
        if(event.getBlockPlaced().getType() == Material.HOPPER || event.getBlockPlaced().getType() == Material.HOPPER_MINECART){
            if(event.getBlockAgainst().getType() == Material.SHULKER_BOX || event.getBlockAgainst().getType() == Material.BLACK_SHULKER_BOX || event.getBlockAgainst().getType() == Material.WHITE_SHULKER_BOX || event.getBlockAgainst().getType() == Material.BLUE_SHULKER_BOX || event.getBlockAgainst().getType() == Material.CYAN_SHULKER_BOX || event.getBlockAgainst().getType() == Material.BROWN_SHULKER_BOX || event.getBlockAgainst().getType() == Material.GRAY_SHULKER_BOX || event.getBlockAgainst().getType() == Material.GREEN_SHULKER_BOX || event.getBlockAgainst().getType() == Material.LIGHT_BLUE_SHULKER_BOX || event.getBlockAgainst().getType() == Material.LIGHT_GRAY_SHULKER_BOX || event.getBlockAgainst().getType() == Material.LIME_SHULKER_BOX || event.getBlockAgainst().getType() == Material.MAGENTA_SHULKER_BOX || event.getBlockAgainst().getType() == Material.ORANGE_SHULKER_BOX || event.getBlockAgainst().getType() == Material.PINK_SHULKER_BOX || event.getBlockAgainst().getType() == Material.PURPLE_SHULKER_BOX || event.getBlockAgainst().getType() == Material.RED_SHULKER_BOX || event.getBlockAgainst().getType() == Material.YELLOW_SHULKER_BOX){
                event.setCancelled(true);
                event.getPlayer().sendMessage(PREFIX + "You can not place hoppers going into shulkers!");
            }
        }
        else if(event.getBlockPlaced().getType() == Material.SHULKER_BOX || event.getBlockAgainst().getType() == Material.BLACK_SHULKER_BOX || event.getBlockAgainst().getType() == Material.WHITE_SHULKER_BOX || event.getBlockAgainst().getType() == Material.BLUE_SHULKER_BOX || event.getBlockAgainst().getType() == Material.CYAN_SHULKER_BOX || event.getBlockAgainst().getType() == Material.BROWN_SHULKER_BOX || event.getBlockAgainst().getType() == Material.GRAY_SHULKER_BOX || event.getBlockAgainst().getType() == Material.GREEN_SHULKER_BOX || event.getBlockAgainst().getType() == Material.LIGHT_BLUE_SHULKER_BOX || event.getBlockAgainst().getType() == Material.LIGHT_GRAY_SHULKER_BOX || event.getBlockAgainst().getType() == Material.LIME_SHULKER_BOX || event.getBlockAgainst().getType() == Material.MAGENTA_SHULKER_BOX || event.getBlockAgainst().getType() == Material.ORANGE_SHULKER_BOX || event.getBlockAgainst().getType() == Material.PINK_SHULKER_BOX || event.getBlockAgainst().getType() == Material.PURPLE_SHULKER_BOX || event.getBlockAgainst().getType() == Material.RED_SHULKER_BOX || event.getBlockAgainst().getType() == Material.YELLOW_SHULKER_BOX){
            Location loc = event.getBlockPlaced().getLocation();
            Block b1 = loc.getBlock().getRelative(BlockFace.NORTH);
            Block b2 = loc.getBlock().getRelative(BlockFace.EAST);
            Block b3 = loc.getBlock().getRelative(BlockFace.SOUTH);
            Block b4 = loc.getBlock().getRelative(BlockFace.WEST);
            Block b5 = loc.getBlock().getRelative(BlockFace.UP);
            Block b6 = loc.getBlock().getRelative(BlockFace.DOWN);

            if(b1.getType() == Material.HOPPER || b2.getType() == Material.HOPPER || b3.getType() == Material.HOPPER || b4.getType() == Material.HOPPER || b5.getType() == Material.HOPPER || b6.getType() == Material.HOPPER || b1.getType() == Material.HOPPER_MINECART || b2.getType() == Material.HOPPER_MINECART || b3.getType() == Material.HOPPER_MINECART || b4.getType() == Material.HOPPER_MINECART || b5.getType() == Material.HOPPER_MINECART || b6.getType() == Material.HOPPER_MINECART) {
                event.getPlayer().sendMessage(PREFIX + "You can not place shulkers by hoppers!");
                event.setCancelled(true);
            }
        }
    }
}
