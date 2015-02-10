package com.chris97b.mcstuff.tileentities;


import com.chris97b.mcstuff.init.ModItems;
import net.minecraft.item.ItemStack;

/**
 * Created by Chris on 2/10/2015.
 */
public class TileEntityCloudSeeder extends TileEntityInventory
{

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
        ItemStack saltCartridgeStack = new ItemStack(ModItems.saltCartridge);
        return itemstack.isItemEqual(saltCartridgeStack);
    }

}
