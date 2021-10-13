package de.melanx.undeadreturns.entity.skeleton;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class SkeletonBoss extends Monster {

    public SkeletonBoss(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier defaultAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.25D).build();
    }
}
