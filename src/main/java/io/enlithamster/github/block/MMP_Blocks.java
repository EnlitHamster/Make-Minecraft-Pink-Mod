package io.enlithamster.github.block;

import io.enlithamster.github.MMP_Mod;
import io.enlithamster.github.item.MMP_ItemGroup;
import io.enlithamster.github.world.tree.PinkWoodSaplingGenerator;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class MMP_Blocks {


    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, MMP_Mod.id(name), new BlockItem(block, new FabricItemSettings()));
    }

    private static Block registerBlock(String name, Block block) {
        return Registry.register(Registries.BLOCK, MMP_Mod.id(name), block);
    }

    private static FabricBlockSettings pinklerSettings(Block original) {
        return FabricBlockSettings.copyOf(original).strength(2.0f).requiresTool();
    }

    public static final FabricBlockSettings S_PINKLER_LOG = pinklerSettings(Blocks.OAK_LOG);
    public static final FabricBlockSettings S_PINKLER_WOOD = pinklerSettings(Blocks.OAK_WOOD);
    public static final FabricBlockSettings S_PINKLER_PLANKS = pinklerSettings(Blocks.OAK_PLANKS);
    public static final FabricBlockSettings S_PINKLER_SAPLING = pinklerSettings(Blocks.OAK_SAPLING);

    public static final Block PINKLER_LOG = registerBlock("pinkler_log", new PillarBlock(S_PINKLER_LOG));
    public static final Block PINKLER_WOOD = registerBlock("pinkler_wood", new PillarBlock(S_PINKLER_WOOD));
    public static final Block PINKLER_PLANKS = registerBlock("pinkler_planks", new Block(S_PINKLER_PLANKS));
    public static final Block PINKLER_SAPLING =
            registerBlock("pinkler_sapling", new SaplingBlock(new PinkWoodSaplingGenerator(), S_PINKLER_SAPLING));

    public static final Item PINKLER_LOG_ITEM = registerBlockItem("pinkler_log", PINKLER_LOG);
    public static final Item PINKLER_WOOD_ITEM = registerBlockItem("pinkler_wood", PINKLER_WOOD);
    public static final Item PINKLER_PLANKS_ITEM = registerBlockItem("pinkler_planks", PINKLER_PLANKS);
    public static final Item PINKLER_SAPLING_ITEM = registerBlockItem("pinkler_sapling", PINKLER_SAPLING);


    private static void addToItemGroup(Item item, ItemGroup group) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }

    private static void addAllToItemGroups() {
        addToItemGroup(PINKLER_LOG_ITEM, MMP_ItemGroup.WOODS);
        addToItemGroup(PINKLER_WOOD_ITEM, MMP_ItemGroup.WOODS);
        addToItemGroup(PINKLER_PLANKS_ITEM, MMP_ItemGroup.WOODS);
        addToItemGroup(PINKLER_SAPLING_ITEM, MMP_ItemGroup.WOODS);
    }

    public static void registerBlocks() {
        MMP_Mod.LOGGER.info("Registering blocks for " + MMP_Mod.MOD_ID);

        addAllToItemGroups();
    }
}
