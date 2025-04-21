package net.atreyo.atommod.blocks;

import net.atreyo.atommod.atommod;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class OchreBlocks extends Block {
    public OchreBlocks(Properties p){
        super(p);
    }



    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        //Called on right click
        System.out.println("Hello");
        if(!pLevel.isClientSide() && pHand == InteractionHand.MAIN_HAND){
            pLevel.createFireworks(pPlayer.getBlockX(),pPlayer.getBlockY(),pPlayer.getBlockZ(),10,20,30,null);
            pLevel.playLocalSound(pPos, SoundEvents.AMETHYST_BLOCK_RESONATE, SoundSource.BLOCKS,10,1,false);
            return InteractionResult.CONSUME;
        }
        else{
            return InteractionResult.CONSUME;
        }
    }





}
