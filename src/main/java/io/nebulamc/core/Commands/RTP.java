package io.nebulamc.core.Commands;

import io.nebulamc.core.Core;
import io.nebulamc.core.PermissionNodes;
import io.nebulamc.core.Util.LocationCheck;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class RTP implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        String commandError = "§cIncorrect Usage: §ePlease use /rtp <player> <x> <z>";
        if (sender instanceof Player){

            Player player = (Player) sender;

            // /rtp player x z
            if (player.hasPermission(PermissionNodes.rtpCommand)){

                if(args.length <= 1){
                    player.sendMessage(Core.prefix + commandError);
                } else if(args.length == 3) {
                    String victimName = args[0];
                    Player victim = Bukkit.getPlayer(victimName);
                    double x = Double.parseDouble(args[1]);
                    double y = 62.0;
                    double z = Double.parseDouble(args[2]);
                    Location loc = victim.getLocation();
                    loc.set(x, y , z);

                    if(LocationCheck.isSafeLocation(loc) == true) {
                        victim.teleport(loc);
                    } else {
                        int i = 1;
                        while(i < 20){
                            //Attempt 20 times
                            if(i != 20 && LocationCheck.isSafeLocation(loc) == false){
                                double yCoord = loc.getY();
                                yCoord = yCoord + 2.0;
                                loc.setY(yCoord);
                                if(LocationCheck.isSafeLocation(loc) == true){
                                    victim.teleport(loc);
                                    player.sendMessage(Core.prefix + "Player teleported to a safe location.");
                                    break;
                                }
                            }


                        }
                    }


                } else {
                    player.sendMessage(Core.prefix + commandError);
                }

            } else {
                player.sendMessage(Core.prefix + "§cYou do not have permission for this command.");
            }

        } else {
            Core.log.info(Core.prefix + " Initializing command.");
            if(args.length == 3){
                String victimName = args[0];
                Player victim = Bukkit.getPlayer(victimName);
                double x = Double.parseDouble(args[1]);
                double y = 62.0;
                double z = Double.parseDouble(args[2]);
                Location loc = victim.getLocation();
                loc.set(x, y , z);

                if(LocationCheck.isSafeLocation(loc) == true) {
                    victim.teleport(loc);
                } else {
                    int i = 1;
                    while(i < 20){
                        //Attempt 20 times
                        if(i != 20 && LocationCheck.isSafeLocation(loc) == false){
                            double yCoord = loc.getY();
                            yCoord = yCoord + 2.0;
                            loc.setY(yCoord);
                            if(LocationCheck.isSafeLocation(loc) == true){
                                victim.teleport(loc);
                                Core.log.info(Core.prefix + "Player teleported to a safe location.");
                                break;
                            }
                        }


                    }
                }
            }
        }


        return false;
    }
}
