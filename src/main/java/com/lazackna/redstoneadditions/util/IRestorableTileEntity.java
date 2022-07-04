package com.lazackna.redstoneadditions.util;
import net.minecraft.nbt.CompoundNBT;
public interface IRestorableTileEntity {

    void readRestorableFromNBT(CompoundNBT compound);
    void writeRestorableFromNBT(CompoundNBT compound);

}
