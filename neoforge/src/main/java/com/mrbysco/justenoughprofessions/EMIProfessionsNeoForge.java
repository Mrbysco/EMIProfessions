package com.mrbysco.justenoughprofessions;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@Mod(Constants.MOD_ID)
public class EMIProfessionsNeoForge {

	public EMIProfessionsNeoForge(Dist dist) {
		if (dist.isClient()) {
//			NeoForge.EVENT_BUS.addListener(this::handleTooltips);
		}
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