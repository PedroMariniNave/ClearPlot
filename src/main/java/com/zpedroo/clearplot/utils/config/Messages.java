package com.zpedroo.clearplot.utils.config;

import com.zpedroo.clearplot.utils.FileUtils;
import com.zpedroo.clearplot.utils.color.Colorize;

public class Messages {

    public static final String NOT_OWNER = Colorize.getColored(FileUtils.get().getString(FileUtils.Files.CONFIG, "Messages.not-owner"));

    public static final String OFFLINE_PLAYER = Colorize.getColored(FileUtils.get().getString(FileUtils.Files.CONFIG, "Messages.offline-player"));

    public static final String INVALID_AMOUNT = Colorize.getColored(FileUtils.get().getString(FileUtils.Files.CONFIG, "Messages.invalid-amount"));

    public static final String COMMAND_USAGE = Colorize.getColored(FileUtils.get().getString(FileUtils.Files.CONFIG, "Messages.command-usage"));
}