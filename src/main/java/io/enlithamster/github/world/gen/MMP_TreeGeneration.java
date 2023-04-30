package io.enlithamster.github.world.gen;

import io.enlithamster.github.world.MMP_PlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class MMP_TreeGeneration {
    public static void generateTrees() {
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.PLAINS, BiomeKeys.FOREST),
                GenerationStep.Feature.VEGETAL_DECORATION,
                MMP_PlacedFeatures.PINKLER_PLACED_KEY);
    }
}
