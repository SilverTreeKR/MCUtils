package com.github.silvertreekr.mcutils.commands;

import com.github.silvertreekr.mcutils.manager.PriderShowcaseManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class ShowcasePrideCommand extends BukkitCommand {
    public ShowcasePrideCommand(@NotNull JavaPlugin plugin) {
        super("자랑");
        plugin.getServer().getCommandMap().register("mcutils", this);
    }
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item.isEmpty()) {
            sender.sendRichMessage("<bold>[ 자랑 시스템 ] <reset><red>빈 손인 상태에서 아이템을 자랑할 수 없습니다 !");
            return false;
        }

        PriderShowcaseManager.getInstance().startShowcase(player, item);
        ItemMeta itemMeta = item.getItemMeta();
        Component baseName;

        if (itemMeta.displayName() != null) {
            baseName = item.displayName();
        } else {
            baseName = Component.translatable(item.getType().translationKey());
        }

        Component itemNameComponent = baseName.hoverEvent(item);

        Bukkit.broadcast(MiniMessage.miniMessage().deserialize(
                "<bold>[ 자랑 시스템 ] <reset><green><player><reset>님께서 <dark_purple>[<item>]<reset>을/를 자랑하고 싶어합니다 !",
                Placeholder.component("player", Component.text(player.getName())),
                Placeholder.component("item", itemNameComponent)
        ));
        Bukkit.broadcast(MiniMessage.miniMessage().deserialize(
                "<bold>[ 자랑 시스템 ] <reset><green><player><reset>님께 관심을 주세요 ! (/관심)",
                Placeholder.component("player", Component.text(player.getName()))
        ));
        return true;
    }
}
