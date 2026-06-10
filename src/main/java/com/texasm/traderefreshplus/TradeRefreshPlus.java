package com.texasm.traderefreshplus;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.texasm.traderefreshplus.client.ClientSetup;
import com.texasm.traderefreshplus.config.Config;

@Mod(TradeRefreshPlus.MOD_ID)
public class TradeRefreshPlus {
    public static final String MOD_ID = "trade_refresh_plus";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public TradeRefreshPlus(ModContainer modContainer, IEventBus modEventBus) {
        // 注册配置
        modContainer.registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_SPEC);
        
        // 只在客户端注册事件
        modEventBus.addListener(this::registerKeyBindings);
    }

    private void registerKeyBindings(RegisterKeyMappingsEvent event) {
        event.register(ClientSetup.TRADE_REFRESH_KEY);
    }
}
