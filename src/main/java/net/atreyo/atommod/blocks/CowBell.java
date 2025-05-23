package net.atreyo.atommod.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class CowBell extends Block {
    public CowBell(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(!pLevel.isClientSide() && pHand == InteractionHand.MAIN_HAND){
            pLevel.playSound(null,pPos, SoundEvents.NOTE_BLOCK_COW_BELL.get(), SoundSource.BLOCKS,1f,1f);
            return InteractionResult.SUCCESS;
        }
        else{
            pLevel.playSound(null,pPos, SoundEvents.NOTE_BLOCK_COW_BELL.get(), SoundSource.BLOCKS,1f,1f);
            return InteractionResult.CONSUME;

        }
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        pLevel.playSound( pEntity,pPos,SoundEvents.NOTE_BLOCK_COW_BELL.get(),SoundSource.BLOCKS,1f,1f);
        super.stepOn(pLevel, pPos, pState, pEntity);
    }
}
