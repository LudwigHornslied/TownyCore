package io.nebulamc.core.listeners;

import com.google.common.collect.ImmutableSet;
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
import org.bukkit.inventory.ItemStack;

import java.util.Set;

import static io.nebulamc.core.Core.PREFIX;

public class ShulkerListener implements Listener {

    private static Set<Material> bannedFromShulker = ImmutableSet.of(
            Material.GOLDEN_APPLE,
            Material.ENCHANTED_GOLDEN_APPLE,
            Material.EXPERIENCE_BOTTLE,
            Material.POTION,
            Material.SPLASH_POTION,
            Material.LINGERING_POTION,
            Material.GOLD_NUGGET,
            Material.GOLD_INGOT,
            Material.GOLD_ORE,
            Material.DEEPSLATE_GOLD_ORE,
            Material.GOLD_BLOCK,
            Material.RAW_GOLD,
            Material.RAW_GOLD_BLOCK);

    @EventHandler
    public void onInventoryMove(InventoryClickEvent event) {
        if (event.getInventory().getType() == InventoryType.SHULKER_BOX) {
            if (event.getClick().isKeyboardClick()) {
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getUniqueId()).sendMessage(PREFIX + "Hotkey movement is disabled for shulkers.");
            }

            ItemStack currentItem = event.getCurrentItem();
            if (currentItem == null)
                return;

            if (bannedFromShulker.contains(currentItem.getType())) {
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getUniqueId()).sendMessage(PREFIX + "You can not move that item into shulkers!");
            }
        }
    }


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
