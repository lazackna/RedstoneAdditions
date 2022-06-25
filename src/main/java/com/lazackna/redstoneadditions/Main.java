package com.lazackna.redstoneadditions;

import com.lazackna.redstoneadditions.setup.Registration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Main.MOD_ID)
public class Main {
    public static Main instance;
    public static final String MOD_ID = "redstoneadditions";
    public static final Logger LOGGER = LogManager.getLogger();

    public Main() {
        instance = this;

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(instance::setup);

        Registration.register(bus);

        MinecraftForge.EVENT_BUS.register(instance);
    }

    private void setup (final FMLCommonSetupEvent event) {

    }
}
