package de.melanx.undeadreturns.data;

import de.melanx.undeadreturns.registration.tags.ModEntityTags;
import io.github.noeppi_noeppi.libx.annotation.data.Datagen;
import io.github.noeppi_noeppi.libx.data.provider.CommonTagsProviderBase;
import io.github.noeppi_noeppi.libx.mod.ModX;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.HashCache;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

@Datagen
public class ModTagProvider extends CommonTagsProviderBase {

    private final EntityTagProvider entityTags;

    public ModTagProvider(ModX mod, DataGenerator generator, ExistingFileHelper fileHelper) {
        super(mod, generator, fileHelper);
        this.entityTags = new EntityTagProvider(generator, mod.modid, fileHelper);
        generator.addProvider(this.entityTags);
    }

    @Override
    public void setup() {
        this.entityTags.tag(ModEntityTags.ENDERMAN).add(EntityType.ENDERMAN);
        //noinspection unchecked
        this.entityTags.tag(ModEntityTags.SKELETON).addTags(EntityTypeTags.SKELETONS).add(EntityType.SKELETON_HORSE);
        this.entityTags.tag(ModEntityTags.ZOMBIE).add(EntityType.DROWNED, EntityType.ZOMBIE, EntityType.ZOMBIE_HORSE, EntityType.ZOMBIE_VILLAGER, EntityType.ZOMBIFIED_PIGLIN, EntityType.PIGLIN, EntityType.PIGLIN_BRUTE);
    }

    public static class EntityTagProvider extends EntityTypeTagsProvider {

        private Map<ResourceLocation, Tag.Builder> tagCache;

        public EntityTagProvider(DataGenerator generator, String modid, ExistingFileHelper fileHelper) {
            super(generator, modid, fileHelper);
        }

        @Override
        protected void addTags() {
            this.builders.putAll(this.tagCache);
        }

        @Override
        public void run(@Nonnull HashCache cache) {
            this.tagCache = new HashMap<>(this.builders);
            super.run(cache);
        }

        @Nonnull
        @Override
        public TagAppender<EntityType<?>> tag(@Nonnull Tag.Named<EntityType<?>> tag) {
            return super.tag(tag);
        }

        @Nonnull
        @Override
        public String getName() {
            return this.modId + " entity type tags";
        }
    }
}
