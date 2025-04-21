package net.atreyo.datagen;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    /**
     * @param consumer
     */
    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        // Recipe for Golden Apple
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, Items.GOLDEN_APPLE)
                .pattern("GGG")
                .pattern("GAG")
                .pattern("GGG")
                .define('G', Blocks.GOLD_BLOCK)
                .define('A', Items.APPLE)
                .unlockedBy("has_apple", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ENCHANTED_GOLDEN_APPLE)).save(consumer); // Condition for unlocking
        // Example for Enchanted Golden Apple
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, Items.ENCHANTED_GOLDEN_APPLE)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Blocks.GOLD_BLOCK) // The input item is 'GOLD_INGOT'
                .define('B', Items.APPLE) // The middle item is 'APPLE'
                .unlockedBy("has_golden_apple", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLDEN_APPLE)).save(consumer);// Unlock when Golden Apple is in inventory
        nineBlockStorageRecipes(consumer,RecipeCategory.FOOD ,Items.APPLE, RecipeCategory.FOOD, Items.GOLDEN_APPLE);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.ROTTEN_FLESH), RecipeCategory.FOOD ,Items.LEATHER, 0.7f,300);
   }
}
