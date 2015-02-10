package com.chris97b.mcstuff.block;

import com.chris97b.mcstuff.tileentities.TileEntityCloudSeeder;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Chris on 2/10/2015.
 */
public class BlockContainerCloudSeeder extends BlockContainerMCStuff
{
    BlockContainerCloudSeeder(Material material)
    {
        super(material);
    }


    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityCloudSeeder();
    }


}
