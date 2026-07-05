package com.github.silvertreekr.mcutils.events;

import com.github.silvertreekr.mcutils.MCUtils;
import com.github.silvertreekr.mcutils.MaintenanceManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PreventLoginWhileMaintenanceListner implements Listener {
    @EventHandler
    public void onAysncPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
        if (Bukkit.getOperators().stream().anyMatch(offlinePlayer -> offlinePlayer.getUniqueId().equals(event.getUniqueId()))) {
            return;
        }
        MaintenanceManager manager = MCUtils.getMaintenanceManager();
        if (manager.getStatus()) {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, Component.text("지금은 점검 상태에 있습니다. 잠시 후 다시 시도해주시길 바랍니다."));
        }
    }

    public PreventLoginWhileMaintenanceListner(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
}
