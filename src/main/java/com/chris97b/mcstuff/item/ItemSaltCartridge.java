package com.chris97b.mcstuff.item;


import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

/**
 * Created by Chris on 2/9/2015.
 */
public class ItemSaltCartridge extends ItemEmptyCartridge
{
    public ItemSaltCartridge()
    {
        super();
        this.setUnlocalizedName("saltCartridge");

    }

    @Override
    public boolean runCartridgeLogic(World world) {
        if(!world.getWorldInfo().isRaining())
        {
            world.getWorldInfo().setRainTime(600);
            world.getWorldInfo().setRaining(true);
            return true;
        }
        return false;
    }
}
