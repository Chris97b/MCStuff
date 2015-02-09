package com.chris97b.mcstuff.block;


import com.chris97b.mcstuff.MCStuff;
import com.chris97b.mcstuff.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;

/**
 * Created by Chris on 2/9/2015.
 */
public class BlockMCStuff extends Block
{

    public BlockMCStuff(Material material)
    {
        super(material);
        this.setCreativeTab(MCStuff.MCSCreativeTab);
    }
    public BlockMCStuff()
    {
        this(Material.rock);
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
