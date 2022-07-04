package com.lazackna.redstoneadditions.blocks.entity;

import com.lazackna.redstoneadditions.blocks.PowerCable;
import com.lazackna.redstoneadditions.setup.BlockInit;
import com.lazackna.redstoneadditions.setup.TileEntityInit;
import com.lazackna.redstoneadditions.util.CableTier;
import com.lazackna.redstoneadditions.util.DamageTracker;
import com.lazackna.redstoneadditions.util.IRestorableTileEntity;
import com.lazackna.redstoneadditions.util.MyEnergyStorage;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import sun.reflect.generics.reflectiveObjects.LazyReflectiveObjectGenerator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class TileGenerator extends TileEntity implements ITickableTileEntity, IRestorableTileEntity {

    private CableTier cableTier;
    private PowerCable myCable = null;

    private AxisAlignedBB trackingBox;

    private MyEnergyStorage energyStorage = new MyEnergyStorage(1000, 1000);
    private LazyOptional<IEnergyStorage> energy;

    public TileGenerator(CableTier cableTier) {
        super(TileEntityInit.CABLE.get());
        this.cableTier = cableTier;
        this.energy = LazyOptional.of(() -> this.energyStorage);
    }

    @Override
    public void tick() {
        if(!level.isClientSide) {


        }
    }

    private void sendEnergy() {
        if(energyStorage.getEnergyStored() > 0) {
            for()
        }
    }

    private void findEntities() {

        //DamageTracker.instance.clear(level.provider.getDimension(), worldPosition);

        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, getTrackingBox());
        for (LivingEntity entity : entities) {
            //DamageTracker.instance.register(level.provider.getDimension(), worldPosition, entity.getUUID());
        }
    }

    private AxisAlignedBB getTrackingBox() {
        if (trackingBox == null) {
        Vector3i bottomLeft = new Vector3i(-5,-3,-5);
        Vector3i topRight = new Vector3i(5,3,5);
        Vector3i newBotLeft = new Vector3i(bottomLeft.getX() + worldPosition.getX(),
                bottomLeft.getY() + worldPosition.getY(),
                bottomLeft.getZ() + worldPosition.getZ());
            Vector3i newTopRight = new Vector3i(topRight.getX() + worldPosition.getX(),
                    topRight.getY() + worldPosition.getY(),
                    topRight.getZ() + worldPosition.getZ());
            trackingBox = new AxisAlignedBB(new Vector3d(newBotLeft.getX(),
                    newBotLeft.getY(), newBotLeft.getZ()), new Vector3d(newTopRight.getX(),
                    newTopRight.getY(), newTopRight.getZ()));
        }
        return trackingBox;
    }

    public void senseDamage(LivingEntity entity, float amount) {

        if (getTrackingBox().contains(entity.position())) {
            energyStorage.generatePower((int) (amount * 5.0f));
        }
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityEnergy.ENERGY) {
            return this.energy.cast();
        }

        return super.getCapability(cap, side);
    }


    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        if(cap == CapabilityEnergy.ENERGY) {
            return this.energy.cast();
        }

        return super.getCapability(cap);
    }

    @Override
    public void load(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
        super.load(p_230337_1_, p_230337_2_);
        readRestorableFromNBT(p_230337_2_);
    }

    @Override
    public void readRestorableFromNBT(CompoundNBT compound) {
        energyStorage.setEnergy(compound.getInt("energy"));
    }

    @Override
    public CompoundNBT save(CompoundNBT p_189515_1_) {
        writeRestorableFromNBT(p_189515_1_);
        return super.save(p_189515_1_);
    }

    @Override
    public void writeRestorableFromNBT(CompoundNBT compound) {
        compound.putInt("energy", energyStorage.getEnergyStored());
    }
}
