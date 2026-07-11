package com.github.silvertreekr.mcutils.commands;

import com.github.silvertreekr.mcutils.utils.CustomItemBuilder;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

public class GimCommand extends BukkitCommand {
    public GimCommand(@NotNull JavaPlugin plugin) {
        super("김");
        plugin.getServer().getCommandMap().register("mcutils", this);
    }

    private final HashMap<UUID, LocalDateTime> lastExecutions = new HashMap<>();
    private final int COOLTIME = 60 * 1;

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }

        if (args.length != 0) {
            sender.sendRichMessage("<bold>【 아이템 】 <reset><red>올바르지 않은 명령어입니다.");
            sender.sendRichMessage("<bold>【 아이템 】 <reset>올바른 사용법: /김");

            return false;
        }
        LocalDateTime nowTime = LocalDateTime.now();
        if (lastExecutions.containsKey(player.getUniqueId())) {
            LocalDateTime lastExecution = lastExecutions.get(player.getUniqueId());
            Duration duration = Duration.between(lastExecution, nowTime);
            long minutesPassed = duration.toMinutes();

            if (minutesPassed < COOLTIME) {
                long minutesLeft = COOLTIME - minutesPassed;
                long hoursLeft = minutesLeft / 60;
                long remainMinutes = minutesLeft % 60;

                String cooltimeText;
                if (hoursLeft > 0) {
                    cooltimeText = hoursLeft + "시간" + remainMinutes + "분";
                } else {
                    cooltimeText = remainMinutes + "분";
                }

                player.sendRichMessage("<bold>【 칭호 】 <reset>남은 시간: <bold><red><cooltime>", Placeholder.unparsed("cooltime", cooltimeText));
                return true;
            }
        }
        lastExecutions.put(player.getUniqueId(), nowTime);

        player.give(CustomItemBuilder.createGim());
        player.sendRichMessage("<bold>【 칭호 】 <reset><aqua>김<reset>이 지급되었습니다 !");

        return true;
    }
}
