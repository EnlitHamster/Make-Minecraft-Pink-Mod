package io.enlithamster.github.item;

import io.enlithamster.github.MMP_Mod;
import io.enlithamster.github.block.MMP_Blocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class MMP_ItemGroup {
    public static ItemGroup WOODS;

    public static void registerItemGroups() {
        WOODS = FabricItemGroup.builder(MMP_Mod.id("woods"))
                .displayName(Text.translatable("itemgroup.woods"))
                .icon(() -> new ItemStack(MMP_Blocks.PINKLER_PLANKS_ITEM)).build();
    }
}
