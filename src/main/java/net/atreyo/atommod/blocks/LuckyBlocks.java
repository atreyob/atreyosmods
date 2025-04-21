package net.atreyo.atommod.blocks;

import net.atreyo.atommod.atommod;
import net.atreyo.atommod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Zoglin;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;
import java.util.Random;
import java.util.function.Supplier;

public class LuckyBlocks extends Block {
    public LuckyBlocks(Properties pProperties) {
        super(pProperties);
    }







    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!(level instanceof ServerLevel serverLevel)) return;

        Random rand = new Random();
        int outcome = rand.nextInt(3);

        Player player = level.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 10, false);
        if (player == null) return;

        switch (outcome) {
            case 0 -> {
                level.setBlock(pos.below(), Blocks.DIAMOND_BLOCK.defaultBlockState(), 3);
                player.sendSystemMessage(Component.literal("You got lucky!"));
                atommod.LOGGER.info("Lucky block: Diamond block spawned");
                atommod.LOGGER.debug("helo");
                Random height = new Random();
                int dist = height.nextInt(10);
                enum colors{
                    red,
                    white,
                    orange,


                }
                for(int j = 0 ; j < dist ; j++) {
                    level.setBlock(pos.above(j), Blocks.RED_GLAZED_TERRACOTTA.defaultBlockState(), 3);
                    if(j == dist){
                        level.setBlock(pos.above(j), Blocks.DIAMOND_BLOCK.defaultBlockState(), 3);
                        LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
                        bolt.setPos(player.getX() , player.getY() , player.getZ());
                    }
                }


            }

            case 1 -> {
                player.spawnAtLocation(new ItemStack(Items.GOLD_BLOCK, 30));
                Zombie zombie = new Zombie(serverLevel);
                zombie.setCanBreakDoors(true);
                zombie.canHoldItem(new ItemStack(Items.DIAMOND_SWORD));
                zombie.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE , 99999,1));
                zombie.setPos(player.getX(), player.getY(), player.getZ());
                serverLevel.addFreshEntity(zombie);


                if (!player.getInventory().getItem(4).isEmpty()) {
                    player.getInventory().removeItem(player.getInventory().getItem(4));
                }
            }

            case 2 -> {
                player.spawnAtLocation(new ItemStack(Items.DIAMOND_BLOCK, 30));
                Zoglin zoglin = new Zoglin(EntityType.ZOGLIN, serverLevel);
                zoglin.setPos(player.getX(), player.getY(), player.getZ());
                serverLevel.addFreshEntity(zoglin);

                if (!player.getInventory().getItem(4).isEmpty()) {
                    player.getInventory().removeItem(player.getInventory().getItem(4));
                }
            }
}


    }}


