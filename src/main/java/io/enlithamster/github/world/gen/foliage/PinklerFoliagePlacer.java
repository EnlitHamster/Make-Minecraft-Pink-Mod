package io.enlithamster.github.world.gen.foliage;

import com.mojang.datafixers.Products;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.enlithamster.github.MMP_Mod;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
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

    protected void generateTopCanopy(
            TestableWorld world,
            FoliagePlacer.BlockPlacer placer,
            Random random,
            TreeFeatureConfig config,
            FoliagePlacer.TreeNode treeNode,
            int canopyHeight
    ) {
        BlockPos.Mutable center = treeNode.getCenter().mutableCopy();

        placeFoliageBlock(world, placer, random, config, center);
        placeFoliageBlock(world, placer, random, config, center.down());
        placeFoliageBlock(world, placer, random, config, center.east().down());
        placeFoliageBlock(world, placer, random, config, center.north().down());
        placeFoliageBlock(world, placer, random, config, center.west().down());
        placeFoliageBlock(world, placer, random, config, center.south().down());

        for (int i = 2; i <= canopyHeight; i++) {
            placeFoliageBlock(world, placer, random, config, center.east(2).down(i));
            placeFoliageBlock(world, placer, random, config, center.north(2).down(i));
            placeFoliageBlock(world, placer, random, config, center.west(2).down(i));
            placeFoliageBlock(world, placer, random, config, center.south(2).down(i));
        }
    }

    protected void generateSideCapony(
            TestableWorld world,
            FoliagePlacer.BlockPlacer placer,
            Random random,
            TreeFeatureConfig config,
            FoliagePlacer.TreeNode treeNode,
            int canopyHeight
    ) {
        BlockPos.Mutable center = treeNode.getCenter().mutableCopy();
        int directionId = treeNode.getFoliageRadius();
        Direction direction = Direction.byId(directionId);

        int x = center.getX();
        int y = center.getY();
        int z = center.getZ();

        placeFoliageBlock(world, placer, random, config, center);

        for (int i = 1; i <= canopyHeight; i++) {
            BlockPos centerLayerPos = new BlockPos(x, y - i, z);
            placeFoliageBlock(world, placer, random, config, centerLayerPos.offset(direction));
            if (direction == Direction.NORTH || direction == Direction.SOUTH) {
                placeFoliageBlock(world, placer, random, config, centerLayerPos.east());
                placeFoliageBlock(world, placer, random, config, centerLayerPos.west());
            }
            if (direction == Direction.EAST || direction == Direction.WEST) {
                placeFoliageBlock(world, placer, random, config, centerLayerPos.south());
                placeFoliageBlock(world, placer, random, config, centerLayerPos.north());
            }
        }
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

