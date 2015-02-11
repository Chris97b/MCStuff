package com.chris97b.mcstuff.block;

import com.chris97b.mcstuff.MCStuff;
import com.chris97b.mcstuff.init.ModItems;
import com.chris97b.mcstuff.tileentities.TileEntityCloudSeeder;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Arrays;

/**
 * Created by Chris on 2/10/2015.
 */
public class BlockContainerCloudSeeder extends BlockContainerMCStuff
{
    public BlockContainerCloudSeeder()
    {
        super();
        this.setBlockName("cloudSeeder");
        this.setHardness(5F);
        setHarvestLevel("pickaxe", 1);
    }

    public IIcon[] icons = new IIcon[6];

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
        for (int i=0; i<6; i++)
        {
            if(i==1)
            {
                this.icons[i] = reg.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "Top");
            }
            else if(i==2)
            {
                this.icons[i] = reg.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "Front");
            }
            else
            {
                this.icons[i] = reg.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "Side");
            }


        }
        System.out.println(Arrays.toString(this.icons));
    }

    @Override
    public IIcon getIcon(int side, int metadata)
    {
        //System.out.println("getIcon called, side "+side);
        //System.out.println(Arrays.toString(this.icons));
        return this.icons[side];
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityCloudSeeder();
    }


    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int size, float hitx, float hity, float hitz)
    {
        if(!world.isRemote)
        {
            FMLNetworkHandler.openGui(player, MCStuff.instance, 0, world, x, y, z);
        }
        return true;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
    {
        TileEntity te=world.getTileEntity(x, y, z);
        if(te!=null && te instanceof TileEntityCloudSeeder)
        {
            IInventory inventory = (IInventory)te;
            for (int i=0; i<inventory.getSizeInventory(); i++)
            {
                ItemStack stack = inventory.getStackInSlotOnClosing(i);
                if (stack != null)
                {
                    float spawnX = x+world.rand.nextFloat();
                    float spawnY = y+world.rand.nextFloat();
                    float spawnZ = z+world.rand.nextFloat();

                    EntityItem drop=new EntityItem(world, spawnX, spawnY, spawnZ, stack);

                    double mult = 0.05;

                    drop.motionX = (-0.5F + world.rand.nextFloat())*mult;
                    drop.motionY = (4 + world.rand.nextFloat())*mult;
                    drop.motionZ = (-0.5F + world.rand.nextFloat())*mult;

                    world.spawnEntityInWorld(drop);
                }
            }
        }
        super.breakBlock(world, x, y, z, block, metadata);
    }


    @Override
    public void onEntityWalking(World world, int x, int y, int z, Entity entity)
    {
        if(!world.isRemote)
        {
            TileEntity te=world.getTileEntity(x,y,z);
            if(te!=null && te instanceof TileEntityCloudSeeder)
            addRain(world, (IInventory)te);
        }
    }

    public void removeRain(World world, IInventory inventory)
    {

        if(world.getWorldInfo().isRaining())
        {
            ItemStack stack = inventory.getStackInSlot(0);
            if(stack!=null && stack.isItemEqual(new ItemStack(ModItems.saltCartridge)))
            {
                inventory.decrStackSize(0, 1);
                world.getWorldInfo().setRainTime(0);
                world.getWorldInfo().setRaining(false);
            }
        }
    }

    public void addRain(World world, IInventory inventory)
    {
        if(!world.getWorldInfo().isRaining())
        {
            ItemStack stack = inventory.getStackInSlot(0);
            if(stack!=null && stack.isItemEqual(new ItemStack(ModItems.saltCartridge)))
            {
                inventory.decrStackSize(0, 1);
                world.getWorldInfo().setRainTime(600);
                world.getWorldInfo().setRaining(true);
            }
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        if(!world.isRemote && world.isBlockIndirectlyGettingPowered(x, y, z))
        {
            TileEntity te=world.getTileEntity(x,y,z);
            if(te!=null && te instanceof TileEntityCloudSeeder)
            removeRain(world, (IInventory)te);
        }
    }

}
