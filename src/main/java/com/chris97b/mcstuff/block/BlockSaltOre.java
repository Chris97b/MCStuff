package com.chris97b.mcstuff.block;

import com.chris97b.mcstuff.MCStuff;
import com.chris97b.mcstuff.init.ModItems;
import net.minecraft.item.Item;

import java.util.Random;

/**
 * Created by Chris on 2/9/2015.
 */
public class BlockSaltOre extends BlockMCStuff
{
    public BlockSaltOre()
    {
        super();
        //System.out.println("setting Salt Ore unlocalized name...");
        this.setBlockName("saltOre");
        this.setHardness(5F);
        setHarvestLevel("pickaxe", 1);
    }

    @Override
    public Item getItemDropped(int metadata, Random random, int fortune)
    {
        return ModItems.saltPile;
    }

    @Override
    public int quantityDropped(int metadata, int fortune, Random random)
    {
        return 4;
    }

}
