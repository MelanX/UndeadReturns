package de.melanx.undeadreturns.registration.tags;

import de.melanx.undeadreturns.UndeadReturns;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.entity.EntityType;

public class ModEntityTags {

    public static final Tag.Named<EntityType<?>> SKELETON = EntityTypeTags.bind(UndeadReturns.getInstance().resource("skeleton").toString());
    public static final Tag.Named<EntityType<?>> ENDERMAN = EntityTypeTags.bind(UndeadReturns.getInstance().resource("enderman").toString());
    public static final Tag.Named<EntityType<?>> ZOMBIE = EntityTypeTags.bind(UndeadReturns.getInstance().resource("zombie").toString());
}
