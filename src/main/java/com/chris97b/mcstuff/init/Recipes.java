package com.chris97b.mcstuff.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * Created by Chris on 2/9/2015.
 */
public class Recipes
{
    public static void init()
    {
        //GameRegistry.addRecipe(new ItemStack(ModItems.emptyCartridge), " i ", "i i", " i ", 'i', new ItemStack(Items.iron_ingot));
        //GameRegistry.addRecipe(new ItemStack(ModItems.saltCartridge), new ItemStack(ModItems.emptyCartridge), new ItemStack(ModItems.saltPile));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emptyCartridge), " i ", "i i", " i ", 'i', "ingotIron"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.saltCartridge), new ItemStack(ModItems.emptyCartridge), "oreSalt"));
    }
}
