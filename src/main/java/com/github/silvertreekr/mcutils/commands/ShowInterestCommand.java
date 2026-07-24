package com.github.silvertreekr.mcutils.commands;

import com.github.silvertreekr.mcutils.manager.PriderShowcaseManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class ShowInterestCommand extends BukkitCommand {
    public ShowInterestCommand(@NotNull JavaPlugin plugin) {
        super("관심");
        plugin.getServer().getCommandMap().register("mcutils", this);
    }
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
        if(!(sender instanceof Player player)) {
            return false;
        }
        PriderShowcaseManager manager = PriderShowcaseManager.getInstance();

        if (!manager.hasActiveShowcase()) {
            player.sendRichMessage("<bold>【 자랑 】 <reset><red>지금은 자랑하고 있는 사람이 없습니다 !");
            return false;
        }

        if (player.getUniqueId().equals(manager.getCurrentShowerID())) {
            player.sendRichMessage("<bold>【 자랑 】 <reset><red>자기 자신에게는 관심을 줄 수 없습니다 !");
            player.sendRichMessage("<bold>【 자랑 】 <reset><red>그렇게나 관심이 받고 싶으셨나요...?");
            Bukkit.broadcast(MiniMessage.miniMessage().deserialize(
                    "<bold>【 자랑 】 <reset><green><player><reset>님께서 본인에게 관심을 주려고 하셨습니다 !",
                    Placeholder.component("player", Component.text(player.getName()))
            ));
            Bukkit.broadcast(MiniMessage.miniMessage().deserialize(
                    "<bold>【 자랑 】 <reset>많이 관심이 고프신가봅니다. 모두 관심을 주세요 ㅋ (/관심)"
            ));
            return false;
        }
        boolean added = manager.tryAddInterest(player);
        if (!added) {
            player.sendRichMessage("<bold>【 자랑 】 <reset><yellow>이미 관심을 주셨습니다 !");
            return false;
        }

        Player target = Bukkit.getPlayer(manager.getCurrentShowerID());
        if (target == null) {
            sender.sendRichMessage("<bold>【 자랑 】 <reset><red>관심 대상이 접속을 종료했거나 존재하지 않습니다.");
            return false;
        }
        sender.sendRichMessage(
                "<bold>【 자랑 】 <reset><aqua><target><reset>님께 관심을 보였습니다 !",
                Placeholder.component("target", Component.text(target.getName()))
        );
        target.sendRichMessage(
                "<bold>【 자랑 】 <reset><light_purple><player><reset>님이 당신의 아이템에 관심을 보였습니다 !",
                Placeholder.component("player", Component.text(sender.getName()))
        );
        target.sendRichMessage(
                "<bold>【 자랑 】 <reset>지금까지 받은 관심 수: <count>",
                Placeholder.component("count", Component.text(manager.getInterestCount()))
        );
        return true;
    }
}
