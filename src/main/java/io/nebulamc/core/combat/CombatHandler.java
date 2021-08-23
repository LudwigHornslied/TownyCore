package io.nebulamc.core.combat;

import io.nebulamc.core.Core;
import io.nebulamc.core.combat.listener.CombatListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent.Reason;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CombatHandler {

    private static final long TAG_TIME = 45 * 1000;

    private Map<UUID, Long> combatTags = new ConcurrentHashMap<>();

    public CombatHandler() {
        Bukkit.getPluginManager().registerEvents(new CombatListener(), Core.getInstance());

        new BukkitRunnable() {

            @Override
            public void run() {
                Iterator<Entry<UUID, Long>> iterator = combatTags.entrySet().iterator();

                while (iterator.hasNext()) {
                    Entry<UUID, Long> entry = iterator.next();

                    if(entry.getValue() > System.currentTimeMillis())
                        continue;

                    iterator.remove();

                    UUID uuid = entry.getKey();
                    Player player = Bukkit.getPlayer(uuid);
                    if(player == null || !player.isOnline())
                        continue;

                    player.sendMessage(ChatColor.GREEN + "You are no longer in combat.");
                    getBossBar(player).setVisible(false);
                }
            }
        }.runTaskTimerAsynchronously(Core.getInstance(), 10L, 10L);

        new BukkitRunnable() {

            @Override
            public void run() {
                Iterator<Entry<UUID, Long>> iterator = combatTags.entrySet().iterator();

                while (iterator.hasNext()) {
                    Entry<UUID, Long> entry = iterator.next();

                    if (entry.getValue() <= System.currentTimeMillis())
                        continue;

                    UUID uuid = entry.getKey();
                    Player player = Bukkit.getPlayer(uuid);
                    if(player == null || !player.isOnline())
                        continue;

                    BossBar bossBar = getBossBar(player);
                    if(!bossBar.isVisible())
                        bossBar.setVisible(true);

                    long remaining = getRemaining(player);
                    bossBar.setTitle(ChatColor.RED + ChatColor.BOLD.toString() + "Combat Tag" + ChatColor.GRAY + ": " + ChatColor.RED + (remaining / 1000) + "s");
                    bossBar.setProgress((double) remaining / TAG_TIME);
                }
            }
        }.runTaskTimer(Core.getInstance(), 10L, 10L);
    }

    public void apply(Player player) {
        if(!isTagged(player)) {
            player.closeInventory(Reason.PLUGIN);
            player.sendMessage(ChatColor.RED + "You have been combat tagged for " + (TAG_TIME/1000) + " seconds! Do not log out or you will get killed instantly.");
        }

        combatTags.put(player.getUniqueId(), System.currentTimeMillis() + TAG_TIME);
    }

    public void remove(Player player) {
        combatTags.remove(player.getUniqueId());
    }

    public boolean isTagged(Player player) {
        return combatTags.containsKey(player.getUniqueId()) && combatTags.get(player.getUniqueId()) > System.currentTimeMillis();
    }

    public long getRemaining(Player player) {
        if(!combatTags.containsKey(player.getUniqueId()))
            return -1;

        return combatTags.get(player.getUniqueId()) - System.currentTimeMillis();
    }

    public BossBar getBossBar(Player player) {
        BossBar out = Bukkit.getBossBar(new NamespacedKey(Core.getInstance(), "CombatLog_" + player.getName()));
        if(out == null) {
            out = Bukkit.createBossBar(new NamespacedKey(Core.getInstance(), "CombatLog_" + player.getName()), ChatColor.RED + ChatColor.BOLD.toString() + "Combat Tag" + ChatColor.GRAY + ": ", BarColor.RED, BarStyle.SOLID);
            out.addPlayer(player);
        }
        return out;
    }

    public void removeBossBar(Player player) {
        Bukkit.removeBossBar(new NamespacedKey(Core.getInstance(), "CombatLog_" + player.getName()));
    }

    public void onDisable() {
        for(Player player : Bukkit.getOnlinePlayers())
            removeBossBar(player);
    }

}
