package com.chris97b.mcstuff.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * Created by Chris on 2/10/2015.
 */
public class TileEntityInventory extends TileEntitiesMCStuff implements IInventory {

    private ItemStack[] items;

    public TileEntityInventory()
    {
        items = new ItemStack[1];
    }


    @Override
    public int getSizeInventory() {
        return items.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return items[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int count)
    {
        ItemStack itemstack = getStackInSlot(slot);
        if(itemstack != null)
        {
            if (itemstack.stackSize <= count)
            {
                setInventorySlotContents(slot, null);
            }
            else
            {
                itemstack=itemstack.splitStack(count);
                markDirty();
            }
        }
        return itemstack;

    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack itemstack=getStackInSlot(slot);
        setInventorySlotContents(slot, null);
        return itemstack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemstack) {
        items[slot]=itemstack;
        if(itemstack!=null && itemstack.stackSize>getInventoryStackLimit()) {
            itemstack.stackSize = 64;
        }
        markDirty();
    }

    @Override
    public String getInventoryName() {
        return "Inventory";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void markDirty() {
        //Not sure wtf this does
        //Hmm. Marks it for update apparently. Do I need to implement this, or is it handled in IInventory?
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return player.getDistanceSq(xCoord+0.5, yCoord+0.5, zCoord+0.5)<64;
    }

    @Override
    public void openInventory() {
        //Do nothing
    }

    @Override
    public void closeInventory() {
        //Do nothing
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
        return true;
    }
}
