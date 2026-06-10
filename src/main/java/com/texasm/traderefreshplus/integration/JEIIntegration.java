package com.texasm.traderefreshplus.integration;

import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JEIIntegration {
    private static Boolean jeiAvailable;

    public static boolean isAvailable() {
        if (jeiAvailable == null) {
            try {
                Class.forName("mezz.jei.api.recipe.RecipeManager");
                jeiAvailable = true;
            } catch (ClassNotFoundException e) {
                jeiAvailable = false;
            }
        }
        return jeiAvailable;
    }

    public static boolean isFavorited(ItemStack stack) {
        if (!isAvailable() || stack.isEmpty()) {
            return false;
        }

        try {
            // 通过 JEI 的 Bookmark List API 检查是否在收藏夹中
            // JEI 的公开 API 中没有直接检查收藏夹的方法，
            // 但可以通过检查 BookmarkList 来实现
            Class<?> bookmarkListClass = Class.forName("mezz.jei.gui.overlay.bookmarks.BookmarkList");
            // 这需要更多的反射和内部 API 访问
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
