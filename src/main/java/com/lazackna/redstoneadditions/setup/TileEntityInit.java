package com.lazackna.redstoneadditions.setup;

import com.lazackna.redstoneadditions.Main;
import com.lazackna.redstoneadditions.blocks.TestBlock;
import com.lazackna.redstoneadditions.blocks.entity.ProximitySensorTileEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityInit {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Main.MOD_ID);

    public static final RegistryObject<TileEntityType<ProximitySensorTileEntity>> PROXIMITY_SENSOR =
            TILE_ENTITIES.register("proximity_tile",
                    () -> TileEntityType.Builder.of(ProximitySensorTileEntity::new, BlockInit.ProximitySensor.get()).build(null));

}
