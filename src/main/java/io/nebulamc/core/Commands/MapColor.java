package io.nebulamc.core.Commands;

import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import io.nebulamc.core.Core;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MapColor implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            if(args.length > 0){
                if(player.hasPermission("nebula.command.mapcolor")){
                    String playerName = player.getName();

                    try {
                        Resident issuer = TownyUniverse.getInstance().getResident(playerName);
                        assert issuer != null;
                        if(issuer.hasTown()) {
                            if(issuer.hasNation()) {
                                Nation nation = issuer.getTown().getNation();

                                if(nation.getKing() == issuer){
                                    switch (args[0]) {
                                        case "aqua":
                                        case "cyan":
                                            nation.setMapColorHexCode("00ffff");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#00FFFF") + "Cyan");
                                            break;
                                        case "azure":
                                            nation.setMapColorHexCode("f0ffff");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#F0FFFF") + "Azure");
                                            break;
                                        case "beige":
                                            nation.setMapColorHexCode("f5f5dc");
                                            player.sendMessage(Core.prefix + "Nation color changed to Biege" + ChatColor.of("#F5F5DC") + "Biege");
                                            break;
                                        case "black":
                                            nation.setMapColorHexCode("000000");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#000000") + "Black");
                                            break;
                                        case "blue":
                                            nation.setMapColorHexCode("0000ff");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#0000FF") + "Blue");
                                            break;
                                        case "brown":
                                            nation.setMapColorHexCode("a52a2a");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#A52A2A") + "Brown");
                                            break;
                                        case "darkblue":
                                            nation.setMapColorHexCode("00008b");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#00008B") + "Dark Blue");
                                            break;
                                        case "darkcyan":
                                            nation.setMapColorHexCode("008b8b");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#008B8B") + "Dark Cyan");
                                            break;
                                        case "darkgrey":
                                            nation.setMapColorHexCode("a9a9a9");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#A9A9A9") + "Dark Grey");
                                            break;
                                        case "darkgreen":
                                            nation.setMapColorHexCode("006400");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#006400") + "Dark Green");
                                            break;
                                        case "darkkhaki":
                                            nation.setMapColorHexCode("bdb76b");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#BDB76B") + "Dark Khaki");
                                            break;
                                        case "darkmagenta":
                                            nation.setMapColorHexCode("8b008b");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#BDB76B") + "Dark Magenta");
                                            break;
                                        case "darkolivegreen":
                                            nation.setMapColorHexCode("556b2f");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#556B2F") + "Dark Olive Green");
                                            break;
                                        case "darkorange":
                                            nation.setMapColorHexCode("ff8c00");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#FF8C00") + "Dark Orange");
                                            break;
                                        case "darkorchid":
                                            nation.setMapColorHexCode("9932cc");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#9932CC") + "Dark Orchid");
                                            break;
                                        case "darkred":
                                            nation.setMapColorHexCode("8b0000");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#8B0000") + "Dark Red");
                                            break;
                                        case "darksalmon":
                                            nation.setMapColorHexCode("e9967a");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#E9967A") + "Dark Salmon");
                                            break;
                                        case "darkviolet":
                                            nation.setMapColorHexCode("9400d3");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#9400D3") + "Dark Violet");
                                            break;
                                        case "fuchsia":
                                            nation.setMapColorHexCode("ff00ff");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#FF00FF") + "Fuchsia");
                                            break;
                                        case "gold":
                                            nation.setMapColorHexCode("ffd700");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#FFD700") + "Gold");
                                            break;
                                        case "green":
                                            nation.setMapColorHexCode("008000");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#008000") + "Green");
                                            break;
                                        case "indigo":
                                            nation.setMapColorHexCode("4b0082");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#4B0082") + "Indigo");
                                            break;
                                        case "khaki":
                                            nation.setMapColorHexCode("f0e68c");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#F0E68C") + "Khaki");
                                            break;
                                        case "lightblue":
                                            nation.setMapColorHexCode("add8e6");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#ADD8E6") + "Light Blue");
                                            break;
                                        case "lightcyan":
                                            nation.setMapColorHexCode("e0ffff");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#E0FFFF") + "Light Cyan");
                                            break;
                                        case "lightgreen":
                                            nation.setMapColorHexCode("90ee90");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#90EE90") + "Light Green");
                                            break;
                                        case "lightgrey":
                                            nation.setMapColorHexCode("d3d3d3");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#D3D3D3") + "Light Grey");
                                            break;
                                        case "lightpink":
                                            nation.setMapColorHexCode("ffb6c1");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#FFB6C1") + "Light Pink");
                                            break;
                                        case "lightyellow":
                                            nation.setMapColorHexCode("ffffe0");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#FFFFE0") + "Light Yellow");
                                            break;
                                        case "lime":
                                            nation.setMapColorHexCode("00ff00");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#00FF00") + "Lime");
                                            break;
                                        case "magenta":
                                            nation.setMapColorHexCode("ff00ff");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#FF00FF") + "Magenta");
                                            break;
                                        case "maroon":
                                            nation.setMapColorHexCode("800000");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#800000") + "Maroon");
                                            break;
                                        case "navy":
                                            nation.setMapColorHexCode("000080");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#000080") + "Navy");
                                            break;
                                        case "olive":
                                            nation.setMapColorHexCode("808000");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#808000") + "Olive");
                                            break;
                                        case "orange":
                                            nation.setMapColorHexCode("ffa500");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#FFA500") + "Orange");
                                            break;
                                        case "pink":
                                            nation.setMapColorHexCode("ffc0cb");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#FFC0CB") + "Pink");
                                            break;
                                        case "purple":
                                            nation.setMapColorHexCode("800080");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#800080") + "Purple");
                                            break;
                                        case "violet":
                                            nation.setMapColorHexCode("800080");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#800080") + "Violet");
                                            break;
                                        case "red":
                                            nation.setMapColorHexCode("ff0000");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#FF0000") + "Red");
                                            break;
                                        case "silver":
                                            nation.setMapColorHexCode("c0c0c0");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#C0C0C0") + "Silver");
                                            break;
                                        case "white":
                                            nation.setMapColorHexCode("ffffff");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#FFFFFF") + "White");
                                            break;
                                        case "yellow":
                                            nation.setMapColorHexCode("ffff00");
                                            player.sendMessage(Core.prefix + "Nation color changed to " + ChatColor.of("#FFFF00") + "Yellow");
                                            break;
                                        case "colors":
                                        default:
                                            player.sendMessage("§b==========[COLORS]==========");
                                            player.sendMessage("§3aqua, azure, beige, black, blue, brown, cyan, darkblue,");
                                            player.sendMessage("§3darkcyan, darkgrey, darkgreen, darkkhaki, darkmagenta,");
                                            player.sendMessage("§3darkolivegreen, darkorange, darkorchid, darkred, darksalmon,");
                                            player.sendMessage("§3darkviolet, fuchsia, gold, green, indigo, khaki, lightblue,");
                                            player.sendMessage("§3lightcyan, lightgreen, lightgrey, lightpink, lightyellow,");
                                            player.sendMessage("§3lime, magenta, maroon, navy, olive, orange, pink, purple,");
                                            player.sendMessage("§3violet, red, silver, white, yellow");
                                            player.sendMessage("§b===========================");
                                            break;
                                    }
                                } else {
                                    player.sendMessage(Core.prefix + "§cYou must be the §eKing §cof the nation to change the map color.");
                                }
                            } else {
                                player.sendMessage(Core.prefix + "§cYou are not in a nation.");
                            }

                        } else {
                            player.sendMessage(Core.prefix + "§cYou are not in a town.");
                        }

                    } catch (NotRegisteredException e) {
                        e.printStackTrace();
                        player.sendMessage(Core.prefix + "§cAn issue occured while running that command.");
                    }

                } else {
                    player.sendMessage(Core.prefix + "§eYou must be §dVIP+ §eto use that command.");
                }
            } else {
                player.sendMessage(Core.prefix + "§eIncorrect Usage: §ePlease specific a §ac§bo§cl§do§5r.");
                player.sendMessage(Core.prefix + "§eCorrect Usage: [§c/mapcolor <color>§e]");
            }


        } else {
            Bukkit.getLogger().info("[TownyCore]: You need to be a player to execute this command.");
        }
        return false;
    }
}
