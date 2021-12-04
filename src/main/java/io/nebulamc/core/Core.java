package io.nebulamc.core;

import io.nebulamc.core.combat.CombatHandler;
import io.nebulamc.core.combat.bossbar.BossBarTask;
import io.nebulamc.core.combat.listener.CombatListener;
import io.nebulamc.core.commands.CombatTagCommand;
import io.nebulamc.core.commands.CoreCommand;
import io.nebulamc.core.commands.MapColorCommand;
import io.nebulamc.core.commands.RTPCommand;
import io.nebulamc.core.commands.SayCommand;
import io.nebulamc.core.commands.TransportationCommand;
import io.nebulamc.core.listeners.ArenaListener;
import io.nebulamc.core.listeners.EXPBottleListener;
import io.nebulamc.core.listeners.EventListener;
import io.nebulamc.core.listeners.ShulkerListener;
import io.nebulamc.core.listeners.TransportationListener;
import io.nebulamc.core.tasks.MobSpawningTask;
import io.nebulamc.core.util.Translation;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Vehicle;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public final class Core extends JavaPlugin {

    private static Core instance;

    public static Core getInstance() {
        return instance;
    }

    private static Logger log = Bukkit.getLogger();

    public static final String PREFIX = ChatColor.of("#3fb78d").toString() + ChatColor.BOLD + "E" + ChatColor.of("#57c28a").toString() + ChatColor.BOLD + "a" + ChatColor.of("#6fcd85").toString() + ChatColor.BOLD + "r" + ChatColor.of("#88d880").toString() + ChatColor.BOLD + "t" + ChatColor.of("#a3e27a").toString() + ChatColor.BOLD + "h" + ChatColor.of("#beeb75").toString() + ChatColor.BOLD + "P" + ChatColor.of("#dcf371").toString() + ChatColor.BOLD + "o" + ChatColor.of("#fafa6e").toString() + ChatColor.BOLD + "l" + ChatColor.WHITE + " • " + ChatColor.RESET + "§6";
    public static final String DISCORD = "https://discord.gg/earthpol";

    public CombatHandler combatHandler;

    public CombatHandler getCombatHandler() {
        return combatHandler;
    }

    @Override
    public void onEnable() {
        instance = this;
        log.info("§e======= §aEarthPol TownyCore §e=======");

        Translation.loadStrings();

        setupListeners();
        setupCommands();
        runTasks();

        Bukkit.broadcastMessage(PREFIX + "Core Plugin has been loaded.");
    }

    private void setupListeners() {
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        getServer().getPluginManager().registerEvents(new ShulkerListener(), this);
        getServer().getPluginManager().registerEvents(new CombatListener(), this);
        getServer().getPluginManager().registerEvents(new EXPBottleListener(), this);
        getServer().getPluginManager().registerEvents(new TransportationListener(), this);
        getServer().getPluginManager().registerEvents(new ArenaListener(), this);
    }

    private void setupCommands() {
        log.info("§5= §bRegistering Commands");
        Objects.requireNonNull(getCommand("rtp")).setExecutor(new RTPCommand());
        Objects.requireNonNull(getCommand("mapcolor")).setExecutor(new MapColorCommand());
        Objects.requireNonNull(getCommand("core")).setExecutor(new CoreCommand());
        Objects.requireNonNull(getCommand("combattag")).setExecutor(new CombatTagCommand());
        Objects.requireNonNull(getCommand("say")).setExecutor(new CoreCommand());

        TransportationCommand transportationCommand = new TransportationCommand();

        Objects.requireNonNull(getCommand("boat")).setExecutor(transportationCommand);
        Objects.requireNonNull(getCommand("cart")).setExecutor(transportationCommand);
    }

    private void runTasks() {
        new MobSpawningTask().runTaskTimer(this, 20, 20);
        new BossBarTask().runTaskTimer(this, 10, 10);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        despawnTransportations();

        Bukkit.broadcastMessage(PREFIX + "Core Plugin has been disabled.");
    }

    private void despawnTransportations() {
        for(World world : Bukkit.getWorlds()) {
            for(Vehicle vehicle : world.getEntitiesByClass(Vehicle.class)) {
                if(vehicle.getPersistentDataContainer().has(new NamespacedKey(Core.getInstance(), "CustomVehicle"), PersistentDataType.BYTE)) {
                    vehicle.remove();
                }
            }
        }
    }
}
