package io.nebulamc.core.Util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class LocationCheck {

    public static boolean isSafeLocation(Location location) {
        // Ensure the player's feet aren't in a block
        Block feet = location.getBlock();
        if (!feet.getType().isAir()) {
            return false; // not transparent (will suffocate)
        }

        // Ensure the player's head isn't in a block
        Block head = feet.getRelative(BlockFace.UP);
        if (!head.getType().isAir()) {
            return false; // not transparent (will suffocate)
        }

        // Ensure the block under the player is solid
        Block ground = feet.getRelative(BlockFace.DOWN);
        if (!ground.getType().isSolid()) {
            return false; // not solid
        }
        return true;
    }
}
