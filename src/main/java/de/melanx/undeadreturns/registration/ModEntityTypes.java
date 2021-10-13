package de.melanx.undeadreturns.registration;

import de.melanx.undeadreturns.UndeadReturns;
import de.melanx.undeadreturns.entity.skeleton.SkeletonBoss;
import io.github.noeppi_noeppi.libx.annotation.registration.RegisterClass;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

@RegisterClass
public class ModEntityTypes {

    public static final EntityType<SkeletonBoss> skeletonBoss = EntityType.Builder.of(SkeletonBoss::new, MobCategory.MONSTER).sized(0.6F, 1.99F).build(UndeadReturns.getInstance().modid + "_skeleton_boss");
}
