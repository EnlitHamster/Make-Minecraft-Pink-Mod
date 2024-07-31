package io.enlithamster.github.render.entity;

import io.enlithamster.github.entity.passive.MMP_PinkletEntity;
import io.enlithamster.github.render.entity.model.MMP_PinkletEntityModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.SaddleFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PigEntityModel;
import net.minecraft.util.Identifier;

@Environment(value=EnvType.CLIENT)
public class MMP_PinkletEntityRenderer
extends MobEntityRenderer<MMP_PinkletEntity, MMP_PinkletEntityModel<MMP_PinkletEntity>> {
    private static final Identifier TEXTURE = new Identifier("textures/entity/pig/pig.png");

    public MMP_PinkletEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new MMP_PinkletEntityModel(context.getPart(EntityModelLayers.PIG)), 0.7f);
        this.addFeature(new SaddleFeatureRenderer(this, new PigEntityModel(context.getPart(EntityModelLayers.PIG_SADDLE)), new Identifier("textures/entity/pig/pig_saddle.png")));
    }

    @Override
    public Identifier getTexture(MMP_PinkletEntity pigEntity) {
        return TEXTURE;
    }
}

