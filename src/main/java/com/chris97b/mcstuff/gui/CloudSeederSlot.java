package com.chris97b.mcstuff.gui;

import com.chris97b.mcstuff.init.ModItems;
import com.chris97b.mcstuff.item.ItemEmptyCartridge;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by Chris on 2/10/2015.
 */
public class CloudSeederSlot extends Slot
{
    public CloudSeederSlot(IInventory inventory, int id, int x, int y)
    {
        super(inventory, id, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack itemstack)
    {
        Item c = itemstack.getItem();
        return (c instanceof ItemEmptyCartridge);
    }
}
