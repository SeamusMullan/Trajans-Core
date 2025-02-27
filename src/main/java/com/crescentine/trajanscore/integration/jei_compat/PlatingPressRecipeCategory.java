package com.crescentine.trajanscore.integration.jei_compat;

import com.crescentine.trajanscore.TrajansCoreMod;
import com.crescentine.trajanscore.item.TrajansCoreItems;
import com.crescentine.trajanscore.recipe.PlatingPressRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class PlatingPressRecipeCategory implements IRecipeCategory<PlatingPressRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(TrajansCoreMod.MOD_ID, "plating_press");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/gui/jei_plating_press.png");
    public static final RecipeType<PlatingPressRecipe> RECIPE_TYPE = RecipeType.create(TrajansCoreMod.MOD_ID, "plating_press", PlatingPressRecipe.class);
    private final IDrawable background;
    private final IDrawable icon;

    public PlatingPressRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 108, 58);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(TrajansCoreItems.PLATE_PRESS_BLOCK.get()));
    }
    @Override
    public RecipeType<PlatingPressRecipe> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Plating Press");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull PlatingPressRecipe recipe, @Nonnull IFocusGroup focusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 1).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 19, 1).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 19).addIngredients(recipe.getIngredients().get(2));
        builder.addSlot(RecipeIngredientRole.INPUT, 19, 19).addIngredients(recipe.getIngredients().get(3));
        builder.addSlot(RecipeIngredientRole.INPUT, 19, 42).addIngredients(recipe.getIngredients().get(4));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 91, 35).addItemStack(recipe.getResultItem());
    }
}