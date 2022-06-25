package com.lazackna.redstoneadditions.setup;

import net.minecraftforge.eventbus.api.IEventBus;

public class Registration {

    public static void register (IEventBus bus) {
        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);
    }
}
