package net.mindoth.enderpearlswap.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class EnderpearlswapConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Double> LANDING_AREA_DISTANCE;

    static {
        BUILDER.push("Configs for Skillcloaks");

        LANDING_AREA_DISTANCE = BUILDER.comment("How far in blocks do entities need to be from the pearl to be teleported (Default = 1.1)")
                .define("Distance from pearl", 1.1);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
