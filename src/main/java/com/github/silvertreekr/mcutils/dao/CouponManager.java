package com.github.silvertreekr.mcutils.dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class CouponManager {
    private final CouponDAO couponDAO;
    private final HashMap<UUID, Set<String>> userCoupons = new HashMap<>();

    public CouponManager(CouponDAO couponDAO) {
        this.couponDAO = couponDAO;
    }

    public CompletableFuture<Void> loadPlayerCouponData(UUID uuid) {
        return couponDAO.getCoupons(uuid).thenAccept(coupons -> {
            userCoupons.put(uuid, coupons);
        });
    }

    public void unloadPlayerCouponData(UUID uuid) {
        userCoupons.remove(uuid);
    }

    public CompletableFuture<Void> savePlayerCouponData(UUID uuid) {
        return couponDAO.setCoupon(uuid, userCoupons.get(uuid));
    }

    public boolean isUsedCoupon(UUID uuid, String coupon) {
        Set<String> coupons = userCoupons.get(uuid);
        if (coupons == null) {
            return false;
        }
        return coupons.contains(coupon);
    }

    public void useCoupon(UUID uuid, String coupon) {
        userCoupons.computeIfAbsent(uuid, k -> new HashSet<>()).add(coupon);
    }
}
