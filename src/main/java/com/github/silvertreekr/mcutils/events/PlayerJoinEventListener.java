package com.github.silvertreekr.mcutils.events;

import com.github.silvertreekr.mcutils.MCUtils;
import com.github.silvertreekr.mcutils.dao.CouponManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.CompletableFuture;

public class PlayerJoinEventListener implements Listener {
    private final MCUtils plugin = MCUtils.getInstance();
    private final CouponManager couponManager = plugin.getCouponManager();

    public PlayerJoinEventListener(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Load PlayerCouponData to Cache
        CompletableFuture<Void> couponLoad = couponManager.loadPlayerCouponData(event.getPlayer().getUniqueId());
    }
}
