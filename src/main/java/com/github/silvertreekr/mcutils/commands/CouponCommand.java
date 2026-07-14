package com.github.silvertreekr.mcutils.commands;

import com.github.silvertreekr.customItems.models.CustomItem;
import com.github.silvertreekr.mcprefixachievement.model.PrefixName;
import com.github.silvertreekr.mcprefixachievement.util.PrefixGranter;
import com.github.silvertreekr.mcutils.MCUtils;
import com.github.silvertreekr.mcutils.dao.CouponManager;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

public class CouponCommand extends BukkitCommand {
    public CouponCommand(@NotNull JavaPlugin plugin) {
        super("쿠폰");
        plugin.getServer().getCommandMap().register("mcutils", this);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }
        if (args.length == 0) {
            sender.sendRichMessage("<bold>【 쿠폰 】 <reset>사용법: /쿠폰 [쿠폰ID]");
            return false;
        }
        CouponManager couponManager = MCUtils.getInstance().getCouponManager();
        UUID uuid = player.getUniqueId();
        var placeholder = Placeholder.parsed("coupon", args[0].toString());

        switch (args[0]) {
            case "0URM3M0R135N01" -> {
                ZoneId kstZone = ZoneId.of("Asia/Seoul");
                ZonedDateTime nowKst = ZonedDateTime.now(kstZone);
                ZonedDateTime expiredDateKst = ZonedDateTime.of(2026, 7, 23, 0, 0, 0, 0, kstZone);

                if (nowKst.isAfter(expiredDateKst)) {
                    sender.sendRichMessage("<bold>【 쿠폰 】 <reset><red>해당 쿠폰은 이미 만료되었습니다.");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    return false;
                }
                if (couponManager.isUsedCoupon(uuid, args[0])) {
                    sender.sendRichMessage("<bold>【 쿠폰 】 <reset><red>이미 사용한 쿠폰입니다.");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    return false;
                }

                couponManager.useCoupon(uuid, args[0]);
                sender.sendRichMessage("<bold>【 쿠폰 】 <reset><green><coupon><reset> 쿠폰을 사용하셨습니다.",placeholder);
                player.give(CustomItem.OUR_MEMORIES_NO1_REWARD.create(1));
                PrefixGranter.grantPrefix(player, PrefixName.SUMMER_POOL);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                return true;
            }
            default -> {
                sender.sendRichMessage("<bold>【 쿠폰 】 <reset><red>올바르지 않은 쿠폰 ID입니다.");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                return false;
            }
        }
    }

//    private void patronDefaultReward(Player player) {
//        PrefixGranter.grantPrefix(player, PrefixName.PATRON);
//        player.give(CustomItem.PATRON_DEFAULT_PRESENT_BOX.create(1));
//        player.sendRichMessage("<bold>【 쿠폰 】 <reset><aqua>후원해주셔서 감사합니다.");
//        player.sendRichMessage("<bold>【 쿠폰 】 <reset><yellow>아이템 상자를 사용하실 때 꼭 인벤토리를 비우신 후에 사용해주세요 !");
//        player.sendRichMessage("<bold>【 쿠폰 】 <reset><yellow>인벤토리 공간 부족으로 인한 아이템 유실은 책임지지 않습니다.");
//        player.sendRichMessage("<bold>【 쿠폰 】 <reset><yellow>아이템의 설명을 통해 지급될 아이템의 양을 확인하실 수 있습니다.");
//        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
//    }
}