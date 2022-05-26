package com.zpedroo.clearplot.utils.config;

import com.zpedroo.clearplot.utils.FileUtils;
import com.zpedroo.clearplot.utils.color.Colorize;

import java.util.List;

public class Settings {

    public static final String COMMAND = FileUtils.get().getString(FileUtils.Files.CONFIG, "Settings.command");

    public static final List<String> ALIASES = FileUtils.get().getStringList(FileUtils.Files.CONFIG, "Settings.aliases");

    public static final String ADMIN_PERMISSION = FileUtils.get().getString(FileUtils.Files.CONFIG, "Settings.admin-permission");

    public static final String NEED_PERMISSION_MESSAGE = Colorize.getColored(
            FileUtils.get().getString(FileUtils.Files.CONFIG, "Settings.need-permission-message")
    );
}