package com.lazackna.redstoneadditions.blocks.entity;

import com.lazackna.redstoneadditions.setup.TileEntityInit;
import net.minecraft.tileentity.FurnaceTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ProximitySensorTileEntity extends TileEntity implements ITickableTileEntity {


    public ProximitySensorTileEntity() {
        super(TileEntityInit.PROXIMITY_SENSOR.get());

    }

    @Override
    public void tick() {
        System.out.println("IT WORKS!");
    }
}
