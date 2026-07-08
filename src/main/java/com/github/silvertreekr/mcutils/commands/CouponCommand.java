package com.github.silvertreekr.mcutils.commands;

import com.github.silvertreekr.mcutils.MCUtils;
import com.github.silvertreekr.mcutils.dao.CouponManager;
import com.github.silvertreekr.mcutils.utils.CustomItemBuilder;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
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
        if (!(sender instanceof Player player)) {
            return false;
        }
        if (args.length == 0) {
            sender.sendRichMessage("<bold>[ 쿠폰 ] <reset>사용법: /쿠폰 [쿠폰ID]");
            return false;
        }
        CouponManager couponManager = MCUtils.getInstance().getCouponManager();
        UUID uuid = player.getUniqueId();
        var placeholder = Placeholder.parsed("coupon", args[0].toString());

        switch (args[0]) {
            case "R3411Y0P3N" -> {
                ZoneId kstZone = ZoneId.of("Asia/Seoul");
                ZonedDateTime nowKst = ZonedDateTime.now(kstZone);
                ZonedDateTime expiredDateKst = ZonedDateTime.of(2026, 7, 10, 0, 0, 0, 0, kstZone);

                if (nowKst.isAfter(expiredDateKst)) {
                    sender.sendRichMessage("<bold>[ 쿠폰 ] <reset><red>해당 쿠폰은 이미 만료되었습니다.");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    return false;
                }
                if (couponManager.isUsedCoupon(uuid, args[0])) {
                    sender.sendRichMessage("<bold>[ 쿠폰 ] <reset><red>이미 사용한 쿠폰입니다.");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    return false;
                }

                couponManager.useCoupon(uuid, args[0]);
                sender.sendRichMessage("<bold>[ 쿠폰 ] <reset><green><coupon><reset> 쿠폰을 사용하셨습니다.",placeholder);
                player.give(createReallyOpenReward());
                sender.sendRichMessage("<bold>[ 쿠폰 ] <reset><aqua>오픈까지 기다려주셔서 감사합니다.");
                sender.sendRichMessage("<bold>[ 쿠폰 ] <reset><aqua>즐거운 마인크래프트 되세요 !");
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                return true;
            }
            // 아야님 전용 쿠폰 코드
            case "7H4NK54Y4" -> {
                if (uuid.toString() != "66123349-3d00-4b75-a72a-836f1a6acf20") {
                    sender.sendRichMessage("<bold>[ 쿠폰 ] <reset><red>오직 아야님만 입력할 수 있는 쿠폰입니다.");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    return false;
                }
                if (couponManager.isUsedCoupon(uuid, args[0])) {
                    sender.sendRichMessage("<bold>[ 쿠폰 ] <reset><red>이미 사용한 쿠폰입니다.");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    return false;
                }

                sender.sendRichMessage("<bold>[ 쿠폰 ] <reset><green><coupon><reset> 쿠폰을 사용하셨습니다.",placeholder);
                couponManager.useCoupon(uuid, args[0]);
                patronDefaultReward(player);
                player.give(CustomItemBuilder.createAyaPresentBox());

                return true;
            }
            // 종식님 전용 쿠폰 코드
            case "74HNKSJ0NG51K" -> {
                if (uuid.toString() != "e078f0f4-8319-4ad0-8fd1-e50223218251") {
                    sender.sendRichMessage("<bold>[ 쿠폰 ] <reset><red>오직 종식님만 입력할 수 있는 쿠폰입니다.");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    return false;
                }
                if (couponManager.isUsedCoupon(uuid, args[0])) {
                    sender.sendRichMessage("<bold>[ 쿠폰 ] <reset><red>이미 사용한 쿠폰입니다.");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    return false;
                }

                sender.sendRichMessage("<bold>[ 쿠폰 ] <reset><green><coupon><reset> 쿠폰을 사용하셨습니다.",placeholder);
                couponManager.useCoupon(uuid, args[0]);
                patronDefaultReward(player);
                player.give(CustomItemBuilder.createJongSickPresentBox());

                return true;
            }
            // 무지님 전용 쿠폰 코드
            case "7H4NK5MUZZ1" -> {
                if (uuid.toString() != "d213a3a4-45b6-416e-af4e-15f319c11a7f") {
                    sender.sendRichMessage("<bold>[ 쿠폰 ] <reset><red>오직 무지님만 입력할 수 있는 쿠폰입니다.");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    return false;
                }
                if (couponManager.isUsedCoupon(uuid, args[0])) {
                    sender.sendRichMessage("<bold>[ 쿠폰 ] <reset><red>이미 사용한 쿠폰입니다.");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    return false;
                }

                sender.sendRichMessage("<bold>[ 쿠폰 ] <reset><green><coupon><reset> 쿠폰을 사용하셨습니다.",placeholder);
                couponManager.useCoupon(uuid, args[0]);
                patronDefaultReward(player);
                player.give(CustomItemBuilder.createMuzziPresentBox());

                return true;
            }
            default -> {
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
    private void patronDefaultReward(Player player) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "/칭호 지급 " + player.getName() + " 13");
        player.give(CustomItemBuilder.createPatronPresentBox());
        player.sendRichMessage("<bold>[ 쿠폰 ] <reset><aqua>후원해주셔서 감사합니다.");
        player.sendRichMessage("<bold>[ 쿠폰 ] <reset><yellow>아이템 상자를 사용하실 때 꼭 인벤토리를 비우신 후에 사용해주세요 !");
        player.sendRichMessage("<bold>[ 쿠폰 ] <reset><yellow>인벤토리 공간 부족으로 인한 아이템 유실은 책임지지 않습니다.");
        player.sendRichMessage("<bold>[ 쿠폰 ] <reset><yellow>아이템의 설명을 통해 지급될 아이템의 양을 확인하실 수 있습니다.");
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
    }
}