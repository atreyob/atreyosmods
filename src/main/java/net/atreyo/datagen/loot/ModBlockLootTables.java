package net.atreyo.datagen.loot;

import net.atreyo.atommod.blocks.ModBlocks;
import net.atreyo.atommod.blocks.OchreBlocks;
import net.atreyo.atommod.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
     public ModBlockLootTables(){
         super(Set.of(), FeatureFlags.REGISTRY.allFlags());
     }

    /**
     *
     */
    @Override
    protected void generate() {
        this.dropOther(ModBlocks.STEEL_BLOCKS.get(), Items.IRON_INGOT);
        this.dropWhenSilkTouch(ModBlocks.STEEL_BLOCKS.get());
        this.dropSelf(ModBlocks.OCHRE_BLOCKS.get());
        

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return super.getKnownBlocks();
        
    }
}
