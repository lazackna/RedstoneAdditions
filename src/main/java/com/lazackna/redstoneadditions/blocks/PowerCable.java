package com.lazackna.redstoneadditions.blocks;

import com.lazackna.redstoneadditions.blocks.entity.PowerCableTileEntity;
import com.lazackna.redstoneadditions.blocks.entity.ProximitySensorTileEntity;
import com.lazackna.redstoneadditions.setup.TileEntityInit;
import com.lazackna.redstoneadditions.util.CableTier;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class PowerCable extends Block implements ITileEntityProvider {

    private CableTier cableTier;
    public List<IEnergyStorage> energyNeighbours = new ArrayList<>();
    public PowerCable(Properties p_i48440_1_, CableTier cableTier) {
        super(p_i48440_1_);
        this.cableTier = cableTier;
    }



    @Override
    public void neighborChanged(BlockState p_220069_1_, World p_220069_2_, BlockPos p_220069_3_, Block p_220069_4_, BlockPos p_220069_5_, boolean p_220069_6_) {
        super.neighborChanged(p_220069_1_, p_220069_2_, p_220069_3_, p_220069_4_, p_220069_5_, p_220069_6_);

        if(p_220069_4_ instanceof IEnergyStorage) {
            IEnergyStorage storage = (IEnergyStorage) p_220069_4_;
            energyNeighbours.add(storage);
        }
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityInit.PROXIMITY_SENSOR.get().create();
    }

    @Nullable
    @Override
    public TileEntity newBlockEntity(IBlockReader p_196283_1_) {
        return new PowerCableTileEntity(cableTier);
    }
}
