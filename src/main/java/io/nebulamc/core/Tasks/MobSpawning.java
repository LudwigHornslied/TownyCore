package io.nebulamc.core.Tasks;

import io.nebulamc.core.Core;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

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
        for(World world : plugin.getServer().getWorlds()) {
            world.setGameRule(GameRule.DO_MOB_SPAWNING, this.spawning);
        }
    }
}
