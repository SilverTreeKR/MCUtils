package com.github.silvertreekr.mcutils.events;

import com.github.silvertreekr.mcutils.MCUtils;
import com.github.silvertreekr.mcutils.utils.CustomItemBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class UseCustomItemEventListener implements Listener {

    private static final PlainTextComponentSerializer PLAIN = PlainTextComponentSerializer.plainText();

    public UseCustomItemEventListener(MCUtils plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerPlaceCustomItem(BlockPlaceEvent event) {
        ItemStack item = event.getItemInHand();
        if (item.isEmpty() || !item.hasItemMeta()) return;

        ItemMeta itemMeta = item.getItemMeta();
        if (!itemMeta.hasLore()) return;

        List<Component> loreComponents = itemMeta.lore();
        if (loreComponents == null) return;

        boolean isUsableBox = loreComponents.stream()
                .map(PLAIN::serialize)
                .anyMatch(line -> line.contains("▶ 우클릭하여 사용"));

        if (!isUsableBox) return;

        String itemName = PLAIN.serialize(item.displayName());

        if (isBoxOf(itemName, CustomItemBuilder.createPatronPresentBox())
                || isBoxOf(itemName, CustomItemBuilder.createPatronFoodBox())
                || isBoxOf(itemName, CustomItemBuilder.createPatronOreBox())
                || isBoxOf(itemName, CustomItemBuilder.createPatronBlockBox())
                || isBoxOf(itemName, CustomItemBuilder.createPatronEtcBox())
                || isBoxOf(itemName, CustomItemBuilder.createAyaPresentBox())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerUseCustomItem(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) return;
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item == null || item.isEmpty() || !item.hasItemMeta()) return;

        ItemMeta itemMeta = item.getItemMeta();
        if (!itemMeta.hasLore()) return;

        List<Component> loreComponents = itemMeta.lore();
        if (loreComponents == null) return;

        boolean isUsableBox = loreComponents.stream()
                .map(PLAIN::serialize)
                .anyMatch(line -> line.contains("▶ 우클릭하여 사용"));

        if (!isUsableBox) return;

        String itemName = PLAIN.serialize(item.displayName());
        List<ItemStack> contents = null;

        if (isBoxOf(itemName, CustomItemBuilder.createPatronPresentBox())) {
            contents = List.of(
                    CustomItemBuilder.createPatronFoodBox(),
                    CustomItemBuilder.createPatronOreBox(),
                    CustomItemBuilder.createPatronBlockBox(),
                    CustomItemBuilder.createPatronEtcBox()
            );
        } else if (isBoxOf(itemName, CustomItemBuilder.createPatronFoodBox())) {
            contents = CustomItemBuilder.createPatronFoodItem();
        } else if (isBoxOf(itemName, CustomItemBuilder.createPatronOreBox())) {
            contents = CustomItemBuilder.createPatronOreItem();
        } else if (isBoxOf(itemName, CustomItemBuilder.createPatronBlockBox())) {
            contents = CustomItemBuilder.createPatronBlockItem();
        } else if (isBoxOf(itemName, CustomItemBuilder.createPatronEtcBox())) {
            contents = CustomItemBuilder.createPatronEtcItem();
        } else if (isBoxOf(itemName, CustomItemBuilder.createAyaPresentBox())) {
            contents = CustomItemBuilder.createAyaItem();
        } else if (isBoxOf(itemName, CustomItemBuilder.createJongSickPresentBox())) {
            contents = CustomItemBuilder.createJongSickItem();
        } else if (isBoxOf(itemName, CustomItemBuilder.createMuzziPresentBox())) {
            contents = CustomItemBuilder.createMuzziItem();
        }

        if (contents == null || contents.isEmpty()) return;

        event.setUseItemInHand(Result.DENY);
        event.setCancelled(true);


        for (ItemStack content : contents) {
            player.getInventory().addItem(content);
        }

        item.setAmount(item.getAmount() - 1);
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.2f);
        player.sendRichMessage("<bold>[ 아이템 ] <reset><green>성공적으로 아이템이 지급되었습니다.");
    }

    private boolean isBoxOf(String itemName, ItemStack reference) {
        if (reference == null || !reference.hasItemMeta()) return false;
        return itemName.equals(PLAIN.serialize(reference.displayName()));
    }
}