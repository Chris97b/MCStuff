package com.chris97b.mcstuff.tileentities;


import com.chris97b.mcstuff.block.BlockContainerCloudSeeder;
import com.chris97b.mcstuff.init.ModItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

/**
 * Created by Chris on 2/10/2015.
 */
public class TileEntityCloudSeeder extends TileEntityInventory
{
    private int isAuto;
    private short ticks = 0;

    public TileEntityCloudSeeder(){
        super();
        isAuto = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
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

    @Override
    public void updateEntity() {
        super.updateEntity();
        this.ticks++;
        if(this.ticks >= 100 && (this.isAuto == 1)){
            this.ticks = 0;
            this.removeRain();
        }
    }

    public void addRain()
    {
        if(!this.worldObj.getWorldInfo().isRaining())
        {
            ItemStack stack = this.getStackInSlot(0);
            if(stack!=null && stack.isItemEqual(new ItemStack(ModItems.saltCartridge)))
            {
                this.decrStackSize(0, 1);
                this.worldObj.getWorldInfo().setRainTime(600);
                this.worldObj.getWorldInfo().setRaining(true);
            }
        }
    }

    public void removeRain()
    {

        if(this.worldObj.getWorldInfo().isRaining())
        {
            ItemStack stack = this.getStackInSlot(0);
            if(stack!=null && stack.isItemEqual(new ItemStack(ModItems.saltCartridge)))
            {
                this.decrStackSize(0, 1);
                this.worldObj.getWorldInfo().setRainTime(0);
                this.worldObj.getWorldInfo().setRaining(false);
            }
        }
    }

    public void toggleAuto()
    {
        System.out.println("Toggling auto function");
        int metadata=worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        metadata = this.isAuto = metadata == 0 ? 1 : 0;
        worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, metadata, 3);
    }


}
