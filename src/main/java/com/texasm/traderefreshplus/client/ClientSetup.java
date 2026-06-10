package com.texasm.traderefreshplus.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import com.texasm.traderefreshplus.TradeRefreshPlus;
import com.texasm.traderefreshplus.client.handler.TradeRefreshHandler;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = TradeRefreshPlus.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientSetup {
    public static final KeyMapping TRADE_REFRESH_KEY = new KeyMapping(
            "key.trade_refresh_plus.refresh",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_C,
            "key.categories.trade_refresh_plus"
    );

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (TRADE_REFRESH_KEY.isDown()) {
            TradeRefreshHandler.handleKeyInput();
        }
    }
}
