package com.github.silvertreekr.mCUtils.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PreventEnchantingTableListener implements Listener {
    @EventHandler
    public void onPlayerInteractBlock(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) {
            return;
        }
        if (event.getClickedBlock().getType() != Material.ENCHANTING_TABLE) {
            return;
        }
        event.setCancelled(true);
    }
    public PreventEnchantingTableListener(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
}
