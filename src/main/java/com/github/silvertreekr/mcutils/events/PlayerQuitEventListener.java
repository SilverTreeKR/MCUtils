package com.github.silvertreekr.mcutils.events;

import com.github.silvertreekr.mcutils.MCUtils;
import com.github.silvertreekr.mcutils.dao.CouponManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class PlayerQuitEventListener implements Listener {
    private final MCUtils plugin = MCUtils.getInstance();
    private final CouponManager couponManager = plugin.getCouponManager();

    public PlayerQuitEventListener(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();

        // Save database & unload from Cache (PlayerCouponData)
        couponManager.savePlayerCouponData(uuid);
        couponManager.unloadPlayerCouponData(uuid);
    }
}
