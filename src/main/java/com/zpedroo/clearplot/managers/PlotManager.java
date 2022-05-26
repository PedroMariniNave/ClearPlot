package com.zpedroo.clearplot.managers;

import com.boydti.fawe.util.EditSessionBuilder;
import com.intellectualcrafters.plot.object.Location;
import com.intellectualcrafters.plot.object.Plot;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.blocks.BaseBlock;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import com.zpedroo.clearplot.utils.config.BlocksToRemove;
import org.bukkit.Material;

import java.util.HashSet;
import java.util.Set;

public class PlotManager {

    private static PlotManager instance;
    public static PlotManager getInstance() { return instance; }

    public PlotManager() {
        instance = this;
    }

    public Plot getPlotByLocation(org.bukkit.Location location) {
        return Plot.getPlot(new Location(
                location.getWorld().getName(),
                (int) location.getX(),
                (int) location.getY(),
                (int) location.getZ()
        ));
    }

    public void clearPlot(Plot plot) {
        Location bottom = plot.getExtendedBottomAbs();
        Location top = plot.getExtendedTopAbs();

        int xMax = top.getX();
        int xMin = bottom.getX();

        int yMax = top.getY();
        int yMin = 1;

        int zMax = top.getZ();
        int zMin = bottom.getZ();

        Vector vectorMin = new Vector(xMin, yMin, zMin);
        Vector vectorMax = new Vector(xMax, yMax, zMax);

        removeBlocks(bottom, vectorMin, vectorMax);
    }

    private void removeBlocks(Location bottom, Vector vectorMin, Vector vectorMax) {
        EditSession editSession = new EditSessionBuilder(bottom.getWorld()).fastmode(true).checkMemory(true).autoQueue(true).build();

        try {
            Region region = new CuboidRegion(vectorMin, vectorMax);
            Set<BaseBlock> blocksToRemove = new HashSet<>(BlocksToRemove.NAME_LIST.size());
            for (String materialName : BlocksToRemove.NAME_LIST) {
                Material blockMaterial = Material.getMaterial(materialName);
                if (blockMaterial == null) continue;

                blocksToRemove.add(new BaseBlock(blockMaterial.getId()));
            }

            editSession.replaceBlocks(region, blocksToRemove, new BaseBlock(0));
            Operations.completeLegacy(editSession.commit());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
