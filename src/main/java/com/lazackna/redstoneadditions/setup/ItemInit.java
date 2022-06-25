package com.lazackna.redstoneadditions.setup;

import com.lazackna.redstoneadditions.Main;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);

    public static final RegistryObject<BlockItem> PROXIMITY_BLOCK = ITEMS.register("proximity_block",
            () -> new BlockItem(BlockInit.ProximitySensor.get(), new Item.Properties().tab(ItemGroup.TAB_REDSTONE)));
}
