package net.atreyo.datagen;

import net.atreyo.atommod.atommod;
import net.atreyo.atommod.item.CustomItem;
import net.atreyo.atommod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider  extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    /**
     *
     */
    @Override
    protected void registerModels() {
        simpleItem(ModItems.CustomItem);
        simpleItem(ModItems.KronosScythe);


    }
    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture
                ("layer0", new ResourceLocation(atommod.MOD_ID , "item" + item.getId().getPath()));
    }
}
