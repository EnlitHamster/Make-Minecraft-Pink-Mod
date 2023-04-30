package io.enlithamster.github.world;

import io.enlithamster.github.block.MMP_Blocks;
import io.enlithamster.github.MMP_Mod;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

public class MMP_PlacedFeatures {

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, MMP_Mod.id(name));
    }

    public static final RegistryKey<PlacedFeature> PINKLER_PLACED_KEY = registerKey("pinkler_placed");

    private static void register(
        Registerable<PlacedFeature> context,
        RegistryKey<PlacedFeature> key,
        RegistryEntry<ConfiguredFeature<?, ?>> configuration,
        List<PlacementModifier> modifiers
    ) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(
        Registerable<PlacedFeature> context,
        RegistryKey<PlacedFeature> key,
        RegistryEntry<ConfiguredFeature<?, ?>> configuration,
        PlacementModifier... modifiers
    ) {
        context.register(key, new PlacedFeature(configuration, List.of(modifiers)));
    }

    private static List<PlacementModifier> modifiers() {
        var countExtraMod = PlacedFeatures.createCountExtraModifier(1, 1.0f, 2);
        return VegetationPlacedFeatures.treeModifiersWithWouldSurvive(countExtraMod, MMP_Blocks.PINKLER_SAPLING);
    }

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var lu_configuredFeature =
                context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        var configFeature =
                lu_configuredFeature.getOrThrow(MMP_ConfiguredFeatures.PINKLER_TREE_KEY);

        register(context, PINKLER_PLACED_KEY, configFeature, modifiers());
    }
}
