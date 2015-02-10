package com.chris97b.mcstuff.gui;

import com.chris97b.mcstuff.MCStuff;
import com.chris97b.mcstuff.tileentities.TileEntityCloudSeeder;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Chris on 2/10/2015.
 */
public class GuiHandler implements IGuiHandler
{

    public GuiHandler()
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(MCStuff.instance, this);
    }


    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch(ID)
        {
            case 0:
                TileEntity te = world.getTileEntity(x, y, z);
                if(te!=null && te instanceof TileEntityCloudSeeder)
                {
                    return new ContainerCloudSeeder(player.inventory, (TileEntityCloudSeeder)te);
                }
                break;
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch(ID)
        {
            case 0:
                TileEntity te = world.getTileEntity(x,y,z);
                if(te!=null && te instanceof TileEntityCloudSeeder)
                {
                    return new GuiCloudSeeder(player.inventory, (TileEntityCloudSeeder)te);
                }
                break;
        }
        return null;
    }
}
