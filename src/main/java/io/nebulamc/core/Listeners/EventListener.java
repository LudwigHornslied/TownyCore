package io.nebulamc.core.Listeners;

import com.destroystokyo.paper.event.entity.PreCreatureSpawnEvent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerJoinEvent;

import static io.nebulamc.core.Core.prefix;

public class EventListener implements Listener {
    boolean mobSpawning;
    @EventHandler
    public void preMobSpawn(PreCreatureSpawnEvent event) {
        EntityType ent = event.getType();

        if(event.getReason() == CreatureSpawnEvent.SpawnReason.COMMAND) {
            return;
        }

        if (ent == EntityType.RABBIT ||
                ent == EntityType.POLAR_BEAR ||
                ent == EntityType.DONKEY ||
                ent == EntityType.MULE ||
                ent == EntityType.BAT ||
                ent == EntityType.SQUID ||
                ent == EntityType.COD ||
                ent == EntityType.ENDERMITE ||
                ent == EntityType.TROPICAL_FISH ||
                ent == EntityType.LLAMA ||
                ent == EntityType.TRADER_LLAMA ||
                ent == EntityType.VEX ||
                ent == EntityType.GLOW_SQUID ||
                ent == EntityType.AXOLOTL ||
                ent == EntityType.SKELETON_HORSE ||
                ent == EntityType.DOLPHIN
                ) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void playerLogin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPlayedBefore()) {
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.5f, 1.0f);

            TextComponent guide = new TextComponent("§3Get started by using our §e[§bGuide§e]");
            guide.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aOpens link to the Guide on the web browser.").create()));
            guide.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://nebulamc.io/earth/#guide"));

            TextComponent map = new TextComponent("§3See where you are in the world using the §e[§bMap§e]");
            map.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aOpens link to the DynMap on your web browser.").create()));
            map.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://nebulamc.io/earth/map/"));

            TextComponent rules = new TextComponent("§3Make sure to read the §e[§bRules§e]");
            rules.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aOpens link to the rules on your web browser.").create()));
            rules.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://nebulamc.io/earth/rules"));

            player.sendMessage("§e==========[ WELCOME ]==========");
            player.sendMessage(guide);
            player.sendMessage(map);
            player.sendMessage(rules);
            player.sendMessage("§e=============================");

            event.setJoinMessage("");

        }

    }


    @EventHandler
    public void onInventoryMove(InventoryClickEvent event) {
        if (event.getInventory().getType() == InventoryType.SHULKER_BOX) {
            if(event.getCurrentItem().getType() == Material.GOLDEN_APPLE || event.getCurrentItem().getType() == Material.ENCHANTED_GOLDEN_APPLE || event.getCurrentItem().getType() == Material.EXPERIENCE_BOTTLE || event.getCurrentItem().getType() == Material.POTION || event.getCurrentItem().getType() == Material.SPLASH_POTION || event.getCurrentItem().getType() == Material.LINGERING_POTION || event.getCurrentItem().getType() == Material.GOLD_NUGGET || event.getCurrentItem().getType() == Material.GOLD_INGOT || event.getCurrentItem().getType() == Material.GOLD_ORE || event.getCurrentItem().getType() == Material.DEEPSLATE_GOLD_ORE || event.getCurrentItem().getType() == Material.GOLD_BLOCK || event.getCurrentItem().getType() == Material.RAW_GOLD || event.getCurrentItem().getType() == Material.RAW_GOLD_BLOCK){
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getUniqueId()).sendMessage(prefix + "You can not move that item into shulkers!");
            }

        }
    }

    @EventHandler
    public void onHopperPlace(BlockPlaceEvent event) {
        if(event.getBlockPlaced().getType() == Material.HOPPER || event.getBlockPlaced().getType() == Material.HOPPER_MINECART){
            if(event.getBlockAgainst().getType() == Material.SHULKER_BOX){
                event.setCancelled(true);
                event.getPlayer().sendMessage(prefix + "You can not place hoppers going into shulkers!");
            }
        }
    }
}
