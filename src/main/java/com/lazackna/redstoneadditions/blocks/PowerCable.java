package com.lazackna.redstoneadditions.blocks;

import com.lazackna.redstoneadditions.blocks.entity.TileGenerator;
import com.lazackna.redstoneadditions.setup.TileEntityInit;
import com.lazackna.redstoneadditions.util.CableTier;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PowerCable extends Block implements ITileEntityProvider {

    private CableTier cableTier;
    public List<IEnergyStorage> energyNeighbours = new ArrayList<>();
    public PowerCable(Properties p_i48440_1_, CableTier cableTier) {
        super(p_i48440_1_);
        //p_i48440_1_.randomTicks()
        this.cableTier = cableTier;
    }

    @Override
    public void tick(BlockState p_225534_1_, ServerWorld p_225534_2_, BlockPos p_225534_3_, Random p_225534_4_) {
        super.tick(p_225534_1_, p_225534_2_, p_225534_3_, p_225534_4_);

    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityInit.CABLE.get().create();
    }

    @Nullable
    @Override
    public TileEntity newBlockEntity(IBlockReader p_196283_1_) {
        return new TileGenerator(cableTier);
    }
}
