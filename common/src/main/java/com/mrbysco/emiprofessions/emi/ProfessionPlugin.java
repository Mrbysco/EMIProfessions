package com.mrbysco.emiprofessions.emi;

import com.mrbysco.emiprofessions.Constants;
import com.mrbysco.emiprofessions.compat.CompatibilityHelper;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@EmiEntrypoint
public class ProfessionPlugin implements EmiPlugin {
	private static final ResourceLocation UID = Constants.modLoc("jei_plugin");

	private static final EmiTexture PROFESSION_ICON = new EmiTexture(Constants.modLoc("textures/gui/profession_icon.png"), 0, 0, 16, 16);
	public static final EmiRecipeCategory PROFESSION = new EmiRecipeCategory(Constants.modLoc("professions"), PROFESSION_ICON);

	@Override
	public void register(EmiRegistry registry) {
		registry.addCategory(PROFESSION);
		registry.addWorkstation(PROFESSION, EmiStack.of(Items.EMERALD));
		registry.addWorkstation(PROFESSION, EmiStack.of(Items.VILLAGER_SPAWN_EGG));

		registerRecipes(registry);
	}

	public void registerRecipes(EmiRegistry registration) {
		List<ProfessionWrapper> entries = new LinkedList<>();
		List<VillagerProfession> professions = BuiltInRegistries.VILLAGER_PROFESSION.stream().toList();
		for (VillagerProfession profession : professions) {
			if (profession == VillagerProfession.NONE) {
				continue;
			}
			List<ItemStack> stacks = new LinkedList<>();
			List<ResourceLocation> knownItems = new LinkedList<>();
			List<PoiType> types = BuiltInRegistries.POINT_OF_INTEREST_TYPE.stream().toList();
			for (PoiType poiType : types) {
				Optional<ResourceKey<PoiType>> poiKey = BuiltInRegistries.POINT_OF_INTEREST_TYPE.getResourceKey(poiType);
				if (poiKey.isPresent() && profession.acquirableJobSite().test(BuiltInRegistries.POINT_OF_INTEREST_TYPE.getHolder(poiKey.get()).orElse(null))) {
					for (BlockState state : poiType.matchingStates()) {
						Block block = BuiltInRegistries.BLOCK.get(BuiltInRegistries.BLOCK.getKey(state.getBlock()));
						if (block != null) {
							ItemStack stack = CompatibilityHelper.compatibilityCheck(new ItemStack(block), BuiltInRegistries.VILLAGER_PROFESSION.getKey(profession));
							ResourceLocation location = BuiltInRegistries.ITEM.getKey(stack.getItem());
							if (!stack.isEmpty() && !knownItems.contains(location)) {
								stacks.add(stack);
								knownItems.add(location);
							}
						}
					}
				}
			}
			if (!stacks.isEmpty()) {
				entries.add(new ProfessionWrapper(new ProfessionEntry(profession, stacks)));
			}
		}
		entries.forEach(entry -> registration.addRecipe(new ProfessionRecipe<>(entry)));
	}
}