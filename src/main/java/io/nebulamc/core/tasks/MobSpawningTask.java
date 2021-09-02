package io.nebulamc.core.tasks;

import io.nebulamc.core.Core;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

public class MobSpawningTask extends BukkitRunnable {

    private boolean spawning = true;

    @Override
    public void run() {
        double tps = Bukkit.getTPS()[1];

        if (tps < 18 && this.spawning) {
            this.spawning = false;
            Bukkit.broadcastMessage(Core.PREFIX + "Mob Spawning has been " + ChatColor.RED + "disabled" + ChatColor.WHITE + "!");
            update();
        } else if (tps > 18 && !this.spawning) {
            this.spawning = true;
            Bukkit.broadcastMessage(Core.PREFIX + "Mob Spawning has been " + ChatColor.GREEN + "enabled" + ChatColor.WHITE + "!");
            update();
        }
    }

    private void update() {
        if (Bukkit.getOnlinePlayers().size() <= 100) {
            for (World world : Bukkit.getWorlds()) {
                world.setGameRule(GameRule.DO_MOB_SPAWNING, this.spawning);
            }
        } else {
            for (World world : Bukkit.getWorlds()) {
                world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
            }
        }
    }
}
