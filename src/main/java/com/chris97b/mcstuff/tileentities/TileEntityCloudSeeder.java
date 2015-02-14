package com.chris97b.mcstuff.tileentities;


import com.chris97b.mcstuff.init.ModItems;
import com.chris97b.mcstuff.item.ItemEmptyCartridge;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Chris on 2/10/2015.
 */
public class TileEntityCloudSeeder extends TileEntityInventory
{


    int ticks=0;
    boolean autoMode = true;


    @Override
    public void updateEntity()
    {
        ticks++;
        if(ticks==100 & autoMode == true)
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
        compound.setBoolean("autoMode",this.autoMode);
    }

    public void useCartridge(){
        ItemStack stack = this.getStackInSlot(0);
        if(stack != null && isItemValidForSlot(0,stack)){
            ItemEmptyCartridge cart = (ItemEmptyCartridge)stack.getItem();
            if(cart.runCartridgeLogic(this.worldObj)){
                this.decrStackSize(0,1);
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);

        NBTTagList items = compound.getTagList("Itemlist", compound.getId());
        this.autoMode = compound.getBoolean("autoMode");

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

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord,yCoord,zCoord,3,tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
       readFromNBT(pkt.func_148857_g());
    }

    public void toggleAuto()
    {
        System.out.println("Toggling auto function on Tile Entity at" + xCoord + "," + zCoord);
        this.autoMode = !this.autoMode;
        System.out.println("Auto is now " + this.autoMode);
    }

    public boolean isAutoMode() {
        System.out.println("Getting auto on Tile Entity at" + xCoord + "," + zCoord);
        System.out.println("Auto is" + this.autoMode);
        return autoMode;
    }
}
