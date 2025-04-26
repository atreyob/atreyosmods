package net.atreyo.atommod.blocks;

import net.atreyo.atommod.atommod;
import net.atreyo.atommod.item.ModItems;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Zoglin;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class LuckyBlocks extends Block {
    public LuckyBlocks(Properties pProperties) {
        super(pProperties);
    }

    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!(level instanceof ServerLevel serverLevel)) return;

        Random rand = new Random();
        int outcome = rand.nextInt(5);

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
                enum colors {
                    red,
                    white,
                    orange,


                }
                for (int j = 0; j <= dist; j++) {
                    level.setBlock(pos.above(j), Blocks.RED_GLAZED_TERRACOTTA.defaultBlockState(), 3);
                    if (j == dist) {
                        level.setBlock(pos.above(j), Blocks.DIAMOND_BLOCK.defaultBlockState(), 3);
                        LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
                        bolt.setPos(player.getX(), player.getY(), player.getZ());
                    }
                }


            }

            case 1 -> {
                player.spawnAtLocation(new ItemStack(Items.GOLD_BLOCK, 30));
                Zombie zombie = new Zombie(serverLevel);
                zombie.setCanBreakDoors(true);
                zombie.canHoldItem(new ItemStack(Items.DIAMOND_SWORD));
                zombie.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 99999, 1));
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

            case 3 -> {
                BlockPos basePos = pos.below(); // Build 1 block below the lucky block
                int size = 5; // 5 in each direction = 10x10
                int height = 12;
                int length = 15;
                int width = 14;
                Random random = new Random();
//Generate ground floor, walls, and lever puzzle
                for (int dx = -width / 2; dx <= width / 2; dx++) {
                    for (int dz = -length / 2; dz <= length / 2; dz++) {
                        for (int dy = 0; dy < height; dy++) {
                            BlockPos target = basePos.offset(dx, dy, dz);

                            // Ground floor (dy == 0) and lever placements
                            if (dy == 0) {
                                BlockState brick = switch (rand.nextInt(10)) {
                                    case 0, 1 -> Blocks.MOSSY_STONE_BRICKS.defaultBlockState();
                                    case 2 -> Blocks.CHISELED_STONE_BRICKS.defaultBlockState();
                                    default -> Blocks.STONE_BRICKS.defaultBlockState();
                                };
                                level.setBlock(target, brick, 3);

                                // Place levers on the south wall
                                if (dz == length / 2 && Math.abs(dx) <= 1 && dy == 0) {
                                    level.setBlock(target, Blocks.LEVER.defaultBlockState(), 3);
                                }
                            }

                            // Walls (dy between 1 and height-1)
                            else if (dy < height - 1 && (dx == -width / 2 || dx == width / 2 || dz == -length / 2 || dz == length / 2)) {
                                if (dz == length / 2 && Math.abs(dx) <= 1 && dy < 3) continue; // Leave entrance gap

                                BlockState wall = switch (rand.nextInt(10)) {
                                    case 0, 1 -> Blocks.MOSSY_STONE_BRICKS.defaultBlockState();
                                    case 2 -> Blocks.CHISELED_STONE_BRICKS.defaultBlockState();
                                    default -> Blocks.STONE_BRICKS.defaultBlockState();
                                };
                                level.setBlock(target, wall, 3);
                            }

                            // Ceiling (dy == height - 1)
                            else if (dy == height - 1) {
                                BlockState top = Blocks.STONE_BRICK_SLAB.defaultBlockState();
                                level.setBlock(target, top, 3);
                            }
                        }
                    }
                }

                // Middle floor with missing floor block and trap chest
                BlockPos chestPos = basePos.offset(0, height - 3, 0);  // Chest one block above the middle floor
                BlockPos tntPos = basePos.offset(0, height - 4, 0);    // TNT below the chest
                level.setBlock(tntPos, Blocks.TNT.defaultBlockState(), 3); // TNT under the chest
                level.setBlock(chestPos, Blocks.TRAPPED_CHEST.defaultBlockState(), 3); // Trap chest

                if (level.getBlockEntity(chestPos) instanceof ChestBlockEntity chest) {
                    chest.setItem(0, new ItemStack(Items.TNT, 3));  // Add TNT to chest
                    chest.setItem(1, new ItemStack(Items.EMERALD, 1)); // Add emerald as loot
                    chest.setItem(2, new ItemStack(Items.GOLD_INGOT, 1)); // Add gold ingot as loot
                }

                // Generate loot chests and barrels randomly inside the structure
                int lootCount = 2 + rand.nextInt(3); // Randomize 2 to 4 chests/barrels
                for (int i = 0; i < lootCount; i++) {
                    int x = basePos.getX() + rand.nextInt(10) - 5;
                    int z = basePos.getZ() + rand.nextInt(10) - 5;
                    BlockPos lootPos = new BlockPos(x, basePos.getY() + height - 3, z);

                    if (level.getBlockState(lootPos).isAir()) {
                        if (rand.nextBoolean()) {
                            level.setBlock(lootPos, Blocks.CHEST.defaultBlockState(), 3);
                            if (level.getBlockEntity(lootPos) instanceof ChestBlockEntity lootChest) {
                                lootChest.setLootTable(BuiltInLootTables.ANCIENT_CITY, random.nextLong());
                            }
                        } else {
                            level.setBlock(lootPos, Blocks.BARREL.defaultBlockState(), 3);
                            if (level.getBlockEntity(lootPos) instanceof BarrelBlockEntity barrel) {
                                barrel.setLootTable(BuiltInLootTables.FARMER_GIFT, random.nextLong());

                            }
                        }
                    }
                }


            }
            case 4 -> {
                Random dx = new Random();
                int dex = dx.nextInt(-15,30);
                Random dy = new Random();
                int dey = dx.nextInt(-15,30);
                Random dz = new Random();
                int dez = dx.nextInt(-15,30);
                Vec3 playerpos = new Vec3(player.getBlockX() +dex,player.getBlockY() + dey
                        ,player.getBlockZ() + dez);

                player.move(MoverType.PISTON,playerpos);
                player.giveExperienceLevels(399);
                player.giveExperiencePoints(20);
                player.causeFoodExhaustion(20);
                // how to find damage soruces
          //      player.causeFallDamage(2,20, new DamageSource(DamageSou));

            }
        }


    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        if(Screen.hasShiftDown()){
            pTooltip.add(Component.translatable("tooltip.atomod.lucky_blocks"));

        } else {
            pTooltip.add(Component.translatable("tooltip.atomod.lucky_blocks.shift"));

        }
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }
}