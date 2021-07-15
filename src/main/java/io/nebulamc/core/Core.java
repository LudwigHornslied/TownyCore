package io.nebulamc.core;

import io.nebulamc.core.Commands.RTP;
import io.nebulamc.core.Listeners.EventListener;
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
        log.info("§e======= §5Nebula Core §e=======");
        //Register EventListeners
        getServer().getPluginManager().registerEvents(new EventListener(), this);

        //Register Commands
        log.info("§5= §bRegistering Commands");
        Objects.requireNonNull(getCommand("rtp")).setExecutor(new RTP());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
