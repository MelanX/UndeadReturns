package de.melanx.undeadreturns.registration;

import de.melanx.undeadreturns.UndeadReturns;
import io.github.noeppi_noeppi.libx.annotation.registration.RegisterClass;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

@RegisterClass
public class ModItems {

    public static final Item skeletonBossSpawnEgg = new SpawnEggItem(ModEntityTypes.skeletonBoss, 0x6a8183, 0xc5d6d5, new Item.Properties().tab(UndeadReturns.getTab()));
}
