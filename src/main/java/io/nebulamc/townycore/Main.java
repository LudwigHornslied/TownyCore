package io.nebulamc.townycore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Main extends JavaPlugin {
    public static Main instance;
    public static Logger log = Bukkit.getLogger();
    public static String prefix = "§5[§dNEBULA§5]";
    public static String logDivider = "§5=========================";
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        //Start of Log Process
        log.info("§5======== §dNEBULA §5========");
        log.info("§5= §dNebula Towny Core Plugin");
        log.info("§5= §dVersion: §5" + this.getDescription().getVersion());
        log.info("§5= §dAuthors: §5" + this.getDescription().getAuthors());
        log.info("§5= §dWebsite: §5" + this.getDescription().getWebsite());
        log.info("§5= §dSupport: §5https://nebulamc.io/discord");
        log.info(logDivider);
        //Register Event Listener, ChatHandlers, DeathMessageHandlers, Commands.
        //Build try & catches as not to crash entire plugin out if one section fails.
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        log.info("§5======== §dNEBULA §5========");
        log.info("§5= §dPlugin disabled");
        log.info(logDivider);
    }
}
