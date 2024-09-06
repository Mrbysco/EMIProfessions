package com.mrbysco.emiprofessions.emi;

import com.mrbysco.emiprofessions.RenderHelper;
import com.mrbysco.emiprofessions.platform.Services;
import dev.emi.emi.api.widget.DrawableWidget;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public record ProfessionWrapper(ProfessionEntry entry) implements DrawableWidget.DrawableWidgetConsumer {

	/**
	 * Get the profession name for the recipe.
	 *
	 * @return
	 */
	public ResourceLocation getProfessionName() {
		return Services.PLATFORM.getProfessionKey(entry.profession());
	}

	/**
	 * Get the profession name for display.
	 *
	 * @return
	 */
	public Component getDisplayName() {
		ResourceLocation professionKey = getProfessionName();
		String languageKey = professionKey.toLanguageKey();
		if (languageKey.startsWith("minecraft.")) languageKey = languageKey.replace("minecraft.","");
		return Component.translatable("entity.minecraft.villager." + languageKey);
	}

	/**
	 * Get the ItemStacks that represent the blocks in the recipe.
	 *
	 * @return a list of ItemStacks for the blocks in the recipe.
	 */
	public List<ItemStack> getBlockStacks() {
		return this.entry.blockStacks();
	}

	@Override
	public void render(GuiGraphics draw, int mouseX, int mouseY, float delta) {
		Villager entityVillager = entry.getVillagerEntity();
		if (entityVillager != null) {
			RenderHelper.renderVillager(draw, 22, 62, 25.0F,
					38 - mouseX,
					80 - mouseY,
					entityVillager);
		}
	}
}