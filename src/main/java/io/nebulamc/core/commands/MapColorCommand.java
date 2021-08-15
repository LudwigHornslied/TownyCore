package io.nebulamc.core.commands;

import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import io.nebulamc.core.Core;
import io.nebulamc.core.PermissionNodes;
import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.StringJoiner;

public class MapColorCommand implements CommandExecutor {

    // Looks far better
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getLogger().info("[TownyCore]: You need to be a player to execute this command.");
            return true;
        }

        if (!sender.hasPermission(PermissionNodes.MAPCOLOR)) {
            sender.sendMessage(Core.PREFIX + "§eYou must be §dVIP+ §eto use that command.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(Core.PREFIX + "§eIncorrect Usage: §ePlease specific a §ac§bo§cl§do§5r.");
            sender.sendMessage(Core.PREFIX + "§eCorrect Usage: [§c/mapcolor <color>§e]");
            return true;
        }

        Player player = (Player) sender;

        Resident resident = TownyUniverse.getInstance().getResident(player.getUniqueId());
        assert resident != null;

        if (!resident.hasTown()) {
            player.sendMessage(Core.PREFIX + "§cYou are not in a town.");
            return true;
        }

        if (!resident.hasNation()) {
            player.sendMessage(Core.PREFIX + "§cYou are not in a nation.");
            return true;
        }
        Nation nation;
        try {
            nation = resident.getTown().getNation();
        } catch (NotRegisteredException e) {
            e.printStackTrace();
            return true;
        }

        if (!resident.equals(nation.getKing())) {
            player.sendMessage(Core.PREFIX + "§cYou must be the §eKing §cof the nation to change the map color.");
            return true;
        }

        MapColor color;
        try {
            color = MapColor.valueOf(args[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            player.sendMessage("§b==========[COLORS]==========");
            for (int i = 0; i < Math.ceil((double) MapColor.values().length / 7); i++) {
                StringJoiner sj = new StringJoiner(ChatColor.GRAY + ", ");
                for (int j = 0; j < 7 || i * 7 + j < MapColor.values().length; j++) {
                    MapColor value = MapColor.values()[i * 7 + j];
                    sj.add(value.getChatColor() + StringUtils.capitalize(value.toString()));
                }
                player.sendMessage(sj.toString());
            }
            player.sendMessage("§b===========================");
            return true;
        }

        nation.setMapColorHexCode(color.getHexCode());
        player.sendMessage(Core.PREFIX + "Nation color changed to " + color.getChatColor() + StringUtils.capitalize(color.toString()));

        return true;
    }

    public enum MapColor {
        AQUA("00ffff"), CYAN("00ffff"), AZURE("f0ffff"), BEIGE("f5f5dc"), BLACK("000000"), BLUE("0000ff"), BROWN("a52a2a"),
        DARKBLUE("00008b"), DARKCYAN("008b8b"), DARKGREY("006400"), DARKKHAKI("bdb76b"), DARKMAGENTA("8b008b"), DARKOLIVEGREEN("556b2f"),
        DARKORANGE("ff8c00"), DARKORCHID("9932cc"), DARKRED("8b0000"), DARKSALMON("e9967a"), DARKVIOLET("9400d3"), FUCHSIA("ff00ff"),
        GOLD("ffd700"), GREEN("008000"), INDIGO("4b0082"), KHAKI("f0e68c"), LIGHTBLUE("add8e6"), LIGHTCYAN("e0ffff"), LIGHTGREEN("90ee90"),
        LIGHTGREY("d3d3d3"), LIGHTPINK("ffb6c1"), LIGHTYELLOW("ffffe0"), LIME("00ff00"), MAGENTA("ff00ff"), MAROON("800000"), NAVY("000080"),
        OLIVE("808000"), ORANGE("ffa500"), PINK("ffc0cb"), PURPLE("800080"), VIOLET("800080"), RED("ff0000"), SILVER("c0c0c0"),
        WHITE("ffffff"), YELLOW("ffff00");
        private String hexCode;

        MapColor(String hexCode) {
            this.hexCode = hexCode;
        }

        public String getHexCode() {
            return hexCode;
        }

        public ChatColor getChatColor() {
            return ChatColor.of("#" + hexCode);
        }
    }
}
