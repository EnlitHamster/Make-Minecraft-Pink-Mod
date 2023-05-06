package io.enlithamster.github.data;

import io.enlithamster.github.block.MMP_Blocks;
import io.enlithamster.github.item.MMP_ItemGroup;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class MMP_LanguageProvider extends FabricLanguageProvider {
    public MMP_LanguageProvider(FabricDataOutput output) {
        super(output, "en_us");
    }

    private void generateBlocks(TranslationBuilder builder) {
        builder.add(MMP_Blocks.PINKLER_LOG, "Pinkler Log");
        builder.add(MMP_Blocks.PINKLER_WOOD, "Pinkler Wood");
        builder.add(MMP_Blocks.PINKLER_PLANKS, "Pinkler Planks");
        builder.add(MMP_Blocks.PINKLER_SAPLING, "Pinkler Sapling");
        builder.add(MMP_Blocks.PINKLER_LEAVES, "Pinkler Leaves");
        builder.add(MMP_Blocks.FLOWERING_PINKLER_LEAVES, "Flowering Pinkler Leaves");
    }

    private void generateItemGroups(TranslationBuilder builder ){
        builder.add(MMP_ItemGroup.WOODS, "Woods");
    }

    @Override
    public void generateTranslations(TranslationBuilder builder) {
        generateBlocks(builder);
        generateItemGroups(builder);
    }

}
