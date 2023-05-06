package io.enlithamster.github;

import io.enlithamster.github.block.MMP_Blocks;
import io.enlithamster.github.item.MMP_ItemGroup;
import io.enlithamster.github.mixin.MMP_FoliagePlacerType;
import io.enlithamster.github.mixin.MMP_TrunkPlacerType;
import io.enlithamster.github.world.gen.MMP_WorldGeneration;
import io.enlithamster.github.world.gen.foliage.PinklerFoliagePlacer;
import io.enlithamster.github.world.gen.trunk.PinklerTrunkPlacer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MMP_Mod implements ModInitializer {
    public static final String MOD_ID = "mmp";

    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static Identifier id(String name) {
        return new Identifier(MOD_ID, name);
    }

    public static String strId(String name) {
        return MOD_ID + ":" + name;
    }


    public static final FoliagePlacerType<PinklerFoliagePlacer> PINKLER_FOLIAGE_PLACER =
            MMP_FoliagePlacerType.callRegister(strId("pinkler_foliage_placer"), PinklerFoliagePlacer.CODEC);

    public static final TrunkPlacerType<PinklerTrunkPlacer> PINKLER_TRUNK_PLACER =
            MMP_TrunkPlacerType.callRegister(strId("pinkler_foliage_placer"), PinklerTrunkPlacer.CODEC);

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        LOGGER.info("Pinkifying minecraft...");

        MMP_ItemGroup.registerItemGroups();
        MMP_Blocks.registerBlocks();
        MMP_WorldGeneration.generateWorld();
    }
}
