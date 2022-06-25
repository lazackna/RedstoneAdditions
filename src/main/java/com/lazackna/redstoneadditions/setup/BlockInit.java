package com.lazackna.redstoneadditions.setup;

import com.lazackna.redstoneadditions.Main;
import com.lazackna.redstoneadditions.blocks.TestBlock;
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
    public static final RegistryObject<Block> ProximitySensor = BLOCKS.register("proximity", () -> new TestBlock(p));
}
