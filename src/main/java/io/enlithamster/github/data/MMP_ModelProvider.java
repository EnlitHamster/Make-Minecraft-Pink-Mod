package io.enlithamster.github.data;

import io.enlithamster.github.block.MMP_Blocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

public class MMP_ModelProvider extends FabricModelProvider {
    public MMP_ModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        generator.registerLog(MMP_Blocks.PINKLER_LOG)
                .log(MMP_Blocks.PINKLER_LOG)
                .wood(MMP_Blocks.PINKLER_WOOD);

        generator.registerCubeAllModelTexturePool(MMP_Blocks.PINKLER_PLANKS);

        generator.registerTintableCrossBlockState(
                MMP_Blocks.PINKLER_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {}
}
