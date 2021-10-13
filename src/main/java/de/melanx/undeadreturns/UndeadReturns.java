package de.melanx.undeadreturns;

import de.melanx.undeadreturns.entity.skeleton.SkeletonBossRenderer;
import de.melanx.undeadreturns.registration.ModEntityTypes;
import io.github.noeppi_noeppi.libx.mod.registration.ModXRegistration;
import io.github.noeppi_noeppi.libx.mod.registration.RegistrationBuilder;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import javax.annotation.Nonnull;

@Mod("undeadreturns")
public final class UndeadReturns extends ModXRegistration {

    private static UndeadReturns instance;

    public UndeadReturns() {
        super("undeadreturns", new CreativeModeTab("undeadreturns") {

            @Nonnull
            @Override
            public ItemStack makeIcon() {
                return ItemStack.EMPTY;
            }
        });
        instance = this;

        MinecraftForge.EVENT_BUS.register(new EventListener());
    }

    @Override
    protected void initRegistration(RegistrationBuilder builder) {
        builder.setVersion(1);
    }

    @Override
    protected void setup(FMLCommonSetupEvent event) {

    }

    @Override
    protected void clientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntityTypes.skeletonBoss, SkeletonBossRenderer::new);
    }

    @Nonnull
    public static UndeadReturns getInstance() {
        return instance;
    }

    @Nonnull
    public static CreativeModeTab getTab() {
        //noinspection ConstantConditions
        return instance.tab;
    }
}
