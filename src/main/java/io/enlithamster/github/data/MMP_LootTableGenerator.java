package io.enlithamster.github.data;

import io.enlithamster.github.block.MMP_Blocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class MMP_LootTableGenerator extends FabricBlockLootTableProvider {
    public MMP_LootTableGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate() {
        addDrop(MMP_Blocks.PINKLER_LOG);
        addDrop(MMP_Blocks.PINKLER_WOOD);
        addDrop(MMP_Blocks.PINKLER_PLANKS);
        addDrop(MMP_Blocks.PINKLER_SAPLING);
    }
}
