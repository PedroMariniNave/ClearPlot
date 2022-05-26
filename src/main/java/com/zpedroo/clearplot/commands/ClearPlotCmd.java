package com.zpedroo.clearplot.commands;

import com.zpedroo.clearplot.utils.config.Messages;
import com.zpedroo.clearplot.utils.config.Settings;
import com.zpedroo.clearplot.utils.item.ItemUtils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearPlotCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission(Settings.ADMIN_PERMISSION)) {
            sender.sendMessage(Settings.NEED_PERMISSION_MESSAGE);
            return true;
        }

        if (args.length >= 3) {
            switch (args[0].toUpperCase()) {
                case "GIVE":
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null) {
                        sender.sendMessage(Messages.OFFLINE_PLAYER);
                        return true;
                    }

                    int amount = getIntByString(args[2]);
                    if (amount <= 0) {
                        sender.sendMessage(Messages.INVALID_AMOUNT);
                        return true;
                    }

                    ItemUtils.giveItem(target, amount);
                    return true;
            }
        }

        sender.sendMessage(StringUtils.replaceEach(Messages.COMMAND_USAGE, new String[]{
                "{cmd}"
        }, new String[]{
                label
        }));
        return false;
    }

    private int getIntByString(String str) {
        int ret = 0;
        try {
            ret = Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            // ignore
        }

        return ret;
    }
}