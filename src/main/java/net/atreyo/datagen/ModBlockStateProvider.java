package net.atreyo.datagen;

import net.atreyo.atommod.atommod;
import net.atreyo.atommod.blocks.LuckyBlocks;
import net.atreyo.atommod.blocks.ModBlocks;
import net.atreyo.atommod.blocks.OchreBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;

import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
           public ModBlockStateProvider(PackOutput output, ExistingFileHelper helper){
               super(output,atommod.MOD_ID, helper);
           }


    @Override
    protected void registerStatesAndModels() {
               blockWithItem(ModBlocks.LUCKY_BLOCKS);
               blockWithItem(ModBlocks.STEEL_BLOCKS);
               blockWithItem(ModBlocks.OCHRE_BLOCKS);

    }
    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
               simpleBlockWithItem(blockRegistryObject.get(),cubeAll(blockRegistryObject.get()));
    }
}
