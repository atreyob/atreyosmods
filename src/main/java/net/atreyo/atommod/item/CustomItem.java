package net.atreyo.atommod.item;

import com.mojang.blaze3d.shaders.Effect;
import net.atreyo.atommod.atommod;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.WorldlyContainerHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import net.minecraft.world.*;
import java.util.List;
import java.util.Properties;

import static net.minecraft.world.effect.MobEffects.MOVEMENT_SLOWDOWN;

public class CustomItem extends Item {


    public CustomItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        if (!context.getLevel().isClientSide && context.getPlayer() instanceof ServerPlayer) {
            // Sending system message to player
            context.getPlayer().sendSystemMessage(Component.literal("Well, it seems the gun is working"));
            atommod.LOGGER.info("Well");
            // Adding gold block to player's inventory
            ItemStack goldstack = new ItemStack(Items.GOLD_BLOCK, 50);
            boolean added = context.getPlayer().getInventory().add(goldstack);

            // Apply slow effect to the player
            MobEffect slowEffect = MOVEMENT_SLOWDOWN;
            MobEffectInstance effectInstance = new MobEffectInstance(slowEffect, 1000, 3, true, false);
            context.getPlayer().addEffect(effectInstance);
            atommod.LOGGER.info("Well");

            // If the gold stack isn't added, drop it
            if (!added) {
                context.getPlayer().drop(goldstack, false);
            }
        }

        return InteractionResult.SUCCESS; // Return success for the item use
    }

  //  @Override
    public InteractionResult useOn(Level world, Player player, InteractionHand hand , UseOnContext context) {
        // Firing the grenade when used
        if (!world.isClientSide) {
            context.getPlayer().sendSystemMessage(Component.literal("Well, it seems the grenade is thrown"));
            atommod.LOGGER.info("Well");

            use(world, player , hand);
            return InteractionResult.CONSUME; // Consuming the item use after firing grenade
        }
        return InteractionResult.PASS; // If it's client-side, pass
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand pUsedHand) {
        Vec3 dir = player.getLookAngle();
        // Calculate spawn position a bit in front of the player
        player.sendSystemMessage(Component.literal("Well, it seems the grenade is working"));
        atommod.LOGGER.info("Well");

        // Create the explosion
        Explosion explosion = new Explosion(level, player, player.getX(), player.getX(), player.getZ(), 10, true, Explosion.BlockInteraction.DESTROY);

        explosion.explode();
        explosion.finalizeExplosion(false); // Finish explosion without causing block drops

        // Create the "Pig" entity (for testing purposes, you can use any projectile or entity)
        Pig pigEntity = new Pig(EntityType.PIG, level);
        pigEntity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING,999999));
        pigEntity.setPos(player.getX(), player.getY(), player.getZ());
        pigEntity.setDeltaMovement(dir.scale(2.0)); // Set the direction of the pig (this could be a projectile)
        atommod.LOGGER.info("Well");

        // Add the entity to the world
        level.addFreshEntity(pigEntity);
        //Remembet to account for each and single key
        return super.use(level, player, pUsedHand);
    }

    // This method is where you actually create and spawn the grenade's explosion
    public void useGrenade(Level world, Player player , UseOnContext context) {
        Vec3 dir = player.getLookAngle();
        // Calculate spawn position a bit in front of the player

        context.getPlayer().sendSystemMessage(Component.literal("Well, it seems the grenade is working"));
        atommod.LOGGER.info("Well");

        // Create the explosion
        Explosion explosion = new Explosion(world, player, player.getX(), player.getX(), player.getZ(), 10, true, Explosion.BlockInteraction.DESTROY);
        explosion.explode();
        explosion.finalizeExplosion(false); // Finish explosion without causing block drops

        // Create the "Pig" entity (for testing purposes, you can use any projectile or entity)
        Pig pigEntity = new Pig(EntityType.PIG, world);
        pigEntity.setPos(player.getX(), player.getY(), player.getZ());
        pigEntity.setDeltaMovement(dir.scale(2.0)); // Set the direction of the pig (this could be a projectile)
        atommod.LOGGER.info("Well");

        // Add the entity to the world
        world.addFreshEntity(pigEntity);
        //Remembet to account for each and single key
    }

    // Register item to the event bus

}
