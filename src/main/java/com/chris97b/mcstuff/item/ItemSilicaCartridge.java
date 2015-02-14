package com.chris97b.mcstuff.item;

import com.chris97b.mcstuff.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by R3liant on 2/13/2015.
 */
public class ItemSilicaCartridge extends ItemEmptyCartridge{
    public ItemSilicaCartridge()
    {
        super();
        this.setUnlocalizedName("silicaCartridge");

    }

    @Override
    public boolean runCartridgeLogic(World world) {
        if(world.getWorldInfo().isRaining())
        {
            world.getWorldInfo().setRainTime(0);
            world.getWorldInfo().setRaining(false);
            return true;
        }
        return false;
    }
}
