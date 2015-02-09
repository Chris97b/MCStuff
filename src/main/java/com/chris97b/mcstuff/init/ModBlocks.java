package com.chris97b.mcstuff.init;

import com.chris97b.mcstuff.block.BlockMCStuff;
import com.chris97b.mcstuff.block.BlockSaltOre;
import com.chris97b.mcstuff.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Chris on 2/9/2015.
 */

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
    public static final BlockMCStuff saltOre= new BlockSaltOre();


    public static void init()
    {
        GameRegistry.registerBlock(saltOre, "saltOre");

    }

}
