package com.zpedroo.clearplot.utils.config;

import com.zpedroo.clearplot.utils.FileUtils;

import java.util.List;

public class BlocksToRemove {

    public static final List<String> NAME_LIST = FileUtils.get().getStringList(FileUtils.Files.CONFIG, "Blocks-To-Remove");
}