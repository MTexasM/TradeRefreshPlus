package com.texasm.traderefreshplus.client.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MerchantScreen;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.api.distmarker.OnlyIn;
import com.texasm.traderefreshplus.config.Config;
import com.texasm.traderefreshplus.integration.JEIIntegration;
import com.texasm.traderefreshplus.integration.EMIIntegration;
import com.texasm.traderefreshplus.integration.REIIntegration;
import net.minecraft.network.chat.Component;

@OnlyIn(Dist.CLIENT)
public class TradeRefreshHandler {
    private static long lastRefreshTime = 0;
    private static int keyPressedTicks = 0;
    private static boolean isHolding = false;

    public static void handleKeyInput() {
        Minecraft minecraft = Minecraft.getInstance();
        
        if (!(minecraft.screen instanceof MerchantScreen merchantScreen)) {
            return;
        }

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastRefreshTime < Config.REFRESH_DELAY.get() * 50) {
            return; // 防止频繁刷新
        }

        Villager villager = getVillagerFromScreen(merchantScreen);
        if (villager == null) {
            return;
        }

        // 检查是否已交易过
        if (hasVillagerBeenTraded(villager)) {
            showNotification(Component.literal("§c无法刷新 - 村民已被交易过"));
            return;
        }

        keyPressedTicks++;

        if (keyPressedTicks >= Config.HOLD_DURATION_TICKS.get()) {
            // 触发快速刷新
            if (!isHolding) {
                isHolding = true;
            }
            refreshVillagerTrades(villager, merchantScreen);
        } else if (keyPressedTicks == 1) {
            // 单次按键刷新
            refreshVillagerTrades(villager, merchantScreen);
        }

        lastRefreshTime = currentTime;
    }

    public static void handleKeyRelease() {
        isHolding = false;
        keyPressedTicks = 0;
    }

    private static void refreshVillagerTrades(Villager villager, MerchantScreen screen) {
        MerchantOffers offers = villager.getOffers();
        
        if (offers == null || offers.isEmpty()) {
            return;
        }

        // 检查收藏夹物品
        ItemStack favoriteItem = checkFavoriteItems(offers);
        if (favoriteItem != null) {
            showNotification(Component.literal("§c无法刷新收藏夹的物品"));
            isHolding = false;
            keyPressedTicks = 0;
            return;
        }

        // 重新生成交易
        villager.rerollUsingEffects();
        
        if (Config.SHOW_NOTIFICATION.get()) {
            showNotification(Component.literal("§a村民交易已刷新"));
        }
    }

    private static ItemStack checkFavoriteItems(MerchantOffers offers) {
        if (offers == null || offers.isEmpty()) {
            return null;
        }

        for (int i = 0; i < offers.size(); i++) {
            ItemStack result = offers.get(i).getResult();
            
            if (result.isEmpty()) {
                continue;
            }

            // 检查 JEI 收藏夹
            if (Config.ENABLE_JEI_INTEGRATION.get() && JEIIntegration.isAvailable() && JEIIntegration.isFavorited(result)) {
                return result;
            }

            // 检查 EMI 收藏夹
            if (Config.ENABLE_EMI_INTEGRATION.get() && EMIIntegration.isAvailable() && EMIIntegration.isFavorited(result)) {
                return result;
            }

            // 检查 REI 收藏夹
            if (Config.ENABLE_REI_INTEGRATION.get() && REIIntegration.isAvailable() && REIIntegration.isFavorited(result)) {
                return result;
            }
        }

        return null;
    }

    private static Villager getVillagerFromScreen(MerchantScreen screen) {
        try {
            // 通过反射获取 MerchantScreen 中的村民
            Object merchant = screen.getMerchant();
            if (merchant instanceof Villager villager) {
                return villager;
            }
        } catch (Exception e) {
            // 日志记录异常
        }
        return null;
    }

    private static boolean hasVillagerBeenTraded(Villager villager) {
        // 检查村民是否有交易经验
        return villager.getVillagerXp() > 0;
    }

    private static void showNotification(Component message) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player != null) {
            minecraft.player.displayClientMessage(message, true);
        }
    }
}
