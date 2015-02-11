package com.chris97b.mcstuff.gui;

import com.chris97b.mcstuff.tileentities.TileEntityCloudSeeder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Chris on 2/10/2015.
 */
public class ContainerCloudSeeder extends Container
{

    private TileEntityCloudSeeder te;

    public ContainerCloudSeeder(InventoryPlayer invPlayer, TileEntityCloudSeeder te)
    {
        this.te=te;

        for (int x=0; x<9; x++)
        {
            //System.out.println("Adding slot "+x);
            addSlotToContainer(new Slot(invPlayer, x, 8 + 18 * x, 142));
        }

        for (int y=0; y<3; y++)
        {
            for (int x=0; x<9; x++)
            {
                int slot=x+(y*9)+9;
                //System.out.println("Adding slot "+slot);
                addSlotToContainer(new Slot(invPlayer, slot, 8 + 18 * x, 84 + y * 18));
            }
        }

        //56,17
        addSlotToContainer(new CloudSeederSlot(te, 0, 56, 17));

    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return te.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int i)
    {
        return null;
    }

    public TileEntityCloudSeeder getTE()
    {
        return te;
    }

}
