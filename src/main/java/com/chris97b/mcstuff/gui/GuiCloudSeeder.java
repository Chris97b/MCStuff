package com.chris97b.mcstuff.gui;

import com.chris97b.mcstuff.tileentities.TileEntityCloudSeeder;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Chris on 2/10/2015.
 */

@SideOnly(Side.CLIENT)
public class GuiCloudSeeder extends GuiContainer
{
    public GuiCloudSeeder(InventoryPlayer invPlayer, TileEntityCloudSeeder te)
    {
        super(new ContainerCloudSeeder(invPlayer, te));
        xSize=175;
        ySize=165;
    }

    private static final ResourceLocation texture = new ResourceLocation("mcstuff", "textures/gui/guiCloudSeeder.png");

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y)
    {
        GL11.glColor4f(1,1,1,1);
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

}
