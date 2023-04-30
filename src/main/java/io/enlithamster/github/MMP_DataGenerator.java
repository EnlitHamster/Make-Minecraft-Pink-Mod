package io.enlithamster.github;

import io.enlithamster.github.data.MMP_LootTableGenerator;
import io.enlithamster.github.data.MMP_ModelProvider;
import io.enlithamster.github.data.MMP_WorldGenerator;
import io.enlithamster.github.world.MMP_ConfiguredFeatures;
import io.enlithamster.github.world.MMP_PlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class MMP_DataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        pack.addProvider(MMP_LootTableGenerator::new);
        pack.addProvider(MMP_ModelProvider::new);
        pack.addProvider(MMP_WorldGenerator::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, MMP_ConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, MMP_PlacedFeatures::bootstrap);
    }
}
