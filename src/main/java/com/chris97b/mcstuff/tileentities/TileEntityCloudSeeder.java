package com.chris97b.mcstuff.tileentities;


import com.chris97b.mcstuff.init.ModItems;
import net.minecraft.inventory.IInventory;
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


    @Override
    public void updateEntity()
    {
        ticks++;
        ticksSinceLastOperation++;
        if(ticks==100 && autoMode)
        {
            ticks=0;
            this.addRain(worldObj, (IInventory) this);
            this.removeRain(worldObj, (IInventory) this);
        }
    }


    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
        ItemStack saltCartridgeStack = new ItemStack(ModItems.saltCartridge);
        return itemstack.isItemEqual(saltCartridgeStack);
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


    public void removeRain(World world, IInventory inventory)
    {

        if(world.getWorldInfo().isRaining() && ticksSinceLastOperation>=100)
        {
            ItemStack stack = inventory.getStackInSlot(0);
            if(stack!=null && stack.isItemEqual(new ItemStack(ModItems.saltCartridge)))
            {
                inventory.decrStackSize(0, 1);
                world.getWorldInfo().setRainTime(0);
                world.getWorldInfo().setRaining(false);
                ticksSinceLastOperation=0;
            }
        }
    }

    public void addRain(World world, IInventory inventory)
    {
        /*  Temporarily disabled until different cartridge types are added to prevent looping with auto add/remove
        if(!world.getWorldInfo().isRaining())
        {
            ItemStack stack = inventory.getStackInSlot(0);
            if(stack!=null && stack.isItemEqual(new ItemStack(ModItems.saltCartridge)))
            {
                inventory.decrStackSize(0, 1);
                world.getWorldInfo().setRainTime(600);
                world.getWorldInfo().setRaining(true);
                ticksSinceLastOperation=0;
            }
        }
        */
    }


}
