package com.crescentine.trajanscore.integration.jei_compat;


import com.crescentine.trajanscore.TrajansCoreMod;
import com.crescentine.trajanscore.item.TrajansCoreItems;
import com.crescentine.trajanscore.recipe.EngineFabricatorRecipe;
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

public class EngineFabricatorRecipeCategory implements IRecipeCategory<EngineFabricatorRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(TrajansCoreMod.MOD_ID, "engine_fabricator");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/gui/jei_engine_fabricator.png");
    public static final RecipeType<EngineFabricatorRecipe> RECIPE_TYPE = RecipeType.create(TrajansCoreMod.MOD_ID, "engine_fabricator", EngineFabricatorRecipe.class);
    private final IDrawable background;
    private final IDrawable icon;

    public EngineFabricatorRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 134, 53);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(TrajansCoreItems.ENGINE_FABRICATOR.get()));
    }
    @Override
    public RecipeType<EngineFabricatorRecipe> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Engine Fabricator");
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
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull EngineFabricatorRecipe recipe, @Nonnull IFocusGroup focusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 1).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 19, 1).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 37, 1).addIngredients(recipe.getIngredients().get(2));
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 19).addIngredients(recipe.getIngredients().get(3));
        builder.addSlot(RecipeIngredientRole.INPUT, 19, 19).addIngredients(recipe.getIngredients().get(4));
        builder.addSlot(RecipeIngredientRole.INPUT, 37, 19).addIngredients(recipe.getIngredients().get(5));
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 37).addIngredients(recipe.getIngredients().get(6));
        builder.addSlot(RecipeIngredientRole.INPUT, 19, 37).addIngredients(recipe.getIngredients().get(7));
        builder.addSlot(RecipeIngredientRole.INPUT, 37, 37).addIngredients(recipe.getIngredients().get(8));


        builder.addSlot(RecipeIngredientRole.OUTPUT, 109, 19).addItemStack(recipe.getResultItem());
    }
}