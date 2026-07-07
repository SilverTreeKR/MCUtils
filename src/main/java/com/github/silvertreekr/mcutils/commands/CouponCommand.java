package com.github.silvertreekr.mcutils.commands;

import com.github.silvertreekr.mcutils.MCUtils;
import com.github.silvertreekr.mcutils.dao.CouponManager;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class CouponCommand extends BukkitCommand {
    public CouponCommand(@NotNull JavaPlugin plugin) {
        super("쿠폰");
        plugin.getServer().getCommandMap().register("mcutils", this);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player)) {
        if (!(sender instanceof Player player)) {
            return false;
        }
        if (args.length == 0) {
            sender.sendRichMessage("<bold>[ 쿠폰 시스템 ] <reset>사용법: /쿠폰 [쿠폰ID]");
            return true;
            sender.sendRichMessage("<bold>[ 쿠폰 ] <reset>사용법: /쿠폰 [쿠폰ID]");
            return false;
        }
        CouponManager couponManager = MCUtils.getInstance().getCouponManager();
        UUID uuid = ((Player) sender).getUniqueId();
        UUID uuid = player.getUniqueId();
        var placeholder = Placeholder.parsed("coupon", args[0].toString());

        switch (args[0]) {
            case "R3411Y0P3N" -> {
                ZoneId kstZone = ZoneId.of("Asia/Seoul");
                ZonedDateTime nowKst = ZonedDateTime.now(kstZone);
                ZonedDateTime expiredDateKst = ZonedDateTime.of(2026, 7, 10, 0, 0, 0, 0, kstZone);

                if (nowKst.isAfter(expiredDateKst)) {
                    sender.sendRichMessage("<bold>[ 쿠폰 시스템 ] <reset><red>해당 쿠폰은 이미 만료되었습니다.");
                    return true;
                    sender.sendRichMessage("<bold>[ 쿠폰 ] <reset><red>해당 쿠폰은 이미 만료되었습니다.");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    return false;
                }
                if (couponManager.isUsedCoupon(uuid, args[0])) {
                    sender.sendRichMessage("<bold>[ 쿠폰 시스템 ] <reset><red>이미 사용한 쿠폰입니다.");
                    return true;
                    sender.sendRichMessage("<bold>[ 쿠폰 ] <reset><red>이미 사용한 쿠폰입니다.");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    return false;
                }

                couponManager.useCoupon(uuid, args[0]);
                ((Player) sender).give(createReallyOpenReward());
                sender.sendRichMessage("<bold>[ 쿠폰 시스템 ] <reset><green><coupon><reset> 쿠폰을 사용하셨습니다.",placeholder);
                sender.sendRichMessage("<bold>[ 쿠폰 시스템 ] <reset><aqua>오픈까지 기다려주셔서 감사합니다.");
                sender.sendRichMessage("<bold>[ 쿠폰 시스템 ] <reset><aqua>즐거운 마인크래프트 되세요 !");
                sender.sendRichMessage("<bold>[ 쿠폰 ] <reset><green><coupon><reset> 쿠폰을 사용하셨습니다.",placeholder);
                player.give(createReallyOpenReward());
                sender.sendRichMessage("<bold>[ 쿠폰 ] <reset><aqua>오픈까지 기다려주셔서 감사합니다.");
                sender.sendRichMessage("<bold>[ 쿠폰 ] <reset><aqua>즐거운 마인크래프트 되세요 !");
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                return true;
            }
            default -> {
                sender.sendRichMessage("<bold>[ 쿠폰 시스템 ] <reset><red>올바르지 않은 쿠폰 ID입니다.");
                sender.sendRichMessage("<bold>[ 쿠폰 ] <reset><red>올바르지 않은 쿠폰 ID입니다.");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                return false;
            }
        }
    }
    private List<ItemStack> createReallyOpenReward() {
        ItemStack ironPickaxe = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta ironPickaxeItemMeta = ironPickaxe.getItemMeta();

        ironPickaxeItemMeta.addEnchant(Enchantment.UNBREAKING, 1, false);
        ironPickaxe.setItemMeta(ironPickaxeItemMeta);
        ironPickaxe.setAmount(1);

        ItemStack ironAxe = new ItemStack(Material.IRON_AXE);
        ItemMeta ironAxeItemMeta = ironAxe.getItemMeta();

        ironAxeItemMeta.addEnchant(Enchantment.UNBREAKING, 1, false);
        ironAxe.setItemMeta(ironAxeItemMeta);
        ironAxe.setAmount(1);

        return List.of(
                ironPickaxe,
                ironAxe
        );
    }
}
