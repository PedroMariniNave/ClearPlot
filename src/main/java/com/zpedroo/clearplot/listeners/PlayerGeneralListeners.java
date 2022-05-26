package com.zpedroo.clearplot.listeners;

import com.intellectualcrafters.plot.object.Plot;
import com.zpedroo.clearplot.managers.PlotManager;
import com.zpedroo.clearplot.utils.config.Messages;
import com.zpedroo.clearplot.utils.menu.Menus;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerGeneralListeners implements Listener {

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onInteract(PlayerInteractEvent event) {
        if (event.getItem() == null || event.getItem().getType().equals(Material.AIR)) return;
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        ItemStack item = event.getItem().clone();
        NBTItem nbt = new NBTItem(item);
        if (!nbt.hasKey("ClearPlot")) return;

        event.setCancelled(true);

        Player player = event.getPlayer();
        Location clickedBlockLocation = event.getClickedBlock().getLocation();
        Plot plot = PlotManager.getInstance().getPlotByLocation(clickedBlockLocation);
        if (plot == null || !plot.isAdded(player.getUniqueId())) {
            player.sendMessage(Messages.NOT_OWNER);
            return;
        }

        Menus.getInstance().openConfirmMenu(player, plot);
    }
}