package io.enlithamster.github.world.gen.trunk;

import com.google.common.collect.Lists;
import com.mojang.datafixers.Products;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.enlithamster.github.MMP_Mod;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class PinklerTrunkPlacer extends TrunkPlacer {
    public PinklerTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }

    protected static
    <P extends PinklerTrunkPlacer> Products.P3<RecordCodecBuilder.Mu<P>, Integer, Integer, Integer>
    createCodec(RecordCodecBuilder.Instance<P> instance) {
        return fillTrunkPlacerFields(instance);
    }

    public static final Codec<PinklerTrunkPlacer> CODEC =
            RecordCodecBuilder.create(instance -> createCodec(instance).apply(instance, PinklerTrunkPlacer::new));

    @Override
    protected TrunkPlacerType<?> getType() {
        return MMP_Mod.PINKLER_TRUNK_PLACER;
    }

    protected void generateBase(
            TestableWorld world,
            BiConsumer<BlockPos, BlockState> replacer,
            Random random,
            BlockPos startPos,
            TreeFeatureConfig config
    ) {
        BlockPos base = startPos.down();
        setToDirt(world, replacer, random, base, config);
        setToDirt(world, replacer, random, base.east(), config);
        setToDirt(world, replacer, random, base.north(), config);
        setToDirt(world, replacer, random, base.west(), config);
        setToDirt(world, replacer, random, base.south(), config);

        getAndSetState(world, replacer, random, startPos, config);
        getAndSetState(world, replacer, random, startPos.east(), config);
        getAndSetState(world, replacer, random, startPos.north(), config);
        getAndSetState(world, replacer, random, startPos.west(), config);
        getAndSetState(world, replacer, random, startPos.south(), config);
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(
            TestableWorld world,
            BiConsumer<BlockPos, BlockState> replacer,
            Random random,
            int height,
            BlockPos startPos,
            TreeFeatureConfig config
    ) {
        int branchesHeight = height / 3;
        int trunkHeight = height - branchesHeight;

        // The nodes keep the generation direction in their foliageRadius!
        ArrayList<FoliagePlacer.TreeNode> nodes = Lists.newArrayList();

        generateBase(world, replacer, random, startPos, config);

        int x = startPos.getX();
        int y = startPos.getY();
        int z = startPos.getZ();

        MMP_Mod.LOGGER.info("Generating Pinkler tree at (" + x + ", " + y + ", " + z + ")");
        MMP_Mod.LOGGER.info("\t- Height: " + height);
        MMP_Mod.LOGGER.info("\t- Trunk Height: " + trunkHeight);
        MMP_Mod.LOGGER.info("\t- Branches Height: " + branchesHeight);

        for (int i = 0; i < trunkHeight; i++) {
            BlockPos trunkPos = new BlockPos(x, y + i, z);
            if (TreeFeature.isAirOrLeaves(world, trunkPos)) {
                getAndSetState(world, replacer, random, trunkPos, config);
            }
        }

        var directions = Direction.Type.HORIZONTAL.iterator();
        while (directions.hasNext()) {
            Direction direction = directions.next();
            int dx = direction.getOffsetX();
            int dz = direction.getOffsetZ();

            for (int i = 0; i < branchesHeight; i++) {
                BlockPos branchPos = new BlockPos(x + dx * i, y + trunkHeight + i, z + dz * i);
                if (TreeFeature.isAirOrLeaves(world, branchPos)) {
                    getAndSetState(world, replacer, random, branchPos, config);
                }
            }

            int nodeDiv = branchesHeight - 1;
            BlockPos nodePos = new BlockPos(x + dx * nodeDiv, y + height, z + dz * nodeDiv);
            MMP_Mod.LOGGER.info("\t- Node: (" + nodePos.getX() + ", " + nodePos.getY() + ", " + nodePos.getZ() + ")");
            nodes.add(new FoliagePlacer.TreeNode(nodePos, direction.getId(), false));
        }

        BlockPos centerNodePos = new BlockPos(x, y + trunkHeight + 3, z);
        MMP_Mod.LOGGER.info("\t- Node: (" + centerNodePos.getX() + ", " + centerNodePos.getY() + ", " + centerNodePos.getZ() + ")");
        nodes.add(new FoliagePlacer.TreeNode(centerNodePos, -1, false));

        return nodes;
    }
}
