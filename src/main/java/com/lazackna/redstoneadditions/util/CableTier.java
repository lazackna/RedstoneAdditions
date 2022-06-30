package com.lazackna.redstoneadditions.util;

public enum CableTier {

    BASIC(10, 100),
    IMPROVED(50, 500),
    ADVANCED(150, 1500),
    SUPER(300, 3000),
    ULTIMATE(500, 5000);

    public final int MAX_TRANSFER_RATE;
    public final int MAX_STORAGE;
    CableTier(int maxTransfer, int maxStorage) {
        this.MAX_TRANSFER_RATE = maxTransfer;
        this.MAX_STORAGE = maxStorage;
    }

}
