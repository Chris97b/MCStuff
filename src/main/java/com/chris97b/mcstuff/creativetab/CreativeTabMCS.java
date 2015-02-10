package com.chris97b.mcstuff.creativetab;

import com.chris97b.mcstuff.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by Chris on 2/9/2015.
 */
public class CreativeTabMCS extends CreativeTabs
{

    public CreativeTabMCS(String unlocalizedName)
    {
        super(unlocalizedName);
    }

    @Override
    public Item getTabIconItem()
    {
        return ModItems.saltCartridge;
    }



}
