package io.nebulamc.core.Tasks;

import io.nebulamc.core.Core;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class MobSpawning implements Runnable {
    private final JavaPlugin plugin;
    private boolean spawning = true;
    public MobSpawning(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        double tps = Bukkit.getTPS()[0];
        int onlinePlayers = onlinePlayerCount();

        if(tps < 18 && this.spawning) {
            this.spawning = false;
            Bukkit.broadcastMessage(Core.prefix + "Mob Spawning has been " + ChatColor.RED + "Disabled");
            update();
        } else if(tps > 18 && !this.spawning) {
            this.spawning = true;
            Bukkit.broadcastMessage(Core.prefix + "Mob Spawning has been " + ChatColor.GREEN + "Enabled");
            update();
        }
    }

    private void update() {
        if(onlinePlayerCount() <= 100){
            for(World world : plugin.getServer().getWorlds()) {
                world.setGameRule(GameRule.DO_MOB_SPAWNING, this.spawning);
            }
        }
    }

    public int onlinePlayerCount() {
        return Core.instance.getServer().getOnlinePlayers().size();
    }
}
