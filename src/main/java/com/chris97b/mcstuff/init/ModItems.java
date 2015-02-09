package com.chris97b.mcstuff.init;

import com.chris97b.mcstuff.item.ItemEmptyCartridge;
import com.chris97b.mcstuff.item.ItemMCStuff;
import com.chris97b.mcstuff.item.ItemSaltCartridge;
import com.chris97b.mcstuff.item.ItemSaltPile;
import com.chris97b.mcstuff.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by Chris on 2/9/2015.
 */

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
    public static final ItemMCStuff saltCartridge = new ItemSaltCartridge();
    public static final ItemMCStuff emptyCartridge = new ItemEmptyCartridge();
    public static final ItemMCStuff saltPile = new ItemSaltPile();

    public static void init()
    {
        GameRegistry.registerItem(saltCartridge, "saltCartridge");
        GameRegistry.registerItem(emptyCartridge, "emptyCartridge");
        GameRegistry.registerItem(saltPile, "saltPile");

    }

    public static void registerOre()
    {
        OreDictionary.registerOre("oreSalt", new ItemStack(saltPile));

    }


}
