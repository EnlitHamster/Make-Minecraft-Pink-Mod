package io.enlithamster.github.world.tree;

import io.enlithamster.github.world.MMP_ConfiguredFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class PinklerSaplingGenerator extends SaplingGenerator {
    @Override
    protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return MMP_ConfiguredFeatures.PINKLER_TREE_KEY;
    }
}
