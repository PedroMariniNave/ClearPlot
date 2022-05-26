package com.zpedroo.clearplot.utils.menu;

import com.intellectualcrafters.plot.object.Plot;
import com.zpedroo.clearplot.managers.PlotManager;
import com.zpedroo.clearplot.utils.FileUtils;
import com.zpedroo.clearplot.utils.builder.InventoryBuilder;
import com.zpedroo.clearplot.utils.builder.InventoryUtils;
import com.zpedroo.clearplot.utils.builder.ItemBuilder;
import com.zpedroo.clearplot.utils.color.Colorize;
import com.zpedroo.clearplot.utils.item.ItemUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Menus extends InventoryUtils {

    private static Menus instance;
    public static Menus getInstance() { return instance; }

    public Menus() {
        instance = this;
    }

    public void openConfirmMenu(Player player, Plot plot) {
        FileUtils.Files file = FileUtils.Files.CONFIRM;

        String title = Colorize.getColored(FileUtils.get().getString(file, "Inventory.title"));
        int size = FileUtils.get().getInt(file, "Inventory.size");

        InventoryBuilder inventory = new InventoryBuilder(title, size);
        for (String items : FileUtils.get().getSection(file, "Inventory.items")) {
            String action = FileUtils.get().getString(file, "Inventory.items." + items + ".action");
            ItemStack item = ItemBuilder.build(FileUtils.get().getFile(file).getFileConfiguration(), "Inventory.items." + items).build();
            int slot = FileUtils.get().getInt(file, "Inventory.items." + items + ".slot");

            inventory.addItem(item, slot, () -> {
                switch (action.toUpperCase()) {
                    case "CONFIRM":
                        inventory.close(player);

                        ItemStack clearPlotItem = ItemUtils.getItem(1);
                        if (player.getInventory().first(clearPlotItem) == -1) return;

                        player.getInventory().removeItem(clearPlotItem);
                        PlotManager.getInstance().clearPlot(plot);
                        break;
                    case "CANCEL":
                        inventory.close(player);
                        ItemUtils.giveItem(player, 1);
                        break;
                }
            }, InventoryUtils.ActionType.ALL_CLICKS);
        }

        inventory.open(player);
    }
}