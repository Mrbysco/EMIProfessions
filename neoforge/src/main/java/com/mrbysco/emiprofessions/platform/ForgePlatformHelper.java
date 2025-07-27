package com.mrbysco.emiprofessions.platform;

import com.mrbysco.emiprofessions.platform.services.IPlatformHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.registries.ForgeRegistries;

public class ForgePlatformHelper implements IPlatformHelper {

	@Override
	public ResourceLocation getEntityKey(EntityType<?> entityType) {
		return ForgeRegistries.ENTITY_TYPES.getKey(entityType);
	}

	@Override
	public ResourceLocation getProfessionKey(VillagerProfession villagerProfession) {
		return ForgeRegistries.VILLAGER_PROFESSIONS.getKey(villagerProfession);
	}
}
