package com.github.silvertreekr.mCUtils.commands;

import com.github.silvertreekr.mCUtils.MCUtils;
import com.github.silvertreekr.mCUtils.MaintenanceManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class MaintenanceCommand extends BukkitCommand {
    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
        MaintenanceManager manager = MCUtils.getMaintenanceManager();
        // /점검 상태
        if (args.length == 0) {

            // "/점검"
            if (manager.getStatus()) {
                manager.setStatus(false);
                commandSender.sendRichMessage("<bold>[ 점검 모드 ] <reset><red>비활성화 <reset>되었습니다.");
            } else {
                manager.setStatus(true);
                commandSender.sendRichMessage("<bold>[ 점검 모드 ] <reset><green>활성화 <reset>되었습니다.");
            }

        } else {
            // "/점검 ~" -> 올바르지 않은 인자
            if (!args[0].equals("상태")) {
                commandSender.sendRichMessage("<bold>[ 점검 모드 ] <reset><red>올바르지 않은 사용법입니다.");
                commandSender.sendRichMessage("<bold>[ 점검 모드 ] <reset><red>올바른 사용법 : /점검 상태");

                return false;
            }

            // "/점검 상태"
            if (manager.getStatus()) {
                commandSender.sendRichMessage("<bold>[ 점검 모드 ] <reset>상태: <green>활성화");
            } else {
                commandSender.sendRichMessage("<bold>[ 점검 모드 ] <reset>상태: <red>비활성화");
            }
            return true;
        }
        return true;
    }

    public MaintenanceCommand(JavaPlugin plugin) {
        super("점검");
        plugin.getServer().getCommandMap().register("mcutils", this);
    }
}
