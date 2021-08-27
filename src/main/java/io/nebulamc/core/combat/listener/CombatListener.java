package io.nebulamc.core.combat.listener;

import com.google.common.collect.ImmutableSet;
import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.event.damage.TownyPlayerDamagePlayerEvent;
import com.palmergames.bukkit.towny.object.TownBlock;
import com.palmergames.bukkit.towny.object.TownBlockType;
import com.palmergames.bukkit.towny.object.TownyWorld;
import com.palmergames.bukkit.towny.object.Translation;
import com.palmergames.bukkit.towny.utils.CombatUtil;
import io.nebulamc.core.Core;
import io.nebulamc.core.PermissionNodes;
import io.nebulamc.core.combat.CombatHandler;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.projectiles.ProjectileSource;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class CombatListener implements Listener {

    private Set<UUID> deathsForLoggingOut = new HashSet<>();

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player))
            return;

        Player damagee = (Player) event.getEntity();

        CombatHandler combatHandler = Core.getInstance().getCombatHandler();

        if (event.getDamager() instanceof Player) {
            combatHandler.apply(damagee);
            combatHandler.apply((Player) event.getDamager());
        } else if (event.getDamager() instanceof Projectile) {
            ProjectileSource shooter = ((Projectile) event.getDamager()).getShooter();
            if(shooter == null || !(shooter instanceof Player))
                return;

            combatHandler.apply(damagee);
            combatHandler.apply((Player) shooter);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onCobweb(BlockPlaceEvent event) {
        if (event.getBlockPlaced().getType() != Material.COBWEB)
            return;

        if (!Core.getInstance().getCombatHandler().isTagged(event.getPlayer()))
            return;

        event.setCancelled(true);

        event.getPlayer().sendMessage(ChatColor.RED + "You can't place cobwebs while combat tagged.");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        CombatHandler combatHandler = Core.getInstance().getCombatHandler();

        combatHandler.removeBossBar(player);

        if(!combatHandler.isTagged(player))
            return;

        combatHandler.remove(player);
        deathsForLoggingOut.add(player.getUniqueId());
        player.setHealth(0.0);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        if(deathsForLoggingOut.contains(player.getUniqueId())) {
            deathsForLoggingOut.remove(player.getUniqueId());
            event.deathMessage(Component.text(player.getName() + " was killed for logging out in combat."));
        }

        CombatHandler combatHandler = Core.getInstance().getCombatHandler();

        if(!combatHandler.isTagged(player))
            return;

        combatHandler.remove(player);
        combatHandler.getBossBar(player).setVisible(false);
    }

    // Lowercase
    private static final Set<String> WHITELISTED_COMMANDS = ImmutableSet.of("tc", "nc", "g", "ally", "msg", "r", "reply");

    @EventHandler
    public void onPreProcessCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if(!Core.getInstance().getCombatHandler().isTagged(player))
            return;

        if(player.hasPermission(PermissionNodes.COMBATLOG_BYPASS))
            return;

        String message = event.getMessage();
        message = message.replaceFirst("/", "");

        for(String value : WHITELISTED_COMMANDS) {
            if (message.equalsIgnoreCase(value) || message.toLowerCase().startsWith(value + " "))
                return;
        }

        event.setCancelled(true);
        player.sendMessage(ChatColor.RED + "You can't use that command while being in combat.");
    }

    // Prevent claim hopping
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPvP(TownyPlayerDamagePlayerEvent event) {
        if(!event.isCancelled())
            return;

        TownyWorld world = TownyAPI.getInstance().getTownyWorld(event.getVictimPlayer().getWorld().getName());
        Player attacker = event.getAttackingPlayer();
        Player victim = event.getVictimPlayer();

        if (!world.isFriendlyFireEnabled() && CombatUtil.isAlly(attacker.getName(), victim.getName()))
            return;

        if(!Core.getInstance().getCombatHandler().isTagged(victim))
            return;

        event.setCancelled(false);
    }

    /* Just edit purpur configuration and put enderpearl cooldown on 320 ticks (16 seconds)
    @EventHandler
    public void onPearl(ProjectileLaunchEvent event) {
        if(!(event.getEntity() instanceof EnderPearl))
            return;

        EnderPearl pearl = (EnderPearl) event.getEntity();

        if(!(pearl.getShooter() instanceof Player))
            return;

        Player player = (Player) pearl.getShooter();
        // Pearl cooldown (16 seconds)
        player.setCooldown(Material.ENDER_PEARL, 16 * 20);

    @EventHandler
    public void onPearl(PlayerInteractEvent event) {
        if(event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;

        if(event.getItem().getType() != Material.ENDER_PEARL)
            return;

        // Pearl cooldown (16 seconds)
        event.getPlayer().setCooldown(Material.ENDER_PEARL, 16 * 20);
    } */

}
