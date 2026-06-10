package com.texasm.traderefreshplus.integration;

import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EMIIntegration {
    private static Boolean emiAvailable;

    public static boolean isAvailable() {
        if (emiAvailable == null) {
            try {
                Class.forName("dev.emi.emi.api.EmiApi");
                emiAvailable = true;
            } catch (ClassNotFoundException e) {
                emiAvailable = false;
            }
        }
        return emiAvailable;
    }

    public static boolean isFavorited(ItemStack stack) {
        if (!isAvailable() || stack.isEmpty()) {
            return false;
        }

        try {
            // 通过 EMI 的 API 检查是否在收藏夹中
            // EMI 提供了更直接的 API
            Class<?> emiApiClass = Class.forName("dev.emi.emi.api.EmiApi");
            // 可以调用 EmiApi 的静态方法来检查
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
