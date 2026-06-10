package com.texasm.traderefreshplus.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec CLIENT_SPEC;

    public static final ModConfigSpec.IntValue REFRESH_DELAY;
    public static final ModConfigSpec.BooleanValue SHOW_NOTIFICATION;
    public static final ModConfigSpec.BooleanValue ENABLE_JEI_INTEGRATION;
    public static final ModConfigSpec.BooleanValue ENABLE_EMI_INTEGRATION;
    public static final ModConfigSpec.BooleanValue ENABLE_REI_INTEGRATION;
    public static final ModConfigSpec.IntValue HOLD_DURATION_TICKS;

    static {
        BUILDER.push("general");
        
        REFRESH_DELAY = BUILDER
                .comment("刷新之间的延迟（单位：刻），防止频繁刷新")
                .defineInRange("refreshDelay", 2, 0, 20);
        
        SHOW_NOTIFICATION = BUILDER
                .comment("是否显示刷新提示信息")
                .define("showNotification", true);
        
        HOLD_DURATION_TICKS = BUILDER
                .comment("触发快速刷新所需的按键按住时间（单位：刻），50 刻 = 1 秒")
                .defineInRange("holdDurationTicks", 10, 1, 100);
        
        BUILDER.push("integration");
        
        ENABLE_JEI_INTEGRATION = BUILDER
                .comment("是否启用 JEI 集成（需要安装 JEI）")
                .define("enableJeiIntegration", true);
        
        ENABLE_EMI_INTEGRATION = BUILDER
                .comment("是否启用 EMI 集成（需要安装 EMI）")
                .define("enableEmiIntegration", true);
        
        ENABLE_REI_INTEGRATION = BUILDER
                .comment("是否启用 REI 集成（需要安装 REI）")
                .define("enableReiIntegration", true);
        
        BUILDER.pop();
        BUILDER.pop();
        
        CLIENT_SPEC = BUILDER.build();
    }
}
