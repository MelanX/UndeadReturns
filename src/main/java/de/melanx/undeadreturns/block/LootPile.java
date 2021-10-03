package de.melanx.undeadreturns.block;

import de.melanx.undeadreturns.UndeadReturns;
import de.melanx.undeadreturns.registration.tags.ModEntityTags;
import io.github.noeppi_noeppi.libx.base.tile.TickableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class LootPile extends BlockEntity implements TickableBlock {

    private static final UUID MAX_HEALTH_ID = UUID.fromString("e5978966-23c7-11ec-9621-0242ac130002");

    private int existingTicks;
    private ResourceLocation type;
    private final Set<ItemStack> inventory = new HashSet<>();

    public LootPile(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void tick() {
        if (this.level != null && !this.level.isClientSide && this.type != null) {
            this.existingTicks++;

            if (this.existingTicks >= 1000) {
                BlockPos blockPos = this.getBlockPos();
                double x = blockPos.getX() + 0.5;
                double y = blockPos.getY();
                double z = blockPos.getZ() + 0.5;
                this.setRemoved();
                this.level.removeBlock(blockPos, false);

                EntityType<?> bossMob = null;
                if (ModEntityTags.SKELETON.getName().equals(this.type)) {
                    bossMob = EntityType.SKELETON;
                } else if (ModEntityTags.ZOMBIE.getName().equals(this.type)) {
                    bossMob = EntityType.ZOMBIE;
                }

                if (bossMob != null) {
                    LivingEntity entity = (LivingEntity) bossMob.create(this.level);
                    //noinspection ConstantConditions
                    AttributeInstance attribute = entity.getAttribute(Attributes.MAX_HEALTH);
                    //noinspection ConstantConditions
                    if (attribute.getModifier(MAX_HEALTH_ID) == null) {
                        attribute.addPermanentModifier(new AttributeModifier(MAX_HEALTH_ID, "Boss Modifier", this.getTotalItemCount(), AttributeModifier.Operation.ADDITION));
                    }
                    entity.setHealth(entity.getMaxHealth());
                    entity.setPos(x, y, z);
                    entity.getPersistentData().putBoolean("Boss", true); // todo remove when having own mob
                    this.level.addFreshEntity(entity);
                }
            }
        }
    }

    public int getTotalItemCount() {
        int count = 0;
        for (ItemStack stack : this.inventory) {
            count += stack.getCount();
        }

        return count;
    }

    public void setMobType(net.minecraft.tags.Tag.Named<EntityType<?>> tag) {
        if (this.type != null) {
            UndeadReturns.getInstance().logger.info("Overwrite pile with existing mob type.");
        }

        this.type = tag.getName();
    }

    public void addLoot(Set<ItemStack> loot) {
        for (ItemStack stack : loot) {
            boolean added = false;
            for (ItemStack existing : this.inventory) {
                if (ItemStack.isSameItemSameTags(existing, stack)) {
                    existing.grow(stack.getCount());
                    added = true;
                    break;
                }
            }

            if (!added) {
                this.inventory.add(stack);
            }
        }
        this.setChanged();
    }

    public boolean matchesMob(EntityType<?> entityType) {
        return entityType.getTags().contains(this.type);
    }

    @Override
    public void load(@Nonnull CompoundTag tag) {
        this.existingTicks = tag.getInt("ExistingTicks");
        this.type = ResourceLocation.tryParse(tag.getString("MobType"));

        this.inventory.clear();
        for (Tag nbt : tag.getList("Loot", Constants.NBT.TAG_COMPOUND)) {
            this.inventory.add(ItemStack.of((CompoundTag) nbt));
        }
    }

    @Nonnull
    @Override
    public CompoundTag save(@Nonnull CompoundTag tag) {
        tag.putInt("ExistingTicks", this.existingTicks);

        if (this.type != null) {
            tag.putString("MobType", this.type.toString());
        }

        ListTag tags = new ListTag();
        this.inventory.forEach(stack -> {
            tags.add(stack.serializeNBT());
        });
        tag.put("Loot", tags);

        return super.save(tag);
    }

    @Nonnull
    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        if (this.level != null && !this.level.isClientSide) {
            tag.putInt("ExistingTicks", this.existingTicks);

            if (this.type != null) {
                tag.putString("MobType", this.type.toString());
            }

            ListTag tags = new ListTag();
            this.inventory.forEach(stack -> {
                tags.add(stack.serializeNBT());
            });
            tag.put("Loot", tags);
        }
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        if (this.level != null && this.level.isClientSide) {
            super.handleUpdateTag(tag);
            this.existingTicks = tag.getInt("ExistingTicks");
            this.type = ResourceLocation.tryParse(tag.getString("MobType"));

            this.inventory.clear();
            for (Tag nbt : tag.getList("Loot", Constants.NBT.TAG_COMPOUND)) {
                this.inventory.add(ItemStack.of((CompoundTag) nbt));
            }
        }
    }
}
