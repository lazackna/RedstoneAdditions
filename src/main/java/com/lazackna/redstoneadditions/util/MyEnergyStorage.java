package com.lazackna.redstoneadditions.util;

import net.minecraftforge.energy.EnergyStorage;

public class MyEnergyStorage extends EnergyStorage {
    public MyEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer, maxTransfer, 0);
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void consumePower(int energy) {
        this.energy -= energy;
        if (this.energy < 0) {
            this.energy = 0;
        }
    }

    public void generatePower(int energy) {
        this.energy += energy;
        if (this.energy > capacity) {
            this.energy = capacity;
        }
    }
}
