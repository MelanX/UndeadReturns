package de.melanx.undeadreturns;

import de.melanx.undeadreturns.block.LootPile;
import de.melanx.undeadreturns.config.ConfigHandler;
import de.melanx.undeadreturns.registration.ModBlocks;
import de.melanx.undeadreturns.registration.tags.ModEntityTags;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.Tag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class EventListener {

    private static final Set<Tag.Named<EntityType<?>>> UNDEADS = Set.of(ModEntityTags.ENDERMAN, ModEntityTags.SKELETON, ModEntityTags.ZOMBIE);

    @SubscribeEvent
    public void onEntityDropsLoot(LivingDropsEvent event) {
        LivingEntity entity = event.getEntityLiving();
        Level level = entity.level;

        for (Tag.Named<EntityType<?>> tag : UNDEADS) {
            if (!tag.contains(entity.getType())) {
                continue;
            }

            Vec3 entityPos = entity.position();

            List<BlockPos> positions = BlockPos.betweenClosedStream(AABB.ofSize(entityPos, ConfigHandler.extractionRange * 2, ConfigHandler.extractionRange * 2, ConfigHandler.extractionRange * 2))
                    .filter(blockPos -> level.getBlockState(blockPos).is(ModBlocks.lootPile))
                    .map(BlockPos::immutable)
                    .sorted(Comparator.comparing(blockPos -> blockPos.distSqr(entityPos.x, entityPos.y, entityPos.z, false)))
                    .collect(Collectors.toList());
            for (BlockPos pos : positions) {
                if (level.getBlockEntity(pos) instanceof LootPile blockEntity && blockEntity.matchesMob(entity.getType())) {
                    blockEntity.addLoot(event.getDrops().stream().map(ItemEntity::getItem).collect(Collectors.toSet()));
                    event.setCanceled(true);
                    return;
                }

            }

            BlockPos pos = new BlockPos(entityPos);
            level.setBlock(pos, ModBlocks.lootPile.defaultBlockState(), Block.UPDATE_ALL);
            ((LootPile) Objects.requireNonNull(level.getBlockEntity(pos))).setMobType(tag);
        }
    }
}
