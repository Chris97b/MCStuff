package com.chris97b.mcstuff.block;

import com.chris97b.mcstuff.MCStuff;
import com.chris97b.mcstuff.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Chris on 2/9/2015.
 */
public class BlockContainerMCStuff extends BlockContainer
{

    public BlockContainerMCStuff(Material material)
    {
        super(material);
        this.setCreativeTab(MCStuff.MCSCreativeTab);
    }

    public BlockContainerMCStuff()
    {
        this(Material.rock);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return null;
    }


    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase()+":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        //System.out.println("Registering Icon"+this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".")+1));
        blockIcon=iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".")+1));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".")+1);
    }
}
