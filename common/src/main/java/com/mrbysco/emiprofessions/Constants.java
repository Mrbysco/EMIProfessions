package com.mrbysco.emiprofessions;

import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Constants {

	public static final String MOD_ID = "emiprofessions";
	public static final String MOD_NAME = "EMI Professions";
	public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

	public static ResourceLocation modLoc(String path) {
		return new ResourceLocation(MOD_ID, path);
	}
}