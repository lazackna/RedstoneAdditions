package com.lazackna.redstoneadditions.setup;

import com.lazackna.redstoneadditions.Main;
import com.lazackna.redstoneadditions.blocks.entity.TileGenerator;
import com.lazackna.redstoneadditions.blocks.entity.ProximitySensorTileEntity;
import com.lazackna.redstoneadditions.util.CableTier;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityInit {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Main.MOD_ID);

    public static final RegistryObject<TileEntityType<ProximitySensorTileEntity>> PROXIMITY_SENSOR =
            TILE_ENTITIES.register("proximity_tile",
                    () -> TileEntityType.Builder.of(ProximitySensorTileEntity::new, BlockInit.ProximitySensor.get()).build(null));
    public static final RegistryObject<TileEntityType<TileGenerator>> CABLE =
            TILE_ENTITIES.register("cable",
                    () -> TileEntityType.Builder.of(() -> new TileGenerator(CableTier.BASIC), BlockInit.Cable_Basic.get()).build(null));

}
