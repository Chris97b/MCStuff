package com.chris97b.mcstuff.item;

import net.minecraft.world.World;

/**
 * Created by Chris on 2/9/2015.
 */
public class ItemEmptyCartridge extends ItemMCStuff
{
    public ItemEmptyCartridge()
    {
        super();
        this.setUnlocalizedName("emptyCartridge");
    }

    public boolean runCartridgeLogic(World world){
        return false;
    }

}
