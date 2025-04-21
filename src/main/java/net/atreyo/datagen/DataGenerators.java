package net.atreyo.datagen;

import net.atreyo.atommod.atommod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = atommod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper helper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        generator.addProvider(event.includeServer(), new ModRecipeProvider(output));
        generator.addProvider(event.includeServer(),ModLootTableProvider.create(output));
        generator.addProvider(event.includeServer() , new ModBlockTagGenerator(output,lookupProvider,atommod.MOD_ID,helper));
        generator.addProvider(event.includeClient(), new ModItemModelProvider(output,atommod.MOD_ID,helper));
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(output,helper));
    }
}
