package com.crescentine.trajanscore.recipe;

import com.crescentine.trajanscore.TrajansCoreMod;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class PlatingPressRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final ItemStack output;
    public final NonNullList<Ingredient> recipeItems;

    public PlatingPressRecipe(ResourceLocation id, ItemStack output,
                             NonNullList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public boolean matches(SimpleContainer inventory, Level world) {
        if (recipeItems.get(0).test(inventory.getItem(0))) {
            if (recipeItems.get(1).test(inventory.getItem(1))) {
                if (recipeItems.get(2).test(inventory.getItem(2))) {
                    if (recipeItems.get(3).test(inventory.getItem(3))) {
                        return (recipeItems.get(4).test(inventory.getItem(4)));
                                }}}}
        return false;
    }


    @Override
    public ItemStack assemble(SimpleContainer p_44001_) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.PLATING_PRESS_SERIALIZER.get();
    }
    @Override
    public RecipeType<?> getType() {
        return ModRecipes.PLATING_PRESS_RECIPE.get();
    }
    public static class Serializer implements RecipeSerializer<PlatingPressRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "plating_press";
        @Override
        public PlatingPressRecipe fromJson(ResourceLocation id, JsonObject json) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(5, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new PlatingPressRecipe(id, output, inputs);
        }

        @Override
        public PlatingPressRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack output = buf.readItem();
            return new PlatingPressRecipe(id, output,
                    inputs);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, PlatingPressRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(recipe.getResultItem(), false);
        }
    }
}
