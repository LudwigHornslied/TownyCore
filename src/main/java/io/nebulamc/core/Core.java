package io.nebulamc.core;

import io.nebulamc.core.Commands.CoreCmd;
import io.nebulamc.core.Commands.MapColor;
import io.nebulamc.core.Commands.RTP;
import io.nebulamc.core.Listeners.EventListener;
import io.nebulamc.core.Tasks.MobSpawning;
import io.nebulamc.core.Util.Translation;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public final class Core extends JavaPlugin {

    public static Core instance;
    public static Logger log = Bukkit.getLogger();
    public static String prefix = "[NEBULA]: ";
    public static String discord = "https://discord.gg/nebulamc";

    @Override
    public void onEnable() {
        instance = this;
        log.info("§e======= §5Nebula TownyCore §e=======");
        //Register EventListeners
        getServer().getPluginManager().registerEvents(new EventListener(), this);

        Translation.loadStrings();

        getServer().getScheduler().scheduleSyncRepeatingTask(this, new MobSpawning(this), 0L, 20L);

        //Register Commands
        log.info("§5= §bRegistering Commands");
        Objects.requireNonNull(getCommand("rtp")).setExecutor(new RTP());
        Objects.requireNonNull(getCommand("mapcolor")).setExecutor(new MapColor());
        Objects.requireNonNull(getCommand("core")).setExecutor(new CoreCmd());

    }

    public static Core getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
