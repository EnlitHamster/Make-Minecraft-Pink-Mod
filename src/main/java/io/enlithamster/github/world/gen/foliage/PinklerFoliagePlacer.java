package io.enlithamster.github.world.gen.foliage;

import com.mojang.datafixers.Products;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.enlithamster.github.MMP_Mod;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class PinklerFoliagePlacer extends FoliagePlacer {
    public PinklerFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    protected static
    <P extends PinklerFoliagePlacer> Products.P2<RecordCodecBuilder.Mu<P>, IntProvider, IntProvider>
    createCodec(RecordCodecBuilder.Instance<P> instance) {
        return fillFoliagePlacerFields(instance);
    }

    public static final Codec<PinklerFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
            createCodec(instance).apply(instance, PinklerFoliagePlacer::new));

    @Override
    protected FoliagePlacerType<?> getType() {
        return MMP_Mod.PINKLER_FOLIAGE_PLACER;
    }

    protected void generateVerticalCanopy(
        TestableWorld world,
        FoliagePlacer.BlockPlacer placer,
        Random random,
        TreeFeatureConfig config,
        BlockPos center,
        int canopyHeight
    ) {
        for (int i = 0; i < canopyHeight; i++) {
            placeFoliageBlock(world, placer, random, config, center.down(i));
        }
    }

    protected void generateTopCanopy(
            TestableWorld world,
            FoliagePlacer.BlockPlacer placer,
            Random random,
            TreeFeatureConfig config,
            FoliagePlacer.TreeNode treeNode,
            int canopyHeight
    ) {
        BlockPos center = treeNode.getCenter();

        placeFoliageBlock(world, placer, random, config, center);
        placeFoliageBlock(world, placer, random, config, center.down());
        placeFoliageBlock(world, placer, random, config, center.east().down());
        placeFoliageBlock(world, placer, random, config, center.north().down());
        placeFoliageBlock(world, placer, random, config, center.west().down());
        placeFoliageBlock(world, placer, random, config, center.south().down());

        generateVerticalCanopy(world, placer, random, config, center.east().north().down(2), canopyHeight - 2);
        generateVerticalCanopy(world, placer, random, config, center.north().west().down(2), canopyHeight - 2);
        generateVerticalCanopy(world, placer, random, config, center.west().south().down(2), canopyHeight - 2);
        generateVerticalCanopy(world, placer, random, config, center.south().east().down(2), canopyHeight - 2);
    }

    protected void generateSideCapony(
            TestableWorld world,
            FoliagePlacer.BlockPlacer placer,
            Random random,
            TreeFeatureConfig config,
            FoliagePlacer.TreeNode treeNode,
            int canopyHeight
    ) {
        BlockPos center = treeNode.getCenter();

        placeFoliageBlock(world, placer, random, config, center);

        int sideCanopyHeight = canopyHeight + random.nextBetween(-2, 0);
        generateVerticalCanopy(world, placer, random, config, center.down().east(), sideCanopyHeight);
        generateVerticalCanopy(world, placer, random, config, center.down().north(), sideCanopyHeight);
        generateVerticalCanopy(world, placer, random, config, center.down().west(), sideCanopyHeight);
        generateVerticalCanopy(world, placer, random, config, center.down().south(), sideCanopyHeight);
    }

    @Override
    protected void generate(
            TestableWorld world,
            FoliagePlacer.BlockPlacer placer,
            Random random,
            TreeFeatureConfig config,
            int trunkHeight,
            FoliagePlacer.TreeNode treeNode,
            int foliageHeight,
            int radius,
            int offset
    ) {
        var center = treeNode.getCenter();
        MMP_Mod.LOGGER.info("Generating Pinkler foliage at (" + center.getX() + ", " + center.getY() + ", " + center.getZ() + ")");
        switch (treeNode.getFoliageRadius()) {
            case -1:
                generateTopCanopy(world, placer, random, config, treeNode, foliageHeight);
                break;
            case 2:
            case 3:
            case 4:
            case 5:
                generateSideCapony(world, placer, random, config, treeNode, foliageHeight);
                break;
        }
    }

    @Override
    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
        return random.nextBetween(4, 6);
    }

    @Override
    protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return false;
    }
}

