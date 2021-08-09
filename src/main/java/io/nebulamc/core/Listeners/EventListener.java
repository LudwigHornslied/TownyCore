package io.nebulamc.core.Listeners;

import com.destroystokyo.paper.event.entity.PreCreatureSpawnEvent;
import io.nebulamc.core.Core;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Shulker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

import static io.nebulamc.core.Core.prefix;

public class EventListener implements Listener {
    boolean mobSpawning;
    boolean inShulker = false;
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
            if(event.getCurrentItem().getType() == Material.GOLDEN_APPLE || event.getCurrentItem().getType() == Material.ENCHANTED_GOLDEN_APPLE || event.getCurrentItem().getType() == Material.EXPERIENCE_BOTTLE || event.getCurrentItem().getType() == Material.POTION || event.getCurrentItem().getType() == Material.SPLASH_POTION || event.getCurrentItem().getType() == Material.LINGERING_POTION){
                event.setCancelled(true);
                Bukkit.getPlayer(event.getWhoClicked().getUniqueId()).sendMessage(prefix + "You can not move that item into shulkers!");
            }

        }
    }
}
