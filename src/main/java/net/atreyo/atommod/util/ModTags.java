package net.atreyo.atommod.util;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class ModTags {
    public static final Tier nije = new Tier() {
        @Override
        public int getUses() {
            return 22;
        }

        @Override
        public float getSpeed() {
            return 33;
        }

        @Override
        public float getAttackDamageBonus() {
            return 22;
        }

        @Override
        public int getLevel() {
            return 11;
        }

        @Override
        public int getEnchantmentValue() {
            return 11;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return null;
        }
    };
}
