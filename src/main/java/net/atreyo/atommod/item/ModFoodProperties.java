package net.atreyo.atommod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;

public class ModFoodProperties {
    //        ENCHANTED_GOLDEN_APPLE = (new FoodProperties.Builder()).nutrition(4).saturationMod(1.2F).effect(new MobEffectInstance(MobEffects.REGENERATION, 400, 1), 1.0F).effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 6000, 0), 1.0F).effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0), 1.0F).effect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 3), 1.0F).alwaysEat().build();
    public static final FoodProperties ANISE = new FoodProperties.Builder().nutrition(3).saturationMod(30f).effect(() ->new MobEffectInstance(MobEffects.REGENERATION,100,3),0.5f).effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,10),200).effect(new MobEffectInstance(MobEffects.HEALTH_BOOST,100,20),0.5f).build();
}
