package com.zpedroo.clearplot;

import com.zpedroo.clearplot.commands.ClearPlotCmd;
import com.zpedroo.clearplot.listeners.PlayerGeneralListeners;
import com.zpedroo.clearplot.managers.PlotManager;
import com.zpedroo.clearplot.utils.FileUtils;
import com.zpedroo.clearplot.utils.menu.Menus;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

import static com.zpedroo.clearplot.utils.config.Settings.ALIASES;
import static com.zpedroo.clearplot.utils.config.Settings.COMMAND;

public class ClearPlot extends JavaPlugin {

    private static ClearPlot instance;
    public static ClearPlot get() { return instance; }

    public void onEnable() {
        instance = this;
        new FileUtils(this);
        new PlotManager();
        new Menus();

        registerListeners();
        registerCommand(COMMAND, ALIASES, new ClearPlotCmd());
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerGeneralListeners(), this);
    }

    private void registerCommand(String command, List<String> aliases, CommandExecutor executor) {
        try {
            Constructor<PluginCommand> constructor = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            constructor.setAccessible(true);

            PluginCommand pluginCmd = constructor.newInstance(command, this);
            pluginCmd.setAliases(aliases);
            pluginCmd.setExecutor(executor);

            Field field = Bukkit.getPluginManager().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            CommandMap commandMap = (CommandMap) field.get(Bukkit.getPluginManager());
            commandMap.register(getName().toLowerCase(), pluginCmd);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}