package com.zpedroo.clearplot.utils.item;

import com.zpedroo.clearplot.utils.FileUtils;
import com.zpedroo.clearplot.utils.builder.ItemBuilder;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemUtils {

    private static final ItemStack ITEM = ItemBuilder.build(FileUtils.get().getFile(FileUtils.Files.CONFIG).getFileConfiguration(), "Item").build();

    public static ItemStack getItem() {
        NBTItem nbt = new NBTItem(ITEM.clone());
        nbt.addCompound("ClearPlot");

        return nbt.getItem();
    }

    public static ItemStack getItem(int amount) {
        ItemStack item = getItem();
        item.setAmount(amount);

        return item;
    }

    public static void giveItem(Player player) {
        giveItem(player, 1);
    }

    public static void giveItem(Player player, int amount) {
        ItemStack item = ItemUtils.getItem();
        if (item.getMaxStackSize() == 1) {
            for (int i = 0; i < amount; ++i) {
                giveItem(player, item);
            }
        } else {
            item.setAmount(amount);
            giveItem(player, item);
        }
    }

    private static void giveItem(Player player, ItemStack item) {
        if (player.getInventory().firstEmpty() != -1) {
            player.getInventory().addItem(item);
        } else {
            player.getWorld().dropItemNaturally(player.getLocation(), item);
        }
    }
}