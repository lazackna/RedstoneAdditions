package com.lazackna.redstoneadditions.blocks;

import com.lazackna.redstoneadditions.util.CableTier;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class RedstonePowerCable extends PowerCable{

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    private boolean isPowered = false;
    List<BlockPos> poweredNeighbours = new ArrayList<>();
    public RedstonePowerCable(Properties p_i48440_1_, CableTier cableTier) {
        super(p_i48440_1_, cableTier);
    }

    @Override
    public boolean isSignalSource(BlockState p_149744_1_) {
        return false;
    }

    @Override
    public void neighborChanged(BlockState p_220069_1_, World p_220069_2_, BlockPos p_220069_3_, Block p_220069_4_, BlockPos p_220069_5_, boolean p_220069_6_) {
        super.neighborChanged(p_220069_1_, p_220069_2_, p_220069_3_, p_220069_4_, p_220069_5_, p_220069_6_);
        if(!p_220069_1_.getValue(POWERED)){
            poweredNeighbours.remove(p_220069_3_);
            return;
        }
        p_220069_2_.setBlock(p_220069_3_, p_220069_1_.setValue(POWERED, true), 3);
        isPowered = true;
        poweredNeighbours.add(p_220069_3_);
    }
}
