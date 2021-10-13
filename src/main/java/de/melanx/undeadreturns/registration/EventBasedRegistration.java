package de.melanx.undeadreturns.registration;

import de.melanx.undeadreturns.entity.skeleton.SkeletonBoss;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "undeadreturns", bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventBasedRegistration {

    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.skeletonBoss, SkeletonBoss.defaultAttributes());
    }
}
