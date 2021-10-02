package de.melanx.undeadreturns.registration;

import de.melanx.undeadreturns.UndeadReturns;
import de.melanx.undeadreturns.block.BlockLootPile;
import io.github.noeppi_noeppi.libx.annotation.registration.RegisterClass;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

@RegisterClass(priority = 1)
public class ModBlocks {

    public static final Block lootPile = new BlockLootPile(UndeadReturns.getInstance(), BlockBehaviour.Properties.of(Material.MOSS));
}
