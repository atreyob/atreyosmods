package net.atreyo.atommod.item;

import net.atreyo.atommod.atommod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, atommod.MOD_ID);
    public static final RegistryObject<Item> KronosScythe = ITEMS.register("kronosscythe", () -> new Item(new Item.Properties().durability(300)));
    public static final RegistryObject<Item> CustomItem = ITEMS.register("customitem", () -> new CustomItem(new Item.Properties().durability(300)));
    public static final RegistryObject<Item> ANISE = ITEMS.register("anise",() -> new Item(new Item.Properties().food(ModFoodProperties.ANISE)));
    public static final RegistryObject<Item> DRYWOOD = ITEMS.register("drywood",() -> new CombustiblesItem(new Item.Properties(), 399));
    public static void register(IEventBus bus){
        ITEMS.register(bus);
    }
}
