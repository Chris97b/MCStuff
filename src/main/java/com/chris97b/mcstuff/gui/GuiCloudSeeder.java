package com.chris97b.mcstuff.gui;

import com.chris97b.mcstuff.MCStuff;
import com.chris97b.mcstuff.network.CloudSeederPacket;
import com.chris97b.mcstuff.tileentities.TileEntityCloudSeeder;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
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
        this.te = te;

    }

    private static final ResourceLocation texture = new ResourceLocation("mcstuff", "textures/gui/guiCloudSeeder.png");
    private static final String AUTO_TEXT = "Automatic";
    private static final String MANUAL_TEXT = "Manual";
    private TileEntityCloudSeeder te;

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y)
    {
        GL11.glColor4f(1,1,1,1);
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        fontRendererObj.drawString("Cloud Seeder", 8, 6, 0x404040);
    }

    @Override
    public void initGui()
    {
        super.initGui();
        buttonList.clear();
        if(!te.isAutoMode())
        {
            buttonList.add(new GuiButton(0, guiLeft + 100, guiTop + 14, 60, 20, MANUAL_TEXT));
        }
        else
        {
            buttonList.add(new GuiButton(0, guiLeft + 100, guiTop + 14, 60, 20, AUTO_TEXT));
        }
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        if(button.id==0)
        {
            System.out.println("Auto button clicked");
            MCStuff.NetworkChannel.sendToServer(new CloudSeederPacket("Auto button clicked by player"));
            button.displayString = button.displayString.equals(AUTO_TEXT) ? MANUAL_TEXT : AUTO_TEXT;
        }
    }
}
