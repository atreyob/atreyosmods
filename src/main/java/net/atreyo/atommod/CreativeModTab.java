package net.atreyo.atommod;

import net.atreyo.atommod.blocks.ModBlocks;
import net.atreyo.atommod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeModTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, atommod.MOD_ID);
    public static final RegistryObject<CreativeModeTab> ATO_TAB = CREATIVE_MODE_TABS.register("ato_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.KronosScythe.get()))
                    .title(Component.translatable("creativetab.ato_tab"))
                    .displayItems((displayParameters, output) -> {
                        output.accept(ModBlocks.STEEL_BLOCKS.get());
                        output.accept(ModItems.KronosScythe.get());
                        output.accept(ModItems.CustomItem.get());
                    }).build());
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
        atommod.LOGGER.atError();
        atommod.LOGGER.atWarn();
        atommod.LOGGER.atTrace();


    }
}