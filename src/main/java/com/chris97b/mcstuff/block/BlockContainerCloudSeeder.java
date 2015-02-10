package com.chris97b.mcstuff.block;

import com.chris97b.mcstuff.MCStuff;
import com.chris97b.mcstuff.tileentities.TileEntityCloudSeeder;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
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

}
