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
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import sun.reflect.generics.reflectiveObjects.LazyReflectiveObjectGenerator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class TileGenerator extends TileEntity implements ITickableTileEntity, IRestorableTileEntity {

    private CableTier cableTier;
    private PowerCable myCable = null;

    private AxisAlignedBB trackingBox;

   // private MyEnergyStorage energyStorage = new MyEnergyStorage(1000, 1000);
    private EnergyStorage energyStorage = new EnergyStorage(1000000, 10000, 10000, 0) {
       @Override
       public int receiveEnergy(int maxReceive, boolean simulate) {
           int retval = super.receiveEnergy(maxReceive, simulate);
           if(!simulate) {
               assert level != null;
               level.markAndNotifyBlock(worldPosition,
                       level.getChunkAt(worldPosition), level.getBlockState(worldPosition),
                       level.getBlockState(worldPosition), 2, 2);
           }
           return retval;
       }
       
       @Override
       public int extractEnergy(int maxExtract, boolean simulate) {
           int retval = super.extractEnergy(maxExtract, simulate);
           if(!simulate) {
               level.markAndNotifyBlock(worldPosition,
                       level.getChunkAt(worldPosition),
                               level.getBlockState(worldPosition),
                                       level.getBlockState(worldPosition), 2, 2);
           }
           return retval;
       }

   };
    private LazyOptional<IEnergyStorage> energy;

    public TileGenerator(CableTier cableTier) {
        super(TileEntityInit.CABLE.get());
        this.cableTier = cableTier;
        this.energy = LazyOptional.of(() -> this.energyStorage);
    }

    @Override
    public void tick() {
        if(!level.isClientSide) {

            if(new Object() {
                public boolean canReceiveEnergy(BlockPos pos) {
                    AtomicBoolean retval = new AtomicBoolean(false);
                    TileEntity ent = level.getBlockEntity(pos);
                    if(ent != null)
                        ent.getCapability(CapabilityEnergy.ENERGY).ifPresent(

                                capability -> {
                                    retval.set(true);
                                   // capability.
                                }
                        );
                    return retval.get();
                }
            }.canReceiveEnergy(new BlockPos(getBlockPos().getX(), getBlockPos().getY() - 1, getBlockPos().getZ()))) {
                TileEntity ent = level.getBlockEntity(new BlockPos(getBlockPos().getX(), getBlockPos().getY() - 1, getBlockPos().getZ()));
                int amount = 100;
                if(ent != null) {

                    ent.getCapability(CapabilityEnergy.ENERGY)
                            .ifPresent(capability -> capability.receiveEnergy(amount, false));
                }
            };


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

    // energy is being taken but not given
    @Override
    public void load(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
        super.load(p_230337_1_, p_230337_2_);
        readRestorableFromNBT(p_230337_2_);
    }

    @Override
    public void readRestorableFromNBT(CompoundNBT compound) {
        if(compound.get("energy") != null)
        CapabilityEnergy.ENERGY.readNBT(energyStorage, null, compound.get("energy"));
      //  energyStorage.setEnergy(compound.getInt("energy"));
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.worldPosition, 9, this.getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        this.readRestorableFromNBT(pkt.getTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.save(new CompoundNBT());
    }

    @Override
    public CompoundNBT save(CompoundNBT p_189515_1_) {
        writeRestorableFromNBT(p_189515_1_);
        return super.save(p_189515_1_);
    }

    @Override
    public void writeRestorableFromNBT(CompoundNBT compound) {
        compound.put("energy", Objects.requireNonNull(CapabilityEnergy.ENERGY.writeNBT(energyStorage, null)));
    }
}
