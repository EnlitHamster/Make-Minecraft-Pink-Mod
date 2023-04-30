package io.enlithamster.github;

import io.enlithamster.github.block.MMP_Blocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class MMP_Client implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(MMP_Blocks.PINKLER_SAPLING, RenderLayer.getCutout());
    }
}
