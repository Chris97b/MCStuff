package com.chris97b.mcstuff.creativetab;

import com.chris97b.mcstuff.init.ModItems;
import com.chris97b.mcstuff.reference.Reference;
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

    /*@Override
    public String getTranslatedTabLabel()
    {
        return "MC Stuff";
    }*/


}
