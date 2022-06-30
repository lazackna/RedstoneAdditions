package com.lazackna.redstoneadditions.setup;

import com.lazackna.redstoneadditions.Main;
import com.lazackna.redstoneadditions.blocks.PowerCable;
import com.lazackna.redstoneadditions.blocks.ProximitySensorBlock;
import com.lazackna.redstoneadditions.util.CableTier;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MOD_ID);
    public static final AbstractBlock.Properties p = AbstractBlock.Properties.of(Material.DECORATION).instabreak().sound(SoundType.WOOD);
    public static final RegistryObject<Block> ProximitySensor = BLOCKS.register("proximity", () -> new ProximitySensorBlock(p));
    public static final RegistryObject<Block> Cable_Basic = BLOCKS.register("cable_basic", () -> new PowerCable(p, CableTier.BASIC));
    public static final RegistryObject<Block> Cable_Improved = BLOCKS.register("cable_improved", () -> new PowerCable(p, CableTier.IMPROVED));
    public static final RegistryObject<Block> Cable_Advanced = BLOCKS.register("cable_advanced", () -> new PowerCable(p, CableTier.ADVANCED));
    public static final RegistryObject<Block> Cable_Super = BLOCKS.register("cable_super", () -> new PowerCable(p, CableTier.SUPER));
    public static final RegistryObject<Block> Cable_Ultimate = BLOCKS.register("cable_ultimate", () -> new PowerCable(p, CableTier.ULTIMATE));
}
