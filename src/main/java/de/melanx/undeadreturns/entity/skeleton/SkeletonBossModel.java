// Forge model conversion from 1.16 to 1.17 by Steven (Steaf23), program outline loosely based on https://github.com/Globox1997/ModelConverter
// Generate all required imports yourself
package de.melanx.undeadreturns.entity.skeleton;// Made with Blockbench 4.0.0-beta.0
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

import javax.annotation.Nonnull;

public class SkeletonBossModel extends HumanoidModel<SkeletonBoss> {
//	private final ModelPart body;
//	private final ModelPart head;
//	private final ModelPart left_arm;
//	private final ModelPart right_arm;
//	private final ModelPart left_leg;
//	private final ModelPart right_leg;

	public SkeletonBossModel(ModelPart model) {
		super(model);
//		this.body = model;
//		this.head = model.getChild("head");
//		this.left_arm = model.getChild("left_arm");
//		this.right_arm = model.getChild("right_arm");
//		this.left_leg = model;
//		this.right_leg = model;
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshDefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition partDefinition = meshDefinition.getRoot();

		partDefinition.addOrReplaceChild("body", CubeListBuilder.create()
						.texOffs(16, 16).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f),
				PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));

		partDefinition.addOrReplaceChild("head", CubeListBuilder.create()
						.texOffs(0, 0).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f),
				PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));

		partDefinition.addOrReplaceChild("left_arm", CubeListBuilder.create()
						.texOffs(40, 16).addBox(9.0f, -2.0f, -1.0f, 2.0f, 12.0f, 2.0f).mirror(),
				PartPose.offsetAndRotation(-5.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f));

		partDefinition.addOrReplaceChild("right_arm", CubeListBuilder.create()
						.texOffs(40, 16).addBox(-11.0f, -2.0f, -1.0f, 2.0f, 12.0f, 2.0f),
				PartPose.offsetAndRotation(5.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f));

		partDefinition.addOrReplaceChild("left_leg", CubeListBuilder.create()
						.texOffs(0, 16).addBox(3.0f, 0.0f, -1.1f, 2.0f, 12.0f, 2.0f).mirror(),
				PartPose.offsetAndRotation(-2.0f, 12.0f, 0.1f, 0.0f, 0.0f, 0.0f));

		partDefinition.addOrReplaceChild("right_leg", CubeListBuilder.create()
						.texOffs(0, 16).addBox(-5.0f, 0.0f, -1.1f, 2.0f, 12.0f, 2.0f),
				PartPose.offsetAndRotation(2.0f, 12.0f, 0.1f, 0.0f, 0.0f, 0.0f));

		return LayerDefinition.create(meshDefinition, 64, 32);
	}

	@Override
	public void setupAnim(@Nonnull SkeletonBoss entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		AnimationUtils.bobArms(this.rightArm, this.leftArm, ageInTicks);
	}

	@Override
	public void renderToBuffer(@Nonnull PoseStack poseStack, @Nonnull VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.body.render(poseStack, buffer, packedLight, packedOverlay);
		this.head.render(poseStack, buffer, packedLight, packedOverlay);
		this.leftArm.render(poseStack, buffer, packedLight, packedOverlay);
		this.rightArm.render(poseStack, buffer, packedLight, packedOverlay);
		this.leftLeg.render(poseStack, buffer, packedLight, packedOverlay);
		this.rightLeg.render(poseStack, buffer, packedLight, packedOverlay);
	}
}
