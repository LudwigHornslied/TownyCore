package io.nebulamc.core.listeners;

import io.nebulamc.core.Core;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.persistence.PersistentDataType;

public class TransportationListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onEnter(VehicleEnterEvent event) {
        Vehicle vehicle = event.getVehicle();
    }

    @EventHandler(ignoreCancelled = true)
    public void onExit(VehicleExitEvent event) {
        if(!event.getVehicle().getPersistentDataContainer().has(new NamespacedKey(Core.getInstance(), "CustomVehicle"), PersistentDataType.BYTE))
            return;

        event.getVehicle().remove();
    }
}
