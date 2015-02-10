package com.chris97b.mcstuff.init;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

/**
 * Created by Chris on 2/9/2015.
 */
public class Oregen implements IWorldGenerator
{

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        if(world.provider.dimensionId==0)
        {
            generateSurface(world, random, chunkX*16, chunkZ*16);
        }
    }

    private void generateSurface(World world, Random random, int x, int z)
    {
        this.addOreSpawn(ModBlocks.saltOre, world, random, x, z, 16, 80, 16, 4+random.nextInt(3), 15, 15, 50);
    }

    public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxY, int maxZ, int maxVeinSize, int chanceToSpawn, int minY, int minZ)
    {
        int diff=maxY-minY;
        for(int i=0; i<chanceToSpawn; i++)
        {
            int posX=blockXPos + random.nextInt(maxX);
            int posY=minY + random.nextInt(diff);
            int posZ=blockZPos + random.nextInt(maxZ);
            new WorldGenMinable(block, maxVeinSize).generate(world, random, posX, posY, posZ);
        }
    }


}
