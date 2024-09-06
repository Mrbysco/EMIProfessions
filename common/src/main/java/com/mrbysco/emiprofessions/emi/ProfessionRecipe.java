package com.mrbysco.emiprofessions.emi;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.DrawableWidget;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ProfessionRecipe<T extends ProfessionWrapper> implements EmiRecipe {
	private final ProfessionWrapper wrapper;

	public ProfessionRecipe(ProfessionWrapper wrapper) {
		this.wrapper = wrapper;
	}

	@Override
	public EmiRecipeCategory getCategory() {
		return ProfessionPlugin.PROFESSION;
	}

	@Override
	@Nullable
	public ResourceLocation getId() {
		return wrapper.getProfessionName();
	}

	@Override
	public List<EmiIngredient> getInputs() {
		return List.of();
	}

	@Override
	public List<EmiStack> getOutputs() {
		return List.of();
	}

	@Override
	public int getDisplayWidth() {
		return 124;
	}

	@Override
	public int getDisplayHeight() {
		return 62;
	}

	@Override
	public void addWidgets(WidgetHolder widgets) {
		widgets.addSlot(EmiIngredient.of(Ingredient.of(wrapper.getBlockStacks().toArray(new ItemStack[0]))), 92, 22);

		widgets.addDrawable(22, 0, 72, 62, wrapper);
		widgets.addDrawable(0, 0, 124, 62, new DrawableProfessionName(wrapper));
	}

	private record DrawableProfessionName(ProfessionWrapper wrapper) implements DrawableWidget.DrawableWidgetConsumer {

		@Override
		public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
			String text = Screen.hasShiftDown() ? wrapper.getProfessionName().toString() : wrapper.getDisplayName().getString();
			// Draw entity name
			PoseStack poseStack = guiGraphics.pose();
			poseStack.pushPose();
			poseStack.translate(1, 0, 0);
			Font font = Minecraft.getInstance().font;
			if (font.width(text) > 122) {
				poseStack.scale(0.75F, 0.75F, 0.75F);
			}
			guiGraphics.drawString(font, text, 0, 0, 8, false);
			poseStack.popPose();
		}
	}
}
