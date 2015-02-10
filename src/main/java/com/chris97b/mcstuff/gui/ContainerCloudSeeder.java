package com.chris97b.mcstuff.gui;

import com.chris97b.mcstuff.tileentities.TileEntityCloudSeeder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

/**
 * Created by Chris on 2/10/2015.
 */
public class ContainerCloudSeeder extends Container
{

    private TileEntityCloudSeeder te;

    public ContainerCloudSeeder(InventoryPlayer invPlayer, TileEntityCloudSeeder te)
    {
        this.te=te;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return te.isUseableByPlayer(player);
    }
}
