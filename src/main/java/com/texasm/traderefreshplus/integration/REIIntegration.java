package com.texasm.traderefreshplus.integration;

import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class REIIntegration {
    private static Boolean reiAvailable;

    public static boolean isAvailable() {
        if (reiAvailable == null) {
            try {
                Class.forName("me.shedaniel.rei.api.ClientHelper");
                reiAvailable = true;
            } catch (ClassNotFoundException e) {
                reiAvailable = false;
            }
        }
        return reiAvailable;
    }

    public static boolean isFavorited(ItemStack stack) {
        if (!isAvailable() || stack.isEmpty()) {
            return false;
        }

        try {
            // 通过 REI 的 API 检查是否在收藏夹中
            Class<?> clientHelperClass = Class.forName("me.shedaniel.rei.api.ClientHelper");
            // 可以调用相关的 API 方法
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
