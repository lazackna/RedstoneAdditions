package com.lazackna.redstoneadditions.blocks.entity;

import com.lazackna.redstoneadditions.setup.TileEntityInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.FurnaceTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.Objects;

public class ProximitySensorTileEntity extends TileEntity implements ITickableTileEntity {

    private AxisAlignedBB box = AxisAlignedBB.ofSize(10,10,10);

    private boolean init = false;

    public ProximitySensorTileEntity() {
        super(TileEntityInit.PROXIMITY_SENSOR.get());
    }

    @Override
    public void tick() {

        if(!init) {
            box = box.move(getBlockPos());
            init = true;
        }
        List<LivingEntity> entities = Objects.requireNonNull(getLevel()).getEntitiesOfClass(LivingEntity.class, box);

        if(entities.size() > 0) {

            getLevel().setBlock(getBlockPos(), getBlockState().setValue(BlockStateProperties.POWERED, true), 3);
        } else {
            getLevel().setBlock(getBlockPos(), getBlockState().setValue(BlockStateProperties.POWERED, false), 3);
        }
    }
}
