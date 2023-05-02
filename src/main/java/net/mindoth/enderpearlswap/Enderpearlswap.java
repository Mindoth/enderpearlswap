package net.mindoth.enderpearlswap;

import net.mindoth.enderpearlswap.config.EnderpearlswapConfig;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Enderpearlswap.MOD_ID)
public class Enderpearlswap {
    public static final String MOD_ID = "enderpearlswap";

    public Enderpearlswap() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, EnderpearlswapConfig.SPEC, "enderpearlswap-common.toml");
    }
}
