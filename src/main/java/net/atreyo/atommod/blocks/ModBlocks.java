package net.atreyo.atommod.blocks;

import net.atreyo.atommod.atommod;

import net.atreyo.atommod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;


public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
    DeferredRegister.create(ForgeRegistries.BLOCKS,atommod.MOD_ID);
    public static final RegistryObject<Block> LUCKY_BLOCKS = registerBlock("lucky_blocks", () -> new LuckyBlocks(BlockBehaviour.Properties.copy(Blocks.DIRT)));
    public static final RegistryObject<Block> OCHRE_BLOCKS = registerBlock("ochre_blocks", () -> new OchreBlocks(BlockBehaviour.Properties.copy(Blocks.BONE_BLOCK)));
    public static final RegistryObject<Block> STEEL_BLOCKS = registerBlock("steel_blocks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(SoundType.COPPER)));
    public static final RegistryObject<Block> COW_BELL = registerBlock("cow_bell", () -> new CowBell(BlockBehaviour.Properties.copy(Blocks.JUKEBOX)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name,toReturn);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<BlockItem> registerBlockItem(String name , Supplier<T> block){

        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
