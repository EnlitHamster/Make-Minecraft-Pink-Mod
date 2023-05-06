package io.enlithamster.github.world;

import io.enlithamster.github.MMP_Mod;
import io.enlithamster.github.block.MMP_Blocks;
import io.enlithamster.github.world.gen.foliage.PinklerFoliagePlacer;
import io.enlithamster.github.world.gen.trunk.PinklerTrunkPlacer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

public class MMP_ConfiguredFeatures {

    public static RegistryKey<ConfiguredFeature<? ,?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(MMP_Mod.MOD_ID, name));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> PINKLER_TREE_KEY = registerKey("pink_wood_tree");

    public static TreeFeatureConfig.Builder pinkWood() {
        return new TreeFeatureConfig.Builder(
            BlockStateProvider.of(MMP_Blocks.PINKLER_LOG),
            new PinklerTrunkPlacer(10, 5, 0),
            new WeightedBlockStateProvider(DataPool.<BlockState>builder()
                    .add(MMP_Blocks.PINKLER_LEAVES.getDefaultState(), 3)
                    .add(MMP_Blocks.FLOWERING_PINKLER_LEAVES.getDefaultState(), 1)
                    .build()),
            new PinklerFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)),
            new TwoLayersFeatureSize(1, 0, 1));
    }

    public static <FC extends FeatureConfig, F extends Feature<FC>> void register(
            Registerable<ConfiguredFeature<?, ?>> context,
            RegistryKey<ConfiguredFeature<?, ?>> key,
            F feature,
            FC configuration
    ) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        register(context, PINKLER_TREE_KEY, Feature.TREE, pinkWood().build());
    }
}
