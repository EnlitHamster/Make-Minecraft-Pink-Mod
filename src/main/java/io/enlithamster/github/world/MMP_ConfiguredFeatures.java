package io.enlithamster.github.world;

import io.enlithamster.github.MMP_Mod;
import io.enlithamster.github.block.MMP_Blocks;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

public class MMP_ConfiguredFeatures {

    public static RegistryKey<ConfiguredFeature<? ,?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(MMP_Mod.MOD_ID, name));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> PINKLER_TREE_KEY = registerKey("pink_wood_tree");

    public static TreeFeatureConfig.Builder pinkWood() {
        return new TreeFeatureConfig.Builder(
            BlockStateProvider.of(MMP_Blocks.PINKLER_LOG),
            new StraightTrunkPlacer(8, 3, 0),
            BlockStateProvider.of(Blocks.OAK_LEAVES),
            new BlobFoliagePlacer(ConstantIntProvider.create(5), ConstantIntProvider.create(0), 3),
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
