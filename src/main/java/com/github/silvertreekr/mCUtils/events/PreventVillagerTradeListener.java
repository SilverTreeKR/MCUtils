package com.github.silvertreekr.mCUtils.events;

import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PreventVillagerTradeListener implements Listener {
    @EventHandler
    public void onPlayerInteractVilager(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Villager) {
            event.setCancelled(true);
        }
    }

    public PreventVillagerTradeListener(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
}

// 주민 거래 막아야함
// -> PlayerInteractEntityEvent
