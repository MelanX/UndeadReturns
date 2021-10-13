package de.melanx.undeadreturns.entity.skeleton;

import de.melanx.undeadreturns.UndeadReturns;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class SkeletonBossRenderer extends HumanoidMobRenderer<SkeletonBoss, SkeletonBossModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(UndeadReturns.getInstance().modid, "textures/entity/skeleton_boss/skeleton_boss.png");

    public SkeletonBossRenderer(EntityRendererProvider.Context context) {
        super(context, new SkeletonBossModel(context.bakeLayer(ModelLayers.SKELETON)), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new SkeletonBossModel(context.bakeLayer(ModelLayers.SKELETON_INNER_ARMOR)), new SkeletonBossModel(context.bakeLayer(ModelLayers.SKELETON_OUTER_ARMOR))));
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(@Nonnull SkeletonBoss entity) {
        return TEXTURE;
    }
}
