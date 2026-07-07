package com.github.silvertreekr.mcutils.manager;

import com.github.silvertreekr.mcutils.MCUtils;
import me.arcaniax.hdb.api.DatabaseLoadEvent;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class HeadDatabaseManager implements Listener {
    private final MCUtils plugin;
    private HeadDatabaseAPI api;

    public HeadDatabaseManager(MCUtils plugin) {
        this.plugin = plugin;

        if (Bukkit.getPluginManager().getPlugin("HeadDatabase") != null) {
            Bukkit.getPluginManager().registerEvents(this, plugin);
            plugin.getSLF4JLogger().info("HeadDatabase에 연결 중...");
        } else {
            plugin.getSLF4JLogger().warn("HeadDatabase 플러그인을 찾을 수 없습니다.");
        }
    }

    @EventHandler
    public void onDatabaseLoad(DatabaseLoadEvent e) {
        this.api = new HeadDatabaseAPI();
        plugin.getSLF4JLogger().info("HeadDatabase API를 성공적으로 로드하였습니다 !");
    }

    public ItemStack getHead(String headID) {
        if (api == null) {
            plugin.getSLF4JLogger().warn("HeadDatabase API가 아직 로드되지 않았거나 사용할 수 없습니다.");
            return null;
        }
        return api.getItemHead(headID);
    }
}
