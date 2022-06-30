package com.lazackna.redstoneadditions.blocks.entity;

import com.lazackna.redstoneadditions.blocks.PowerCable;
import com.lazackna.redstoneadditions.setup.BlockInit;
import com.lazackna.redstoneadditions.setup.TileEntityInit;
import com.lazackna.redstoneadditions.util.CableTier;
import net.minecraft.block.Block;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.energy.IEnergyStorage;

public class PowerCableTileEntity extends TileEntity implements ITickableTileEntity, IEnergyStorage {

    private int currentStorage = 0;
    private CableTier cableTier;
    private PowerCable myCable = null;


    public PowerCableTileEntity(CableTier cableTier) {
        super(TileEntityInit.CABLE.get());
        this.cableTier = cableTier;
    }

    @Override
    public void tick() {
        if(myCable == null) {
            myCable = (PowerCable) getBlockState().getBlock();
        }
        if(currentStorage >= cableTier.MAX_STORAGE) return;
        int toFill = cableTier.MAX_STORAGE - currentStorage;
        for(IEnergyStorage ES : myCable.energyNeighbours) {
            if(ES.canExtract()) {
                ES.extractEnergy(toFill, false);
            }
        }
    }

    protected boolean isEnabled() {
        return true;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        if(!isEnabled()) return 0;

        int overflow = (currentStorage + maxReceive) % cableTier.MAX_STORAGE;
        if(overflow > 0) {
            if(!simulate)
                currentStorage = cableTier.MAX_STORAGE;
            return maxReceive - overflow;
        } else {
            if(!simulate)
                currentStorage+= maxReceive;
            return maxReceive;
        }
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        if(!isEnabled()) return 0;

        int maxPull = cableTier.MAX_TRANSFER_RATE;
        if(maxExtract > maxPull) maxExtract = maxPull;

        int underflow = currentStorage - maxExtract;
        if(underflow < 0) {
            //not enough energy
            if(!simulate)
                currentStorage = 0;
            return maxExtract + underflow;
        } else {
            //enough energy
            if(!simulate)
                currentStorage -= maxExtract;
            return maxExtract;
        }
    }

    @Override
    public int getEnergyStored() {
        return currentStorage;
    }

    @Override
    public int getMaxEnergyStored() {
        return cableTier.MAX_STORAGE;
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public boolean canReceive() {
        return true;
    }
}
