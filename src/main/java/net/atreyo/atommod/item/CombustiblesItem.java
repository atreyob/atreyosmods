package net.atreyo.atommod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;

public class CombustiblesItem extends Item {
    private int burnTime = 15;
    public CombustiblesItem(Properties pProperties,int burnTime){
        super(pProperties);
        this.burnTime = burnTime;

    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return this.burnTime;
    }
}
