package com.chris97b.mcstuff.network;


import com.chris97b.mcstuff.gui.ContainerCloudSeeder;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;

/**
 * Created by Chris on 2/11/2015.
 */
public class CloudSeederPacket implements IMessage

{
    public String text;

    public CloudSeederPacket()
    {

    }

    public CloudSeederPacket(String text)
    {
        this.text=text;
    }


    @Override
    public void fromBytes(ByteBuf buf)
    {
        text= ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeUTF8String(buf, text);
    }

    public static class Handler implements IMessageHandler<CloudSeederPacket, IMessage>
    {
        @Override
        public IMessage onMessage(CloudSeederPacket message, MessageContext ctx)
        {
            //System.out.println(String.format("Received %s from %s", message.text, ctx.getServerHandler().playerEntity.getDisplayName()));
            Container container = ctx.getServerHandler().playerEntity.openContainer;
            if(container != null && container instanceof ContainerCloudSeeder)
            {
                //Flip modes
                ((ContainerCloudSeeder) container).getTE().toggleAuto();
                World w = ((ContainerCloudSeeder) container).getTE().getWorldObj();
                int xcoord = ((ContainerCloudSeeder) container).getTE().xCoord;
                int ycoord = ((ContainerCloudSeeder) container).getTE().yCoord;
                int zcoord = ((ContainerCloudSeeder) container).getTE().zCoord;
                w.markBlockForUpdate(xcoord,ycoord,zcoord);
            }
            return null;

        }
    }
}


