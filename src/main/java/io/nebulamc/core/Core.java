package io.nebulamc.core;

import io.nebulamc.core.commands.CoreCommand;
import io.nebulamc.core.commands.MapColorCommand;
import io.nebulamc.core.commands.RTPCommand;
import io.nebulamc.core.listeners.EventListener;
import io.nebulamc.core.listeners.ShulkerListener;
import io.nebulamc.core.tasks.MobSpawningTask;
import io.nebulamc.core.util.Translation;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public final class Core extends JavaPlugin {

    private static Core instance;

    public static Core getInstance() {
        return instance;
    }

    private static Logger log = Bukkit.getLogger();

    public static final String PREFIX = ChatColor.of("#BAE6FF").toString() + ChatColor.BOLD + "N" + ChatColor.of("#BADDFF").toString() + ChatColor.BOLD + "e" + ChatColor.of("#BAD4FF").toString() + ChatColor.BOLD + "b" + ChatColor.of("#BBCBFF").toString() + ChatColor.BOLD + "u" + ChatColor.of("#BBC2FF").toString() + ChatColor.BOLD + "l" + ChatColor.of("#BCBAFF").toString() + ChatColor.BOLD + "a" + ChatColor.of("#ecabff").toString() + ChatColor.BOLD + " Towny " + ChatColor.WHITE + "• " + ChatColor.RESET + "§6";
    public static final String DISCORD = "https://discord.gg/nebulamc";

    @Override
    public void onEnable() {
        instance = this;
        log.info("§e======= §5Nebula TownyCore §e=======");

        setupListeners();

        Translation.loadStrings();

        getServer().getScheduler().scheduleSyncRepeatingTask(this, new MobSpawningTask(this), 0L, 20L);

        setupCommands();

        Bukkit.broadcastMessage(PREFIX + "Core Plugin has been loaded.");
    }

    private void setupListeners() {
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        getServer().getPluginManager().registerEvents(new ShulkerListener(), this);
    }

    private void setupCommands() {
        log.info("§5= §bRegistering Commands");
        Objects.requireNonNull(getCommand("rtp")).setExecutor(new RTPCommand());
        Objects.requireNonNull(getCommand("mapcolor")).setExecutor(new MapColorCommand());
        Objects.requireNonNull(getCommand("core")).setExecutor(new CoreCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.broadcastMessage(PREFIX + "Core Plugin has been disabled.");
    }
}
