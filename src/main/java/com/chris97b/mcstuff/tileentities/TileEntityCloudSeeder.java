package com.chris97b.mcstuff.tileentities;


import com.chris97b.mcstuff.init.ModItems;
import com.chris97b.mcstuff.item.ItemEmptyCartridge;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Chris on 2/10/2015.
 */
public class TileEntityCloudSeeder extends TileEntityInventory
{


    int ticks=0;
<<<<<<< HEAD
    int ticksSinceLastOperation=0;
    boolean autoMode = true;

    public TileEntityCloudSeeder()
    {
        super();
        if(worldObj.getBlockMetadata(xCoord, yCoord, zCoord)==1)
        {
            autoMode=false;
        }
    }

=======
>>>>>>> Added more things.

    @Override
    public void updateEntity()
    {
        ticks++;
<<<<<<< HEAD
        ticksSinceLastOperation++;
        if(ticks==100 && autoMode)
=======
        if(ticks==100)
>>>>>>> Added more things.
        {
            ticks=0;
            useCartridge();
        }
    }


    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
        Item c = itemstack.getItem();
        return (c instanceof ItemEmptyCartridge);
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);

        NBTTagList itemlist = new NBTTagList();

        for (int i=0; i< getSizeInventory(); i++)
        {
            ItemStack stack = getStackInSlot(i);

            if(stack!=null)
            {
                NBTTagCompound item = new NBTTagCompound();
                item.setByte("Slot", (byte)i);
                stack.writeToNBT(item);
                itemlist.appendTag(item);
            }
        }

        compound.setTag("Itemlist", itemlist);

    }

    public void useCartridge(){
        ItemStack stack = this.getStackInSlot(0);
        if(stack != null && isItemValidForSlot(0,stack)){
            this.decrStackSize(0,1);
            ItemEmptyCartridge cart = (ItemEmptyCartridge)stack.getItem();
            cart.runCartridgeLogic(this.worldObj);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);

        NBTTagList items = compound.getTagList("Itemlist", compound.getId());

        for (int i=0; i<items.tagCount(); i++)
        {
            NBTTagCompound item = (NBTTagCompound)items.getCompoundTagAt(i);
            int slot=item.getByte("Slot");
            if(slot >=0 && slot < getSizeInventory())
            {
                setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
            }
        }

    }

    public void toggleAuto()
    {
        System.out.println("Toggling auto function");
        int metadata=worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        if(metadata==0)
        {
            autoMode=true;
            worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 3);
        }
        else
        {
            autoMode=false;
            worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 3);
        }


    }



}
