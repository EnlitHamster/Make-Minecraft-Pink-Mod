package io.enlithamster.github;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MMP_Mod implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("mmp");

    private static final FabricBlockSettings PINK_WOOD_SETTINGS =
        FabricBlockSettings
            .of(Material.WOOD)
            .strength(2.0f);

    private static final Block PINK_WOOD_PLANKS = new Block(PINK_WOOD_SETTINGS);

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        LOGGER.info("Pinkifying minecraft...");

        Registry.register(
            Registries.BLOCK,
            new Identifier("mmp", "pink_wood_planks"),
            PINK_WOOD_PLANKS
        );

        Registry.register(
            Registries.ITEM,
            new Identifier("mmp", "pink_wood_planks"),
            new BlockItem(PINK_WOOD_PLANKS, new FabricItemSettings())
        );


    }
}
