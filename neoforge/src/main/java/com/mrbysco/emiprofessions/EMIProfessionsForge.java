package com.mrbysco.emiprofessions;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.IExtensionPoint.DisplayTest;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class EMIProfessionsForge {

	public EMIProfessionsForge() {
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			MinecraftForge.EVENT_BUS.addListener(this::onLevelUnload);
//			MinecraftForge.EVENT_BUS.addListener(this::handleTooltips);
		});

		//Make sure the mod being absent on the other network side does not cause the client to display the server as incompatible
		ModLoadingContext.get().registerExtensionPoint(DisplayTest.class, () ->
				new IExtensionPoint.DisplayTest(() -> "Trans Rights Are Human Rights",
						(remoteVersionString, networkBool) -> networkBool));

	}

	private void onLevelUnload(LevelEvent.Unload event) {
		VillagerCache.clearCache();
	}

	public void handleTooltips(ItemTooltipEvent event) {
//		if (Minecraft.getInstance().screen instanceof ??????) {
//			ItemStack stack = event.getItemStack();
//			if (stack.hasTag() && stack.getTag().getBoolean("JEP_outfitter")) { TODO: Re-implement in the future
//				event.getToolTip().add(Component.literal("Needs to have a shader applied").withStyle(ChatFormatting.GOLD));
//			}
//		}
	}
}