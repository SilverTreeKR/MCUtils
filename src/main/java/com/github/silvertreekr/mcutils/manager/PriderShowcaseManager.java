package com.github.silvertreekr.mcutils.manager;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PriderShowcaseManager {
    private static PriderShowcaseManager instance;

    private UUID currentShowerID;
    private ItemStack currentItem;
    private final Set<UUID> interestedPlayers = new HashSet<>();

    private PriderShowcaseManager() {}

    public static PriderShowcaseManager getInstance() {
        if (instance == null) {
            instance = new PriderShowcaseManager();
        }
        return instance;
    }

    public void startShowcase(Player player, ItemStack item) {
        this.currentShowerID = player.getUniqueId();
        this.currentItem = item;
        this.interestedPlayers.clear();
    }

    public boolean hasActiveShowcase() {
        return currentShowerID != null;
    }

    public UUID getCurrentShowerID() {
        return currentShowerID;
    }

    public boolean tryAddInterest(Player player){
        if (!hasActiveShowcase()) {
            return false;
        }
        return interestedPlayers.add(player.getUniqueId());
    }
}
