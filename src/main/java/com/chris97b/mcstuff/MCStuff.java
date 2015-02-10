package com.chris97b.mcstuff;

import com.chris97b.mcstuff.creativetab.CreativeTabMCS;
import com.chris97b.mcstuff.gui.GuiHandler;
import com.chris97b.mcstuff.init.ModBlocks;
import com.chris97b.mcstuff.init.ModItems;
import com.chris97b.mcstuff.init.Oregen;
import com.chris97b.mcstuff.init.Recipes;
import com.chris97b.mcstuff.proxy.IProxy;
import com.chris97b.mcstuff.reference.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Chris on 2/9/2015.
 */

@Mod(modid= Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.VERSION)
public class MCStuff
{
    @Mod.Instance(Reference.MOD_ID)
    public static MCStuff instance;

    public static CreativeTabMCS MCSCreativeTab = new CreativeTabMCS(Reference.MOD_ID.toLowerCase());

    @SidedProxy(clientSide="com.chris97b.mcstuff.proxy.ClientProxy", serverSide="com.chris97b.mcstuff.proxy.ServerProxy")
    public static IProxy proxy;

    Oregen MCSOreGen = new Oregen();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ModItems.init();
        ModBlocks.init();
        ModItems.registerOre();
        GameRegistry.registerWorldGenerator(MCSOreGen, 10);

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        Recipes.init();
        new GuiHandler();
        //Oregen.generate();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }


}
